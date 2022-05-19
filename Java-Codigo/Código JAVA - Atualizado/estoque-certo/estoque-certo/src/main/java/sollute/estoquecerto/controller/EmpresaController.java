package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.response.EmpresaResponse;
import sollute.estoquecerto.response.EnderecoEmpresaResponse;
import sollute.estoquecerto.response.ProdutoLoginResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repositoryEmpresa;
    EmpresaInsertRepository empresaInsertRepository;

    @Autowired
    private ProdutoRepository repositoryProduto;

    @Autowired
    private CaixaRepository repositoryCaixa;

    @Autowired
    private ClienteRepository repositoCliente;

    @Autowired
    private EnderecoRepository repositoryEndereco;
    EnderecoInsertRepository enderecoInsertRepository;

    @Autowired
    private FornecedorRepository repositoryFornecedor;

    @Autowired
    private FuncionarioRepository repositoryFuncionario;

    @PostMapping
    public ResponseEntity criaEmpresa(@RequestBody @Valid EnderecoEmpresaResponse enderecoEmpresaResponse,
                                      @PathVariable Long fkEmpresa) {

        String email = enderecoEmpresaResponse.getEmail();
        String senha = enderecoEmpresaResponse.getSenha();
        String nomeFantasia = enderecoEmpresaResponse.getNomeFantasia();
        String razaoSocial = enderecoEmpresaResponse.getRazaoSocial();
        String cnpj = enderecoEmpresaResponse.getCnpj();
        Integer qtdProdutosVendidos = enderecoEmpresaResponse.getQtdProdutosVendidos();
        Double totalProdutosVendidos = enderecoEmpresaResponse.getTotalProdutosVendidos();
        Boolean autenticado = enderecoEmpresaResponse.getAutenticado();
        String logradouro = enderecoEmpresaResponse.getLogradouro();
        String cep = enderecoEmpresaResponse.getCep();
        String uf = enderecoEmpresaResponse.getUf();
        String cidade = enderecoEmpresaResponse.getCidade();
        String pontoReferencia = enderecoEmpresaResponse.getPontoReferencia();

        try {

            empresaInsertRepository.insertEmpresa(email, senha, nomeFantasia, razaoSocial, cnpj,
                    qtdProdutosVendidos, totalProdutosVendidos, autenticado);
            enderecoInsertRepository.insertEndereco(fkEmpresa, logradouro, cep, uf,
                    cidade, pontoReferencia);

            return status(201).build();

        } catch (Exception e) {
            return status(400).body(e);
        }

    }

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(@RequestBody @Valid EmpresaResponse requisicao) {

        List<Empresa> empresa = repositoryEmpresa.findAll();

        for (Empresa e : empresa) {
            if (e.getEmail().equals(requisicao.getLogin()) && e.getSenha().equals(requisicao.getSenha())) {
                repositoryEmpresa.atualizarAutenticado(requisicao.getLogin(), true);
                return status(200).body(e);
            }
        }

        return status(401).build();
    }

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                          @PathVariable Integer idEmpresa) {

        if (repositoryEmpresa.existsById(idEmpresa)) { // Verificando se a empresa existe
            repositoryProduto.save(novoProduto);       // Adicionado no Banco de Dados
            return status(201).build();
        }

        return status(404).build();
    }

    @PostMapping("/adicionar-cliente/{idEmpresa}")
    public ResponseEntity adicionaCliente(@RequestBody @Valid Cliente novoCliente,
                                          @PathVariable Integer idEmpresa) {

        if (repositoryEmpresa.existsById(idEmpresa)) { // Verificando se a empresa existe
            repositoCliente.save(novoCliente);       // Adicionado no Banco de Dados
            return status(201).build();
        }

        return status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {

        List<Empresa> listaEmpresas = repositoryEmpresa.findAll();

        if (listaEmpresas.isEmpty()) {
            return status(204).build();
        }

        return status(200).body(listaEmpresas);

    }

    @PostMapping("/vender-produtos")
    public ResponseEntity venderProdutos(@RequestBody @Valid ProdutoLoginResponse produtoLoginResponse) {

        if (repositoryEmpresa.existsById(produtoLoginResponse.getIdEmpresa().intValue())) {
            if (repositoryProduto.existsByCodigo(produtoLoginResponse.getCodigo())) {
                //if (repositoryProduto.findByEstoqueInicialLessThanEqual(produtoLoginResponse.getEstoqueInicial())) {
                //  repositoryProduto.atualizarAlerta(
                //        produtoLoginResponse.getCodigo(),
                //      true);
                repositoryProduto.atualizarQtdVendida(
                        produtoLoginResponse.getCodigo(),
                        produtoLoginResponse.getQtdVendida());
                return status(200).build();
            } else {
                return status(400).build(); // Não há estoque suficiente.
            }
        } else {
            return status(404).build(); // Não existe produto com o codigo informado
        }
    }


    @GetMapping("/listar-produtos/{fkEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Integer fkEmpresa) {

        //if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {

        List<Produto> lista = repositoryProduto.findByEmpresaId(fkEmpresa);

            return status(200).body(lista);
        //}
        // return status(404).build();
    }

    @GetMapping("/calcular-produtos-vendidos/{fkEmpresa}")
    public ResponseEntity calcularProdutosVendidos(@PathVariable Integer fkEmpresa) {
        int aux = 0;
        if (repositoryEmpresa.existsById(fkEmpresa)) {
            for (Produto prod : repositoryProduto.findAll()) {
                aux += prod.getQtdVendidos();
            }
            return status(200).body(aux);
        }
        return status(404).build();
    }

    @GetMapping("/calcular-valor-vendidos/{fkEmpresa}")
    public ResponseEntity calcularValorVendidos(@PathVariable Integer fkEmpresa) {
        int aux = 0;
        if (repositoryEmpresa.existsById(fkEmpresa)) {
            for (Produto prod : repositoryProduto.findAll()) {
                aux += prod.getValorVendidos();
            }
            return status(200).body(aux);
        }
        return status(404).build();
    }


    @DeleteMapping("/deletar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity deletarProduto(@PathVariable String codigo, @PathVariable Integer fkEmpresa) {
        if (repositoryProduto.existsByCodigo(codigo)){
            repositoryProduto.deleteProdutoByIdProduto(repositoryProduto.findByCodigoAndEmpresaId(codigo, fkEmpresa).getIdProduto());
            return status(200).build();
        }
        return status(404).build();
    }


    @GetMapping("/relatorio/{fkEmpresa}")
    public ResponseEntity relatorio(@PathVariable Integer fkEmpresa) {

        List<Produto> lista = repositoryProduto.findByEmpresaId(fkEmpresa);
        String relatorio = "" +
                "CODIGO;NOME;MARCA;CATEGORIA;TAMANHO;PESO;PRECO COMPRA;PRECO VENDA;" +
                "ESTOQUE INICIAL;ESTOQUE MINIMO;ESTOQUE MAXIMO;QTD VENDIDOS;\r\n";
        for (Produto prod : lista) {
            relatorio += "" +
                    "" + prod.getCodigo() +
                    ";" + prod.getNome() +
                    ";" + prod.getMarca() +
                    ";" + prod.getCategoria() +
                    ";" + prod.getTamanho() +
                    ";" + prod.getPeso() +
                    ";" + prod.getPrecoCompra() +
                    ";" + prod.getPrecoVenda() +
                    ";" + prod.getEstoque() +
                    ";" + prod.getEstoqueMin() +
                    ";" + prod.getEstoqueMax() +
                    ";" + (prod.getQtdVendidos() == null ? 0 : prod.getQtdVendidos()) + "\r\n";
        }

        return status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-de-produtos.csv\"")
                .body(relatorio);
    }
}
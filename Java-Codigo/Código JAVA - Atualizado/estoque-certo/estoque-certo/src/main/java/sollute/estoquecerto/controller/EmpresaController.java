package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.response.EmpresaResponse;
import sollute.estoquecerto.response.ProdutoLoginResponse;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private String cnpj = null;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaEnderecoRepository empresaEnderecoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CaixaRepository repositoryCaixa;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteEnderecoRepository clienteEnderecoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FornecedorEnderecoRepository fornecedorEnderecoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioEnderecoRepository funcionarioEnderecoRepository;

    @PostMapping("/cria-empresa")
    public ResponseEntity criaEmpresa(@RequestBody @Valid Empresa createEmpresaResponse) {

        try {

            cnpj = createEmpresaResponse.getCnpj();
            empresaRepository.save(createEmpresaResponse);
            return status(201).build();

        } catch (Exception e) {
            return status(400).body(e);
        }
    }

    @PostMapping("/cria-endereco")
    public ResponseEntity criaEndereco(@RequestBody @Valid EmpresaEndereco empresaEndereco) {


        try {

            Empresa emp = empresaRepository.findByCnpj(cnpj);
            empresaEndereco.setFkEmpresa(emp);

            // Tente criar uma query manual setando o fkEmpresa com o idEmpresa do objeto 'emp'
            // Talvez isso funcione, visto que voce atualizará manualmente.

            empresaEnderecoRepository.save(empresaEndereco);
            return status(201).build();


        } catch (Exception e) {
            return status(400).body(e);
        }
    }

    @GetMapping("/get-id-empresa/{cnpj}")
    public ResponseEntity getIdEmpresa(@PathVariable String cnpj) {

        Integer idEmpresa = empresaRepository.findByCnpj(cnpj).getId();

        if (idEmpresa == null) {
            return status(400).build();
        }

        return status(200).body(idEmpresa);
    }

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(@RequestBody @Valid EmpresaResponse requisicao) {

        List<Empresa> empresa = empresaRepository.findAll();

        for (Empresa e : empresa) {
            if (e.getEmail().equals(requisicao.getLogin()) && e.getSenha().equals(requisicao.getSenha())) {
                empresaRepository.atualizarAutenticado(requisicao.getLogin(), true);
                return status(200).body(e);
            }
        }

        return status(401).build();
    }

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                          @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) { // Verificando se a empresa existe
            produtoRepository.save(novoProduto);       // Adicionado no Banco de Dados
            return status(201).build();
        }

        return status(404).build();
    }

    @PostMapping("/adicionar-cliente/{idEmpresa}")
    public ResponseEntity adicionaCliente(@RequestBody @Valid Cliente novoCliente,
                                          @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) { // Verificando se a empresa existe
            clienteRepository.save(novoCliente);       // Adicionado no Banco de Dados
            return status(201).build();
        }

        return status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {

        List<Empresa> listaEmpresas = empresaRepository.findAll();

        if (listaEmpresas.isEmpty()) {
            return status(204).build();
        }

        return status(200).body(listaEmpresas);

    }

    @PostMapping("/vender-produtos")
    public ResponseEntity venderProdutos(@RequestBody @Valid ProdutoLoginResponse produtoLoginResponse) {

        if (empresaRepository.existsById(produtoLoginResponse.getIdEmpresa().intValue())) {
            if (produtoRepository.existsByCodigo(produtoLoginResponse.getCodigo())) {
                //if (repositoryProduto.findByEstoqueInicialLessThanEqual(produtoLoginResponse.getEstoqueInicial())) {
                //  repositoryProduto.atualizarAlerta(
                //        produtoLoginResponse.getCodigo(),
                //      true);
                produtoRepository.atualizarQtdVendida(
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


    @GetMapping("/listar-produtos/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable String idEmpresa) {

        //if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {

        List<Produto> lista = produtoRepository.findByEmpresaIdEmpresa(Integer.valueOf(idEmpresa));

        return status(200).body(lista);
        //}
        // return status(404).build();
    }

    @GetMapping("/calcular-produtos-vendidos/{fkEmpresa}")
    public ResponseEntity calcularProdutosVendidos(@PathVariable Integer fkEmpresa) {
        int aux = 0;
        if (empresaRepository.existsById(fkEmpresa)) {
            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getQtdVendidos();
            }
            return status(200).body(aux);
        }
        return status(404).build();
    }

    @GetMapping("/calcular-valor-vendidos/{fkEmpresa}")
    public ResponseEntity calcularValorVendidos(@PathVariable Integer fkEmpresa) {
        int aux = 0;
        if (empresaRepository.existsById(fkEmpresa)) {
            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getValorVendidos();
            }
            return status(200).body(aux);
        }
        return status(404).build();
    }


    @DeleteMapping("/deletar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity deletarProduto(@PathVariable String codigo, @PathVariable Integer fkEmpresa) {
        if (produtoRepository.existsByCodigo(codigo)) {
            produtoRepository.deleteProdutoByIdProduto(produtoRepository.findByCodigoAndEmpresaIdEmpresa(codigo, fkEmpresa).getIdProduto());
            return status(200).build();
        }
        return status(404).build();
    }


    @GetMapping("/relatorio/{fkEmpresa}")
    public ResponseEntity relatorio(@PathVariable Integer fkEmpresa) {

        List<Produto> lista = produtoRepository.findByEmpresaIdEmpresa(fkEmpresa);
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

    @PostMapping("/criar-funcionario/{idEmpresa}")
    public ResponseEntity criarFuncionario(@RequestBody @Valid Funcionario novoFuncionario,
                                           @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) { // Verificando se a empresa existe
            funcionarioRepository.save(novoFuncionario);       // Adicionado no Banco de Dados
            return status(201).build();
        }

        return status(404).build();
    }

    @GetMapping("/listar-funcionarios/{idEmpresa}")
    public ResponseEntity<List<Funcionario>> listarFuncionario(@PathVariable String idEmpresa) {

        List<Funcionario> lista = funcionarioRepository.findByfkEmpresaIdEmpresa(Integer.valueOf(idEmpresa));

        return status(200).body(lista);

    }

    @GetMapping("/listar-clientes/{idEmpresa}")
    public ResponseEntity<List<Cliente>> listarCliente(@PathVariable String idEmpresa) {

        List<Cliente> lista = clienteRepository.findByfkEmpresaIdEmpresa(Integer.valueOf(idEmpresa));

        return status(200).body(lista);
    }

    @PostMapping("/criar-fornecedor/{idEmpresa}")
    public ResponseEntity criarFornecedor(@RequestBody @Valid Fornecedor novoFornecedor,
                                           @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) { // Verificando se a empresa existe
            fornecedorRepository.save(novoFornecedor);       // Adicionado no Banco de Dados
            return status(201).build();
        }

        return status(404).build();
    }

    @GetMapping("/listar-fornecedores/{idEmpresa}")
    public ResponseEntity<List<Fornecedor>> listarFornecedor(@PathVariable String idEmpresa) {

        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(Integer.valueOf(idEmpresa));

        return status(200).body(lista);
    }

}
package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CaixaRepository repositoryCaixa;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/cria-empresa")
    public ResponseEntity criaEmpresa(@RequestBody @Valid Empresa createEmpresaResponse) {

        String cnpj = createEmpresaResponse.getCnpj();

        if (!empresaRepository.existsByCnpj(cnpj)) {
            empresaRepository.save(createEmpresaResponse);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(@RequestBody @Valid EmpresaLoginRequest requisicao) {

        List<Empresa> empresa = empresaRepository.findAll();

        for (Empresa e : empresa) {
            if (e.getEmail().equals(requisicao.getLogin()) && e.getSenha().equals(requisicao.getSenha())) {

                empresaRepository.atualizarAutenticado(requisicao.getLogin(), true);
                return status(HttpStatus.OK).body(e);

            }
        }

        return status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {

        List<Empresa> listaEmpresas = empresaRepository.findAll();

        if (listaEmpresas.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(listaEmpresas);
    }

    @GetMapping("/get-id-empresa/{cnpj}")
    public ResponseEntity getIdEmpresa(@PathVariable String cnpj) {

        Empresa empresa = empresaRepository.findByCnpj(cnpj);

        if (empresa != null) {
            return status(HttpStatus.OK).body(empresa.getIdEmpresa());
        }

        return status(HttpStatus.NOT_FOUND).build();

    }

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                          @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {
            produtoRepository.save(novoProduto);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    // this snippet needs refactoring
    @PostMapping("/vender-produtos")
    public ResponseEntity venderProdutos(@RequestBody @Valid ProdutoVenderRequest produtoVenderRequest) {

        if (empresaRepository.existsById(produtoVenderRequest.getIdEmpresa().intValue())) {
            if (produtoRepository.existsByCodigo(produtoVenderRequest.getCodigo())) {
                //if (repositoryProduto.findByEstoqueInicialLessThanEqual(produtoLoginResponse.getEstoqueInicial())) {
                produtoRepository.atualizarQtdVendida(
                        produtoVenderRequest.getCodigo(),
                        produtoVenderRequest.getQtdVendida());
                return status(200).build();
            } else {
                return status(400).build();
            }
        } else {
            return status(404).build();
        }

    }

    @GetMapping("/listar-produtos/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Integer idEmpresa) {

        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(lista);
    }

    @DeleteMapping("/deletar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity deletarProduto(@PathVariable String codigo,
                                         @PathVariable Integer fkEmpresa) {

        if (empresaRepository.existsById(fkEmpresa)) {
            produtoRepository.deleteProdutoByCodigo(codigo);
            return status(HttpStatus.OK).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    // this snippet needs refactoring
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

    // this snippet needs refactoring
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

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/adicionar-cliente/{idEmpresa}")
    public ResponseEntity adicionaCliente(@RequestBody @Valid Cliente novoCliente,
                                          @PathVariable Integer idEmpresa) {

        Long idCliente = novoCliente.getIdCliente();

        if (empresaRepository.existsById(idEmpresa)) {

            if (!clienteRepository.existsById(idCliente)) {
                clienteRepository.save(novoCliente);
                return status(HttpStatus.CREATED).build();
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/editar-cliente/{idEmpresa}")
    public ResponseEntity editarCliente(@RequestBody @Valid NovoClienteRequest novoClienteRequest,
                                        @PathVariable Integer idEmpresa) {

        List<Cliente> lista = clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.BAD_REQUEST).build();

        for (Cliente cliente : lista) {
            if (clienteRepository.existsById(cliente.getIdCliente())) {

                Long idCliente = cliente.getIdCliente();
                String nome = novoClienteRequest.getNomeCliente();
                String tele = novoClienteRequest.getTelefoneCliente();

                if (clienteRepository.atualizarCliente(nome, tele, idEmpresa, idCliente)) {
                    return status(HttpStatus.OK).build();
                }

                return status(HttpStatus.BAD_REQUEST).build();

            }
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/listar-clientes/{idEmpresa}")
    public ResponseEntity<List<Cliente>> listarCliente(@PathVariable Integer idEmpresa) {

        List<Cliente> lista = clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            return status(204).build();
        }

        return status(200).body(lista);
    }

    @DeleteMapping("/deletar-cliente/{idCliente}/{idEmpresa}")
    public ResponseEntity deletaCliente(@PathVariable Integer idCliente,
                                        @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {

            if (clienteRepository.existsById(idCliente.longValue())) {
                clienteRepository.deleteById(idCliente.longValue());
                return status(HttpStatus.OK).build();
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/criar-funcionario/{idEmpresa}")
    public ResponseEntity criarFuncionario(@RequestBody @Valid Funcionario novoFuncionario,
                                           @PathVariable Integer idEmpresa) {

        Long idFuncionario = novoFuncionario.getIdFuncionario();

        if (empresaRepository.existsById(idEmpresa)) {

            if (!clienteRepository.existsById(idFuncionario)) {
                funcionarioRepository.save(novoFuncionario);
                return status(HttpStatus.CREATED).build();
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/editar-funcionario/{idEmpresa}")
    public ResponseEntity editarFuncionario(@RequestBody @Valid NovoFuncionarioRequest novoFuncionarioRequest,
                                            @PathVariable Integer idEmpresa) {

        List<Funcionario> lista = funcionarioRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.BAD_REQUEST).build();

        for (Funcionario funcionario : lista) {
            if (funcionarioRepository.existsById(funcionario.getIdFuncionario())) {

                Long idFunc = funcionario.getIdFuncionario();
                String nome = novoFuncionarioRequest.getNomeFuncionario();
                String tele = novoFuncionarioRequest.getTelefoneFuncionario();
                String cpf = novoFuncionarioRequest.getCpfFuncionario();
                Double salario = novoFuncionarioRequest.getSalario();

                if (funcionarioRepository.atualizarFuncionario(nome, tele, cpf, salario, idEmpresa, idFunc)) {
                    return status(HttpStatus.OK).build();
                }

                return status(HttpStatus.BAD_REQUEST).build();

            }
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/listar-funcionarios/{idEmpresa}")
    public ResponseEntity<List<Funcionario>> listarFuncionario(@PathVariable Integer idEmpresa) {

        List<Funcionario> lista = funcionarioRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(lista);
    }

    @DeleteMapping("/deletar-funcionario/{idFuncionario}/{idEmpresa}")
    public ResponseEntity deletaFuncionario(@PathVariable Integer idFuncionario,
                                            @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {

            if (funcionarioRepository.existsById(idFuncionario.longValue())) {
                funcionarioRepository.deleteById(idFuncionario.longValue());
                return status(HttpStatus.OK).build();
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/criar-fornecedor/{idEmpresa}")
    public ResponseEntity criarFornecedor(@RequestBody @Valid Fornecedor novoFornecedor,
                                          @PathVariable Integer idEmpresa) {

        Long idFornecedor = novoFornecedor.getIdFornecedor();

        if (empresaRepository.existsById(idEmpresa)) {

            if (!fornecedorRepository.existsById(idFornecedor)) {
                fornecedorRepository.save(novoFornecedor);
                return status(HttpStatus.CREATED).build();
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/editar-fornecedor/{idEmpresa}")
    public ResponseEntity editarFornecedor(@RequestBody @Valid NovoFornecedorRequest novoFornecedorRequest,
                                           @PathVariable Integer idEmpresa) {

        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.BAD_REQUEST).build();

        for (Fornecedor fornecedor : lista) {
            if (fornecedorRepository.existsById(fornecedor.getIdFornecedor())) {

                Long idFornecedor = fornecedor.getIdFornecedor();
                String nome = novoFornecedorRequest.getNomeFornecedor();
                String tele = novoFornecedorRequest.getTelefoneFornecedor();
                String prod = novoFornecedorRequest.getNomeProduto();
                Integer qtd = novoFornecedorRequest.getQtdFornecida();

                if (fornecedorRepository.atualizarFornecedor(nome, tele, prod, qtd, idEmpresa, idFornecedor)) {
                    return status(HttpStatus.OK).build();
                }

                return status(HttpStatus.BAD_REQUEST).build();

            }
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/listar-fornecedores/{idEmpresa}")
    public ResponseEntity<List<Fornecedor>> listarFornecedor(@PathVariable Integer idEmpresa) {

        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(lista);
    }

    @DeleteMapping("/deletar-fornecedor/{idFornecedor}/{idEmpresa}")
    public ResponseEntity deletaFornecedor(@PathVariable Integer idFornecedor,
                                           @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {

            if (fornecedorRepository.existsById(idFornecedor.longValue())) {
                fornecedorRepository.deleteById(idFornecedor.longValue());
                return status(HttpStatus.OK).build();
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    // ------------------------------------------------------------------------------------------ //

    // this snippet needs refactoring
    @GetMapping("/relatorio/{fkEmpresa}")
    public ResponseEntity relatorio(@PathVariable Integer fkEmpresa) {

        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresa(fkEmpresa);
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

        return status(HttpStatus.OK)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-de-produtos.csv\"")
                .body(relatorio);
    }

}
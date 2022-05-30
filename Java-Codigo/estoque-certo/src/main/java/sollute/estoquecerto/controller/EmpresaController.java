package sollute.estoquecerto.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/cria-empresa")
    public ResponseEntity<ResponseEntity.BodyBuilder> criaEmpresa(@RequestBody @Valid Empresa createEmpresaResponse) {

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

    // ------------------------------------------------------------------------------------------ //

    @GetMapping("/pegar-saldo/{idEmpresa}")
    public ResponseEntity<Double> getValor(@PathVariable Integer idEmpresa) {

        boolean empresa = empresaRepository.existsById(idEmpresa);

        if (empresa) {
            Double saldo = repositoryCaixa.findCaixaByFkEmpresaIdEmpresa(idEmpresa).getValor();

            return status(HttpStatus.OK).body(saldo);
        }

        return status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/adicionar-valor-caixa/{idEmpresa}/{valor}")
    public ResponseEntity<Double> creditarValor(@PathVariable Integer idEmpresa,
                                                @PathVariable Double valor) {

        boolean empresa = empresaRepository.existsById(idEmpresa);

        if (empresa) {

            if (valor < 0) return status(HttpStatus.BAD_REQUEST).build();

            double saldo = repositoryCaixa.findCaixaByFkEmpresaIdEmpresa(idEmpresa).getValor();
            double saldoAtual = saldo + valor;

            repositoryCaixa.atualizarValor(saldoAtual, 1L, idEmpresa);

            return status(HttpStatus.OK).body(saldoAtual);
        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/remover-valor-caixa/{idEmpresa}/{valor}")
    public ResponseEntity<Double> debitarValor(@PathVariable Integer idEmpresa,
                                               @PathVariable Double valor) {

        boolean empresa = empresaRepository.existsById(idEmpresa);

        if (empresa) {

            if (valor < 0) return status(HttpStatus.BAD_REQUEST).build();

            double saldo = repositoryCaixa.findCaixaByFkEmpresaIdEmpresa(idEmpresa).getValor();
            double saldoAtual = saldo - valor;

            if ((saldo - valor) >= 0) {
                repositoryCaixa.atualizarValor(saldoAtual, 1L, idEmpresa);

                return status(HttpStatus.OK).body(saldoAtual);
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/adicionar-carrinho/{codigo}/{cnpj}/{qtdProduto}")
    public ResponseEntity<ResponseEntity.BodyBuilder> addCarrinho(@PathVariable String codigo,
                                                                  @PathVariable String cnpj,
                                                                  @PathVariable Integer qtdProduto) {

        boolean existsEmpresa = empresaRepository.existsByCnpj(cnpj);
        int qtdEstoque = produtoRepository.findProdutoByCodigo(codigo).getEstoque();

        if (existsEmpresa) {

            Carrinho carrinho = new Carrinho();

            if ((qtdEstoque - qtdProduto) >= 0) {

                Produto p = produtoRepository.findProdutoByCodigo(codigo);
                Empresa e = empresaRepository.findByCnpj(cnpj);

                carrinho.setFkEmpresa(e);
                carrinho.setFkProduto(p);
                carrinho.setCodigo(p.getCodigo());
                carrinho.setNome(p.getNome());
                carrinho.setMarca(p.getMarca());
                carrinho.setQtdVenda(qtdProduto);
                carrinho.setValorVenda(qtdProduto * p.getPrecoVenda());

                carrinhoRepository.save(carrinho);

                return status(HttpStatus.OK).build();

            } else {
                return status(HttpStatus.BAD_REQUEST).build();
            }

        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/listar-produtos-carrinho/{fkEmpresa}")
    public ResponseEntity listCarrinho(@PathVariable Integer fkEmpresa) {

        List<Carrinho> lista = carrinhoRepository.findByFkEmpresaIdEmpresa(fkEmpresa);

        if (lista.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(lista);
    }

    @PutMapping("/vender-produtos-carrinho/{fkEmpresa}")
    @Transactional
    public ResponseEntity<ResponseEntity.BodyBuilder> venderCarrinho(@PathVariable Integer fkEmpresa) {

        if (empresaRepository.existsById(fkEmpresa)) {

            List<Carrinho> listaCarrinho = carrinhoRepository.findByFkEmpresaIdEmpresa(fkEmpresa);

            if (!listaCarrinho.isEmpty()) {
                double saldoCaixa = 0.0;

                for (Carrinho c : listaCarrinho) {

                    Produto p = c.getFkProduto();
                    Empresa e = c.getFkEmpresa();
                    Integer qtdVenda = c.getQtdVenda();
                    Integer estoqueAtual = p.getEstoque() - qtdVenda;
                    Integer qtdVendida = c.getQtdVenda() + p.getQtdVendidos();
                    double valor = c.getValorVenda() + p.getValorVendidos();

                    produtoRepository.venderProduto(
                            qtdVendida,
                            valor,
                            estoqueAtual,
                            p.getIdProduto(),
                            e.getIdEmpresa());
                    saldoCaixa += valor;

                    carrinhoRepository.delete(c);
                }

                saldoCaixa += repositoryCaixa.findCaixaByFkEmpresaIdEmpresa(fkEmpresa).getValor();
                repositoryCaixa.atualizarValor(saldoCaixa, 1L, fkEmpresa);

                return status(HttpStatus.OK).build();
            }
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/carrinho-apagar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity<ResponseEntity.BodyBuilder> apagarProdutoCarrinho(@PathVariable String codigo,
                                                                            @PathVariable Integer fkEmpresa) {

        List<Carrinho> lista = carrinhoRepository.findByFkEmpresaIdEmpresa(fkEmpresa);

        if (!lista.isEmpty()) {

            for (Carrinho c : lista) {
                if (c.getFkProduto().getCodigo().equals(codigo)) {
                    carrinhoRepository.deleteById(c.getIdCarrinho());
                    return status(HttpStatus.OK).build();
                }
            }

            return status(HttpStatus.NOT_FOUND).build();
        }

        return status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/atualizar-carrinho/{codigo}/{idEmpresa}/{qtdVenda}")
    public ResponseEntity<ResponseEntity.BodyBuilder> atualizaCarrinho(@PathVariable String codigo,
                                                                       @PathVariable Integer idEmpresa,
                                                                       @PathVariable Integer qtdVenda) {

        List<Carrinho> lista = carrinhoRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (!lista.isEmpty()) {

            for (Carrinho c : lista) {
                if (c.getFkProduto().getCodigo().equals(codigo)) {

                    Produto p = c.getFkProduto();

                    if ((p.getEstoque() - qtdVenda) >= 0) {
                        double novoValor = p.getPrecoVenda() * qtdVenda;

                        carrinhoRepository.atualizaCarrinho(
                                qtdVenda,
                                novoValor,
                                idEmpresa,
                                codigo
                        );

                        return status(HttpStatus.OK).build();
                    }
                }

            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity<ResponseEntity.BodyBuilder> adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                                                      @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {
            produtoRepository.save(novoProduto);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/listar-produtos/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Integer idEmpresa) {

        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresaOrderByEstoqueDesc(idEmpresa);

        if (lista.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/listar-produtos-ordem-maior/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutosOrdemMaior(@PathVariable Integer idEmpresa) {

        List<Produto> lista = produtoRepository.findFirst5ByFkEmpresaIdEmpresaOrderByQtdVendidosDesc(idEmpresa);

        if (lista.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(lista);
    }

    @PutMapping("/editar-produto/{idEmpresa}/{codigo}")
    public ResponseEntity<ResponseEntity.BodyBuilder> editarProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest,
                                                                    @PathVariable Integer idEmpresa,
                                                                    @PathVariable String codigo) {

        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.BAD_REQUEST).build();

        for (Produto p : lista) {
            if (produtoRepository.existsByCodigo(codigo)) {

                Integer estoque = novoProdutoRequest.getEstoque();
                Integer estoqueMin = novoProdutoRequest.getEstoqueMin();
                Integer estoqueMax = novoProdutoRequest.getEstoqueMax();
                Double precoCompra = novoProdutoRequest.getPrecoCompra();
                Double precoVenda = novoProdutoRequest.getPrecoVenda();

                produtoRepository.atualizarProduto(
                        estoque,
                        estoqueMin,
                        estoqueMax,
                        precoCompra,
                        precoVenda,
                        codigo,
                        idEmpresa);

                return status(HttpStatus.OK).build();
            }
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/deletar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity<ResponseEntity.BodyBuilder> deletarProduto(@PathVariable String codigo,
                                                                     @PathVariable Integer fkEmpresa) {

        if (empresaRepository.existsById(fkEmpresa)) {
            produtoRepository.deleteProdutoByCodigo(codigo);
            return status(HttpStatus.OK).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/calcular-produtos-vendidos/{fkEmpresa}")
    public ResponseEntity<Integer> calcularProdutosVendidos(@PathVariable Integer fkEmpresa) {

        int aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getQtdVendidos();
            }
            return status(HttpStatus.OK).body(aux);

        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/calcular-valor-vendidos/{fkEmpresa}")
    public ResponseEntity<Double> calcularValorVendidos(@PathVariable Integer fkEmpresa) {

        double aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getValorVendidos();
            }

            return status(HttpStatus.OK).body(aux);
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/calcular-liquido/{fkEmpresa}")
    public ResponseEntity<Double> lucroLiquido(@PathVariable Integer fkEmpresa) {

        Double bruto = calcularValorVendidos(fkEmpresa).getBody();
        double aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getPrecoCompra() * prod.getQtdVendidos();
            }

            aux = bruto - aux;

            return status(200).body(aux);
        }

        return status(404).build();
    }

    // ------------------------------------------------------------------------------------------ //

    @PostMapping("/adicionar-cliente/{idEmpresa}")
    public ResponseEntity<ResponseEntity.BodyBuilder> adicionaCliente(@RequestBody @Valid Cliente novoCliente,
                                                                      @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {
            clienteRepository.save(novoCliente);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/editar-cliente/{idEmpresa}/{idCliente}")
    public ResponseEntity<ResponseEntity.BodyBuilder> editarCliente(@RequestBody @Valid NovoClienteRequest novoClienteRequest,
                                                                    @PathVariable Integer idEmpresa,
                                                                    @PathVariable Long idCliente) {

        List<Cliente> lista = clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.BAD_REQUEST).build();

        for (Cliente cliente : lista) {
            if (clienteRepository.existsById(idCliente)) {

                String nome = novoClienteRequest.getNomeCliente();
                String tele = novoClienteRequest.getTelefoneCliente();

                if (clienteRepository.atualizarCliente(nome, tele, idEmpresa, idCliente) == 1) {
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
    public ResponseEntity<ResponseEntity.BodyBuilder> deletaCliente(@PathVariable Integer idCliente,
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
    public ResponseEntity<ResponseEntity.BodyBuilder> criarFuncionario(@RequestBody @Valid Funcionario novoFuncionario,
                                                                       @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {
            funcionarioRepository.save(novoFuncionario);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/editar-funcionario/{idEmpresa}/{idFuncionario}")
    public ResponseEntity<ResponseEntity.BodyBuilder> editarFuncionario(@RequestBody @Valid NovoFuncionarioRequest novoFuncionarioRequest,
                                                                        @PathVariable Integer idEmpresa,
                                                                        @PathVariable Long idFuncionario) {

        List<Funcionario> lista = funcionarioRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.BAD_REQUEST).build();

        for (Funcionario funcionario : lista) {
            if (funcionarioRepository.existsById(idFuncionario)) {

                String nome = novoFuncionarioRequest.getNomeFuncionario();
                String tele = novoFuncionarioRequest.getTelefoneFuncionario();
                String cpf = novoFuncionarioRequest.getCpfFuncionario();
                Double salario = novoFuncionarioRequest.getSalario();

                if (funcionarioRepository.atualizarFuncionario(nome, tele, cpf, salario, idEmpresa, idFuncionario) == 1) {
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
    public ResponseEntity<ResponseEntity.BodyBuilder> deletaFuncionario(@PathVariable Integer idFuncionario,
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
    public ResponseEntity<ResponseEntity.BodyBuilder> criarFornecedor(@RequestBody @Valid Fornecedor novoFornecedor,
                                                                      @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {
            fornecedorRepository.save(novoFornecedor);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/editar-fornecedor/{idEmpresa}/{idFornecedor}")
    public ResponseEntity<ResponseEntity.BodyBuilder> editarFornecedor(@RequestBody @Valid NovoFornecedorRequest novoFornecedorRequest,
                                                                       @PathVariable Integer idEmpresa,
                                                                       @PathVariable Long idFornecedor) {

        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.BAD_REQUEST).build();

        for (Fornecedor fornecedor : lista) {
            if (fornecedorRepository.existsById(idFornecedor)) {

                String nome = novoFornecedorRequest.getNomeFornecedor();
                String tele = novoFornecedorRequest.getTelefoneFornecedor();
                String prod = novoFornecedorRequest.getNomeProduto();
                Integer qtd = novoFornecedorRequest.getQtdFornecida();

                if (fornecedorRepository.atualizarFornecedor(nome, tele, prod, qtd, idEmpresa, idFornecedor) == 1) {
                    return status(HttpStatus.OK).build();
                }

                return status(HttpStatus.BAD_REQUEST).build();

            }
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/listar-fornecedores/{idEmpresa}")
    public ResponseEntity<List <Fornecedor>> listarFornecedor(@PathVariable Integer idEmpresa) {

        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            return status(HttpStatus.NO_CONTENT).build();
        }

        return status(HttpStatus.OK).body(lista);
    }

    @DeleteMapping("/deletar-fornecedor/{idFornecedor}/{idEmpresa}")
    public ResponseEntity<ResponseEntity.BodyBuilder> deletaFornecedor(@PathVariable Integer idFornecedor,
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

    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "text/csv"))
    })
    @GetMapping("/relatorio-csv/{fkEmpresa}")
    public ResponseEntity relatorio(@PathVariable Integer fkEmpresa) {

        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresa(fkEmpresa);

        String relatorio =
                "CODIGO;NOME;MARCA;CATEGORIA;TAMANHO;PESO;PRECO COMPRA;PRECO VENDA;" +
                        "ESTOQUE;ESTOQUE MINIMO;ESTOQUE MAXIMO;QTD VENDIDOS;\r\n";

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

    @PatchMapping(value = "/arquivo-txt/{cnpj}", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity patchFoto(@PathVariable String cnpj,
                                    @RequestBody byte[] novoArquivo,
                                    Model model, @RequestParam("files") MultipartFile[] files) {

        int atualizado = empresaRepository.patchArquivo(novoArquivo, cnpj);

        if (atualizado > 0) {
            return status(200).build();
        }

        return status(404).build();
    }


}
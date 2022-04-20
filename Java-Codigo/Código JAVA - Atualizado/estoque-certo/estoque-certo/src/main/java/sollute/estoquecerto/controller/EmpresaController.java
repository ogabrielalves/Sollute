package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.EmpresaResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repositoryEmpresa;

    @Autowired
    private ProdutoRepository repositoryProduto;

    ListaObj<Empresa> listaEmpresa = new ListaObj(10);
    ListaObj<Produto> listaProduto = new ListaObj(10);

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(
            @RequestBody EmpresaResponse requisicao) {
        List<Empresa> empresa = repositoryEmpresa.findAll();
        for (Empresa e : empresa) {
            if (e.getLogin().equals(requisicao.getLogin()) && e.getSenha().equals(requisicao.getSenha())) {
                repositoryEmpresa.atualizarAutenticado(requisicao.getLogin(), true);
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping
    public ResponseEntity criaEmpresa(@RequestBody @Valid Empresa novaEmpresa) {
        listaEmpresa.adiciona(novaEmpresa);
        repositoryEmpresa.save(novaEmpresa);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/criar-produto/{cnpj}")
    public ResponseEntity adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                          @PathVariable String cnpj) {
        if (repositoryEmpresa.existsByCnpj(cnpj)) { // Verificando se o CNPJ existe
            repositoryProduto.save(novoProduto);    // Adicionado no Banco de Dados
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.status(200).body(repositoryEmpresa.findAll());
    }

    @PostMapping("/venderProdutos/{cnpj}/{nome}/{qtd}")
    public ResponseEntity venderProdutosPorCnpj(@PathVariable String cnpj,
                                                @PathVariable String nome,
                                                @PathVariable int qtd) {
        if (listaEmpresa.getTamanho() == 0) {
            return ResponseEntity.status(204).build(); // Não há empresas.
        } else {

            // Iterando a lista de Empresas
            for (int i = 0; i < listaEmpresa.getTamanho(); i++) {

                // Verificando se existe uma empresa com o cnpj do parâmetro
                if (listaEmpresa.getElemento(i).getCnpj().equals(cnpj)) {

                    // Iterando a lista de Produtos
                    for (int j = 0; j < listaProduto.getTamanho(); j++) {

                        // Variáveis criadas para evitar repetição de código
                        Produto p = listaProduto.getElemento(i);
                        Empresa e = listaEmpresa.getElemento(i);

                        // Verificando se o nome do produto é o mesmo do parâmetro
                        if (p.getNome().equals(nome)) {
                            // Verificando se o objeto do FOR é da classe ProdutoAlimento
                            if (p instanceof Produto) {
                                if (p.getEstoqueInicial() - qtd >= 0) {
                                    p.vender(qtd);
                                    e.setQtdProdutosVendidos(qtd);
                                    e.setTotalProdutosVendidos(p.getValorVendidos());
                                    return ResponseEntity.status(200).build();
                                }
                                return ResponseEntity.status(400).build(); // Estoque insuficiente
                            }

                            // Verificando se o objeto do FOR é da classe ProdutoServico
                            if (p instanceof Produto) {
                                if (p.getEstoqueInicial() - qtd >= 0) {
                                    p.vender(qtd);
                                    e.setQtdProdutosVendidos(qtd);
                                    e.setTotalProdutosVendidos(p.getValorVendidos());
                                    return ResponseEntity.status(200).build();
                                }
                                return ResponseEntity.status(400).build(); // Estoque insuficiente
                            }

                            // Verificando se o objeto do FOR é da classe ProdutoVestuario
                            if (p instanceof Produto) {
                                if (p.getEstoqueInicial() - qtd >= 0) {
                                    p.vender(qtd);
                                    e.setQtdProdutosVendidos(qtd);
                                    e.setTotalProdutosVendidos(p.getValorVendidos());
                                    return ResponseEntity.status(200).build();
                                }
                                return ResponseEntity.status(400).build(); // Estoque insuficiente
                            }

                        }
                    }
                }
            }
            // Retornando 400 se não houver a empresa cadastrada
            return ResponseEntity.status(404).build(); // Empresa não encontrada.
        }
    }

    @GetMapping("/listar-produtos/{cnpj}")
    public ResponseEntity<List<Produto>> listarProdutosVestuarioPorCnpj(@PathVariable @Valid String cnpj) {
        return ResponseEntity.status(200).body(repositoryProduto.findByCnpj(cnpj));
    }

    @GetMapping("/calcular-produtos-vendidos/{cnpj}")
    public ResponseEntity calcularProdutosVendidos(@PathVariable String cnpj) {
        if (cnpj.length() != 14) {
            return ResponseEntity.status(400).build(); // CNPJ inválido.
        } else {
            for (int i = 0; i < listaEmpresa.getTamanho(); i++) {
                // Validando se a empresa existe com o cnpj do parametro
                if (listaEmpresa.getElemento(i).getCnpj().equals(cnpj)) {
                    // Pegando a quantidade produtos que foram vendidos pela empresa
                    int aux = listaEmpresa.getElemento(i).getQtdProdutosVendidos();
                    return ResponseEntity.status(204).body("Total de produtos Vendidos: " + aux);
                }
            }
        }
        return ResponseEntity.status(404).build(); // Empresa não encontrada.
    }

    @GetMapping("/calcular-valor-vendidos/{cnpj}")
    public ResponseEntity calcularValorVendidos(@PathVariable String cnpj) {
        if (cnpj.length() != 14) {
            return ResponseEntity.status(400).build(); // CNPJ inválido.
        } else {
            for (int i = 0; i < listaEmpresa.getTamanho(); i++) {
                // Validando se a empresa existe com o cnpj do parametro
                if (listaEmpresa.getElemento(i).getCnpj().equals(cnpj)) {
                    // Pegando a quantidade produtos que foram vendidos pela empresa
                    double aux = listaEmpresa.getElemento(i).getTotalProdutosVendidos();
                    return ResponseEntity.status(204).body("Valor total de produtos Vendidos: R$" + aux);
                }
            }
        }
        return ResponseEntity.status(404).build(); // Empresa não encontrada.
    }

    @DeleteMapping("/deletar-produto/{codigo}")
    public ResponseEntity deletarProduto(@PathVariable String codigo) {
        repositoryProduto.deleteById(codigo);
        return ResponseEntity.status(200).build();
    }
}

package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.EmpresaResponse;
import sollute.estoquecerto.request.ProdutoResponse;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repositoryEmpresa;

    @Autowired
    private ProdutoRepository repositoryProduto;

    @PostMapping
    public ResponseEntity criaEmpresa(@RequestBody @Valid Empresa novaEmpresa) {
        repositoryEmpresa.save(novaEmpresa);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(@RequestBody @Valid EmpresaResponse requisicao) {
        List<Empresa> empresa = repositoryEmpresa.findAll();
        for (Empresa e : empresa) {
            if (e.getLogin().equals(requisicao.getLogin()) && e.pegarSenha().equals(requisicao.pegarSenha())) {
                repositoryEmpresa.atualizarAutenticado(requisicao.getLogin(), true);
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                          @PathVariable Long idEmpresa) {
        novoProduto.getFkEmpresa();
        if (repositoryEmpresa.existsById(idEmpresa)) { // Verificando se a empresa existe
            repositoryProduto.save(novoProduto);       // Adicionado no Banco de Dados
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.status(200).body(repositoryEmpresa.findAll());
    }

    @PostMapping("/vender-produtos")
    public ResponseEntity venderProdutos(@RequestBody @Valid ProdutoResponse produtoResponse) {
        if (repositoryEmpresa.existsById(produtoResponse.getIdEmpresa())) {
            if (repositoryProduto.existsByCodigo(produtoResponse.getCodigo())) {
                if (repositoryProduto.findByQtdVendidosIsGreaterThan(produtoResponse.getQtdVendida())) {
                    repositoryProduto.atualizarQtdVendida(
                            produtoResponse.getCodigo(),
                            produtoResponse.getQtdVendida());
                } else {
                    return ResponseEntity.status(400).build(); // Não há estoque suficiente.
                }
            } else {
                return ResponseEntity.status(404).build(); // Não existe produto com o codigo informado
            }
        }
        return ResponseEntity.status(404).build(); // Não existe empresa com o cnpj informado
    }

    @GetMapping("/listar-produtos/{fkEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Integer fkEmpresa) {

        if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {
            return ResponseEntity.status(200).body(repositoryProduto.findByFkEmpresa(fkEmpresa));
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/calcular-produtos-vendidos/{fkEmpresa}")
    public ResponseEntity calcularProdutosVendidos(@PathVariable Integer fkEmpresa) {
        int aux = 0;
        if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {
            for (Produto prod : repositoryProduto.findAll()) {
                aux += prod.getQtdVendidos();
            }
            return ResponseEntity.status(200).body(aux);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/calcular-valor-vendidos/{fkEmpresa}")
    public ResponseEntity calcularValorVendidos(@PathVariable Integer fkEmpresa) {
        int aux = 0;
        if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {
            for (Produto prod : repositoryProduto.findAll()) {
                aux += prod.getValorVendidos();
            }
            return ResponseEntity.status(200).body(aux);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/deletar-produto/{codigo}")
    public ResponseEntity deletarProduto(@PathVariable String codigo) {
        repositoryProduto.deleteById(codigo);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity relatorio() {

        List<Produto> lista = repositoryProduto.findAll();
        String relatorio = "" +
                "CODIGO;NOME;MARCA;CATEGORIA;TAMANHO;PESO;PRECO COMPRA;PRECO VENDA;" +
                "ESTOQUE INICIAL;ESTOQUE MINIMO;ESTOQUE MAXIMO;QTD VENDIDOS";
        for (Produto prod : lista) {
            relatorio += "" +
                    ";" + prod.getCodigo() +
                    ";" + prod.getNome() +
                    ";" + prod.getMarca() +
                    ";" + prod.getCategoria() +
                    ";" + prod.getTamanho() +
                    ";" + prod.getPeso() +
                    ";" + prod.getPrecoCompra() +
                    ";" + prod.getPrecoVenda() +
                    ";" + prod.getEstoqueInicial() +
                    ";" + prod.getEstoqueMin() +
                    ";" + prod.getEstoqueMax() +
                    ";" + prod.getQtdVendidos() + "\r\n";
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-de-produtos.csv\"")
                .body(relatorio);
    }
}
package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.EmpresaResponse;
import sollute.estoquecerto.request.ProdutoLoginResponse;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@SuppressWarnings("ALL")
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
        return status(201).build();
    }

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(@RequestBody @Valid EmpresaResponse requisicao) {
        List<Empresa> empresa = repositoryEmpresa.findAll();
        for (Empresa e : empresa) {
            if (e.getLogin().equals(requisicao.getLogin()) && e.getSenha().equals(requisicao.getSenha())) {
                repositoryEmpresa.atualizarAutenticado(requisicao.getLogin(), true);
                return status(200).body(e);
            }
        }
        return status(401).build();
    }

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                          @PathVariable Long idEmpresa) {
        novoProduto.getFkEmpresa();
        if (repositoryEmpresa.existsById(idEmpresa)) { // Verificando se a empresa existe
            repositoryProduto.save(novoProduto);       // Adicionado no Banco de Dados
            return status(201).build();
        }
        return status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return status(200).body(repositoryEmpresa.findAll());
    }

    @PostMapping("/vender-produtos")
    public ResponseEntity venderProdutos(@RequestBody @Valid ProdutoLoginResponse produtoLoginResponse) {
        if (repositoryEmpresa.existsById(produtoLoginResponse.getIdEmpresa())) {
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
                return status(400).build(); // N??o h?? estoque suficiente.
            }
        } else {
            return status(404).build(); // N??o existe produto com o codigo informado
        }
    }


    @GetMapping("/listar-produtos/{fkEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Integer fkEmpresa) {

        if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {
            return status(200).body(repositoryProduto.findByFkEmpresa(fkEmpresa));
        }
        return status(404).build();
    }

    @GetMapping("/calcular-produtos-vendidos/{fkEmpresa}")
    public ResponseEntity calcularProdutosVendidos(@PathVariable Integer fkEmpresa) {
        int aux = 0;
        if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {
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
        if (repositoryEmpresa.existsById(fkEmpresa.longValue())) {
            for (Produto prod : repositoryProduto.findAll()) {
                aux += prod.getValorVendidos();
            }
            return status(200).body(aux);
        }
        return status(404).build();
    }


    @DeleteMapping("/deletar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity deletarProduto(@PathVariable String codigo, @PathVariable Integer fkEmpresa) {
        repositoryProduto.deleteProdutoByIdProduto(repositoryProduto.findByCodigoAndFkEmpresa(codigo, fkEmpresa).getIdProduto());
        return status(200).build();
    }


    @GetMapping("/relatorio/{fkEmpresa}")
    public ResponseEntity relatorio(@PathVariable Integer fkEmpresa) {

        List<Produto> lista = repositoryProduto.findByFkEmpresa(fkEmpresa);
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
                    ";" + prod.getEstoqueInicial() +
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
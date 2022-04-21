package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.EmpresaResponse;
import sollute.estoquecerto.request.ProdutoResponse;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity adicionaProduto(@RequestBody @Valid Produto novoProduto,
                                          @PathVariable Long idEmpresa) {
        if (repositoryEmpresa.existsById(idEmpresa)) { // Verificando se a empresa existe
            repositoryProduto.save(novoProduto);    // Adicionado no Banco de Dados
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.status(200).body(repositoryEmpresa.findAll());
    }

    @PostMapping("/vender-produtos")
    public ResponseEntity venderProdutos(@RequestBody ProdutoResponse produtoResponse) {
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

    @GetMapping("/listar-produtos/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable @Valid String cnpj) {

        if (repositoryEmpresa.existsByCnpj(cnpj)) {
            return ResponseEntity.status(201).body(repositoryProduto.findByCnpj(cnpj));
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/calcular-produtos-vendidos/{cnpj}")
    public ResponseEntity calcularProdutosVendidos(@PathVariable @Valid String cnpj) {
        int aux = 0;
        if (repositoryEmpresa.existsByCnpj(cnpj)) {
            for (Produto prod : repositoryProduto.findAll()) {
                aux += prod.getQtdVendidos();
            }
            return ResponseEntity.status(200).body(aux);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/calcular-valor-vendidos/{cnpj}")
    public ResponseEntity calcularValorVendidos(@PathVariable String cnpj) {
        int aux = 0;
        if (repositoryEmpresa.existsByCnpj(cnpj)) {
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
}
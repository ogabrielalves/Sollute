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

    @GetMapping("/listar-produtos/{cnpj}/{fkEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable @Valid String cnpj,
                                                        @PathVariable Integer fkEmpresa) {

        if (repositoryEmpresa.existsByCnpj(cnpj)) {
            return ResponseEntity.status(200).body(repositoryProduto.findByFkEmpresa(fkEmpresa));
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

    @GetMapping("/relatorio")
    public ResponseEntity relatorio() {

//        List<Produto> lista = new List<>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<Produto> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] a) {
//                return null;
//            }
//
//            @Override
//            public boolean add(Produto produto) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends Produto> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(int index, Collection<? extends Produto> c) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public Produto get(int index) {
//                return null;
//            }
//
//            @Override
//            public Produto set(int index, Produto element) {
//                return null;
//            }
//
//            @Override
//            public void add(int index, Produto element) {
//
//            }
//
//            @Override
//            public Produto remove(int index) {
//                return null;
//            }
//
//            @Override
//            public int indexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public int lastIndexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public ListIterator<Produto> listIterator() {
//                return null;
//            }
//
//            @Override
//            public ListIterator<Produto> listIterator(int index) {
//                return null;
//            }
//
//            @Override
//            public List<Produto> subList(int fromIndex, int toIndex) {
//                return null;
//            }
//        };
//
//        for (int j = 0; j < repositoryEmpresa.count(); j++) {
//            lista.add(repositoryProduto.findByIdProduto(j));
//        }
//
//        ArquivoCsv.gravaArquivoCsv(repositoryProduto.findAll(), "relatorio-de-produtos");

        List<Produto> lista = repositoryProduto.findAll();
        String relatorio = "CODIGO;NOME;MARCA;CATEGORIA;TAMANHO;PESO;PRECO COMPRA;PRECO VENDA;ESTOQUE INICIAL;ESTOQUE MINIMO;ESTOQUE MAXIMO;QTD VENDIDOS;";
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
                    ";" + prod.getQtdVendidos() +
            "\r\n";
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-de-produtos.csv\"")
                .body(relatorio);
    }
}
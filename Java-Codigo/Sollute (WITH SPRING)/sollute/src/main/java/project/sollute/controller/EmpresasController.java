package project.sollute.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sollute.entity.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresasController {

    private List<Empresa> listaEmpresa = new ArrayList<>();
    private List<Produto> listaProdutos = new ArrayList<>();

    @PostMapping("/criarEmpresa/{cpf}")
    public ResponseEntity criaEmpresa(@RequestBody Empresa novaEmpresa,
                                      @PathVariable String cpf) {
        if (novaEmpresa == null) {
            return ResponseEntity.status(400).build(); // Objeto inválido
        } else if (novaEmpresa != null) {
            listaEmpresa.add(novaEmpresa);
            return ResponseEntity.status(201).body("Empresa criada com sucesso");
        }
        return ResponseEntity.status(404).build(); // CEO não encontrado
    }

    @PostMapping("/criarProdutoAlimento/{cnpj}")
    public ResponseEntity adicionaProdutoAlimento(@RequestBody ProdutoAlimento prod,
                                                  @PathVariable String cnpj) {
        if (prod == null) {
            return ResponseEntity.status(400).build(); // Objeto inválido
        } else {
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    listaProdutos.add(prod);
                    return ResponseEntity.status(201).body("Produto adicionado com sucesso");
                }
            }
            return ResponseEntity.status(404).build(); // Empresa não encontrada
        }
    }

    @PostMapping("/criarProdutoServico/{cnpj}")
    public ResponseEntity adicionaProdutoServico(@RequestBody ProdutoOrdemDeServico prod,
                                                 @PathVariable String cnpj) {
        if (prod == null) {
            return ResponseEntity.status(400).build(); // Objeto inválido
        } else {
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    listaProdutos.add(prod);
                    return ResponseEntity.status(201).body("Produto adicionado com sucesso");
                }
            }
            return ResponseEntity.status(404).build(); // Empresa não encontrada
        }
    }

    @PostMapping("/criarProdutoVestuario/{cnpj}")
    public ResponseEntity adicionaProdutoVestuario(@RequestBody ProdutoVestuario prod,
                                                   @PathVariable String cnpj) {
        if (prod == null) {
            return ResponseEntity.status(400).build(); // Objeto inválido
        } else {
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    listaProdutos.add(prod);
                    return ResponseEntity.status(201).body("Produto adicionado com sucesso");
                }
            }
            return ResponseEntity.status(404).build(); // Empresa não encontrada
        }
    }

    @GetMapping("/listarEmpresas")
    public ResponseEntity listarEmpresas() {
        if (listaEmpresa.isEmpty()) {
            return ResponseEntity.status(204).build(); // Lista Vazia
        } else {
            return ResponseEntity.status(200).body(listaEmpresa);
        }
    }

    @PostMapping("/venderProdutos/{cnpj}/{nome}/{qtd}")
    public ResponseEntity venderProdutosPorCnpj(@PathVariable String cnpj,
                                                @PathVariable String nome,
                                                @PathVariable int qtd) {
        if (listaEmpresa.isEmpty()) {
            return ResponseEntity.status(204).build(); // Não há empresas.
        } else {
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    for (Produto p : listaProdutos) {
                        if (p.getNome().equals(nome)) {
                            if (p instanceof ProdutoAlimento) {
                                if (p.getQtdEstoque() - qtd >= 0) {
                                    p.vender(qtd);
                                    e.setQtdProdutosVendidos(qtd);
                                    e.setTotalProdutosVendidos(p.getValorVendidos());
                                    return ResponseEntity.status(200).build();
                                }
                                return ResponseEntity.status(400).build(); // Estoque insuficiente
                            }
                            if (p instanceof ProdutoOrdemDeServico) {
                                if (p.getQtdEstoque() - qtd >= 0) {
                                    p.vender(qtd);
                                    e.setQtdProdutosVendidos(qtd);
                                    e.setTotalProdutosVendidos(p.getValorVendidos());
                                    return ResponseEntity.status(200).build();
                                }
                                return ResponseEntity.status(400).build(); // Estoque insuficiente
                            }
                            if (p instanceof ProdutoVestuario) {
                                if (p.getQtdEstoque() - qtd >= 0) {
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
            return ResponseEntity.status(404).build(); // Empresa não encontrada.
        }
    }

    @GetMapping("/listarProdutos/{cnpj}")
    public ResponseEntity listarProdutosPorCnpj(@PathVariable String cnpj) {
        if (cnpj.length() != 14) {
            return ResponseEntity.status(400).build(); // CNPJ inválido.
        } else {
            for (int i = 0; i < listaEmpresa.size(); i++) {
                if (listaEmpresa.get(i).getCnpj().equals(cnpj)) {
                    return ResponseEntity.status(200).body(listaProdutos);
                }
            }
            return ResponseEntity.status(404).build(); // Empresa não encontrada.
        }
    }

    @GetMapping("/calcularProdutosVendidos/{cnpj}")
    public ResponseEntity calcularProdutosVendidos(@PathVariable String cnpj) {
        if (cnpj.length() != 14) {
            return ResponseEntity.status(400).build(); // CNPJ inválido.
        } else {
            for (int i = 0; i < listaEmpresa.size(); i++) {
                if (listaEmpresa.get(i).getCnpj().equals(cnpj)) {
                    if (listaEmpresa.get(i).getQtdProdutosVendidos() <= 0) {
                        return ResponseEntity.status(204).body("Nenhum produto foi vendido.");
                    } else {
                        int aux = 0;
                        for (int j = 0; j < listaProdutos.size(); j++) {
                            aux += listaProdutos.get(i).getQtdVendidos();
                        }
                        return ResponseEntity.status(204).body("Total de produtos Vendidos: " + aux);
                    }
                }
            }
        }
        return ResponseEntity.status(404).build(); // Empresa não encontrada.
            /*
            return ResponseEntity.status(404).body("Empresa não encontrada.");
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    if (e.calculaTotalProdutosVendidos() > 0) {
                        return ResponseEntity.status(204).body(e.calculaTotalProdutosVendidos());
                    } else {
                        return ResponseEntity.status(201).body(e.calculaTotalProdutosVendidos());
                    }
                }
            }
            return ResponseEntity.status(404).body("Empresa não encontrada.");
        }
        */
    }

    @GetMapping("/calcularValorVendidos/{cnpj}")
    public ResponseEntity calcularValorVendidos(@PathVariable String cnpj) {
        if (cnpj.length() != 14) {
            return ResponseEntity.status(400).build(); // CNPJ inválido.
        } else {
            for (int i = 0; i < listaEmpresa.size(); i++) {
                if (listaEmpresa.get(i).getCnpj().equals(cnpj)) {
                    if (listaEmpresa.get(i).getQtdProdutosVendidos() <= 0) {
                        return ResponseEntity.status(204).body("Nenhum produto foi vendido.");
                    } else {
                        int aux = 0;
                        for (int j = 0; j < listaProdutos.size(); j++) {
                            aux += listaProdutos.get(i).getValorVendidos();
                        }
                        return ResponseEntity.status(204).body("Varlo total de produtos Vendidos: R$" + aux);
                    }
                }
            }
        }
        return ResponseEntity.status(404).build(); // Empresa não encontrada.
    }
}
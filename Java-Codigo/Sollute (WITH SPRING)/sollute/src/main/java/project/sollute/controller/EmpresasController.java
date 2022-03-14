package project.sollute.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sollute.entity.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/empresas")
public class EmpresasController {

    private List<Empresa> listaEmpresa = new ArrayList<>();
    EmpreendedorController eC = new EmpreendedorController();

    @PostMapping("/criarEmpresa/{cpf}")
    public ResponseEntity criaEmpresa(@RequestBody Empresa emp, @PathVariable String cpf) {
        if (emp == null) {
            return ResponseEntity.status(400).body("Objeto inválido.");
        } else {
            Empresa empresa = new Empresa(
                    emp.getNomeFantasia(),
                    emp.getCnpj(),
                    emp.getInscricaoEstadual(),
                    emp.getCeo());
            listaEmpresa.add(empresa);
            return ResponseEntity.status(201).body("Empresa criada com sucesso");
        }
        // return ResponseEntity.status(400).body("CEO não encontrado.");
    }

    @PostMapping("/criarProdutoAlimento/{cnpj}")
    public ResponseEntity adicionaProdutoAlimento(@RequestBody ProdutoAlimento prod,@PathVariable String cnpj) {
        if (prod == null) {
            return ResponseEntity.status(400).body("Objeto inválido.");
        } else {
            // listaP.add(prod);
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    e.adicionaProduto(prod);
                    return ResponseEntity.status(201).body("Produto adicionado com sucesso");
                }
            }
            return ResponseEntity.status(404).body("Empresa não encontrada.");
        }
    }

    @PostMapping("/criarProdutoServico/{cnpj}")
    public ResponseEntity adicionaProdutoServico(@RequestBody ProdutoOrdemDeServico prod,@PathVariable String cnpj) {
        if (prod == null) {
            return ResponseEntity.status(400).body("Objeto inválido.");
        } else {
            // listaP.add(prod);
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    e.adicionaProduto(prod);
                    return ResponseEntity.status(201).body("Produto adicionado com sucesso");
                }
            }
            return ResponseEntity.status(404).body("Empresa não encontrada.");
        }
    }

    @PostMapping("/criarProdutoVestuario/{cnpj}")
    public ResponseEntity adicionaProdutoVestuario(@RequestBody ProdutoVestuario prod,@PathVariable String cnpj) {
        if (prod == null) {
            return ResponseEntity.status(400).body("Objeto inválido.");
        } else {
            // listaP.add(prod);
            for (Empresa e : listaEmpresa) {
                if (e.getCnpj().equals(cnpj)) {
                    e.adicionaProduto(prod);
                    return ResponseEntity.status(201).body("Produto adicionado com sucesso");
                }
            }
            return ResponseEntity.status(404).body("Empresa não encontrada.");
        }
    }

        @GetMapping("/listarProdutos/{cnpj}")
        public ResponseEntity listarProdutosPorCnpj(@PathVariable String cnpj){
            if (cnpj.length() != 14) {
                return ResponseEntity.status(400).body("CNPJ inválido.");
            } else {
                for (Empresa e : listaEmpresa) {
                    if (e.getCnpj().equals(cnpj)) {
                        return ResponseEntity.status(200).body(e.mostraProduto());
                    }
                }
                return ResponseEntity.status(404).body("Empresa não encontrada.");
            }
        }

        @GetMapping("/calcularProdutosVendidos/{cnpj}")
        public ResponseEntity calcularProdutosVendidos (@PathVariable String cnpj){
            if (cnpj.length() != 14) {
                return ResponseEntity.status(400).body("CNPJ inválido.");
            } else {
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
        }

        @GetMapping("/calcularValorVendidos/{cnpj}")
        public ResponseEntity calcularValorVendidos (@PathVariable String cnpj){
            if (cnpj.length() != 14) {
                return ResponseEntity.status(400).body("CNPJ inválido.");
            } else {
                for (Empresa e : listaEmpresa) {
                    if (e.getCnpj().equals(cnpj)) {
                        if (e.calculaTotalProdutosVendidos() > 0) {
                            return ResponseEntity.status(204).body(e.calculaValorProdutosVendidos());
                        } else {
                            return ResponseEntity.status(201).body(e.calculaValorProdutosVendidos());
                        }
                    }
                }
                return ResponseEntity.status(404).body("Empresa não encontrada.");
            }
        }
    }
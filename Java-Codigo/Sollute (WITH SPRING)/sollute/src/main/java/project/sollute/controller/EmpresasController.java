package project.sollute.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sollute.entity.Empreendedor;
import project.sollute.entity.Empresa;
import project.sollute.entity.Produto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/empresa")
public class EmpresasController {

    // Empreendedor empreendedor = new Empreendedor("Leonardo Vicchietti", "548.965.852-02");
    private List<Empresa> listaEmpresa = new ArrayList<>();
    EmpreendedorController eC = new EmpreendedorController();

    @PostMapping
    public ResponseEntity criaEmpresa(@RequestBody Empresa emp) {
        if (emp == null) {
            return ResponseEntity.status(400).body("Objeto inválido.");
        } else if (eC.getListaEmpreendedor().contains(emp.getCeo())) {
            Empresa empresa = new Empresa(
                    emp.getNomeFantasia(),
                    emp.getCnpj(),
                    emp.getInscricaoEstadual(),
                    emp.getCeo());
            listaEmpresa.add(empresa);
            return ResponseEntity.status(201).body("Empresa criada com sucesso");
        } else {
            return ResponseEntity.status(400).body("CEO não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity adicionaProduto(@PathVariable String cnpj, @RequestBody Produto prod) {
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

    @GetMapping("/listarProdutos")
    public ResponseEntity listarProdutosPorCnpj(@PathVariable String cnpj) {
        if (cnpj.length() != 18) {
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

    @GetMapping("/calcularProdutosVendidos")
    public ResponseEntity calcularProdutosVendidos(@PathVariable String cnpj) {
        if (cnpj.length() != 18) {
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

    @GetMapping("/calcularValorVendidos")
    public ResponseEntity calcularValorVendidos(@PathVariable String cnpj) {
        if (cnpj.length() != 18) {
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
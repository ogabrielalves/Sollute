package project.sollute.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sollute.entity.Empreendedor;
import project.sollute.entity.Empresa;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/empreendedor")
public class EmpreendedorController {

    // Atributos
    private List<Empreendedor> listaEmpreendedor = new ArrayList<>();

    // Aqui é criado somente o empreendedor, item essencial para a criação de uma empresa
    @PostMapping
    public ResponseEntity criaEmpreendedor(@RequestBody Empreendedor empreendedor) {
        if (empreendedor == null) {
            return ResponseEntity.status(400).body("Objeto inválido.");
        } else {
            Empreendedor emp = new Empreendedor(empreendedor.getNome(), empreendedor.getCpf());
            listaEmpreendedor.add(emp);
            return ResponseEntity.status(201).body("Empreendedor criado com sucesso");
        }
    }

    @GetMapping("/listarEmpreendedores")
    public ResponseEntity listarEmpreendedores() {
        if (listaEmpreendedor.isEmpty()) {
            return ResponseEntity.status(204).body("Não há empreendedores.");
        } else {
            return ResponseEntity.status(200).body(listaEmpreendedor);
        }
    }

    // Getter
    public List<Empreendedor> getListaEmpreendedor() {
        return listaEmpreendedor;
    }
}
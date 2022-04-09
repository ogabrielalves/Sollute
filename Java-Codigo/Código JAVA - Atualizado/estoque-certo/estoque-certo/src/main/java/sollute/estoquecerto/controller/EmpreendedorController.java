package sollute.estoquecerto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Empreendedor;
import sollute.estoquecerto.entity.ListaObj;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empreendedores")
public class EmpreendedorController {

    // Atributos
    ListaObj<Empreendedor> listaEmpreendedor = new ListaObj(10);

    // Aqui é criado somente o empreendedor, item essencial para a criação de uma empresa
    @PostMapping("/criarEmpreendedor")
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

    public Boolean getEmpreendedor(String cpf) {
        for (Empreendedor e : listaEmpreendedor) {
            if (e.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    // Getter
    public List<Empreendedor> getListaEmpreendedor() {
        return listaEmpreendedor;
    }
}
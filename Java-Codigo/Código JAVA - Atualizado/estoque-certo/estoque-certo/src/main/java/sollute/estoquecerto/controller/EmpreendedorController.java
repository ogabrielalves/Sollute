package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Empreendedor;
import sollute.estoquecerto.entity.ListaObj;
import sollute.estoquecerto.repository.EmpreendedorRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empreendedores")
public class EmpreendedorController {

    @Autowired
    private EmpreendedorRepository repository;

    ListaObj<Empreendedor> listaEmpreendedor = new ListaObj(10);

    // Aqui é criado somente o empreendedor, item essencial para a criação de uma empresa
    @PostMapping
    public ResponseEntity criaEmpreendedor(@RequestBody @Valid Empreendedor empreendedor) {
        listaEmpreendedor.adiciona(empreendedor);   // Salva localmente na ListaObj
        repository.save(empreendedor);  // Salva no Banco de Dados
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Empreendedor>> listarEmpreendedores() {
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Empreendedor> getEmpreendedor(@PathVariable long codigo) {
        return ResponseEntity.of(repository.findById(codigo));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity deleteEmpreendedor(@PathVariable long codigo) {
        repository.deleteById(codigo);
        return ResponseEntity.status(200).build();
    }
}
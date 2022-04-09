package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Empreendedor;
import sollute.estoquecerto.entity.ListaObj;
import sollute.estoquecerto.repository.EmpreendedorRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/empreendedores")
public class EmpreendedorController {

    @Autowired
    private EmpreendedorRepository repositoryEmpreendedor;

    ListaObj<Empreendedor> listaEmpreendedor = new ListaObj(10);

    // Aqui é criado somente o empreendedor, item essencial para a criação de uma empresa
    @PostMapping("/criarEmpreendedor")
    public ResponseEntity criaEmpreendedor(@RequestBody @Valid Empreendedor empreendedor) {
        listaEmpreendedor.adiciona(empreendedor);   // Salva localmente na ListaObj
        repositoryEmpreendedor.save(empreendedor);  // Salva no Banco de Dados
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/listarEmpreendedores")
    public ResponseEntity<ListaObj<Empreendedor>> listarEmpreendedores() {
        if (listaEmpreendedor.getTamanho() == 0) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(listaEmpreendedor);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Empreendedor> getEmpreendedor(@PathVariable long codigo) {
        return ResponseEntity.of(repositoryEmpreendedor.findById(codigo));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity deleteEmpreendedor(@PathVariable long codigo) {
        repositoryEmpreendedor.deleteById(codigo);
        return ResponseEntity.status(200).build();
    }
}
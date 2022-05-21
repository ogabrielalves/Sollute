package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Fornecedor;
import sollute.estoquecerto.entity.Funcionario;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByfkEmpresaIdEmpresa(Integer idEmpresa);

    @Transactional
    @Modifying
    @Query("update Funcionario f " +
            "set f.nomeFuncionario = ?1, f.telefoneFuncionario = ?2, f.cpfFuncionario = ?3, f.salarioFuncionario = ?4 " +
            "where f.fkEmpresa.idEmpresa = ?5 and f.idFuncionario = ?6")
    boolean atualizarFuncionario(String nomeFuncionario,        // -> ?1
                                 String telefoneFuncionario,     // -> ?2
                                 String cpfFuncionario,          // -> ?3
                                 Double salarioFuncionario,      // -> ?4
                                 Integer idEmpresa,              // -> ?5
                                 Long idFuncionario);            // -> ?6
}

package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Funcionario;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByfkEmpresaIdEmpresa(Integer idEmpresa);
}

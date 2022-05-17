package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}

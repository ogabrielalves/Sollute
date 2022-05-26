package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
}

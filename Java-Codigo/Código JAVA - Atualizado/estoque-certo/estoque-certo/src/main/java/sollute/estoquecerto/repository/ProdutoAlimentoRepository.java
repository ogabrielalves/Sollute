package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.ProdutoAlimento;

public interface ProdutoAlimentoRepository extends JpaRepository<ProdutoAlimento, Long> {
}

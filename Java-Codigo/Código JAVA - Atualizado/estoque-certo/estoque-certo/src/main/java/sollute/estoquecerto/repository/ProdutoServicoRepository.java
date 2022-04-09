package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.ProdutoServico;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Long> {
}

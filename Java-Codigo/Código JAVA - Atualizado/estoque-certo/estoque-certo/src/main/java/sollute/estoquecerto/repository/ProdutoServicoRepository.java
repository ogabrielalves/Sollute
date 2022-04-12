package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.ProdutoAlimento;
import sollute.estoquecerto.entity.ProdutoServico;

import java.util.List;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Long> {
    List<ProdutoServico> findByCnpj(String cnpj);
}

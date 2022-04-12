package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.ProdutoServico;
import sollute.estoquecerto.entity.ProdutoVestuario;

import java.util.List;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Long> {

    List<ProdutoServico> findByCnpj(String cnpj);
}

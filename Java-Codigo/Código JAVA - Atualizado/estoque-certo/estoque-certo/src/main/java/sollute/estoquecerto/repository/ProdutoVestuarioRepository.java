package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.ProdutoVestuario;

import java.util.List;

public interface ProdutoVestuarioRepository extends JpaRepository<ProdutoVestuario, Long> {

    List<ProdutoVestuario> findByCnpj(String cnpj);

    List<ProdutoVestuario> findByCodigo(String codigo);

    void deleteByCodigo(String codigo);
}

package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    void deleteByCodigo(String codigo);
}

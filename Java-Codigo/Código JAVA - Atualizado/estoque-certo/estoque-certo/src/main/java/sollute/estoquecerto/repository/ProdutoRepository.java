package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> findByCnpj(String cnpj);
}

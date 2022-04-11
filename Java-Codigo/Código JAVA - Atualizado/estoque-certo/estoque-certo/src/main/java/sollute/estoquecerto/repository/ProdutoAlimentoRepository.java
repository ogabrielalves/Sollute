package sollute.estoquecerto.repository;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sollute.estoquecerto.entity.ProdutoAlimento;

import java.util.List;

public interface ProdutoAlimentoRepository extends JpaRepository<ProdutoAlimento, Long> {

}

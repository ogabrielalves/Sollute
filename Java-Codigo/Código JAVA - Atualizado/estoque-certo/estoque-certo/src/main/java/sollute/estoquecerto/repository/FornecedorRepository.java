package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}

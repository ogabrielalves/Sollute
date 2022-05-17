package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

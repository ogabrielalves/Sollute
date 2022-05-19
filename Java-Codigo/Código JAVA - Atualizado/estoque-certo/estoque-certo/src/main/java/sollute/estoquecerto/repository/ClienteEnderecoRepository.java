package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.ClienteEndereco;

public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Integer> {
}

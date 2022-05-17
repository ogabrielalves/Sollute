package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

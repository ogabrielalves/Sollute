package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Empreendedor;

public interface EmpreendedorRepository extends JpaRepository<Empreendedor, Long> {
}

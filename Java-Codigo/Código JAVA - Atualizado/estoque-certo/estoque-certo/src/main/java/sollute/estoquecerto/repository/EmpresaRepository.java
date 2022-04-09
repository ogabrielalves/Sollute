package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}

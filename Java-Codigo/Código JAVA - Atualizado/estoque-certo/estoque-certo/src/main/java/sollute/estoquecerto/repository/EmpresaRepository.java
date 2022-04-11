package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sollute.estoquecerto.entity.Empresa;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Empresa findByIdEmpresa(Long idEmpresa);


}

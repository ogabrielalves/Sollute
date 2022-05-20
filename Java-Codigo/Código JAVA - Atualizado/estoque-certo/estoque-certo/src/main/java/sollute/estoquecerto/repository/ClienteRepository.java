package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Cliente;
import sollute.estoquecerto.entity.Funcionario;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByfkEmpresaIdEmpresa(Integer idEmpresa);
}

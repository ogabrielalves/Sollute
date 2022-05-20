package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Fornecedor;
import sollute.estoquecerto.entity.Funcionario;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    List<Fornecedor> findByfkEmpresaIdEmpresa(Integer idEmpresa);
}

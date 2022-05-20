package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Fornecedor;
import sollute.estoquecerto.entity.Funcionario;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    boolean existsByIdFornecedorAndFkEmpresaIdEmpresa(Long idFornecedor, Integer idEmpresa);

    @Transactional
    void deleteFornecedorByIdFornecedor(Long idFornecedor);

    List<Fornecedor> findByfkEmpresaIdEmpresa(Integer idEmpresa);
}

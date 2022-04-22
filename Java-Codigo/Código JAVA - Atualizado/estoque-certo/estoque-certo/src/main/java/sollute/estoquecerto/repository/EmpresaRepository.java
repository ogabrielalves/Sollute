package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Empresa;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Empresa findByIdEmpresa(Long idEmpresa);
    boolean existsByCnpj(String cnpj);

    @Transactional
    @Modifying
    @Query("update Empresa u set u.autenticado = ?2 where u.login = ?1")
    void atualizarAutenticado(String codigo, boolean autenticado);
}

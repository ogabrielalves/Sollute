package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sollute.estoquecerto.entity.Empresa;
import sollute.estoquecerto.entity.Produto;

import java.nio.file.Files;
import java.util.List;

<<<<<<<< HEAD:Java-Codigo/estoque-certo/src/main/java/sollute/estoquecerto/repository/EmpresaRepository.java
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Empresa findByCnpj(String cnpj);
========
@SuppressWarnings("ALL")
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
>>>>>>>> 6ebf1b9a13b7ffd207d44c5b28af6909a5be54bf:Java-Codigo/estoque-certo/estoque-certo/src/main/java/sollute/estoquecerto/repository/EmpresaRepository.java

    boolean existsByCnpj(String cnpj);

    @Transactional
    @Modifying
    @Query("update Empresa u set u.autenticado = ?2 where u.email = ?1")
    void atualizarAutenticado(String codigo, boolean autenticado);

    @Transactional
    @Modifying
    @Query("update Empresa e set e.fileName = ?1 where e.cnpj = ?2")
    int patchArquivo(byte[] fileName, String cnpj);

    @Query("select e.fileName from Empresa e where e.cnpj = ?1")
    byte[] getFoto(String cnpj);
}

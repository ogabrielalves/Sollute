package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByCodigo(String codigo);

    List<Produto> findByFkEmpresaIdEmpresa(Integer idEmpresa);

    @Transactional
    void deleteProdutoByCodigo(String codigo);

    @Transactional
    @Modifying
    @Query("update Produto p set p.qtdVendidos = ?2 where p.codigo = ?1")
    int atualizarQtdVendida(String codigo, Integer qtdVendidos);

}
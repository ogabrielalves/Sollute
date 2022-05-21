package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findByCodigoAndEmpresaIdEmpresa(String codigo, Integer idEmpresa);

    List<Produto> findByEmpresaIdEmpresa(Integer idEmpresa);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigo(Integer codigo);

    boolean findByEstoqueLessThanEqual(int estoqueMin);

    @Transactional
    void deleteProdutoByIdProduto(Integer idProduto);

    @Transactional
    @Modifying
    @Query("update Produto p set p.qtdVendidos = ?2 where p.codigo = ?1")
    void atualizarQtdVendida(Integer codigo, Integer qtdVendidos);

}
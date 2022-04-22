package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    Produto findByIdProduto(Integer idProduto);
    List<Produto> findByFkEmpresa(Integer fkEmpresa);
    boolean existsByCodigo(Integer codigo);
    boolean findByQtdVendidosIsGreaterThan(int qtd);

    // Atualiza a quantidade de produtos vendidos pelo produto
    @Transactional
    @Modifying
    @Query("update Produto p set p.qtdVendidos = ?2 where p.codigo = ?1")
    void atualizarQtdVendida(Integer codigo, Integer qtdVendidos);

}
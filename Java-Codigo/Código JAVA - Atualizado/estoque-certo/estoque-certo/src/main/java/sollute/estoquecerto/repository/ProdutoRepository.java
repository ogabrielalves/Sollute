package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    // Retorna um Produto completo, é utilizado para posteriormente apagar pelo getIdProduto() no endpoint DELETE
    Produto findByCodigoAndFkEmpresa(String codigo, Integer fkEmpresa);

    // Apaga do banco de dados um produto
    @Transactional
    void deleteProdutoByIdProduto(Integer idProduto);

    boolean existsByCodigo(String codigo);
    // Retorna uma lista de PRODUTO pelo fk da empresa
    List<Produto> findByFkEmpresa(Integer fkEmpresa);

    // Verifica se o produto existe ou não!
    boolean existsByCodigo(Integer codigo);

    boolean findByEstoqueInicialLessThanEqual(int estoqueMin);

    // Atualiza a quantidade de produtos vendidos pelo produto
    @Transactional
    @Modifying
    @Query("update Produto p set p.qtdVendidos = ?2 where p.codigo = ?1")
    void atualizarQtdVendida(Integer codigo, Integer qtdVendidos);

    // Atualiza a boolean alerta para true
//    @Transactional
//    @Modifying
//    @Query("update Produto p set p.alerta = ?2 where p.codigo = ?1")
//    void atualizarAlerta(Integer codigo, Boolean alerta);
}
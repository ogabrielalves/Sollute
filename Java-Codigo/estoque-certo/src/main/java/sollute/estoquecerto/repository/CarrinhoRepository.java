package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sollute.estoquecerto.entity.Carrinho;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

    List<Carrinho> findByFkEmpresaIdEmpresa(Integer idEmpresa);

}

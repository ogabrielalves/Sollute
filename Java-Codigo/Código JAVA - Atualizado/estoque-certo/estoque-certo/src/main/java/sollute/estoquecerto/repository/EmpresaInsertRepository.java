package sollute.estoquecerto.repository;

import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.response.NovaEmpresaResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EmpresaInsertRepository {

    @PersistenceContext
    EntityManager entityManager = null;

    @Transactional
    public void insertEmpresa(String email,
                              String senha,
                              String nomeFantasia,
                              String razaoSocial,
                              String cnpj,
                              Integer qtdProdutosVendidos,
                              Double totalProdutosVendidos,
                              Boolean autenticado) {
        entityManager.createNativeQuery("" +
                        "insert into empresa (email, senha, nome_fantasia, razao_social, cnpj) values" +
                        "(?,?,?,?,?,?,?,?)")
                .setParameter(1, email)
                .setParameter(2, senha)
                .setParameter(3, nomeFantasia)
                .setParameter(4, razaoSocial)
                .setParameter(5, cnpj)
                .setParameter(6, qtdProdutosVendidos)
                .setParameter(7, totalProdutosVendidos)
                .setParameter(8, autenticado);
    }
}

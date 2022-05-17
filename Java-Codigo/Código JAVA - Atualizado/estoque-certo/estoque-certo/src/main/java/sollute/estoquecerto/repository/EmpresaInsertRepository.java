package sollute.estoquecerto.repository;

import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.response.NovaEmpresaResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EmpresaInsertRepository {

    @PersistenceContext
    EntityManager entityManager = null;

    @Transactional
    public void insertEmpresa(NovaEmpresaResponse novaEmpresa) {
        entityManager.createNativeQuery("insert into empresa (email, senha, nome_fantasia, razao_social, cnpj) values" +
                        "(?,?,?,?,?)")
                .setParameter(1, novaEmpresa.getEmail())
                .setParameter(2, novaEmpresa.getSenha())
                .setParameter(3, novaEmpresa.getNomeFantasia())
                .setParameter(4, novaEmpresa.getRazaoSocial())
                .setParameter(5, novaEmpresa.getCnpj());
    }
}

package sollute.estoquecerto.repository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EnderecoInsertRepository {

    @PersistenceContext
    EntityManager entityManager = null;

    @Transactional
    public void insertEndereco(Integer fkEmpresa,
                               String logradouro,
                               String cep,
                               String uf,
                               String cidade,
                               String pontoReferencia) {
        entityManager.createNativeQuery(
                        "insert into endereco (fkEmpresa, logradouro, cep, uf, cidade, pontoReferencia) " +
                           "values (?,?,?,?,?,?)")
                .setParameter(1, fkEmpresa)
                .setParameter(2, logradouro)
                .setParameter(3, cep)
                .setParameter(4, uf)
                .setParameter(5, cidade)
                .setParameter(6, pontoReferencia);
    }
}
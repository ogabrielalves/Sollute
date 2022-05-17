package sollute.estoquecerto.response;

import org.springframework.web.bind.annotation.PatchMapping;
import sollute.estoquecerto.entity.Endereco;

import javax.persistence.ManyToOne;

public class NovaEmpresaResponse {

    private String email;
    private String senha;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;

    public NovaEmpresaResponse(String email, String senha, String nomeFantasia, String razaoSocial, String cnpj) {
        this.email = email;
        this.senha = senha;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }
}

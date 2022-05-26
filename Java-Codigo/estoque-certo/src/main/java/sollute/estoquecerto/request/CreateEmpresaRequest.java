package sollute.estoquecerto.request;

public class CreateEmpresaRequest {

    private String email;
    private String senha;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Integer qtdProdutosVendidos;
    private Double totalProdutosVendidos;
    private Boolean autenticado;

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

    public Integer getQtdProdutosVendidos() {
        return qtdProdutosVendidos;
    }

    public Double getTotalProdutosVendidos() {
        return totalProdutosVendidos;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

}

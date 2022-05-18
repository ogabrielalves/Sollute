package sollute.estoquecerto.response;

public class EnderecoEmpresaResponse {

    private String email;
    private String senha;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Integer qtdProdutosVendidos;
    private Double totalProdutosVendidos;
    private Boolean autenticado;
    private Integer fkEmpresa;
    private String logradouro;
    private String cep;
    private String uf;
    private String cidade;
    private String pontoReferencia;

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

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public String getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }
}

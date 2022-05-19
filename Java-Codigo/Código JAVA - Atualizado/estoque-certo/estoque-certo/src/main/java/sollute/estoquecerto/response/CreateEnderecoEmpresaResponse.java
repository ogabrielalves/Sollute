package sollute.estoquecerto.response;

public class CreateEnderecoEmpresaResponse {

    private String cep;
    private String logradouro;
    private String uf;
    private String cidade;
    private String pontoReferencia;

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
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

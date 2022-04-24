package sollute.estoquecerto.request;

public class ProdutoLoginResponse {
    private Long idEmpresa;
    private Integer codigo;
    private Integer qtdVendida;
    private Integer estoqueInicial;

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Integer getQtdVendida() {
        return qtdVendida;
    }

    public Integer getEstoqueInicial() {
        return estoqueInicial;
    }
}

package sollute.estoquecerto.request;

public class ProdutoResponse {

    private Long idEmpresa;
    private Integer codigo;
    private Integer qtdVendida;


    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(Integer qtdVendida) {
        this.qtdVendida = qtdVendida;
    }
}

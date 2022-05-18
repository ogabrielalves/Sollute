package sollute.estoquecerto.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @Column(name = "id_caixa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaixa;

    @NotNull
    // @Column(name = "fk_empresa")
    @ManyToOne
    private Empresa fkEmpresa;

    @PositiveOrZero
    private Double valor;

    @PositiveOrZero
    @Column(name = "qtd_entradas")
    private Integer qtdEntradas;

    @PositiveOrZero
    @Column(name = "qtd_saidas")
    private Integer qtdsaidas;

    @PositiveOrZero
    @Column(name = "valor_entradas")
    private Integer valorEntradas;

    @PositiveOrZero
    @Column(name = "valor_saidas")
    private Integer valorSaidas;

    public Long getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Long idCaixa) {
        this.idCaixa = idCaixa;
    }

    public Empresa getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Empresa fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQtdEntradas() {
        return qtdEntradas;
    }

    public void setQtdEntradas(Integer qtdEntradas) {
        this.qtdEntradas = qtdEntradas;
    }

    public Integer getQtdsaidas() {
        return qtdsaidas;
    }

    public void setQtdsaidas(Integer qtdsaidas) {
        this.qtdsaidas = qtdsaidas;
    }

    public Integer getValorEntradas() {
        return valorEntradas;
    }

    public void setValorEntradas(Integer valorEntradas) {
        this.valorEntradas = valorEntradas;
    }

    public Integer getValorSaidas() {
        return valorSaidas;
    }

    public void setValorSaidas(Integer valorSaidas) {
        this.valorSaidas = valorSaidas;
    }
}

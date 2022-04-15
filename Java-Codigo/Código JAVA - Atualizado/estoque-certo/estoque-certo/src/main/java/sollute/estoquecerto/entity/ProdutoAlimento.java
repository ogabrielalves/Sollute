package sollute.estoquecerto.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class ProdutoAlimento extends Produto {

    //Atributos
    @NotBlank
    private String tipoAlimento;
    @NotNull
    private LocalDate dataFabricacao;
    @NotNull
    private LocalDate dataValidade;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    String dataFabricacaoFormatada = dataFabricacao.format(formatter);
//    String dataValidadeFormatada = dataValidade.format(formatter);


    //Metodos
    @Override
    public Boolean vender(int i) {
        if ((getEstoqueInicial() - i) < 0) {
            System.out.println("Estoque insuficiente");
            return false;
        } else {
            setEstoqueInicial(getEstoqueInicial() - i);
            super.setQtdVendidos(getQtdVendidos() + i);
            super.setValorVendidos(super.getPrecoVenda() * i);
            super.setPrecoVenda(super.getPrecoVenda()*1);
            return true;
        }
    }


    // Getters e Setters
    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

}
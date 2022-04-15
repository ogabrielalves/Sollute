package sollute.estoquecerto.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class ProdutoVestuario extends Produto {

    //Atributos
    @NotBlank
    private String tipoVestuario;

    @NotBlank
    private String tamanho;


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
    public String getTipoVestuario() {
        return tipoVestuario;
    }

    public void setTipoVestuario(String tipoVestuario) {
        this.tipoVestuario = tipoVestuario;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
}
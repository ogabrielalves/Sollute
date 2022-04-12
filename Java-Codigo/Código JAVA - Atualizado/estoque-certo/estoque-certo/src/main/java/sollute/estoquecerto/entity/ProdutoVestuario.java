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
        if ((getQtdEstoque() - i) < 0) {
            System.out.println("Estoque insuficiente");
            return false;
        } else {
            setQtdEstoque(getQtdEstoque() - i);
            super.setQtdVendidos(getQtdVendidos() + i);
            super.setValorVendidos(super.getPreco() * i);
            super.setPreco(super.getPreco()*1);
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format("" +
                        "%6s %20s %9s %7s %7s %6s %10s %5s %10s %15s %8s" +
                        "%06d %-20s %7.2f %7d %7d %4.2f %10s %4.1f %10s %15s %8s",
                // Cabeçalho
                "ID",
                "Nome do Produto",
                "Preco",
                "Estoque",
                "Vendidos",
                "Valor dos Vendidos",
                "Marca",
                "Peso",
                "Categoria",
                "Tipo de Vestuario",
                "Tamanho",
                // Corpo
                super.getCodProduto(),
                super.getNome(),
                super.getPreco(),
                super.getQtdEstoque(),
                super.getQtdVendidos(),
                super.getValorVendidos(),
                super.getMarca(),
                super.getPreco(),
                super.getCategoria(),
                tipoVestuario,
                tamanho);
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
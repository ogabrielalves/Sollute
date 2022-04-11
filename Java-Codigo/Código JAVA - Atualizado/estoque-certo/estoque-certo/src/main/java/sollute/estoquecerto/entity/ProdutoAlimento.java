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
                        "%6s %20s %9s %7s %7s %6s %10s %5s %10s %15s %8s %15s %15s" +
                        "%06d %-20s %7.2f %7d %7d %4.2f %10s %4.1f %10s %15s %15s %15s",
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
                "Tipo de Alimento",
                "Tamanho",
                "Fabricação",
                "Validade",
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
                tipoAlimento,
                dataFabricacao,
                dataValidade);
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
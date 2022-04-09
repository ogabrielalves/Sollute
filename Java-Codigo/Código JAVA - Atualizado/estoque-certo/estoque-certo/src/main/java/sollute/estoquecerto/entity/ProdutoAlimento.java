package sollute.estoquecerto.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoAlimento extends Produto {

    //Atributos
    private String tipoAlimento;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dataFabricacaoFormatada = dataFabricacao.format(formatter);
    String dataValidadeFormatada = dataValidade.format(formatter);

    //Construtores
    public ProdutoAlimento(Long codProduto,
                           String nome,
                           double preco,
                           Integer qtdEstoque,
                           Integer qtdVendidos,
                           Double valorVendidos,
                           String marca,
                           Double peso,
                           Character categoria,
                           String tipoAlimento,
                           LocalDate dataFabricacao,
                           LocalDate dataValidade) {
        super(codProduto, nome, preco, qtdEstoque, qtdVendidos, valorVendidos, marca, peso, categoria);
        this.tipoAlimento = tipoAlimento;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
    }

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
                super.pegaCategoria(),
                tipoAlimento,
                dataFabricacaoFormatada,
                dataValidadeFormatada);
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

    public String getDataFabricacaoFormatada() {
        return dataFabricacaoFormatada;
    }

    public void setDataFabricacaoFormatada(String dataFabricacaoFormatada) {
        this.dataFabricacaoFormatada = dataFabricacaoFormatada;
    }

    public String getDataValidadeFormatada() {
        return dataValidadeFormatada;
    }

    public void setDataValidadeFormatada(String dataValidadeFormatada) {
        this.dataValidadeFormatada = dataValidadeFormatada;
    }
}
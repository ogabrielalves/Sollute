package project.sollute.entity;

public abstract class Produto {

    //Atributos
    private String nome;
    private double preco;
    private Integer qtdEstoque;
    private Integer qtdVendidos;
    private Double valorVendidos;

    //Construtor
    public Produto(String nome,
                   double preco,
                   Integer qtdEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        qtdVendidos = 0;
        valorVendidos = 0.0;
    }

    public Produto(String nome,
                   double preco) {
        this.nome = nome;
        this.preco = preco;
        qtdVendidos = 0;
        valorVendidos = 0.0;
    }

    //Metodos
    public abstract double vender(int i);

    @Override
    public String toString() {
        return String.format("\n" +
                        "PRODUTOS: \n" +
                        "Nome do Produto: %s\n" +
                        "Preço: R$%.2f\n" +
                        "Quantidade em Estoque: %d\n" +
                        "Quantidade vendidas: %d\n" +
                        "Valor total das vendas: R$%.2f",
                nome,
                preco,
                qtdEstoque,
                qtdVendidos,
                valorVendidos);
    }

    //Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdVendidos() {
        return qtdVendidos;
    }

    public void setQtdVendidos(Integer qtdVendidos) {
        this.qtdVendidos = qtdVendidos;
    }

    public Double getValorVendidos() {
        return valorVendidos;
    }

    public void setValorVendidos(Double valorVendidos) {
        this.valorVendidos = valorVendidos;
    }
}
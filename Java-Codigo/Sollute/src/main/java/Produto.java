public class Produto {

    //Atributos
    private String nome;
    private double preco;
    private Integer qtdEstoque;


    //Construtor
    public Produto(String nome,
                   double preco,
                   Integer qtdEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    //Metodos
    public double vender() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("\n" +
                        "PRODUTOS: \n" +
                        "Nome do Produto: %s\n" +
                        "Preço: R$%.2f\n" +
                        "Quantidade em Estoque: %d"
                , nome, preco, qtdEstoque);
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
}

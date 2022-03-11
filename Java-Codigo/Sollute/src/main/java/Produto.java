public class Produto {
    //Atributos
    private String nome;
    private double preco;


    //Construtor
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
        return String.format("\nPRODUTOS: \n" +
                        "Nome do Produto: %s\n" +
                        "Preço: %.2f\n" +
                        "Quantidade Estoque: %d\n"
                , nome, preco);
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


}

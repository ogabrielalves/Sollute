public class ProdutoVestuario extends Produto {
    //Atributos
    private String tipoVestuario;
    private String marca;
    private String tamanho;

    //Construtores
    public ProdutoVestuario(String nome,
                            double preco,
                            Integer qtdEstoque,
                            String tipoVestuario,
                            String marca,
                            String tamanho) {
        super(nome, preco, qtdEstoque);
        this.tipoVestuario = tipoVestuario;
        this.marca = marca;
        this.tamanho = tamanho;
    }

    //Metodos
    @Override
    public double vender() {
        return super.vender();
    }

    @Override
    public String toString() {
        return String.format("\n" +
                "PRODUTOS_VESTUARIO: \n" +
                "Nome do Produto: %s\n" +
                "Preço: %.2f\n" +
                "Quantidade em Estoque: %d\n" +
                "Tipo de Vestuario: %s\n" +
                "Marca: %s\n" +
                "Tamanho: %s", super.getNome(), super.getPreco(), super.getQtdEstoque(), tipoVestuario, marca, tamanho);
    }

}

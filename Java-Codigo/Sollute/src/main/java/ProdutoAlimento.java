public class ProdutoAlimento extends Produto {
    //Atributos
    private String tipoAlimento;
    private String dataFabricacao;
    private String dataValidade;

    //Construtores
    public ProdutoAlimento(String nome, double preco, String tipoAlimento, String dataFabricacao, String dataValidade) {
        super(nome, preco);
        this.tipoAlimento = tipoAlimento;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
    }

    //Metodos
    @Override
    public double vender() {
        return super.vender();
    }

    @Override
    public String toString() {
        return String.format("\nPRODUTOS_ALIMENTOS: \n" +
                        "Nome do Produto: %s\n" +
                        "Preço: %.2f\n" +
                        "Tipo de Alimento: %s\n" +
                        "Data de fabricação: %s\n" +
                        "Data de validade: %s\n"
                , super.getNome(), super.getPreco(), tipoAlimento, dataFabricacao, dataValidade);
    }
}

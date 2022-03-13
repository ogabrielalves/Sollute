package project.sollute.entity;

public class ProdutoAlimento extends Produto {

    //Atributos
    private String tipoAlimento;
    private String dataFabricacao;
    private String dataValidade;

    //Construtores
    public ProdutoAlimento(String nome,
                           double preco,
                           Integer qtdEstoque,
                           String tipoAlimento,
                           String dataFabricacao,
                           String dataValidade) {
        super(nome, preco, qtdEstoque);
        this.tipoAlimento = tipoAlimento;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
    }

    //Metodos
    @Override
    public double vender(int i) {
        if ((getQtdEstoque() - i) < 0) {
            System.out.println("Estoque insuficiente");
            return 0.0;
        } else {
            setQtdEstoque(getQtdEstoque() - i);
            super.setQtdVendidos(getQtdVendidos() + i);
            super.setValorVendidos(super.getPreco() * i);
            return super.getPreco() * i;
        }
    }

    @Override
    public String toString() {
        return String.format("\n" +
                        "PRODUTOS_ALIMENTOS: \n" +
                        "Nome do Produto: %s\n" +
                        "Preço: %.2f\n" +
                        "Quantidade em Estoque: %d\n" +
                        "Tipo de Alimento: %s\n" +
                        "Data de fabricação: %s\n" +
                        "Data de validade: %s",
                super.getNome(),
                super.getPreco(),
                super.getQtdEstoque(),
                tipoAlimento,
                dataFabricacao,
                dataValidade);
    }
}
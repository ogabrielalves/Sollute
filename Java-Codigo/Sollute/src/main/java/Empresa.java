import java.util.ArrayList;
import java.util.List;

public class Empresa {

    //Atributos
    private List<Produto> produtos;
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private Empreendedor ceo;
    private Integer qtdProdutosVendidos;
    private Double totalProdutosVendidos;

    //Construtor
    public Empresa(
            String nomeFantasia,
            String cnpj,
            String inscricaoEstadual,
            Empreendedor ceo) {
        produtos = new ArrayList<>();
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.ceo = ceo;
        qtdProdutosVendidos = 0;
        totalProdutosVendidos = 0.0;
    }

    //Metodos
    public void adicionaProduto(Produto p) {
        produtos.add(p);
        System.out.printf("\nProduto %s adicionado com sucesso!", p.getNome());
    }

    public void mostraProduto() {
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    public int calculaTotalProdutosVendidos() {
        for (Produto p : produtos) {
            qtdProdutosVendidos += p.getQtdVendidos();
        }
        return qtdProdutosVendidos;
    }

    public double calculaValorProdutosVendidos() {
        for (Produto p : produtos) {
            totalProdutosVendidos += p.getValorVendidos();
        }
        return totalProdutosVendidos;
    }

    // toString()
    @Override
    public String toString() {
        return String.format("\n" +
                        "EMPRESA: \n" +
                        "Nome Fantasia: %s\n" +
                        "CNPJ: %s\n" +
                        "CEO: %s\n" +
                        "Inscrição Estadual: %s\n" +
                        "Quantidade de Produtos Vendidos: %d\n" +
                        "Valor Total Dos Produtos Vendidos: R$%.2f",
                nomeFantasia,
                cnpj,
                ceo.getNome(), // Pegando somente o nome, não é necessário pegar o CPF do ceo.
                inscricaoEstadual,
                qtdProdutosVendidos,
                totalProdutosVendidos);
    }
}
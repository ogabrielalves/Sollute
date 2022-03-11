import java.util.ArrayList;
import java.util.List;

public class Empresa {
    //Atributos
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private Empreendedor ceo;
    private List<Produto> produtos;

    //Construtor
    public Empresa(String nomeFantasia,
                   String cnpj,
                   String inscricaoEstadual,
                   Empreendedor ceo) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.ceo = ceo;
        produtos = new ArrayList<>();
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

    @Override
    public String toString() {
        return String.format("\nEMPRESA: \n" +
                "Nome Fantasia: %s\n" +
                "CNPJ: %s\n" +
                "CEO: %s\n" +
                "Inscrição Estadual: %s\n", nomeFantasia, cnpj, ceo.getNome(),inscricaoEstadual);
    }
}

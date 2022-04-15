package sollute.estoquecerto.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Empresa {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @NotBlank
    @Length(min = 3, max = 45)
    private String nomeFantasia;

    private String cnpj;

    private String inscricaoEstadual;
    @CPF
    private String cpfEmpresario;

    @Min(0)
    @Max(0)
    private int qtdProdutosVendidos;

    @Min(0)
    @Max(0)
    private double totalProdutosVendidos;

    //Construtor
    public Empresa(
            String nomeFantasia,
            String cnpj,
            String inscricaoEstadual,
            String cpfEmpresario) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.cpfEmpresario = cpfEmpresario;
        qtdProdutosVendidos = 0;
        totalProdutosVendidos = 0.0;
    }

    public Empresa() {

    }

    public Empresa(Empresa byIdEmpresa) {
    }

    // Metodos
    public void venderProduto(ListaObj<Empreendedor> listaE,
                              String cnpj,
                              ListaObj<Produto> lista,
                              Produto p,
                              Integer qtd) {
        boolean vendido = p.vender(qtd);
        if (vendido) {
            ListaObj<Produto> produtosVerificados = verificaStatus(listaE, cnpj, lista);
            if (produtosVerificados.getTamanho() > 0) {
                //notificarTodos(listaE, cnpj, produtosVerificados);
                notificarTodos(listaE, produtosVerificados);
            }
        }
    }

    public ListaObj<Produto> verificaStatus(ListaObj<Empreendedor> listaE,
                                            String cnpj,
                                            ListaObj<Produto> lista) {

        LocalDate data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-7{dd/MM/yyyy}");
        String dataFormatada = data.format(formatter);

        if (lista.getTamanho() == 0) {
            return null;
        }

        ListaObj<Produto> produtosEmAlerta = new ListaObj<>(lista.getTamanho());

        for (int i = 0; i < lista.getTamanho(); i++) {
            // Verificando se está com baixa quantidade em estoque
            // No caso, menor ou igual a 3
            if (lista.getElemento(i).getEstoqueInicial() <= 3) {
                produtosEmAlerta.adiciona(lista.getElemento(i));
            }
            // veficando se o alimento está vencendo...
            if (lista.getElemento(i) instanceof ProdutoAlimento) {
                if (lista.getElemento(i).equals(dataFormatada)) {
                    produtosEmAlerta.adiciona(lista.getElemento(i));
                }
            }
        }
        return produtosEmAlerta;
    }

    public void notificarTodos(ListaObj<Empreendedor> listaE,
                               // String cnpj,
                               ListaObj<Produto> lista) {
        for (int i = 0; i < lista.getTamanho(); i++) {
            System.out.printf("\nO produto %s necessita de atenção", lista.getElemento(i).getNome());
        }
    }

    public int calculaTotalProdutosVendidos(ListaObj<Produto> lista) {
        for (int i = 0; i < lista.getTamanho(); i++) {
            qtdProdutosVendidos += lista.getElemento(i).getQtdVendidos();
        }
        return qtdProdutosVendidos;
    }

    public double calculaValorProdutosVendidos(ListaObj<Produto> lista) {
        for (int i = 0; i < lista.getTamanho(); i++) {
            totalProdutosVendidos += lista.getElemento(i).getQtdVendidos();
        }
        return totalProdutosVendidos;
    }

    // toString()
    @Override
    public String toString() {
        return String.format("" +
                        "%6s %25s %15s %10s %20s %20s %15s" +
                        "%06d %-25s %15s %10s %20s %5d %4.1f",
                // Cabeçalho
                "ID",
                "Nome da Empresa",
                "CNPJ",
                "Empreendedor",
                "Inscrição Estadual",
                "Produtos Vendidos",
                "Valor Vendidos",
                // Corpo
                idEmpresa,
                nomeFantasia,
                cnpj,
                // empresario.getNome(),
                inscricaoEstadual,
                qtdProdutosVendidos,
                totalProdutosVendidos);
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public int getQtdProdutosVendidos() {
        return qtdProdutosVendidos;
    }

    public void setQtdProdutosVendidos(int qtdProdutosVendidos) {
        this.qtdProdutosVendidos = qtdProdutosVendidos;
    }

    public double getTotalProdutosVendidos() {
        return totalProdutosVendidos;
    }

    public void setTotalProdutosVendidos(double totalProdutosVendidos) {
        this.totalProdutosVendidos = totalProdutosVendidos;
    }

    public String getCpfEmpresario() {
        return cpfEmpresario;
    }

    public void setCpfEmpresario(String cpfEmpresario) {
        this.cpfEmpresario = cpfEmpresario;
    }
}
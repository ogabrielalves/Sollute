package sollute.estoquecerto.entity;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    //Atributos
    private Long idEmpresa;
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private Empreendedor empresario;
    private int qtdProdutosVendidos;
    private double totalProdutosVendidos;
    // private List<Produto> produtos; -> Isso não é necessário

    //Construtor
    public Empresa(
            String nomeFantasia,
            String cnpj,
            String inscricaoEstadual,
            Empreendedor empresario) {
        // ListaObj produtos = new ListaObj<Produto>(20);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.empresario = empresario;
        qtdProdutosVendidos = 0;
        totalProdutosVendidos = 0.0;
    }

    //Metodos
    // FALTA COMPLETAR ESSES CÓDIGOS
    public void venderProduto(Produto p) {

    }

    public List verificaStatus() {
        return null;
    }

    public void notificarTodos(ListaObj listaObj) {

    }

    public int calculaTotalProdutosVendidos() {
        for (Produto p : produtos) {
            qtdProdutosVendidos += p.getValorVendidos();
        }
        return qtdProdutosVendidos;
    }

    public double calculaValorProdutosVendidos() {
        for (Produto p : produtos) {
            totalProdutosVendidos += (p.getQtdVendidos() * p.getPreco());
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
                empresario.getNome(),
                inscricaoEstadual,
                qtdProdutosVendidos,
                calculaValorProdutosVendidos());
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

    public Empreendedor getEmpresario() {
        return empresario;
    }

    public void setEmpresario(Empreendedor empresario) {
        this.empresario = empresario;
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
}
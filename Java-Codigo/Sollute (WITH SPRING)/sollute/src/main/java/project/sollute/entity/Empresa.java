package project.sollute.entity;

import project.sollute.controller.EmpresasController;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    //Atributos
    private List<Produto> produtos;
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private Empreendedor ceo;
    private int qtdProdutosVendidos;
    private double totalProdutosVendidos;

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
    }

    public Empresa() {
    }

    //Metodos
    public void adicionaProduto(Produto p) {
        if (p == null) {
            System.out.println("ERRO");
        } else {
            produtos.add(p);
        }
        // System.out.printf("\nProduto %s adicionado com sucesso!", p.getNome());
    }

    public List mostraProduto() {
        List prod = new ArrayList<>();
        for (Produto p : produtos) {
            prod.add(p);
        }
        return prod;
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

    public String getCnpj() {
        return cnpj;
    }

    public Integer getQtdProdutosVendidos() {
        return qtdProdutosVendidos;
    }

    public void setQtdProdutosVendidos(Integer qtdProdutosVendidos) {
        this.qtdProdutosVendidos = qtdProdutosVendidos;
    }

    public void setTotalProdutosVendidos(Double totalProdutosVendidos) {
        this.totalProdutosVendidos = totalProdutosVendidos;
    }
}
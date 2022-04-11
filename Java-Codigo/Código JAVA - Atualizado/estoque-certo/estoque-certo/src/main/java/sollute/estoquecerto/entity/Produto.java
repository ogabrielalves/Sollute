package sollute.estoquecerto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public abstract class Produto {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codProduto;

    @NotBlank
    private String nome;

    @PositiveOrZero
    private Double preco;

    @PositiveOrZero
    private Integer qtdEstoque;

    @PositiveOrZero
    private Integer qtdVendidos;

    @PositiveOrZero
    private Double valorVendidos;

    @NotBlank
    private String marca;

    @Positive
    private Double peso;

    @NotBlank
    private String categoria;

    //Construtor
    public Produto(Long codProduto,
                   String nome,
                   double preco,
                   Integer qtdEstoque,
                   Integer qtdVendidos,
                   Double valorVendidos,
                   String marca,
                   Double peso,
                   String categoria) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.qtdVendidos = qtdVendidos;
        this.valorVendidos = valorVendidos;
        this.marca = marca;
        this.peso = peso;
        this.categoria = categoria;
    }

    public Produto(Long codProduto,
                   String nome,
                   double preco,
                   Integer qtdVendidos,
                   Double valorVendidos,
                   String marca,
                   Double peso,
                   String categoria) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.preco = preco;
        this.qtdVendidos = qtdVendidos;
        this.valorVendidos = valorVendidos;
        this.marca = marca;
        this.peso = peso;
        this.categoria = categoria;
    }

    public Produto() {

    }

    //Metodos
    public abstract Boolean vender(int i);

    @Override
    public String toString() {
        return String.format("" +
                        "%6s %20s %9s %7s %7s %6s %10s %5s %10s" +
                        "%06d %-20s %7.2f %7d %7d %4.2f %10s %4.1f %10s",
                // Cabeçalho
                "ID",
                "Nome do Produto",
                "Preco",
                "Estoque",
                "Vendidos",
                "Valor dos Vendidos",
                "Marca",
                "Peso",
                "Categoria",
                // Corpo
                codProduto,     // %06d
                nome,           // %-20s
                preco,          // %7.2f
                qtdEstoque,     // %7d
                qtdVendidos,    // %7d
                valorVendidos,  // %4.2f
                marca,          // %10s
                peso,           // %4.1f
                categoria);     // %10s
    }

//    public String pegaCategoria() {
//        if (categoria.equals("v")) {
//            return "Vestuário";
//        } else if (categoria.equals("a")) {
//            return "Alimento";
//        } else {
//            return "Serviço";
//        }
//    }

    //Getters and Setters
    public Long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
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

    public Integer getQtdVendidos() {
        return qtdVendidos;
    }

    public void setQtdVendidos(Integer qtdVendidos) {
        this.qtdVendidos = qtdVendidos;
    }

    public Double getValorVendidos() {
        return valorVendidos;
    }

    public void setValorVendidos(Double valorVendidos) {
        this.valorVendidos = valorVendidos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
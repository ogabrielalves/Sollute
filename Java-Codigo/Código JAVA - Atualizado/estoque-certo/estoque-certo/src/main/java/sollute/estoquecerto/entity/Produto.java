package sollute.estoquecerto.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name= "produto")
public class Produto {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto; // Usado como index no banco de dados, o ID do BANCO!!!

    @NotNull
    @Column(name = "fk_empresa")
    private Integer fkEmpresa;

    @NotBlank
    private Integer codigo;     // Usado para identificar com produto pelo codigo que a empresa quer

    @NotBlank
    @Length(min = 2, max = 45)
    private String nome;

    @NotBlank
    @Length(min = 2, max = 45)
    private String marca;

    @NotBlank
    @Length(min = 3, max = 45)
    private String categoria;

    @NotBlank
    private String tamanho;

    @PositiveOrZero
    private Double peso;

    @PositiveOrZero
    @Column(name = "preco_compra")
    private Double precoCompra;

    @PositiveOrZero
    @Column(name = "preco_venda")
    private Double precoVenda;

    @PositiveOrZero
    @Column(name = "estoque_inicial")
    private Integer estoqueInicial;

    @PositiveOrZero
    @Column(name = "estoque_min")
    private Integer estoqueMin;

    @Positive
    @Column(name = "estoque_max")
    private Integer estoqueMax;

    @PositiveOrZero
    @Column(name = "qtd_vendidos")
    private Integer qtdVendidos;

    @PositiveOrZero
    @Column(name = "valor_vendidos")
    private Double valorVendidos;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getEstoqueInicial() {
        return estoqueInicial;
    }

    public void setEstoqueInicial(Integer estoqueInicial) {
        this.estoqueInicial = estoqueInicial;
    }

    public Integer getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(Integer estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public Integer getEstoqueMax() {
        return estoqueMax;
    }

    public void setEstoqueMax(Integer estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    public Integer getQtdVendidos() {
        return qtdVendidos;
    }

    public void setQtdVendidos(int qtdVendidos) {
        this.qtdVendidos = qtdVendidos;
    }

    public Double getValorVendidos() {
        return valorVendidos;
    }

    public void setValorVendidos(Double valorVendidos) {
        this.valorVendidos = valorVendidos;
    }
}
package sollute.estoquecerto.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @Column(name = "id_endereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;

    // @Column(name = "fk_empresa")
    @ManyToOne
    private Empresa fkEmpresa;

    @ManyToOne
    // @Column(name = "fk_cliente")
    private Cliente fkCliente;

    @ManyToOne
    // @Column(name = "fk_fornecedor")
    private Fornecedor fkFornecedor;

    @ManyToOne
//    @Column(name = "fk_funcionario")
    private Funcionario fkFuncionario;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String cep;

    @NotBlank
    private String uf;

    @NotBlank
    private String cidade;

    @NotBlank
    @Column(name = "ponto_referencia")
    private String pontoReferencia;

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Empresa getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Empresa fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Cliente getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Cliente fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Fornecedor getFkFornecedor() {
        return fkFornecedor;
    }

    public void setFkFornecedor(Fornecedor fkFornecedor) {
        this.fkFornecedor = fkFornecedor;
    }

    public Funcionario getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Funcionario fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradura) {
        this.logradouro = logradura;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }
}

package sollute.estoquecerto.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Funcionario {

    @Id
    @Column(name = "id_funcionario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    @NotNull
    @Column(name = "fk_empresa")
    @ManyToOne
    private Empresa fkEmpresa;

    @NotNull
    @Column(name = "fk_endereco")
    @OneToOne
    private Endereco fkEndereco;

    @NotBlank
    @Column(name = "nome_funcionario")
    private String nomeFuncionario;

    @NotBlank
    @Column(name = "cpf_funcionario")
    private String cpfFuncionario;

    @NotBlank
    @Column(name = "telefone_funcionario")
    private String telefoneFuncionario;

    @Positive
    @Column(name = "salario_funcionario")
    private String salarioFuncionario;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Empresa getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Empresa fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getTelefoneFuncionario() {
        return telefoneFuncionario;
    }

    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public String getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(String salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }
}

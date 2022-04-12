package sollute.estoquecerto.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Empreendedor {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpreendedor;
    @NotBlank
    @Length(min = 3, max = 45)
    private String nome;
    @CPF
    private String cpf;
    @PastOrPresent
    @NotNull
    private LocalDate nascimento;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    String dataNascimentoFormatada = nascimento.format(formatter);

    //Construtor
    public Empreendedor(String nome, String cpf, LocalDate nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public Empreendedor() {

    }

    //Metodos
    @Override
    public String toString() {
        return String.format("" +
                        "%6s %25s %15s %20s" +
                        "%06d %-25s %15s %15s",
                // Cabeçalho
                "ID",
                "Nome",
                "CPF",
                "Data de Nascimento",
                // Corpo
                idEmpreendedor,
                nome,
                cpf,
                nascimento);
    }

    //Getters and Setters
    public Long getIdEmpreendedor() {
        return idEmpreendedor;
    }

    public void setIdEmpreendedor(Long idEmpreendedor) {
        this.idEmpreendedor = idEmpreendedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
}
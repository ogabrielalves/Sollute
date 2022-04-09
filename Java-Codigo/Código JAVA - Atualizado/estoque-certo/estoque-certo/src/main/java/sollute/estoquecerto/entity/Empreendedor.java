package sollute.estoquecerto.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Empreendedor {

    //Atributos
    private Long idEmpreendedor;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dataNascimentoFormatada = nascimento.format(formatter);

    //Construtor
    public Empreendedor(String nome, String cpf, LocalDate nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
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
                dataNascimentoFormatada);
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
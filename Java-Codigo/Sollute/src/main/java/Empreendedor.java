public class Empreendedor {

    //Atributos
    private String nome;
    private String cpf;

    //Construtor
    public Empreendedor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    //Metodos
    @Override
    public String toString() {
        return String.format("\n" +
                        "EMPREENDEDOR: \n" +
                        "Nome: %s\n" +
                        "CPF: %s\n",
                nome,
                cpf);
    }

    //Getters and Setters
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
}
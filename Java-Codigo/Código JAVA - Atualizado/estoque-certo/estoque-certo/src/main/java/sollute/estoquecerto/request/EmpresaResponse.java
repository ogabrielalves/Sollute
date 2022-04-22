package sollute.estoquecerto.request;

public class EmpresaResponse {

    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String pegarSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

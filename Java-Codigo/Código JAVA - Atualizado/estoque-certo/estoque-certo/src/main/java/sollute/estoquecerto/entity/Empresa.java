package sollute.estoquecerto.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name= "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;

    @Email(message = "Insira um e-mail válido")
    @Column(name = "email")
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    @Length(min = 3, max = 45)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Length(min = 3, max = 45)
    @Column(name = "razao_social")
    private String razaoSocial;

    @CNPJ
    private String cnpj;

    @PositiveOrZero
    @Column(name = "qtd_produtos_vendidos")
    private int qtdProdutosVendidos;

    @PositiveOrZero
    @Column(name = "total_produtos_vendidos")
    private double totalProdutosVendidos;

    @NotNull
    private boolean autenticado;

//    public void venderProduto(ListaObj<Empreendedor> listaE,
//                              String cnpj,
//                              ListaObj<Produto> lista,
//                              Produto p,
//                              Integer qtd) {
//        boolean vendido = p.vender(qtd);
//        if (vendido) {
//            ListaObj<Produto> produtosVerificados = verificaStatus(listaE, cnpj, lista);
//            if (produtosVerificados.getTamanho() > 0) {
//                //notificarTodos(listaE, cnpj, produtosVerificados);
//                notificarTodos(listaE, produtosVerificados);
//            }
//        }
//    }

//    public ListaObj<Produto> verificaStatus(ListaObj<Empreendedor> listaE,
//                                            String cnpj,
//                                            ListaObj<Produto> lista) {

//        LocalDate data = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-7{dd/MM/yyyy}");
//        String dataFormatada = data.format(formatter);
//
//        if (lista.getTamanho() == 0) {
//            return null;
//        }

//        ListaObj<Produto> produtosEmAlerta = new ListaObj<>(lista.getTamanho());

//        for (int i = 0; i < lista.getTamanho(); i++) {
//            // Verificando se está com baixa quantidade em estoque
//            // No caso, menor ou igual a 3
//            if (lista.getElemento(i).getEstoqueInicial() <= 3) {
//                produtosEmAlerta.adiciona(lista.getElemento(i));
//            }
//            // veficando se o alimento está vencendo...
//            if (lista.getElemento(i) instanceof Produto) {
//                if (lista.getElemento(i).equals(dataFormatada)) {
//                    produtosEmAlerta.adiciona(lista.getElemento(i));
//                }
//            }
//        }
//        return produtosEmAlerta;
//    }

//    public void notificarTodos(ListaObj<Empreendedor> listaE,
//                               // String cnpj,
//                               ListaObj<Produto> lista) {
//        for (int i = 0; i < lista.getTamanho(); i++) {
//            System.out.printf("\nO produto %s necessita de atenção", lista.getElemento(i).getNome());
//        }
//    }

    public Integer getId() {
        return idEmpresa;
    }

    public void setId(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
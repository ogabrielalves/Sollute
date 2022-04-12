package sollute.estoquecerto.entity;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class ProdutoServico extends Produto {

    //Atributos
    @NotBlank
    private String nomeCliente;
    @NotBlank
    private String whatsappCliente;
    @NotBlank
    private String tipoServico;
    @NotBlank
    private LocalDate dataAgendamento;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    String dataAgendamentoFormatada = dataAgendamento.format(formatter);


    //Metodos
    @Override
    public Boolean vender(int i) {
        if ((getQtdEstoque() - i) < 0) {
            System.out.println("Estoque insuficiente");
            return false;
        } else {
            setQtdEstoque(getQtdEstoque() - i);
            super.setQtdVendidos(getQtdVendidos() + i);
            super.setValorVendidos(super.getPreco() * i);
            super.setPreco(super.getPreco()*1);
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format("" +
                        "%6s %20s %9s %7s %7s %6s %10s %5s %10s %15s %15s %20s %12s" +
                        "%06d %-20s %7.2f %7d %7d %4.2f %10s %4.1f %10s %15s %15s %20s %12s",
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
                "Tipo de Serviço",
                "Data Agendada",
                "Nome do Cliente",
                "Whatsapp",
                // Corpo
                super.getCodProduto(),
                super.getNome(),
                super.getPreco(),
                super.getQtdEstoque(),
                super.getQtdVendidos(),
                super.getValorVendidos(),
                super.getMarca(),
                super.getPreco(),
                super.getCategoria(),
                tipoServico,
                dataAgendamento,
                nomeCliente,
                whatsappCliente);
    }

    // Getters e Setters
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getWhatsappCliente() {
        return whatsappCliente;
    }

    public void setWhatsappCliente(String whatsappCliente) {
        this.whatsappCliente = whatsappCliente;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
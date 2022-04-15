package sollute.estoquecerto.entity;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private LocalDateTime dataAgendamento;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    String dataAgendamentoFormatada = dataAgendamento.format(formatter);


    //Metodos
    @Override
    public Boolean vender(int i) {
        if ((getEstoqueInicial() - i) < 0) {
            System.out.println("Estoque insuficiente");
            return false;
        } else {
            setEstoqueInicial(getEstoqueInicial() - i);
            super.setQtdVendidos(getQtdVendidos() + i);
            super.setValorVendidos(super.getPrecoVenda() * i);
            super.setPrecoVenda(super.getPrecoVenda()*1);
            return true;
        }
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

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
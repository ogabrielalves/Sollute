public class ProdutoOrdemDeServico extends Produto {
    //Atributos
    private String tipoServico;
    private String dataAgendamento;
    private String horaAgendamento;

    //Construtor
    public ProdutoOrdemDeServico(String nome,
                                     double preco,
                                     String tipoServico,
                                     String dataAgendamento,
                                     String horaAgendamento) {
        super(nome, preco);
        this.tipoServico = tipoServico;
        this.dataAgendamento = dataAgendamento;
        this.horaAgendamento = horaAgendamento;
    }

    //Metodos
    @Override
    public double vender() {
        return super.vender();
    }

    @Override
    public String toString() {
        return String.format("\n" +
                        "PRODUTOS_ORDEM_DE_SERVIÇO: \n" +
                        "Nome do Produto: %s\n" +
                        "Preço: %.2f\n" +
                        "Tipo de Ordem de Serviço: %s\n" +
                        "Data de Agendamento: %s\n" +
                        "Hora do Agendamento: %s"
                , super.getNome(), super.getPreco(), tipoServico, dataAgendamento, horaAgendamento);
    }
}

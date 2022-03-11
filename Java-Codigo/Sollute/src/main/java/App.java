public class App {

    public static void main(String[] args) {

        // Instanciando um CEO
        Empreendedor ceo = new Empreendedor(
                "Leonardo Arruda",
                "53122012501");

        // Instanciando uma empresa
        Empresa empresa = new Empresa(
                "Microsoft",
                "19.537.979/0001-60",
                "123.678.345",
                ceo);

        // Instanciando um alimento
        ProdutoAlimento produtoAlimento = new ProdutoAlimento(
                "Arroz",
                20.0,
                30,
                "Perecivel",
                "20/03/2022",
                "20/04/2023");

        // Instanciando um serviço
        ProdutoOrdemDeServico produtoOrdemDeSerivco = new ProdutoOrdemDeServico(
                "Manicure",
                10.0,
                "Estetica",
                "20/03/2022",
                "10H00");

        // Instanciando um vestuario
        ProdutoVestuario produtoVestuario = new ProdutoVestuario(
                "Calça",
                100.0,
                5,
                "Vestimenta",
                "GUCCI",
                "GG");

        empresa.adicionaProduto(produtoAlimento);

        empresa.adicionaProduto(produtoOrdemDeSerivco);

        empresa.adicionaProduto(produtoVestuario);

        System.out.println();

        empresa.mostraProduto();

        System.out.println(empresa);
    }
}

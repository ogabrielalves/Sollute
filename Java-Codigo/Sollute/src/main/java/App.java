public class App {
    public static void main(String[] args) {
        //Criando Objetos
        Empresa empresa = new Empresa("Microsoft","033.999.890/90","123.678.345");
        ProdutoAlimento produtoAlimento = new ProdutoAlimento("Arroz",20.0,"Perecivel","20/03/2022","20/04/2023");
        ProdutoOrdemDeSerivco produtoOrdemDeSerivco = new ProdutoOrdemDeSerivco("Manicure",10.0,"Estetica","20/03/2022","10H00");
        ProdutoVestuario produtoVestuario = new ProdutoVestuario("Calça", 100.0,"Vestimenta","GUCCI","GG");

        empresa.adicionaProduto(produtoAlimento);
        System.out.println();
        empresa.adicionaProduto(produtoOrdemDeSerivco);
        System.out.println();
        empresa.adicionaProduto(produtoVestuario);
        System.out.println();
        System.out.println(empresa);



    }
}

package sollute.estoquecerto.entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ArquivoCsv {

    public static void gravaArquivoCsv(List<Produto> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        nomeArq += ".csv";
        Boolean deuRuim = false;

        // Bloco try catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            // Se quiser que o conteúdo seja acrescentado ao final do arquivo,
            // teria que fazer:
            // arq = new FileWriter(nomeArq,true);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try catch para gravar no arquivo
        try {
            for (int i=0; i < lista.size(); i++) {
                Produto prod = lista.get(i);
                saida.format("%d;%s;%s;%s;%s;%s;%.2f;%.2f;%.2f;%d;%d;%d;%d\n",
                        prod.getIdProduto(),
                        prod.getCodigo(),
                        prod.getNome(),
                        prod.getMarca(),
                        prod.getCategoria(),
                        prod.getTamanho(),
                        prod.getPeso(),
                        prod.getPrecoCompra(),
                        prod.getPrecoVenda(),
                        prod.getEstoqueInicial(),
                        prod.getEstoqueMin(),
                        prod.getEstoqueMax(),
                        prod.getQtdVendidos());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivoCsv (String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        nomeArq += ".csv";
        boolean deuRuim = false;

        // try catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        // Bloco try catch para ler o arquivo
        try {
            System.out.printf("" +
                            "%-5s %-6s %-20s %-20s %-10s %12s %5s %-12s %-12s %-15s %-15s %-15s %-15s",
                    "ID",
                    "CODIGO",
                    "NOME",
                    "MARCA",
                    "CATEGORIA",
                    "TAMANHO",
                    "PESO",
                    "PRECO COMPRA",
                    "PRECO VENDA",
                    "ESTOQUE INICIAL",
                    "ESTOQUE MINIMO",
                    "ESTOQUE MAXIMO",
                    "QTD VENDIDA\n");
            while (entrada.hasNext()) {
                Integer idProduto = entrada.nextInt();
                String codigo = entrada.next();
                String nome = entrada.next();
                String marca = entrada.next();
                String categoria = entrada.next();
                String tamanho = entrada.next();
                Double peso = entrada.nextDouble();
                Double precoCompra = entrada.nextDouble();
                Double precoVenda = entrada.nextDouble();
                Integer estoqueInicial = entrada.nextInt();
                Integer estoqueMin = entrada.nextInt();
                Integer estoqueMax = entrada.nextInt();
                Integer qtdVendidos = entrada.nextInt();
                System.out.printf("%5d %-6s %-20s %-20s %-10s %-12s %5.2f %12.2f %12.2f %15d %15d %15d %15d",
                        idProduto,
                        codigo,
                        nome,
                        marca,
                        categoria,
                        tamanho,
                        peso,
                        precoCompra,
                        precoVenda,
                        estoqueInicial,
                        estoqueMin,
                        estoqueMax,
                        qtdVendidos);
            }
        }
        catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        }
        catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }


//    public static void gravaArquivoCsv(ListaObj<Produto> lista, String nomeArq) {
//        FileWriter arq = null;
//        Formatter saida = null;
//        nomeArq += ".csv";
//        Boolean deuRuim = false;
//
//        // Bloco try catch para abrir o arquivo
//        try {
//            arq = new FileWriter(nomeArq);
//            // Se quiser que o conteúdo seja acrescentado ao final do arquivo,
//            // teria que fazer:
//            // arq = new FileWriter(nomeArq,true);
//            saida = new Formatter(arq);
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao abrir o arquivo");
//            System.exit(1);
//        }
//
//        // Bloco try catch para gravar no arquivo
//        try {
//            for (int i=0; i < lista.getTamanho(); i++) {
//                Produto prod = lista.getElemento(i);
//                saida.format("%d;%s;%s;%s;%s;%s;%.2f;%.2f;%.2f;%d;%d;%d;%d\n",
//                        prod.getIdProduto(),
//                        prod.getCodigo(),
//                        prod.getNome(),
//                        prod.getMarca(),
//                        prod.getCategoria(),
//                        prod.getTamanho(),
//                        prod.getPeso(),
//                        prod.getPrecoCompra(),
//                        prod.getPrecoVenda(),
//                        prod.getEstoqueInicial(),
//                        prod.getEstoqueMin(),
//                        prod.getEstoqueMax(),
//                        prod.getQtdVendidos());
//            }
//        }
//        catch (FormatterClosedException erro) {
//            System.out.println("Erro ao gravar o arquivo");
//            deuRuim = true;
//        }
//        finally {
//            saida.close();
//            try {
//                arq.close();
//            }
//            catch (IOException erro) {
//                System.out.println("Erro ao fechar o arquivo");
//                deuRuim = true;
//            }
//            if (deuRuim) {
//                System.exit(1);
//            }
//        }
//    }
//
//    public static void leExibeArquivoCsv (String nomeArq) {
//        FileReader arq = null;
//        Scanner entrada = null;
//        nomeArq += ".csv";
//        boolean deuRuim = false;
//
//        // try catch para abrir o arquivo
//        try {
//            arq = new FileReader(nomeArq);
//            entrada = new Scanner(arq).useDelimiter(";|\\n");
//        }
//        catch (FileNotFoundException erro) {
//            System.out.println("Arquivo não encontrado");
//            System.exit(1);
//        }
//
//        // Bloco try catch para ler o arquivo
//        try {
//            System.out.printf("" +
//                    "%-5s %-6s %-20s %-20s %-10s %12s %5s %-12s %-12s %-15s %-15s %-15s %-15s",
//                    "ID",
//                    "CODIGO",
//                    "NOME",
//                    "MARCA",
//                    "CATEGORIA",
//                    "TAMANHO",
//                    "PESO",
//                    "PRECO COMPRA",
//                    "PRECO VENDA",
//                    "ESTOQUE INICIAL",
//                    "ESTOQUE MINIMO",
//                    "ESTOQUE MAXIMO",
//                    "QTD VENDIDA\n");
//            while (entrada.hasNext()) {
//                Integer idProduto = entrada.nextInt();
//                String codigo = entrada.next();
//                String nome = entrada.next();
//                String marca = entrada.next();
//                String categoria = entrada.next();
//                String tamanho = entrada.next();
//                Double peso = entrada.nextDouble();
//                Double precoCompra = entrada.nextDouble();
//                Double precoVenda = entrada.nextDouble();
//                Integer estoqueInicial = entrada.nextInt();
//                Integer estoqueMin = entrada.nextInt();
//                Integer estoqueMax = entrada.nextInt();
//                Integer qtdVendidos = entrada.nextInt();
//                System.out.printf("%5d %-6s %-20s %-20s %-10s %-12s %5.2f %12.2f %12.2f %15d %15d %15d %15d",
//                        idProduto,
//                        codigo,
//                        nome,
//                        marca,
//                        categoria,
//                        tamanho,
//                        peso,
//                        precoCompra,
//                        precoVenda,
//                        estoqueInicial,
//                        estoqueMin,
//                        estoqueMax,
//                        qtdVendidos);
//            }
//        }
//        catch (NoSuchElementException erro) {
//            System.out.println("Arquivo com problemas");
//            deuRuim = true;
//        }
//        catch (IllegalStateException erro) {
//            System.out.println("Erro na leitura do arquivo");
//            deuRuim = true;
//        }
//        finally {
//            entrada.close();
//            try {
//                arq.close();
//            }
//            catch (IOException erro) {
//                System.out.println("Erro ao fechar o arquivo");
//                deuRuim = true;
//            }
//            if (deuRuim) {
//                System.exit(1);
//            }
//        }
//    }
}

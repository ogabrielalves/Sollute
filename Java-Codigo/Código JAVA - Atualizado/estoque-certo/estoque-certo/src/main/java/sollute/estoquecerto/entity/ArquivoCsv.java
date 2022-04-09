package sollute.estoquecerto.entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ArquivoCsv {
    public static void gravaArquivoCsv(ListaObj<Produto> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Aluno aluno = lista.getElemento(i);
                saida.format("%d;%s;%.2f\n",
                        aluno.getRa(),
                        aluno.getNome(),
                        aluno.getNota());
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

    public static void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null;  // obj que representa o arquivo para leitura
        Scanner entrada = null; // obj que será usado para ler do arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv";

        // Bloco try catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada= new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado!");
            System.exit(1);
        }

        // Bloco try catch para ler o arquivo
        try {
            // Exibe uma linha com os títulos das colunas
            System.out.printf("%4s %-15s %4s\n","RA","NOME","NOTA");
            while (entrada.hasNext()) {
                Integer id = entrada.nextInt();
                String nome = entrada.next();
                Double nota = entrada.nextDouble();
                System.out.printf("%4d %-15s %4.2f\n",id,nome,nota);
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
}

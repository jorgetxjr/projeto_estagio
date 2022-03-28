package rascunhos;//vamos começar com as boas práticas? VAMOS!!!!

import java.io.File;
import java.util.Scanner;

public class TesteArquivos
{
    public static void main(String[] args)
    {
        String caminhoArquivos="";
        //String[] extensao;
        Scanner teclado=new Scanner(System.in);
        //File localDosArquivos=null;

        System.out.print("Entre com o caminho do arquivo: ");
        caminhoArquivos=teclado.nextLine(); 
        //localDosArquivos=(teclado.nextLine()); NOPE!
        File arquivoSelecionado=new File(caminhoArquivos);

        if(arquivoSelecionado.isFile()&&arquivoSelecionado.exists())
        {
            //seria interessante testar se o arquivo é um PDF? se sim, como fazer?
            //testar se posso ler? .canRead()
            System.out.printf("O arquivo %s esta disponivel!", arquivoSelecionado.getName());//retorna somente o nome e não o caminho todo
            System.out.printf("extensao: %s",getExtension(arquivoSelecionado.getName()));

        }
        else
            System.out.println("Nome ou caminho de arquivo invalido!");
        
        teclado.close();
        


    }
    public static String getExtension(String nomeArquivo)
    {
        if(nomeArquivo.contains("."))
            return nomeArquivo.substring(nomeArquivo.lastIndexOf(".")+1);
        else
            return "";
        //obrigado, https://dicasdejava.com.br/java-como-obter-a-extensao-de-um-arquivo/
    }
}
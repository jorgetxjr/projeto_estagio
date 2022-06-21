import java.io.IOException;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class main 
{
	public static void main(String [] args)throws IOException
	{
		/*String caminho="";
		Scanner entrada=new Scanner(System.in);
		String textoUnisinos="";
		String textoExterno="";
		
		//por isso em um try/catch
		System.out.print("Entre com o caminho do arquivo da Unisinos: ");
		caminho=entrada.nextLine();
		textoUnisinos=adquirirTexto(caminho);
		//verificar a possibilidade destas informações serem "fixas"
		entrada.reset();
		System.out.print("Entre com o caminho do arquivo da universidade externa: ");
		caminho=entrada.nextLine();
		textoExterno=adquirirTexto(caminho);
		*/
		//String cobaia="a vida é o que é!";
		String cobaia="Onde a dona Rosa roubou pão? Ela, que é muito esperta, roubou pão na casa do João.";
		//cobaia=cobaia.toLowerCase();
		//cobaia=removerPontuacao(cobaia);
		/* está ocorrendo uma exception que ainda não entendi: java.util.ConcurrentModificationException
		 * com a função "removerStopWords()
		 * pondo direto no main, para ver se roda
		 */
		//ArrayList<String> recebido0=tokenizarTexto(cobaia);
		ArrayList<String> recebido=normalizarTexto(cobaia);
		for(String r:recebido)
			System.out.print(r+" ");
	}
	
	//-------------------FUNÇÕES SECUNDÁRIAS
	
	public static String adquirirTexto(String caminhoArquivo) throws IOException
	{
		//extraindo texto de um arquivo PDF
		File original=new File(caminhoArquivo);
		PDDocument document=PDDocument.load(original);
				
		PDFTextStripper pdfstripper=new PDFTextStripper();
		String texto=pdfstripper.getText(document);
		document.close();
		return texto;
	}
	
	public static String removerPontuacao(String texto)
	{//função 3 do diagrama
		String textoRetorno="";
		final char[]stopPoints= {'.',',','(',')',':',';','\n','\t','/','!','?'};//more?
		char[] textoEmCharArray=texto.toCharArray();
		for(char ponto:stopPoints)
		{
			for(int i=0;i<textoEmCharArray.length;i++)
			{
				if(ponto==textoEmCharArray[i]) 
				{
					textoEmCharArray[i]=' ';//mudar o X para algo melhor?
				}
			}
		}
		for(char c:textoEmCharArray)
			textoRetorno+=c;
				
		return textoRetorno;
	}
	
	public static ArrayList<String> tokenizarTexto(String texto)//testar
	{//função 4, parte 1 do diagrama
		String[] textoTemp=texto.split("\\s");
		ArrayList<String> textoModificado=new ArrayList<>();
		for(String s:textoTemp)
			textoModificado.add(s);
						
		return textoModificado;
	}
	
	public static ArrayList<String> removerStopWords(ArrayList<String> texto)
	{//função 4, parte 2 do diagrama
		ArrayList <String> cloneTexto=new ArrayList<>(texto);
		final String[] stopWords= {"a","as","o","os","um","uma","uns","umas", "e","até", "que", "é"};//more?
		for(String sw:stopWords)
		{
			for(String tm:cloneTexto)
			{
				if(sw.equalsIgnoreCase(tm))
				{
					texto.remove(tm);
				}
			}
			
		}
		return texto;
		//problemas com 
		//Solução:
		//https://pt.stackoverflow.com/questions/18856/itera%c3%a7%c3%a3o-d%c3%a1-erro-de-concurrentmodificationexception-ao-incluir-mais-de-um-bot%c3%a3o
	}
	
	public static ArrayList<String> normalizarTexto(String texto)
	{
		texto=texto.toLowerCase();
		texto=removerPontuacao(texto);
				
		ArrayList <String> tokensDoTexto=tokenizarTexto(texto);
		tokensDoTexto=removerStopWords(tokensDoTexto);
						
		return tokensDoTexto;
	}

}

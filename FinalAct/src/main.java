import java.io.IOException;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String cobaia001="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		String cobaia002="Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
		ArrayList<String> token001=new ArrayList<>();
		ArrayList<String> token002=new ArrayList<>();
		token001=normalizarTexto(cobaia001);
		token002=normalizarTexto(cobaia002);
		double percent=compararTextos(token001,token002);
		
		System.out.println("Igualdade: "+percent+" %");
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
	
	public static double compararTextos(ArrayList<String> texto1,ArrayList<String> texto2)
	{//função 2
		HashMap<String,Integer> todasAsPalavras=new HashMap<String, Integer>();
		int totalDePalavras=0;
		//int totalDeIgualdades=0;
		double percentSemelhanca=-1.0;
		Set<String> conjTextoComum = new TreeSet<>();
		//adicionando o primeiro conjunto de palavras
		for(String palavra:texto1)
			todasAsPalavras.put(palavra, 1);
		//adicionando o segundo conjunto de palavras
		for(String palavra:texto2)
		{
			if(todasAsPalavras.containsKey(palavra))
				todasAsPalavras.put(palavra, todasAsPalavras.get(palavra)+1);
			else
				todasAsPalavras.put(palavra, 1);
				
		}
		totalDePalavras=todasAsPalavras.size();
		for (Map.Entry<String, Integer> w : todasAsPalavras.entrySet()) {
			if (w.getValue() == 2) {
				conjTextoComum.add(w.getKey());
			}
		}
		
		percentSemelhanca=(double)conjTextoComum.size()/totalDePalavras *100;
		return percentSemelhanca;
	}

}

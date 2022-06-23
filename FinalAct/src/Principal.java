import java.io.IOException;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Principal 
{
	public static void main(String [] args)
	{
		String caminho="";
		Scanner entrada=new Scanner(System.in);
		String textoUnisinos="";
		String textoExterno="";
		double percent=-1.0;
		ArrayList<String> token001=new ArrayList<>();
		ArrayList<String> token002=new ArrayList<>();
		
		try
		{
			System.out.print("Entre com o caminho do arquivo da Unisinos: ");
			caminho=entrada.nextLine();
			textoUnisinos=adquirirTexto(caminho);
			//verificar a possibilidade destas informações serem "fixas"
			entrada.reset();
			System.out.print("Entre com o caminho do arquivo da universidade externa: ");
			caminho=entrada.nextLine();
			textoExterno=adquirirTexto(caminho);
			entrada.close();
		}
		catch(IOException e)
		{
			System.out.println("Falha na leitura dos arquivos!");
			System.out.println(e.getStackTrace());
		}
		
		token001=normalizarTexto(textoUnisinos);
		token002=normalizarTexto(textoExterno);
		percent=compararTextos(token001,token002);
		
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
		final char[]stopPoints= {'.',',','(',')',':',';','\n','\t','/','!','?'};//mais?
		char[] textoEmCharArray=texto.toCharArray();
		for(char ponto:stopPoints)
			for(int i=0;i<textoEmCharArray.length;i++)
				if(ponto==textoEmCharArray[i]) 
					textoEmCharArray[i]=' ';
		
		for(char c:textoEmCharArray)
			textoRetorno+=c;
				
		return textoRetorno;
	}
	
	public static ArrayList<String> tokenizarTexto(String texto)
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
			for(String tm:cloneTexto)
				if(sw.equalsIgnoreCase(tm))
					texto.remove(tm);
		
		return texto;
		//problemas com arraylist
		//Solução:
		//https://pt.stackoverflow.com/questions/18856/itera%c3%a7%c3%a3o-d%c3%a1-erro-de-concurrentmodificationexception-ao-incluir-mais-de-um-bot%c3%a3o
	}
	
	public static ArrayList<String> normalizarTexto(String texto)
	{//função 1 do diagrama
		texto=texto.toLowerCase();
		texto=removerPontuacao(texto);
				
		ArrayList <String> tokensDoTexto=tokenizarTexto(texto);
		tokensDoTexto=removerStopWords(tokensDoTexto);
						
		return tokensDoTexto;
	}
	
	public static double compararTextos(ArrayList<String> texto1,ArrayList<String> texto2)
	{//função 2
		//fontes de pesquisa:
		//https://github.com/ThiagoToWo/Plagio
		//https://github.com/AlvaroMNCruz/codeSimilarity
		HashMap<String,Integer> todasAsPalavras=new HashMap<String, Integer>();
		int totalDePalavras=0;
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
		for (Map.Entry<String, Integer> w : todasAsPalavras.entrySet()) 
		{
			if (w.getValue() == 2) 
				conjTextoComum.add(w.getKey());
		}
		
		percentSemelhanca=(double)conjTextoComum.size()/totalDePalavras *100;
		return percentSemelhanca;
	}
}

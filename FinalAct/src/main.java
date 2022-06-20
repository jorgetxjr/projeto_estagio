import java.io.IOException;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
public class main 
{
	public static void main(String [] args)throws IOException
	{
		
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
		final char[]stopPoints= {'.',',','(',')',':',';','\n','\t','/'};
		char[] textoEmCharArray=texto.toCharArray();
		for(char ponto:stopPoints)
		{
			for(int i=0;i<textoEmCharArray.length;i++)
			{
				if(ponto==textoEmCharArray[i]) 
				{
					textoEmCharArray[i]='X';//mudar para algo melhor?
				}
			}
		}
		for(char c:textoEmCharArray)
			textoRetorno+=c;
				
		return textoRetorno;
	}
	
	public static String removerStopWords(String texto)//testar
	{//função 4 do diagrama
		String textoModificado="";
		final String[] stopWords= {"a","as","o","os","um","uma","uns","umas", "e"};
		for(String sw:stopWords)
			textoModificado=texto.replaceAll(sw, "");
		
		return textoModificado;
	}

}

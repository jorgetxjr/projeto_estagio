import java.io.IOException;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

public class Principal {

	public static void main(String[] args) throws IOException
	{
		//extraindo texto de um arquivo PDF
		File original=new File("./original2.pdf");
		PDDocument document=PDDocument.load(original);
		
		PDFTextStripper pdfstripper=new PDFTextStripper();
		String texto=pdfstripper.getText(document);
		document.close();
		System.out.println("TEXTO ANTIGO:");
		System.out.println(texto);
								
		System.out.println("\nTEXTO NOVO:");
		String novoTexto=removeStopWords(texto);
		System.out.println(novoTexto);

	}
	
	
	
	public static String removeStopWords(String texto)
	{
		final char[]stopWords= {'.',',','(',')',':',';','\n','\t',' ','/'};
		char[] textoEmCharArray=texto.toCharArray();
		for(char stopWord:stopWords)
		{
			for(int i=0;i<textoEmCharArray.length;i++)
			{
				if(stopWord==textoEmCharArray[i]) 
				{
					textoEmCharArray[i]='X';
				}
			}
		}
		String textoRetorno="";
		for(char c:textoEmCharArray)
			textoRetorno+=c;
		
		return textoRetorno;
		
	}

}

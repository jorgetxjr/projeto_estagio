import java.io.IOException;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

public class Principal {

	public static void main(String[] args) throws IOException{
		// primeiro contato e adicionando páginas
		/*PDDocument document=new PDDocument();
		PDPage myPage=new PDPage();
		document.save("./meuArquivo.pdf");
		System.out.println("Documento PDF criado!");
		document.addPage(myPage);
		//document.addPage(myPage);
		System.out.println("Nova página em branco adicionada!");*/
		
		//lendo um documento existente e adicionando uma página
		/*File arqLeitura=new File("./doc_velho.pdf");
		PDDocument document=PDDocument.load(arqLeitura);
		PDPage myPage=new PDPage();
		document.addPage(myPage);
		document.save("./doc_adicionado.pdf");
		System.out.println("OK!");*/
		
		//lendo um documento existente e removendo uma página
		/*File caminho=new File("./doc_adicionado.pdf");
		PDDocument document=PDDocument.load(caminho);
		System.out.print("PDF lido! Quantidade de páginas: ");
		int noOfPages=document.getNumberOfPages();
		System.out.println(noOfPages);
		document.removePage(0);//o índice começa em zero!
		document.save("./doc_modificado.pdf");
		System.out.println("OK!");*/
		
		//extraindo texto de um arquivo PDF
		File original=new File("./original2.pdf");
		PDDocument document=PDDocument.load(original);
		
		PDFTextStripper pdfstripper=new PDFTextStripper();
		String texto=pdfstripper.getText(document);
		System.out.println(texto);
								
		document.close();
		

	}

}

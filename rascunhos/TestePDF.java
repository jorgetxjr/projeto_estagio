import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class TestePDF
{
    public void main(String[] args)
    {
        PDDocument documento=new PDDocument();
        documento.save("./meuPDF.pdf");
        System.out.println("PDF Criado");
        documento.close();
    }
}
package dgf.xfa22c.maven.docxFile.documents;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfReader {

    public static void main(String[] args) {

        File file = new File("example.pdf");

        try(PDDocument document = PDDocument.load(file)){
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println(text);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

}

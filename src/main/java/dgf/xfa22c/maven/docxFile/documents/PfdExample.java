package dgf.xfa22c.maven.docxFile.documents;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PfdExample {

    public static void main(String[] args) {

        String filePath = "example.pdf";

        try {
            PdfWriter pdfWriter = new PdfWriter(filePath);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            PdfFont font = PdfFontFactory.createFont("C:/Windows/Fonts/arial.ttf", "Identity-H");

            Paragraph p = new Paragraph("Hello World!").setFont(font);
            Paragraph p1 = new Paragraph("Создан через Java и iText.").setFont(font);
            document.add(p);
            document.add(p1);
            document.close();
            System.out.println("Файл создан");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}

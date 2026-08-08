package dgf.xfa22c.maven.docxFile;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Scanner sc = new Scanner(System.in);

        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("ExampleText2");
        run.setFontFamily("Arial");
        run.setFontSize(20);
        run.setBold(true);
        run.setColor("1E90FF");
        run.setItalic(true);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.addBreak();


        XWPFParagraph paragraph2 = doc.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        File file = new File("unnamed.gif");
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
        run2.setText(" ");
        try(FileInputStream f = new FileInputStream(file)){
            run2.addPicture(f, XWPFDocument.PICTURE_TYPE_GIF, "unnamed.gif",
                    Units.toEMU(300), Units.toEMU(200));
        }catch (IOException | InvalidFormatException e) {
            System.out.println(e.getMessage());
        }

        try(FileOutputStream out = new FileOutputStream("Examle.docx")){
            doc.write(out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}

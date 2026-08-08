package dgf.xfa22c.maven.docxFile.documents;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExample {

    public static void main(String[] args) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        Row header = sheet.createRow(0);
        Cell cell = header.createCell(0);
        cell.setCellValue("Name");
        cell.setCellStyle(style);

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue("Ангелина");
        row.createCell(1).setCellValue(20);

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Войд");
        row2.createCell(1).setCellValue(45000);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        try(FileOutputStream fileOut = new FileOutputStream("ExcelExample.xlsx")){
            workbook.write(fileOut);
            System.out.println("Файд создан");
        }catch(IOException e){
            System.err.println(e.getMessage());
        }


    }

}

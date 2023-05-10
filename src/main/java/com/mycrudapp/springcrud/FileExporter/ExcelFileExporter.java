package com.mycrudapp.springcrud.FileExporter;

import com.mycrudapp.springcrud.Entity.Contact;
import org.apache.poi.ss.usermodel.BorderStyle;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.CellStyle;
        import org.apache.poi.ss.usermodel.FillPatternType;
        import org.apache.poi.ss.usermodel.IndexedColors;
        import org.apache.poi.ss.usermodel.Row;
        import org.apache.poi.ss.usermodel.Sheet;
        import org.apache.poi.ss.usermodel.Workbook;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.util.List;

/**
 * Excel File Exporter
 */

@Component
public class ExcelFileExporter {

    public ByteArrayOutputStream exportExcelFile(List<Contact> contacts, String[] headers) throws IOException {
        // create a new Workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new Sheet named "Contacts"
        Sheet sheet = workbook.createSheet("Contacts");

        // Create header row
        createHeaderRow(workbook, sheet, headers);

        // Create rows
        for(int i = 0; i < contacts.size(); i++) {
            // row index equals i + 1 because the first row of Excel file is the header row.
            int rowIndex = i + 1;
            createNewRow(workbook, sheet, rowIndex, contacts.get(i));
        }

        // Adjusts 3 columns to set the width to fit the contents.
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);

        // Write to file
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            workbook.write(stream);
            return  stream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            workbook.close();
        }
        return null;
    }

    /**
     * Create header row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param headers the headers text
     */
    private void createHeaderRow(Workbook workbook, Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    /**
     * Create a new row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param rowIndex the index of row to create
     * @param contact the Contact object which represent information to write to row.
     */
    private void createNewRow(Workbook workbook, Sheet sheet, int rowIndex, Contact contact) {
        Row row = sheet.createRow(rowIndex);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        Cell cell = row.createCell(0);
        cell.setCellValue(contact.getName());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue(contact.getEmail());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(contact.getMobile());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue(contact.getLandline());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue(contact.getWebsite());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue(contact.getAddress());
        cell.setCellStyle(cellStyle);
    }

}

package com.indus.app2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToInsertSqlLatest {
    public static void main(String[] args) throws IOException {
        String fileName = "C:/Saagar/Sample2.xlsx";
        String tableName = "configuration.question";
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Row headerRow = rowIterator.next();
        StringBuilder headerBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
        Iterator<Cell> headerIterator = headerRow.iterator();
        while (headerIterator.hasNext()) {
            Cell headerCell = headerIterator.next();
            headerBuilder.append(headerCell.getStringCellValue());
            if (headerIterator.hasNext()) {
                headerBuilder.append(", ");
            }
        }
        headerBuilder.append(") VALUES (");
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            StringBuilder queryBuilder = new StringBuilder(headerBuilder.toString());
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                case STRING:
                    queryBuilder.append("'" + cell.getStringCellValue() + "'");
                    break;
                case NUMERIC:
                    if (cell.getNumericCellValue() % 1 == 0) {
                        // If the numeric value is an integer
                        queryBuilder.append((int) cell.getNumericCellValue());
                    } else {
                        // If the numeric value is not an integer, append NULL
                        queryBuilder.append("NULL");
                    }
                    break;
                case BOOLEAN:
                    queryBuilder.append(cell.getBooleanCellValue());
                    break;
                default:
                    queryBuilder.append("NULL");
                }
                if (cellIterator.hasNext()) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(");");
            String query = queryBuilder.toString();
            System.out.println(query); // Do whatever you want with the query here
        }
        workbook.close();
        inputStream.close();
    }
}

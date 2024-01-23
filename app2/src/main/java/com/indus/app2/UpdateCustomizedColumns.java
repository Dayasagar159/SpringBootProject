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

public class UpdateCustomizedColumns {
    public static void main(String[] args) throws IOException {
        String fileName = "C:/Saagar/Lola.xlsx";
        String tableName = "table_name";
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Row headerRow = rowIterator.next();
        StringBuilder headerBuilder = new StringBuilder("UPDATE " + tableName + " SET ");
        StringBuilder queryBuilder = new StringBuilder();
        int idColIndex = 0; // Assumes that the ID column is the first column
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            queryBuilder.append("(");
            int columnIndex = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (columnIndex == idColIndex) {
                    // Get the ID column value and store it for the WHERE clause
                    String idValue = "";
                    switch (cell.getCellType()) {
                        case STRING:
                            idValue = "'" + cell.getStringCellValue() + "'";
                            break;
                        case NUMERIC:
                            idValue = Double.toString(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            idValue = Boolean.toString(cell.getBooleanCellValue());
                            break;
                        default:
                            idValue = "NULL";
                    }
                    headerBuilder.append("WHERE id = " + idValue);
                } else if (columnIndex == 2 || columnIndex == 7) { // Assumes col2 and col7 are to be updated
                    // Get the value of the specified columns
                    String colName = headerRow.getCell(columnIndex).getStringCellValue();
                    String colValue = "";
                    switch (cell.getCellType()) {
                        case STRING:
                            colValue = "'" + cell.getStringCellValue() + "'";
                            break;
                        case NUMERIC:
                            colValue = Double.toString(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            colValue = Boolean.toString(cell.getBooleanCellValue());
                            break;
                        default:
                            colValue = "NULL";
                    }
                    queryBuilder.append(colName + " = " + colValue);
                    if (cellIterator.hasNext()) {
                        queryBuilder.append(", ");
                    }
                }
                columnIndex++;
            }
            queryBuilder.append("),");
        }
        String query = headerBuilder.toString() + queryBuilder.toString().substring(0, queryBuilder.length() - 1);
        System.out.println(query); // Do whatever you want with the query here
        workbook.close();
        inputStream.close();
    }
}

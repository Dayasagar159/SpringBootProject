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

public class DeleteFromCustomizedTable {
    public static void main(String[] args) throws IOException {
        String fileName = "C:/Saagar/Lola3.xlsx";
        String tableName = "profile_management.user_queue";
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell idCell = row.getCell(0);
            Cell userIdCell = row.getCell(2);
            if (idCell != null && userIdCell != null) {
                String id = "";
                String userId = "";
                switch (idCell.getCellType()) {
                    case STRING:
                        id = "'" + idCell.getStringCellValue() + "'";
                        break;
                    case NUMERIC:
                        id = String.valueOf(idCell.getNumericCellValue());
                        break;
                }
                switch (userIdCell.getCellType()) {
                    case STRING:
                        userId = "'" + userIdCell.getStringCellValue() + "'";
                        break;
                    case NUMERIC:
                        userId = String.valueOf(userIdCell.getNumericCellValue());
                        break;
                }
                String query = "DELETE FROM " + tableName + " WHERE id = " + id + " AND user_id = " + userId + ";";
                System.out.println(query); // Do whatever you want with the query here
            }
        }
        workbook.close();
        inputStream.close();
    }
}
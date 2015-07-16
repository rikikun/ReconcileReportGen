package com.dtec.reconcilegenerate.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReconcileDo {

    public void doReconcile(String templatePath, String fileName, String sql)
            throws SQLException {
        Connection connection = DbConnect.connectDatabase();
        IoService excelService = new IoService();
        if (connection != null) {
            try {
                XSSFWorkbook work = excelService.readExcel(templatePath);
                System.out.println(work.getSheetAt(0).getRow(0).getCell(0)
                        .getStringCellValue());

                Statement s = connection.createStatement();
                ResultSet rs = s.executeQuery(sql);

                ResultSetMetaData rsmd = rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                int row = 7;
                int cell = 0;

                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        String columnValue = rs.getString(i);
                        if (columnValue == null || columnValue == "") {
                            checkRowCellIfNull(work, row, cell + i);
                            work.getSheetAt(0).getRow(row)
                                    .getCell(cell + i).setCellValue("");
                        } else {
                            checkRowCellIfNull(work, row, cell + i);
                            try {
                                work.getSheetAt(0).getRow(row)
                                        .getCell(cell + i)
                                        .setCellValue(Double.parseDouble(columnValue));
                            } catch (Exception e) {
                                work.getSheetAt(0).getRow(row)
                                        .getCell(cell + i)
                                        .setCellValue(columnValue);
                            }
                        }
                    }
                    row++;
                }
                System.out.println("Finish create " + fileName);
                excelService.writeExcel(work, "reconResult" + File.separator + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't connect");
        }
    }

    public void checkRowCellIfNull(XSSFWorkbook work, int row, int col) {
        if (work.getSheetAt(0).getRow(row) == null) {
            work.getSheetAt(0).createRow(row);
        }
        if (work.getSheetAt(0).getRow(row).getCell(col) == null) {
            work.getSheetAt(0).getRow(row).createCell(col);
        }
    }
}

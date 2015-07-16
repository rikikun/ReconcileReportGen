/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtec.reconcilegenerate.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author rikikun
 */
public class IoService {

    public void writeExcel(XSSFWorkbook workBook, String fileName) throws IOException {
        FileOutputStream outPut = new FileOutputStream(new File(fileName));
        workBook.write(outPut);
        outPut.close();
    }

    public XSSFWorkbook readExcel(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileName));
        return new XSSFWorkbook(file);
    }

    public String readSql(String sqlName) throws IOException {
        BufferedReader input =new BufferedReader(new FileReader(sqlName));
        String sql="";
        sql+=input.readLine();
        return sql;
    }
    
    public Boolean checkFileExist(String fileName){
        File file=new File(fileName);
        return file.exists();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtec.reconcilegenerate.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rikikun
 */
public class DbConnect {
    public static Connection connectDatabase() {
        
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("not found Oracle JDBC Driver");
		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.200.108.35:1521/DMIL", "vm1dta",
					"vmldta1819");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
		}
                
                System.out.println("Oracle JDBC was connected");
                
		return connection;
	}
}

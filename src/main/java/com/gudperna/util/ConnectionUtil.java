package com.gudperna.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil
{
	public static Connection getConnection()
	{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.62.180.8:1521:dgsdb", "pmsdgs", "Telkom#1234");
			
		} catch(ClassNotFoundException cnfe) {
			System.err.println("Class error: "+cnfe.getMessage());
		} catch(SQLException se) {
			System.err.println("SQL error: "+se.getMessage());
		} catch(Exception e) {
			System.err.println("Error: "+e.getMessage());
		}

		return conn;
	}
}
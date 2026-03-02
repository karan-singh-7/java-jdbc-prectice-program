package com.nit.kk.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test01_Connection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		  //1. Loadding oracle driver into JVM
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	      System.out.println("Driver is loaded!");
	      
	      //2. Creating connection to DB
		  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
	      System.out.println("Connection is established");
	      
	      //3. Priniting connection object class
	      System.out.println("Con: "+con);
	      
	      // Creating statement object
	      Statement stmt = con.createStatement();
	      System.out.println("Statement created!");
	      //4. closing connection
	      stmt.close();
		  con.close();
	}

}

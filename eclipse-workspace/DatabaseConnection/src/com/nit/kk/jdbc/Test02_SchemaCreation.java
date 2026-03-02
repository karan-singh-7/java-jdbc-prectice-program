package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02_SchemaCreation {

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
	      
	      // Creating user
	      try
	      {
	    	     stmt.execute("ALTER SESSION SET \"_ORACLE_SCRIPT\" = true");
		      stmt.execute("CREATE USER username IDENTIFIED BY password");
		      stmt.execute("GRANT connect, resource, unlimited tablespace to username");
		      System.out.println("User is created and DBA permissions are granted");
	      }
	      catch(SQLException e)
	      {
	    	     if(e.getErrorCode()==1920)
	    	     {
	    	    	    System.out.println("User already exit!!");
	    	     }
	    	     else
	    	     {
	    	    	    System.out.println("User creation fail: "+e.getMessage());
	    	     }
	      }
	
	      //4. closing connection
	      stmt.close();
		  con.close();

	}

}

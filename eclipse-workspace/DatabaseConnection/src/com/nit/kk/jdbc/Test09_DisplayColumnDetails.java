package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Test09_DisplayColumnDetails {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	       
	       Connection con = DriverManager.getConnection(
	    		                                            "jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password"
	    		                                           );
	       Statement stmt = con.createStatement();
	       
	       //display table column property
	     ResultSet rs = stmt.executeQuery("""
	       		                          SELECT *
	       		                          FROM course
	       		                          """);
	     
	     ResultSetMetaData rsmd = rs.getMetaData();
	     
	     for(int i=1; i<=rsmd.getColumnCount();i++)
	     {
	    	   System.out.print(rsmd.getColumnName(i)+" ");
	     }
	     
	     rs.close();
	     stmt.close();
	     con.close();

	}

}

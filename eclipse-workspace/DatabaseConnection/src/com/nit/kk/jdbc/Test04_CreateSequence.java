package com.nit.kk.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Test04_CreateSequence {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
				
	    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
	    
	    Statement stmt = con.createStatement();
	    
	    // Create sequences
	    
	   try
	   {
		   String courseSeq = """
	               CREATE SEQUENCE courseSeq
	               START WITH 1
	               INCREMENT BY 1
	               """;

          String studentSeq = """
                  CREATE SEQUENCE studentSeq
                  START WITH 101
                  INCREMENT BY 1
                       """;

                  stmt.execute(courseSeq);
                  System.out.println("Course sequence created!");

                  stmt.execute(studentSeq);
                  System.out.println("Student sequence created!");
	   }
	   catch(SQLException e)
	   {
		   System.out.println("Sequences already created! ");
	   }
	    
	    stmt.close();
	    con.close();
	    
	    
	}

}

package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test06_UpdateRows {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
Class.forName("oracle.jdbc.driver.OracleDriver");
		
	    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
	    
	    Statement stmt = con.createStatement();
	    
//	    stmt.executeUpdate("""
//	    		              INSERT INTO course(course_id, course_name, course_fee)
//		                  VALUES(101, 'Crs1', 3500)      
//	    		              """);
//	    
//	    stmt.executeUpdate("""
//	              INSERT INTO course(course_id, course_name, course_fee)
//            VALUES(102, 'Crs2', 2500)      
//	              """);
//	    
//	    stmt.executeUpdate("""
//	              INSERT INTO course(course_id, course_name, course_fee)
//            VALUES(103, 'Cr3', 3500)      
//	              """);
//	    
//	    stmt.executeUpdate("""
//	              INSERT INTO course(course_id, course_name, course_fee)
//            VALUES(104, 'Cr4', 3500)      
//	              """);
	    
	    // Update table
//	    stmt.executeUpdate("""
//	    		               UPDATE course
//	    		               SET course_fee = course_fee + 1000
//	    		               WHERE course_name LIKE 'Crs%'
//	    		              """);
	    
	  //DELETE ROWS
	    
	    stmt.executeUpdate("""
	    		               DELETE course
	    		               WHERE course_id IN (5, 6)
	    		               """);
	    stmt.close();
	    con.close();
	}

}

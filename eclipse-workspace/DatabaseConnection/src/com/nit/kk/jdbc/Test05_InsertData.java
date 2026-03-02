package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test05_InsertData {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
	    
	    Statement stmt = con.createStatement();
	    
	    // Course Table Data inserting
	    
	      stmt.executeUpdate("""
	    		               INSERT INTO course(course_id, course_name, course_fee)
	    		               VALUES(courseSeq.nextVal, 'Core Java', 3500)
	    		               """);
	    
	      stmt.executeUpdate(
					"""
					INSERT INTO course(course_id, course_name, course_fee)
					VALUES(courseSeq.nextval, 'Oracle', 2500)
					"""
				);	
	      
	      stmt.executeUpdate(
					"""
					INSERT INTO course(course_id, course_name, course_fee)
					VALUES(courseSeq.nextval, 'HTML, CSS, JS', 2500);
					"""
				);	
			
		stmt.executeUpdate(
					"""
					INSERT INTO course(course_id, course_name, course_fee)
					VALUES(courseSeq.nextval, 'Adv Java', 3500);
					"""
				);	
		stmt.close();
		con.close();
	    
	}

}

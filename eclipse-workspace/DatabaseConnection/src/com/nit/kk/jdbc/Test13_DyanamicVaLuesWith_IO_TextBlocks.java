/*
 Test 12 program are also Dyanamic but in that program we not use textblock.
 */

package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test13_DyanamicVaLuesWith_IO_TextBlocks {

	public static void main(String[] args) {		
		
		try(
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
				Statement stmt = con.createStatement();
				
			){
			String option = "N";
			
			do {
				
				int courseId = Integer.parseInt(IO.readln("Enter the course Id: "));
				String courseName = IO.readln("Enter course name: ");
				double coursefee = Double.parseDouble(IO.readln("Enter the course fee: "));
	            
				String query = """
						       INSERT INTO course(course_id, course_name, course_fee)
						       VALUES(%d, '%s', %f)
						       """;
				
				// if we use textBlock then we must use query.formatted()
				stmt.executeUpdate(query.formatted(courseId, courseName, coursefee));
				System.out.println("1 row inserted!");
				
				option = IO.readln("Do you want to continue ?(Y/N): ");
				
			}while(option.equalsIgnoreCase("Y"));
			
			System.out.println("_/\\_ Thanks You, Visit again _/\\_");			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}

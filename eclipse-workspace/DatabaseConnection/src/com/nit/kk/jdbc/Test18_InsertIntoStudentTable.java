/* 
  Devlop a program in jdbc to insert row in student table
  1> use Autoloading driver
  2> use TRY with RESOURCE 
  3> use properties file.
 */



package com.nit.kk.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Test18_InsertIntoStudentTable {

	public static void main(String[] args) {
        Properties props = new Properties();
		
		try(FileReader fr = new FileReader("StudentConnection.properties")){
			props.load(fr);
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String url = props.getProperty("URL");
		String username = props.getProperty("USERNAME");
		String password = props.getProperty("PASSWORD");
			
		try(
			     Connection con = DriverManager.getConnection(url, username, password);
			     PreparedStatement pstmt = con.prepareStatement("""
			     		                        INSERT INTO student VALUES(studentSeq.nextVal,?,
			     		                        (SELECT course_id FROM course WHERE UPPER(course_name) = UPPER(?)),
			     		                        (SELECT course_fee FROM course WHERE UPPER(course_name) =UPPER(?)))  
			     		                                      """);
			   ){
				
				String option = "N";
				
				do {
					
					String studentName = IO.readln("Enter the Student name: ");	
					String courseName = IO.readln("Enter the course name: ");
					
					pstmt.setString(1, studentName);
					pstmt.setString(2,courseName);
					pstmt.setString(3,courseName);
					
					try {
						
						 pstmt.executeUpdate();
						IO.println("Student enrolled in "+courseName+" course");
					}catch(SQLException e)
					{
						System.err.println("Enter valid course name!");
					}
					
					option = IO.readln("Do you want to continue?(Y/N): ");
					}while(option.equalsIgnoreCase("Y"));
			
				System.out.println("*****************Thanks you, visit again!**************");
			}catch(SQLException e)
			{
				e.printStackTrace();
			}

	}

}

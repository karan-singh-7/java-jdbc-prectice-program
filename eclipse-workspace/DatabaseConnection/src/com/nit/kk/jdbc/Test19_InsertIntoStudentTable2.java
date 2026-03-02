package com.nit.kk.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Test19_InsertIntoStudentTable2 {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		try(FileReader fp = new FileReader("connectionInfo.properties"))
		{
			props.load(fp);
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		try(
			Connection con = DriverManager.getConnection(
					                                       props.getProperty("URL"),
					                                       props.getProperty("USERNAME"),
					                                       props.getProperty("PASSWORD")
					                                     );
				
		    PreparedStatement studentPstmt = con.prepareStatement("""
		   		                                          INSERT INTO student(sid, sname, course_id, fee)
		   		                                          VALUES(studentSeq.nextVal,?,?,?)
		   		                                          """);	
		    
			PreparedStatement coursePstmt = con.prepareStatement(
					                                             """
					                                             SELECT course_id FROM course
					                                             WHERE UPPER(course_name) = UPPER(?)
					                                             """);
				
			){
			
			String option = "N";
			do
			{
				
				String sname = IO.readln("Enter the student name");
				
				int courseId;
				while(true) {
					
					String courseName = IO.readln("Enter course name: ");
					coursePstmt.setString(1, courseName);
					
					try(ResultSet rs = coursePstmt.executeQuery())
					{
					   if(rs.next()) {
						   courseId = rs.getInt(1);
						   break;
					   }
					   else {
						   
						   System.out.println(courseName+" not Available! Choose course from below list!!");
						  try(
								  Statement stmt = con.createStatement();
								  ResultSet rs2 =  stmt.executeQuery("""
								   		             SELECT course_name FROM course ORDER BY course_id
								   		            """);
							  ){
							  
							          int courseNumber = 1;
							          while(rs2.next()) {
								      System.out.println(courseNumber+". "+rs2.getString(1));
								      courseNumber++;
						      }
							  
						   }
						   
					   }
					}
				}
				
				double fee = Double.parseDouble(IO.readln("Enter the course fee: "));
				studentPstmt.setString(1, sname);
				studentPstmt.setInt(2, courseId);
				studentPstmt.setDouble(3, fee);
				
				studentPstmt.executeUpdate();
				IO.println("1 row inserted");
				
				
				option = IO.readln("Do you want to continue?(Y/N):");
			
			}while(option.equalsIgnoreCase("Y"));
			
			IO.println("Thanks you! Visit Again");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}

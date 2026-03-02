package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test03_CreateTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//1. Loadding oracle driver into JVM
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	      System.out.println("Driver is loaded!");
	      

	      //2. Creating connection to DB
		  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
	      System.out.println("Connection is established");   
	      

	      //3. Creating statement object
	      Statement stmt = con.createStatement();
	      System.out.println("Statement Created!");
	      
	      try
		  {
			  String courseTableQuery = 	
						"""
			  	          CREATE TABLE course
			  	          ( 		    
							course_id	NUMBER(4) PRIMARY KEY,		
							course_name	VARCHAR2(50)	 UNIQUE NOT NULL,  	
							course_fee	Number(7,2)    	
						)""";
			  
			  String studentTable =
					  """
                       		CREATE TABLE student(
					   sid		NUMBER(4)	PRIMARY KEY,
					   sname	    VARCHAR2(20)	NOT NULL,
					   course_id 	NUMBER(4)	REFERENCES course(course_id),
					   fee		NUMBER(7,2)	 	  
					   )		
			  		  """;
			  stmt.execute(courseTableQuery);
			  System.out.println("Course Table is created!!");
			  
			  stmt.execute(studentTable);
			  System.out.println("Student table is created!!");
		  }
		  catch(SQLException e)
		  {
			  if(e.getErrorCode()==955)
			  {
				  System.out.println("Table already exists!!");
			  }
			  else
			  {
				  System.out.println("Table creation failed: "+e.getMessage());
			  }
		  }
		  finally
		  {
			//Closing statement
			  stmt.close();
			  con.close();  
		  }
	      
	}

}

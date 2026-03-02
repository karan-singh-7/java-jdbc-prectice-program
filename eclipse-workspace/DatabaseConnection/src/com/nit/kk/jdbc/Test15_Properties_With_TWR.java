package com.nit.kk.jdbc;

/* This program is same as a test14 program but in this program i use TWA and
   jdbc 4v Autoloading driver 
 */

import java.util.Properties;

import java.io.FileReader;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Test15_Properties_With_TWR {

	public static void main(String[] args){
		
		Properties props = new Properties();
		
		//Load propperties safely
		
		try(FileReader fr = new FileReader("driverinfo.properties")) {
			props.load(fr);
			
		}catch(IOException e)
		{
		    IO.println("Failed to load DB properties file!");
		    return;
		}
			
			String  url        = props.getProperty("URL");
		    String	username   = props.getProperty("USERNAME");
		    String	password   = props.getProperty("PASSWORD");
		    String  query      = props.getProperty("QUERY");
			
		
		try(
				Connection con = DriverManager.getConnection(url, username, password);
				Statement stmt = con.createStatement();
				
			){
			
			
			String option = "N";
			do {
				
				int courseId = Integer.parseInt(IO.readln("Enter the course Id: "));
				String courseName = IO.readln("Enter course name: ");
				double coursefee = Double.parseDouble(IO.readln("Enter the course fee: "));
				
				stmt.executeUpdate(query.formatted(courseId,courseName, coursefee));
				
				IO.println("1 row inserted");
				option = IO.readln("Do you want to continue? (Y/N): ");
				
			}while(option.equalsIgnoreCase("Y"));
			
			System.out.println("_/\\_ Thanks You, Visit again _/\\_");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}// main close

}// class close

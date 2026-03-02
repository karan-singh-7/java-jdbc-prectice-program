package com.nit.kk.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Test17_PreparedStatement_With_TWA {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		
		try(FileReader fr = new FileReader("connectioninfo.properties")){
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
		String query = props.getProperty("QUERY");
		
		try(
		     Connection con = DriverManager.getConnection(url, username, password);
		     PreparedStatement pstmt = con.prepareStatement(query);
		   ){
			
			String option = "N";
			
			do {
				
				String courseName = IO.readln("Enter the course name: ");	
				double coursefee = Double.parseDouble(IO.readln("Enter the course fee: "));
				
				pstmt.setString(1, courseName);
				pstmt.setDouble(2, coursefee);
				
				pstmt.execute();
				System.out.println("1 row inserted!");
				
				option = IO.readln("Do you want to continue?(Y/N): ");
				}while(option.equalsIgnoreCase("Y"));
		
			System.out.println("*****************Thanks you, visit again!**************");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

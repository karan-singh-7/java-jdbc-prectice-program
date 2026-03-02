package com.nit.kk.jdbc;

import java.util.Properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Test14_PropertiesFile {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Properties props = new Properties();
		props.load(new FileReader("driverinfo.properties"));
		
		String driver = props.getProperty("DRIVER");
		String url = props.getProperty("URL");
		String username = props.getProperty("USERNAME");
		String password = props.getProperty("PASSWORD");
		String query = props.getProperty("QUERY");
		
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		
		String option = "N";
		do {
			
			int courseId = Integer.parseInt(IO.readln("Enter the course Id: "));
			String courseName = IO.readln("Enter course name: ");
			double coursefee = Double.parseDouble(IO.readln("Enter the course fee: "));
			
			stmt.executeUpdate(query.formatted(courseId,courseName, coursefee));
			
			IO.println("1 row inserted");
			option = IO.readln("Do you want to continue? (Y/N): ");
			
		}while(option.equalsIgnoreCase("Y"));
		
		IO.println("Thanks you, visit again!");
		
		stmt.close();
		con.close();
	}

}

package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
public class Test12_TWA_DyanamicValues {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		//Driver Are Auotmatic loaded
		try(
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
			 Statement stmt = con.createStatement();
			){
			Scanner sc = new Scanner(System.in);
			String option = "N";
			do {
				
				System.out.println("Enter the course id: ");
				int courseId = Integer.parseInt(sc.nextLine());
				
				System.out.println("Enter the course name: ");
			    String courseName = sc.nextLine();
				
				System.out.println("Enter the course fee: ");
				double courseFee = Double.parseDouble(sc.nextLine());
				
				stmt.executeUpdate("INSERT INTO course(course_id, course_name, course_fee)"+
				                    "VALUES("+courseId+", '"+courseName+"' ,"+courseFee+")"
				                      );
				System.out.println("1 row inserted");
				
				System.out.println("Do you want to continue(Y/N)?");
				option = sc.nextLine();
				
			}while(option.equalsIgnoreCase("Y"));
			
			System.out.println("_/\\_ Thanks You, Visit again _/\\_");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}

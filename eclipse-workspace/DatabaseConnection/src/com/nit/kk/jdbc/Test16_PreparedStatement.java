package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test16_PreparedStatement {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			//1.> Create connnection
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "username", "password");
			
			//2.> Create preparedStatement object
			
			pstmt = con.prepareStatement(
					                     """
					                     INSERT INTO course(course_id, course_name, course_fee) VALUES(courseSeq.nextVal,?,?)
					                     """
					                     );
			
			String option = "N";
			
			do {
				//3.> Taking input from the user
				
				String courseName = IO.readln("Enter the course name: ");
				double coursefee = Double.parseDouble(IO.readln("Enter the course fee: "));
				
				//4> put above input in prepared statement through setter method
				
				pstmt.setString(1,courseName);
				pstmt.setDouble(2, coursefee);
				
				
				pstmt.execute();
				System.out.println("1 row inseted!");
				
				option = IO.readln("Do you want to continue? (Y/N): ");
			}while(option.equalsIgnoreCase("Y"));
			
			IO.println("*************** Thanks you, visit again! *************");
			
		}
		catch(SQLException e){
			IO.println("SQL side error......");
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt = null;
				}
			}
			catch(SQLException e){}
			
			try {
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}
			catch(SQLException e) {}
		}
	}

}

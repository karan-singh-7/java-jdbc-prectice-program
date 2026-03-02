//package com.nit.kk.jdbc;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//
//public class Test10_DisplayRowAndColumn {
//
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		
//
//Class.forName("oracle.jdbc.driver.OracleDriver");
//		
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
//		
//		Statement stmt = con.createStatement();
//		
//		ResultSet rs = stmt.executeQuery("""
//				                         SELECT *
//				                         FROM course
//				                        """);
//		
//		ResultSetMetaData rsmd = rs.getMetaData();
//		
//		    if(rs.next())
//			{
//		    	   int totalWidth = 0;
//				for(int i=1; i<=rsmd.getColumnCount(); i++)
//				{
//					int width = rsmd.getColumnDisplaySize(i)+rsmd.getColumnName(i).length();
//					totalWidth+=width;
//			        System.out.printf("%-"+width+"s",rsmd.getColumnName(i));
//				}
//				    System.out.println();
//					System.out.println("-".repeat(totalWidth));
//				
//				
//				do
//				{
//					for(int i=1; i<=rsmd.getColumnCount(); i++)
//					{
//						int width = rsmd.getColumnDisplaySize(i)+rsmd.getColumnName(i).length();
//						System.out.printf("%-"+width+"s",rs.getString(i));
//					}
//					System.out.println();
//				}while(rs.next());
//			}
//		    else
//		    {
//		    	  System.out.println("no rows selected");
//		    }
//		
//		
//		rs.close();
//		stmt.close();
//		con.close();
//	}
//
//}
























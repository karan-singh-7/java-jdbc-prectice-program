package com.nit.kk.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class Test20_PStmt_SelectStudent_JoinIN{
	public static void main(String[] args) {
		
		Properties props = new Properties();
		
		try(FileReader fr = new FileReader("connectionInfo.properties")){
			
			props.load(fr);
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	     String url = props.getProperty("URL");
	     String usr = props.getProperty("USERNAME");
	     String pwd = props.getProperty("PASSWORD");
	     
	     
	     try(
	    		 Connection con = DriverManager.getConnection(url, usr, pwd);
	    		 
	    		 PreparedStatement allCoursePstmt = con.prepareStatement("""
	    		 		                                                 SELECT s.sid, s.sname,c.course_name,s.fee
	    		 		                                                 FROM student s INNER JOIN course c
	    		 		                                                 ON s.course_id = c.course_id 
	    		 		                                                 ORDER BY s.sid
	    		 		                                                 """);
	    		 
	    		 PreparedStatement oneCoursePstmt = con.prepareStatement("""
	    		 		                                                 SELECT s.sid, s.sname,c.course_name,s.fee
	    		 		                                                 FROM student s INNER JOIN course c
	    		 		                                                 ON s.course_id = c.course_id 
	    		 		                                                 WHERE UPPER(c.course_name) = UPPER(?)
	    		 		                                                 ORDER BY s.sid
	    		 		                                                 """);
	    		 
	    		 ){
	    	 
	  loop:while(true)
	    	 {
	    		 IO.println("\nChoose any one option");
	    		 IO.println("1. ALL Course Details.");
	    		 IO.println("2. one course Detail.");
	    		 IO.println("3. Multiple courses Details.");
	    		 IO.println("4. Exit!");
	    		 
	    		 int choice = Integer.parseInt(IO.readln("\n Enter choice: "));
	    		 
	    		 switch(choice) {
	    		 case 1->{
	    			 displayDetails(allCoursePstmt);
	    			 break;
	    		 }
	    		 case 2->{
	    			      String coursename = IO.readln("Enter the course name :");
	    			      oneCoursePstmt.setString(1, coursename);
	    			      displayDetails(oneCoursePstmt);
	    			      break;
	    		 }
	    		 case 3->{
	    			 String courses = IO.readln("Enter multiple course seperated by | :");
	    			 String[] course = courses.split("\\|");
	    			 
	    		String placeholder = String.join(",",Arrays.stream(course).map( n-> "?").toArray(String[]::new));
	    			 
	    			 	    			 
//	    			 StringBuilder sf = new StringBuilder();
//	    			 for(int i=0; i<course.length; i++) {
//	    				 if(i == course.length-1) {
//	    					 sf.append("?");
//	    				 }else {
//	    					 sf.append("?,");
//	    				 }
//	    		     }
	    			 try(
	    				    PreparedStatement multiCoursePstmt = con.prepareStatement("""
	    				    		                                         SELECT s.sid, s.sname,c.course_name,s.fee
	    		 		                                                 FROM student s INNER JOIN course c
	    		 		                                                 ON s.course_id = c.course_id 
	    		 		                                                 WHERE UPPER(c.course_name) IN (%s)
	    		 		                                                 ORDER BY s.sid  
	    				    		                                                      """.formatted(placeholder)) ;
	    				 ){
	    				 
	    				 for(int i=0; i<course.length;i++) {
	    					 multiCoursePstmt.setString(i+1, course[i].trim().toUpperCase());
	    				 }
	    				 
	    				 displayDetails(multiCoursePstmt);
	    				 
	    			 }catch(SQLException e) {
	    				 e.printStackTrace();
	    			 }
	    		 }
	    		 
	    		 default ->{
	    			 System.out.print("Tata, byyy");
	    			 break loop;
	    		 }
	    		 }
	    	 }
	    	 
	    	 
	     }catch(SQLException e) {
	    	 e.printStackTrace();
	     }
	}
	
	public static void displayDetails(PreparedStatement pstmt) {
		
		try(ResultSet rs = pstmt.executeQuery()){
			
			if(rs.next()) {
				
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int columnCount = rsmd.getColumnCount();
				
				int maxWidth;
				String frmMsg, columnName;
				for(int i=1; i<=columnCount; i++) {
					
					columnName = rsmd.getColumnName(i);
					maxWidth = Math.max(rsmd.getColumnDisplaySize(i), columnName.length());
					
					if(rsmd.getColumnTypeName(i).toLowerCase().contains("varchar2")) {
						frmMsg = "%-"+maxWidth+"s ";
					}
					else
					{
						frmMsg = "%"+maxWidth+"s ";
					}
					System.out.printf(frmMsg, columnName);
				}
				System.out.println();
				
				for(int i=1; i<=rsmd.getColumnCount();i++) {
					columnName = rsmd.getColumnName(i);
					maxWidth = Math.max(rsmd.getColumnDisplaySize(i), columnName.length());
					
					StringBuffer sb = new StringBuffer();
					sb.repeat("-", maxWidth);
					System.out.print(sb+" ");
				}
				
				System.out.println();
				
				do {
					for(int i=1; i<=columnCount; i++) {
						columnName = rsmd.getColumnName(i);
						maxWidth = Math.max(rsmd.getColumnDisplaySize(i),columnName.length());
						
						if(rsmd.getColumnTypeName(i).toLowerCase().contains("varchar2")) {
							frmMsg = "%-"+maxWidth+"s ";
						}
						else
						{
							frmMsg = "%"+maxWidth+"s ";
						}
						System.out.printf(frmMsg, rs.getString(i));
					}
					System.out.println();
				}while(rs.next());
				
			}else {
				IO.println("No row selected!");
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}






























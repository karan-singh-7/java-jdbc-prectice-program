package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test11_BatchUpdates {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
   		
       Class.forName("oracle.jdbc.driver.OracleDriver");
       
       Connection con = DriverManager.getConnection(
    		                                            "jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password"
    		                                           );
       Statement stmt = con.createStatement();
       
      try(con;stmt)
      {
    	      // adding dml updatable query to stmt object batch(collection)
          
          stmt.addBatch("INSERT INTO course VALUES(101,'cr1',3500)");
          stmt.addBatch("INSERT INTO course VALUES(102,'cr2',2500)");
          stmt.addBatch("INSERT INTO course VALUES(103,'crs3',2500)");
          stmt.addBatch("INSERT INTO course VALUES(104,'crs4',3500)");

          //Update the data from batch method.
          stmt.addBatch("""
          		         UPDATE course 
          		         SET course_fee = course_fee + 1000
          		         WHERE course_name LIKE '%crs%'
          		         """);
          
          //Delete the row from batch method
          stmt.addBatch("""
          		         DELETE FROM course
          		         WHERE course_name IN ('cr1' , 'cr2')
          		        """);
          		
          
          int[] result = stmt.executeBatch();
          
          for(int i=0; i<result.length; i++){
       	   
       	     IO.println(result[i]);
          }
          
      }
      catch(SQLException e)
      {
    	     if(e.getErrorCode()==00001)
    	     {
    	    	    IO.println("Values Already exit!");
    	     }
    	     else
    	     {
    	    	   IO.println(e);
    	     }
      }
       
	}

}

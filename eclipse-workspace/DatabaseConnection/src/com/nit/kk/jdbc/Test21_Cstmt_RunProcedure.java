/* 
CallableStatement
===================
	1. The CallableStatement is a Statement object
	2. It is a Sub interface of PreparedStatement 
	3. It is used for executing stored procedures in a database
	   Stored procedures means procedures, functions and cursors stored in the DB server
	4. It allows calling the stored procedures with IN, OUT, and INOUT parameters.
	
	5. In addition to the methods inherited from Statement and PreparedStatement interfaces
	   the CallableStatement interface provides below new methods
	   
	   	1. method for registering OUT parameter
	   	 	public void registerOutParameter(int parameterIndex, int sqlType)
						- for setting sqlType value, we must use Types class
						
		2. methods for setting values to IN parameters
		 	public void setXxx(int paramterIndex, xxx value) //inherited from PStmt
		 	
		3. methods for getting values to OUT parameters
		 	public xxx getXxx(int paramterIndex)
		 	
		4. For running procedure we will use 
			the no-param execute() method inherited from the PreparedStatement 	
						
	6. The CallableStatement object is created by using 
		the Connection.prepareCall() method.
		
	7. The syntax for calling Procedure with IN and OUT parameters
			{call <procedureName>(?, ?, ?, ...)}
		
	   The syntax for calling function with IN and OUT parameters
			{? = call <functionName>(?, ?, ?, ...)}
		  //this ? must be OUT parameter 
	
Program 20: 
========================================================================
   Develop a program to run a procedure from Java application
   for incrementing sal of a given employee with given sal.
   Later read & display this employee's updated sal.
========================================================================

Step : Compile below procedure in oracle DB

	CREATE or REPLACE PROCEDURE 
		updateEmpSal(
			empnum		IN		NUMBER, 
			incrSal		IN		NUMBER, 
			empname		OUT		VARCHAR2,
			empsal		OUT		NUMBER,
			empdept		OUT		VARCHAR2
		)
	IS
		oldSal employee.sal%TYPE;
		
	BEGIN

		SELECT	sal INTO oldSal 
		FROM	employee 
		WHERE	eno = empnum;

		UPDATE	employee
		SET		sal = oldSal + incrSal
		WHERE	eno = empnum;

		COMMIT;

		SELECT	ename, sal, dept	
		INTO	empname, empsal, empdept
		FROM	employee
		WHERE	eno = empnum;

	END;
	/

*/

package com.nit.kk.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class Test21_Cstmt_RunProcedure{
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
				Connection con = DriverManager.getConnection(url,usr,pwd);
				CallableStatement Cstmt = con.prepareCall("{call updatesal(?,?,?,?,?)}");
		   ){
			
			// Registring out parameter type
			Cstmt.registerOutParameter(3, Types.VARCHAR);
			Cstmt.registerOutParameter(4, Types.NUMERIC);
			Cstmt.registerOutParameter(5, Types.VARCHAR);
			
			//Taking input from user for IN parameter
			
			int eno  = Integer.parseInt(IO.readln("Enter the employee no: "));
		    double incr = Double.parseDouble(IO.readln("Enter the amount for incriment: "));
		    
		    //seting value to a in parameter
		    
		    Cstmt.setInt(1, eno);
		    Cstmt.setDouble(2, incr);
		    
		    // executing callable statement
		    
		    Cstmt.execute();
		    
		    //reading out parameter value from callable statement
		    
		    String ename = Cstmt.getString(3);
		    BigDecimal sal = Cstmt.getBigDecimal(4);
		    String dept = Cstmt.getString(5);
			
		    //Printing updated value
		    IO.println("Employee Details after updataion:");
		    IO.println("Employee name: "+ename);
		    IO.println("Employee sal: "+sal);
		    IO.println("Employee department: "+dept);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}






























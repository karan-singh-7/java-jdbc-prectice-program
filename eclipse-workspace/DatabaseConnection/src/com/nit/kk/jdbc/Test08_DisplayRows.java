package com.nit.kk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Test08_DisplayRows {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","username", "password");
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("""
				                         SELECT course_id, course_name, course_fee
				                         FROM course ORDER BY course_id
				                        """);
		while(rs.next()){
			
			IO.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3));
		}
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		for(int i=1; i<=rsmd.getColumnCount(); i++)
		{
			IO.print(rsmd.getPrecision(i)+" ");
		}
		
		rs.close();
		stmt.close();
		con.close();
	}

}

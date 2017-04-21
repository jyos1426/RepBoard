package com.my.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MyConnection {
	
	public static Connection getConnection() throws Exception {
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
		return conn;
	}
	
	public static void close(Connection con){
		if(con!=null){	
			try {			
					con.close();
				}
			 catch (SQLException e1) {
				e1.printStackTrace();
			 }
		}
	}	
	public static void close(Connection con,Statement stmt){
		if(stmt !=null){
			try {
					stmt.close();
				}
			 catch (SQLException e) {
				e.printStackTrace();
			}		
		}	
		close(con);
	}		
	public static void close(Connection con,Statement stmt, ResultSet rs){
		if(rs!=null){	
			try {			
					rs.close();
				}
			 catch (SQLException e1) {
				e1.printStackTrace();
			 }
		}
		close(con,stmt);
	}	
	//Overrode 된 close methods

}

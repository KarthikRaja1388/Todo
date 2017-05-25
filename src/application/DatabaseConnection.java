package application;

import java.sql.*;

public class DatabaseConnection {
	
	public static Connection dbConnector(){
		
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Todolist.sqlite");
			return conn;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
}

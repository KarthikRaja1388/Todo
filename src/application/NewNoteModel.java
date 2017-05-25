package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewNoteModel {

Connection connect;
	
	//Constructor to instantiate the Database connection
	public NewNoteModel(){
		connect = DatabaseConnection.dbConnector();
		if(connect == null) System.exit(1);//if DB not connected exit the system
	}
	
	public boolean isDbConnected(){
		//if DB is connected return true else false
		try {
			return !connect.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addNote(String user,String note,String category,String dateAdded,String dueDate,String time,String priority ){
		PreparedStatement pst = null;
		String query = "insert into Notes (UserName,Note,Category,DateAdded,DueDate,Time,Priority) values(?,?,?,?,?,?,?)";
		
		try {
			pst = connect.prepareStatement(query);
			
			pst.setString(1, user);
			pst.setString(2, note);
			pst.setString(3, category);
			pst.setString(4, dateAdded);
			pst.setString(5, dueDate);
			pst.setString(6, time);
			pst.setString(7, priority);
			
			pst.executeUpdate();
			pst.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}

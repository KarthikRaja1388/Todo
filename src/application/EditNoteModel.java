package application;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditNoteModel {
	
Connection connect;
	
	//Constructor to instantiate the Database connection
	public EditNoteModel(){
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

	public boolean isNoteSaved(String note,String category,String dateAdded,String dueDate,String time,String priority,String id){
		
		PreparedStatement pst = null;
		String query = "update Notes set Note =?,Category = ?, DateAdded = ?, DueDate = ?, Time = ?,Priority = ? where NoteID = ?";
		
		try {
			pst = connect.prepareStatement(query);
			pst.setString(1, note);
			pst.setString(2, category);
			pst.setString(3, dateAdded);
			pst.setString(4, dueDate);
			pst.setString(5, time);
			pst.setString(6, priority);
			
			pst.setString(7, id);
			pst.executeUpdate();
			
			pst.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		
	}
}

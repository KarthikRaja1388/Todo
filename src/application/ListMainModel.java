package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ListMainModel {

Connection connect;
	
	//Constructor to instantiate the Database connection
	public ListMainModel(){
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
	
	public boolean loadTableData(ObservableList<Notes>data,TableView<Notes>notes){
		String query = "select * from Notes";
		ResultSet rs = null;
		try{
			PreparedStatement pst = connect.prepareStatement(query);
			//pst.setString(1, user);
			rs = pst.executeQuery();
			
			while(rs.next()){
				data.add(new Notes(rs.getString("Username"),rs.getString("Note"), rs.getString("Category"),rs.getString("DateAdded"),
						rs.getString("DueDate"), rs.getString("Time"), rs.getString("Priority"),rs.getInt("NoteID")));
				
				notes.setItems(data);

			}
			
			pst.close();
			rs.close();
			return true;
			
		}catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteData(int id){
		String query = "delete from Notes where NoteID = ?";
		
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setInt(1, id);
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

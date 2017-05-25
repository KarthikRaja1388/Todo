package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Reminder extends TimerTask {

	Connection connect;

	public Reminder(){
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
	
	@Override
	public void run() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:MM");
		
		String currentDate = dateFormat.format(date).substring(0, 10);
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "select * from Notes where DueDate = ?";
		
		try {
			pst = connect.prepareStatement(query);
			pst.setString(1, currentDate);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				String reminder = rs.getString("Note");
				String dueTime = rs.getString("Time");
							

				JOptionPane.showMessageDialog(null, reminder+"\n"+dueTime);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
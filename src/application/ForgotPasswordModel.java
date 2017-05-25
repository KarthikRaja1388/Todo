package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ForgotPasswordModel {

	Connection connect;
	PreparedStatement pst;

	//Constructor to instantiate the Database connection
	public ForgotPasswordModel(){
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
	
	public boolean isEmailAvailable(String email) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "select * from UserDetails where Email = ?";

		try {
			pst = connect.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();
			
			if(rs.next())
				return true;
			else
				return false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			pst.close();
			rs.close();
		} 
	}
	
	//random number generator
	public static int randomNumber(){
		Random random = new Random();
		int result = random.nextInt(100000-10000+1)+10000;
		return result;
	}
	
	//code validation
	public static boolean isCodeValidted(int codeSent,int codeEntered){
		if(codeSent == codeEntered)
			return true;
		else
			return false;
	}
	
	public void updatePassword(String pwd,String cfmPwd,String email) throws SQLException{
		PreparedStatement pst = null;
		String query = "update UserDetails set Password =?,ConfirmPwd = ? where Email = '"+email+"'";
			try {
				pst = connect.prepareStatement(query);
				pst.setString(1, pwd);
				pst.setString(2, cfmPwd);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				pst.close();

			}
		}
}

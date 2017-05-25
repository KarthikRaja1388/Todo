package application;

import java.sql.*;

/*Login Model*/

public class LoginModel {

	Connection connect;
	
	//Constructor to instantiate the Database connection
	public LoginModel(){
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
	
	/*Login Logic
	 * ----------
	 * method isLogin takes to Parameters
	 * string query will hold the query to search for user and pass
	 * if condition will check the database until the end of table
	 * */
	public boolean isLogin(String user,String pass) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from UserDetails where UserName = ? and Password = ?";
		
		try{
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			return false;
		}
		finally{
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	
}

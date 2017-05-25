package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterModel {

	static Connection connect;
	
	public RegisterModel(){
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
	
	/*Method to process the logic once event received from btnRegister
	 * and set all the arguments received to the appropriate fields in database
	 * 
	 * @param FN         		takes first name from the controller class
	 * @param LN    	 		takes last name from the controller class
	 * @param email  	 		takes email from the controller class
	 * @param phone  	 		takes Phone from the controller class
	 * @param dob    			takes dob from the controller class
	 * @param un      	 		takes username  from the controller class
	 * @param pwd     	 		takes Password from the controller class
	 * @param confrimPwd 		takes confirm pwd from the controller class
	 * 
	 * @param prpearedStatment  to set text in the appropriate index in db
	 * @param query      		holds the command to insert data received from controller class into database
	 * 
	 * 
	 * */
	public boolean isRegister(String FN,String LN,String email,String phone,String dob,String un,String pwd,String cfmPwd){
		PreparedStatement preparedStatement = null;
		String query = "insert into UserDetails (FirstName,LastName,Email,Phone,DOB,UserName,Password,ConfirmPwd) values (?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connect.prepareStatement(query);

			preparedStatement.setString(1, FN);
			preparedStatement.setString(2, LN);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, dob);
			preparedStatement.setString(6, un);
			preparedStatement.setString(7, pwd);
			preparedStatement.setString(8, cfmPwd);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/*
	 * Name validation model
	 * 
	 * @param FN parameter takes the first name from controller class
	 * @param LN parameter takes the Last name from controller class
	 * 
	 * if first and last name matches the regex
	 * 
	 * @return returns false if FN and LN doesn't match regex
	 * @return returns true if FN and LN matches regex
	 * */
	public static boolean isNameValidated(String FN,String LN){
		
		if(!FN.matches("[A-Z][a-zA-Z]*")&&!LN.matches("[a-zA-z]+([ '-][a-zA-Z]+)*")){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * Method to validate Mail
	 * 
	 * @param String email takes the string value passed from Register Controller
	 * @param mailPattern regex pattern to check mail
	 * 
	 * if email matches pattern 
	 * @return true
	 * else
	 * @return false
	 * */
	public static boolean isMailValidated(String email){
		String mailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(mailPattern);
		Matcher matcher = pattern.matcher(email);
		
		if(matcher.matches()){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static boolean isPhoneValidated(String phone){
		String phonePattern = "^[0-9]{9,10}$";
		Pattern pattern = Pattern.compile(phonePattern);
		Matcher matcher = pattern.matcher(phone);
		
		if(matcher.matches()){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isUserNameValidated(String user) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "select * from UserDetails where UserName = ?";
			try {
				pst = connect.prepareStatement(query);
				pst.setString(1, user);
				rs = pst.executeQuery();
				
				if(user != ""){
					int count = 0;
					while(rs.next()){
						count += 1;
					}
					if(count > 1)
						return false;
					else
						return true;
				}else{
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}finally{
				pst.close();
				rs.close();
			}
			
	}
	
	public static boolean isPwdMatches(String pwd,String ConfirmPwd,String username){
		if(pwd.equals(username)){
			return false;
		}else if(pwd.equals(ConfirmPwd)){
				return true;
		}else{
			return false;
		}
	}
	

	public static boolean isDateValidated(String date){
		
		if(date != ""||!date.matches("^[a-zA-Z]")){
			return true;
		}else{
			return false;
		}
		
	}
}

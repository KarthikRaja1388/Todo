package application;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	//Instance of Login model
	LoginModel loginModel = new LoginModel();
	
	@FXML
	private Button btnLogin,btnRegister,btnForgotPwd;
	@FXML
	private Label lblError;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPwd;
	
	@FXML
	public void loginHandler(ActionEvent event){
		Stage stage;
		Parent root;
		/* Login Button Handler */
		if(event.getSource()==btnLogin){
			/*
			 * if event source is btnLogin
			 * check login method with the user name and password fetched from DB
			 * if user name or password is right will display the screen
			 * else display an error label
			 * */
			try {
				if(loginModel.isLogin(txtUser.getText(), txtPwd.getText())){
					stage = (Stage) btnLogin.getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("ListMain.fxml"));
					root = loader.load();
					ListMainController listController = (ListMainController)loader.getController();
					listController.setUserName(txtUser.getText());
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.centerOnScreen();
					stage.show();
				}
				else{
					lblError.setText("Username or Password is incorrect. Please Try again.");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	

	/*register button Handling*/
	@FXML
	public void registerHandler(ActionEvent event) throws IOException{
		Stage stage;
		Parent root;
		/*If event source is btnRegister
		 * creates new stage
		 * Launches register window
		 * */
		if(event.getSource()==btnRegister){
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("Register.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("TO-DO-LIST");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(btnRegister.getScene().getWindow());
			stage.showAndWait();
		}
	}

	/*Forgot Password button handling*/
	@FXML
	public void forgotPassword(ActionEvent event) throws IOException{
		Stage stage;
		Parent root;
		/*If event source is btnForgotPwd
		 * Launches forgot password window
		 * */
		if(event.getSource()==btnForgotPwd){
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("TO-DO-LIST");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(btnRegister.getScene().getWindow());
			stage.showAndWait();
			
		}
	}
	



	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}


}

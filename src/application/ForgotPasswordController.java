package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.*;
import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class ForgotPasswordController implements Initializable {
	@FXML
	private TextField txtEmail;
	@FXML
	private Label lblMailsent1,lblMailsent2;
	@FXML
	private Button btnSendCode;
	@FXML
	private Button btnResend;
	@FXML
	private TextField txtCode;
	@FXML
	private Button btnValidate;
	@FXML
	private Label lblValidateError;
	@FXML
	private TextField txtNewPwd;
	@FXML
	private TextField txtRetypePwd;
	@FXML
	private Button btnSavePwd;

	ForgotPasswordModel pwdModel = new ForgotPasswordModel();//Object for ForgotPwdModel
	final int result = ForgotPasswordModel.randomNumber();//to generate random number
	

	
	
	/*
	 * Method to send code to the email
	 * */
	@FXML
	public void sendCode(ActionEvent event){
		if((event.getSource() == btnSendCode) || (event.getSource() == btnResend)){
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			Session session = Session.getDefaultInstance(props,
					
					new javax.mail.Authenticator(){
					
				protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
					return new javax.mail.PasswordAuthentication("info.todolist@gmail.com", "Dolly26314");
				}
			}
					
					);
			
			try{
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("info.todolist@gmail.com"));
				if(pwdModel.isEmailAvailable(txtEmail.getText())){
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmail.getText()));
					message.setSubject("Reset Password for To-do-list");
					message.setText("Password reset has been requested for your To-do-list Accont.Please enter the code below in your app\n\n"+result);
					Transport.send(message);
					
					lblMailsent1.setText("Code was sent to your e-mail.Please check your inbox");
					lblMailsent2.setText("or Junk folder. If you haven't received please resend it");
					
					Image imgInfo = new Image("complete.png");
					Notifications msgSent = Notifications.create()
							.title("")
							.text("      CODE WAS SENT TO YOUR E-MAIL")
							.graphic(new ImageView(imgInfo))
							.hideAfter(Duration.seconds(2))
							.position(Pos.CENTER);
					
					msgSent.darkStyle();
					msgSent.show();
					txtCode.setEditable(true);
					btnValidate.setDisable(false); 
				}else{
					Image imgInfo = new Image("error.png");
					Notifications msgSent = Notifications.create()
							.title("                                                            ")
							.text("ENTERED E-MAIL IS NOT ASSOCIATED WITH ANY ACCOUNTS OR INVALID")
							.graphic(new ImageView(imgInfo))
							.hideAfter(Duration.seconds(2))
							.position(Pos.CENTER);
					
					msgSent.darkStyle();
					msgSent.show();
					
					
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	@FXML
	public void validateHandler(ActionEvent event){
		if(event.getSource() == btnValidate){
			if(txtCode.getText().isEmpty()){
				lblValidateError.setText("Please enter a valid code");
			}else{
				if(ForgotPasswordModel.isCodeValidted(result, Integer.parseInt(txtCode.getText()))){
					lblValidateError.setText("Validated Successfully!!");
					lblValidateError.setTextFill(Color.web("#006600"));
					
					txtNewPwd.setEditable(true);
					txtRetypePwd.setEditable(true);
					
					btnSavePwd.setDisable(false);
				}else{
					lblValidateError.setText("Code is invalid");
				}
			}
		}
			
	}
	@FXML
	public void savePwdHandler(ActionEvent event) {
		
		String newPwd = txtNewPwd.getText();
		String cfmPwd = txtRetypePwd.getText();
		String mail = txtEmail.getText();
	
		if(event.getSource()==btnSavePwd){
			
			try {
				pwdModel.updatePassword(newPwd,cfmPwd,mail);
				
				Image imgInfo = new Image("complete.png");
				Notifications msgSent = Notifications.create()
						.title("                     ")
						.text("      PASSWORD UPDATED")
						.graphic(new ImageView(imgInfo))
						.hideAfter(Duration.seconds(2))
						.position(Pos.CENTER);
				
				msgSent.darkStyle();
				msgSent.show();
				resetAll();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	//Method to clear all textfields and labels
	public void resetAll(){
		
		txtEmail.setText("");
		txtCode.setText("");
		txtNewPwd.setText("");
		txtRetypePwd.setText("");
		lblMailsent1.setText("");
		lblMailsent2.setText("");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtCode.setEditable(false);
		txtNewPwd.setEditable(false);
		txtRetypePwd.setEditable(false);
		
		btnValidate.setDisable(true);
		btnSavePwd.setDisable(true);
		
	}
}

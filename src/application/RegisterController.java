package application;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RegisterController implements Initializable {
	
	@FXML
	private Button btnSave;
	@FXML
	private Button btnReset;
	@FXML
	private TextField txtFirstName,txtLastName,txtEmail,txtPhone,txtUserName,txtPwd,txtConfirmPwd;
	@FXML
	private DatePicker datePicker;
	

	RegisterModel regModel = new RegisterModel();
	Image imgError;


	/*
	 * Save button action handler
	 * */
	@FXML
	public void handlingSave(ActionEvent event) throws ParseException, SQLException{
		

		/*
		 * if Source of the event is btnSave
		 * check if the fields are empty
		 * if not empty save the data in database close the stage
		 * if it's empty show error notification
		 * */

		if(event.getSource() == btnSave){
			
			try{
				
				//Checking if textFields are empty,if empty it returns an error
				if(!txtFirstName.getText().equals("")||!txtLastName.getText().equals("")||!txtEmail.getText().equals("")||!txtPhone.getText().equals("")
						||!txtUserName.getText().equals("")||!txtPwd.getText().equals("")||!txtConfirmPwd.getText().equals("")||datePicker.getValue()!=null){
					//Name Validation conditional statement
					if(RegisterModel.isNameValidated(txtFirstName.getText(), txtLastName.getText())){
						//Email validation
						if(RegisterModel.isMailValidated(txtEmail.getText())){
						//Phone validation
							if(RegisterModel.isPhoneValidated(txtPhone.getText())){
							//Date Validation
								if(datePicker.getValue()!=null){
									
									if(RegisterModel.isDateValidated(datePicker.getValue().toString())){
							//User name validation
										if(RegisterModel.isUserNameValidated(txtUserName.getText())){
											
							//Password validation
											if(RegisterModel.isPwdMatches(txtPwd.getText(), txtConfirmPwd.getText(), txtUserName.getText())){
												
												regModel.isRegister(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(),
														txtPhone.getText(),datePicker.getValue().toString(), 
														txtUserName.getText(),txtPwd.getText(), txtConfirmPwd.getText());
									
												Stage stage = ((Stage)btnSave.getScene().getWindow());
												stage.close();
															
												/*
												 * Notification controlFx
												 * once the data is saved, notification will be displayed
												 * */
												Image img = new Image("tickNotification.png");
												Notifications notificationBuilder = Notifications.create()
														.title("                ")
														.text("SAVED TO DATABASE")
														.graphic(new ImageView(img))
														.hideAfter(Duration.seconds(2))
														.position(Pos.TOP_RIGHT);
												
												notificationBuilder.darkStyle();
													notificationBuilder.show();
													
											}else{//Else for password validation
												Image img = new Image("error.png");
												Notifications notificationBuilder = Notifications.create()
														.title("                ")
														.text("USERNAME AND PASSWORD CAN'T BE SAME")
														.graphic(new ImageView(img))
														.hideAfter(Duration.seconds(2))
														.position(Pos.CENTER);
												
												notificationBuilder.darkStyle();
												notificationBuilder.show();
											}
										}else{//Else for User name
											Image img = new Image("error.png");
											Notifications notificationBuilder = Notifications.create()
													.title("                ")
													.text("USERNAME ALREADY EXITS OR INVALID")
													.graphic(new ImageView(img))
													.hideAfter(Duration.seconds(2))
													.position(Pos.CENTER);
											
											notificationBuilder.darkStyle();
											notificationBuilder.show();
										}
									}else{//Else for date validation
										Image img = new Image("error.png");
										Notifications notificationBuilder = Notifications.create()
												.title("                ")
												.text("PLEASE CHOOSE A VALID DATE")
												.graphic(new ImageView(img))
												.hideAfter(Duration.seconds(2))
												.position(Pos.CENTER);
										
										notificationBuilder.darkStyle();
										notificationBuilder.show();
									}
						
							}else{//Else for empty date
								Image img = new Image("error.png");
								Notifications notificationBuilder = Notifications.create()
										.title("                ")
										.text("PLEASE CHOOSE A VALID DATE")
										.graphic(new ImageView(img))
										.hideAfter(Duration.seconds(2))
										.position(Pos.CENTER);
								
								notificationBuilder.darkStyle();
								notificationBuilder.show();
							}
							}else{//Else for phone validation
								Image img = new Image("error.png");
								Notifications notificationBuilder = Notifications.create()
										.title("                ")
										.text("PLEASE ENTER A VALID PHONE NUMBER")
										.graphic(new ImageView(img))
										.hideAfter(Duration.seconds(2))
										.position(Pos.CENTER);
								
								notificationBuilder.darkStyle();
								notificationBuilder.show();
							}
							}else{//Else for e-mail validation
								Image img = new Image("error.png");
								Notifications notificationBuilder = Notifications.create()
										.title("                ")
										.text("PLEASE ENTER A VALID E-MAIL")
										.graphic(new ImageView(img))
										.hideAfter(Duration.seconds(2))
										.position(Pos.CENTER);
								
								notificationBuilder.darkStyle();
								notificationBuilder.show();
							}
					}else{//else condition for name validation
						Image img = new Image("error.png");
						Notifications notificationBuilder = Notifications.create()
								.title("                ")
								.text("NAME CAN'T SPECIAL CHARACTERS AND NUMBER")
								.graphic(new ImageView(img))
								.hideAfter(Duration.seconds(2))
								.position(Pos.CENTER);
						
						notificationBuilder.darkStyle();
						notificationBuilder.show();
					}
				}else{//else for empty fields
					Image img = new Image("error.png");
					Notifications notificationBuilder = Notifications.create()
							.title("                ")
							.text("FIELDS CAN'T BE EMPTY")
							.graphic(new ImageView(img))
							.hideAfter(Duration.seconds(2))
							.position(Pos.CENTER);
					
					notificationBuilder.darkStyle();
					notificationBuilder.show();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				
			}
		}
	}
		public void resetHandler(ActionEvent event){
			if(event.getSource()==btnReset){
				txtFirstName.setText("");
				txtLastName.setText("");
				txtEmail.setText("");
				txtPhone.setText("");
				txtUserName.setText("");
				txtPwd.setText("");
				txtConfirmPwd.setText("");
				
				datePicker.setValue(null);
			}
		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewNoteController implements Initializable {
	@FXML
	private Button btnSaveNote;
	@FXML
	private Button btnResetNote;
	@FXML
	private TextField txtNote;
	@FXML
	private DatePicker addedDatePicker,dueDatePicker;
	@FXML
	private Label lblGreeting;
	@FXML
	private ComboBox<String> categoryCombo,priorityCombo,hoursCombo,minCombo,timingCombo;
	ObservableList<String> categories = FXCollections.observableArrayList("Personal","Work","Family");
	ObservableList<String> priority = FXCollections.observableArrayList("Critical","High","Average","Low");
	ObservableList<String>hours = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
	ObservableList<String>mins = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");
	ObservableList<String>timing = FXCollections.observableArrayList("AM","PM");
	NewNoteModel noteModel = new NewNoteModel();//Instance of NewNoteModel class
	
	public void saveNoteHandler(ActionEvent event){
		
		
		if(event.getSource()==btnSaveNote){
			
			if(!txtNote.getText().equals("")||categoryCombo.getSelectionModel()!=null
					||addedDatePicker.getValue()!=null||dueDatePicker.getValue()!=null||hoursCombo.getSelectionModel()!=null||
					minCombo.getSelectionModel()!=null||timingCombo.getSelectionModel().getSelectedItem()!=null||priorityCombo.getSelectionModel()!=null){
						
							//Variable to concatenate combo boxes and store time
							String time = hoursCombo.getSelectionModel().getSelectedItem()+":"+minCombo.getSelectionModel().getSelectedItem()+" "+timingCombo.getSelectionModel().getSelectedItem();
							if(addedDatePicker.getValue()!=null && dueDatePicker.getValue()!=null){
								//Time selector condition
								if(hoursCombo.getSelectionModel().getSelectedItem()!=null&&minCombo.getSelectionModel().getSelectedItem()!=null&&timingCombo.getSelectionModel().getSelectedItem()!=null){
									//Priority validation
									if(priorityCombo.getSelectionModel().getSelectedItem()!=null){
									
										noteModel.addNote(lblGreeting.getText().toLowerCase(), txtNote.getText(),categoryCombo.getSelectionModel().getSelectedItem(),
											addedDatePicker.getValue().toString(), dueDatePicker.getValue().toString(), 
											time, priorityCombo.getSelectionModel().getSelectedItem());
					
									Stage stage = (Stage) btnSaveNote.getScene().getWindow();
									stage.close();
									
									Image imgAddedToDb = new Image("complete_big.png");
									Notifications dataSavedNotification = Notifications.create()
											.title("                              ")
											.text("              SAVED TO DATABASE")
											.graphic(new ImageView(imgAddedToDb))
											.hideAfter(Duration.seconds(2))
											.position(Pos.CENTER);
									
									dataSavedNotification.darkStyle();
									dataSavedNotification.show();
									}else{//else for priority validation
										Image imgError = new Image("error_big.png");
										Notifications emptyFieldNotification = Notifications.create()
												.title("                              ")
												.text("         FIELD'S CAN'T BE EMPTY")
												.graphic(new ImageView(imgError))
												.hideAfter(Duration.seconds(2))
												.position(Pos.CENTER);
										
										emptyFieldNotification.darkStyle();
										emptyFieldNotification.show();
									}
								}else{//else for timing validation
									Image imgError = new Image("error_big.png");
									Notifications emptyFieldNotification = Notifications.create()
											.title("                               ")
											.text("          FIELD'S CAN'T BE EMPTY")
											.graphic(new ImageView(imgError))
											.hideAfter(Duration.seconds(2))
											.position(Pos.CENTER);
									
									emptyFieldNotification.darkStyle();
									emptyFieldNotification.show();
								}
							}else{//else for date validation
								Image imgError = new Image("error_big.png");
								Notifications emptyFieldNotification = Notifications.create()
										.title("                                 ")
										.text("            FIELD'S CAN'T BE EMPTY")
										.graphic(new ImageView(imgError))
										.hideAfter(Duration.seconds(2))
										.position(Pos.CENTER);
								
								emptyFieldNotification.darkStyle();
								emptyFieldNotification.show();
							}
			}else{
				Image imgError = new Image("error_big.png");
				Notifications emptyFieldNotification = Notifications.create()
						.title("                               ")
						.text("          FIELD'S CAN'T BE EMPTY")
						.graphic(new ImageView(imgError))
						.hideAfter(Duration.seconds(2))
						.position(Pos.CENTER);
				
				emptyFieldNotification.darkStyle();
				emptyFieldNotification.show();
			}
	}
}

	//Method to get username from ListMain Controller and get the substring from the greeting
public void getUser(String user){
	int length = user.length();
	String userName = user.substring(7, length);
	lblGreeting.setText(userName);
}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoryCombo.setItems(categories);
		priorityCombo.setItems(priority);
		hoursCombo.setItems(hours);
		minCombo.setItems(mins);
	  	timingCombo.setItems(timing);
	}

}
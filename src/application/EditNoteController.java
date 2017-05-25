package application;

import java.net.URL;

import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class EditNoteController implements Initializable{

    @FXML
    private JFXTextField txtNote;

    @FXML
    private JFXComboBox<String> categoryCombo;

    @FXML
    private JFXTextField addedDate;

    @FXML
    private JFXTextField dueDate;

    @FXML
    private JFXComboBox<String> priorityCombo;

    @FXML
    private JFXComboBox<String> hoursCombo;

    @FXML
    private JFXComboBox<String> minCombo;

    @FXML
    private JFXComboBox<String> timingCombo;

    @FXML
    private JFXButton btnSaveNote;
    @FXML
    private Label lblNoteId;
    @FXML
    private Label lblGreeting;
    
    ObservableList<String> categories = FXCollections.observableArrayList("Personal","Work","Family");
	ObservableList<String> priority = FXCollections.observableArrayList("Critical","High","Average","Low");
	ObservableList<String>hours = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
	ObservableList<String>mins = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");
	ObservableList<String>timing = FXCollections.observableArrayList("AM","PM");

    @FXML
   public  void saveNoteHandler(ActionEvent event) {
    	if(event.getSource()==btnSaveNote){
    		EditNoteModel editModel = new EditNoteModel();
    		
    		String time = hoursCombo.getValue()+":"+minCombo.getValue()+" "+timingCombo.getValue();
    		
    		if(editModel.isDbConnected()){
	    		if(!txtNote.getText().equals("")&&categoryCombo.getValue()!=null&&!addedDate.getText().equals("")&&!dueDate.getText().equals("")
	    				&&hoursCombo.getValue()!=null&&minCombo.getValue()!=null&&timingCombo.getValue()!=null){
	    			
				    			editModel.isNoteSaved(txtNote.getText(), categoryCombo.getValue(), addedDate.getText(), 
				    					dueDate.getText(), time, priorityCombo.getValue(),lblNoteId.getText());
				    			
				    			Stage stage = (Stage) btnSaveNote.getScene().getWindow();
								stage.close();
								
								
								Image imgConfirm = new Image("complete_big.png");
				    			Notifications confirm = Notifications.create()
				    					.title("                     ")
				    					.text("     SAVED TO DATABASE")
				    					.graphic(new ImageView(imgConfirm))
				    					.hideAfter(Duration.seconds(2))
				    					.position(Pos.CENTER);
				    			confirm.darkStyle();
				    			confirm.show();
	    			
	    		}else{//else for empty fields
	    			Image imgError = new Image("error_big.png");
	    			Notifications error = Notifications.create()
	    					.title("")
	    					.text("FIELDS CAN'T BE EMPTY")
	    					.graphic(new ImageView(imgError))
	    					.hideAfter(Duration.seconds(2))
	    					.position(Pos.CENTER);
	    			error.darkStyle();
	    			error.show();
	    		}
    		}
    	}
    }
    

    public void setNotesFromDb(String note,String category,String dateAdded,String dueDateReceived,String time,String priority,int NoteId){
    	
    	String hours = time.substring(0,2);
    	String min = time.substring(3,5);
    	String timing = time.substring(5,8);
    	String id = String.valueOf(NoteId);
    	
    	txtNote.setText(note);
    	categoryCombo.setValue(category);
    	addedDate.setText(dateAdded);
    	dueDate.setText(dueDateReceived);
    	hoursCombo.setValue(hours);
    	minCombo.setValue(min);
    	timingCombo.setValue(timing);
    	priorityCombo.setValue(priority);
    	lblNoteId.setText(id);
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



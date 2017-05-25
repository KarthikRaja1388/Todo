
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ListMainController implements Initializable{

	@FXML
	private Label lblDate;
	@FXML
	protected Label lblGreeting;
	@FXML
	private Button btnNewNote,btnEdit,btnDelete,btnLogout,btnLoadTable,btnReload;
	@FXML
	private TableView<Notes> notesTable;
	@FXML
	private TableColumn<Notes,String> colUser;
	@FXML 
	private TableColumn<Notes, String> colNote;
	@FXML 
	private TableColumn<Notes, String> colCategory;
	@FXML 
	private TableColumn<Notes, String> colDateAdded;
	@FXML 
	private TableColumn<Notes, String> colDueDate;
	@FXML 
	private TableColumn<Notes, String> colTime;
	@FXML 
	private TableColumn<Notes, Integer> colId;
	@FXML 
	private TableColumn<Notes, String> colPriority;
	
	private ObservableList<Notes> data = FXCollections.observableArrayList();
	
	private String user;
	
	/*Method to handle add new note
	 * 
	 * if event source is btnNewNote
	 * load NewNote.fxml
	 * 
	 * in order to pass parameter to NewNoteController creating an instance of NewNoteController
	 * and getting the controller
	 * 
	 * text on lblGreeting is passed in to @see getUser method from NewNoteController
	 * 
	 * in order to reload data , it's cleared completely
	 * @see loadTableDate method is used to load data in table
	 * */
	
	@FXML
	public void addNewNoteHandler(ActionEvent event){
		Stage stage;
		Parent root;
		try{
			
			if(event.getSource()==btnNewNote){
				stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NewNote.fxml"));
				root = loader.load();
				NewNoteController noteController = (NewNoteController)loader.getController();
				noteController.getUser(lblGreeting.getText());
				stage.setScene(new Scene(root));
				stage.setTitle("TO-DO-LIST");
				stage.setResizable(false);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(btnNewNote.getScene().getWindow());
				stage.showAndWait();
				
				ListMainModel listModel = new ListMainModel();
				data.clear();
				listModel.loadTableData(data, notesTable);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/*Method to handle logout 
	 * if source of the action is btnLogout
	 * 
	 * window will quit and display log in screen
	 * */
	@FXML
	public void logoutHandler(ActionEvent event){
		Stage stage;
		Parent root;
		if(event.getSource()==btnLogout){
			try {
				stage = (Stage)btnLogout.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				root = loader.load();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.centerOnScreen();
				stage.show();
				System.out.println();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void setUserName(String user) {
		this.user = user;
		lblGreeting.setText("WELCOME "+user.toUpperCase());
	}
	
	public String getUserName() {
		return user;
	}
	
/*Load data in table
 * 
 * if Database is connected
 * 
 * @see loadTableDate method is invoked form listModel
 * loadTableDate takes two params
 * 
 * @param observableList data list of data in db
 * @param TableView noteTable  which contains notes
 * */
	public void loadData(){
		ListMainModel listModel = new ListMainModel();
		if(listModel.isDbConnected()){
			 listModel.loadTableData(data,notesTable);
			 
		}
		
	}
	
	/*
	 * Method to handle edit Note
	 * 
	 * if event source is btEdit
	 * pop up a new modality window
	 * 
	 * if note!= null
	 * 
	 * @param String selectedNote note column data fetched from db
	 * @param String selectedCategory Category column data fetched from db
	 * @param String selectedDateAdded DateAdded column data fetched from db
	 * @param String selecetdDueDate    DueDate column data fetched from db
	 * @param String selectedTime  time column data fetched from db
	 * @param String selectedPriority priority column data fetched from db
	 * @param Integer selectedId NoteID column data fetched from db
	 * 
	 * @see SetNotesFromDb method which set all the data fetched from DB in to the appropriate field
	 * 
	 * */
	@FXML
	public void editNote(ActionEvent event) throws IOException{
		Stage stage;
		Parent root;
		Notes note = notesTable.getSelectionModel().getSelectedItem();
		if(event.getSource()==btnEdit){
			if(note!=null){
				String selectedNote = note.getNotes();
				String selecetdCategory = note.getCategory();
				String selectedDateAdded = note.getDateAdded();
				String selecetdDueDate = note.getDueDate();
				String selectedTime = note.getTime();
				String selectedPriority = note.getPriority();
				int selectedId = note.getId();
				
				stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("EditNote.fxml"));
				root = loader.load();
				EditNoteController editController = (EditNoteController)loader.getController();
				editController.setNotesFromDb(selectedNote, selecetdCategory, selectedDateAdded, selecetdDueDate, selectedTime, selectedPriority,selectedId);
				stage.setScene(new Scene(root));
				stage.setTitle("TO-DO-LIST");
				stage.setResizable(false);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(btnNewNote.getScene().getWindow());
				stage.showAndWait();
				
			}else{
				Image imgError = new Image("error.png");
				Notifications noteNotSelected = Notifications.create()
						.title("                    ")
						.text("    PLEASE SELECT A NOTE TO EDIT")
						.graphic(new ImageView(imgError))
						.hideAfter(Duration.seconds(5))
						.position(Pos.CENTER);
				
				noteNotSelected.darkStyle();
				noteNotSelected.show();
			}
		}
	}
	
	/*Delete Note handler
	 * 
	 * if event source is btnDelete
	 * 
	 * @see deleteData which takes
	 * @param Integer noteId which is the ID of the note
	 * 
	 * @see loadTableData which takes two parameters
	 * @param ObservableList data which holds the list of notes
	 * @param TableView notesTable which holds the tableView 
	 * */
	@FXML
	public void deleteNote(ActionEvent event){
		Notes note =  notesTable.getSelectionModel().getSelectedItem();
		ListMainModel listModel = new ListMainModel();

			if(event.getSource()==btnDelete){
				
				if(note!=null){
					
					if(listModel.isDbConnected()){
						
							int noteId = note.getId();
							listModel.deleteData(noteId);
							
							Image imgDelete = new Image("trash.png");
							Notifications deleteNotificiation = Notifications.create()
									.title("                    ")
									.text("              DELETED")
									.graphic(new ImageView(imgDelete))
									.position(Pos.CENTER)
									.hideAfter(Duration.seconds(2));
							
							deleteNotificiation.darkStyle();
							deleteNotificiation.show();
							data.clear();
							listModel.loadTableData(data, notesTable);
						}
				}else{
					Image imgError = new Image("error_big.png");
					
					Notifications errorNotification = Notifications.create()
							.title("                          ")
							.text("       PLEASE SELECT A NOTE")
							.graphic(new ImageView(imgError))
							.hideAfter(Duration.seconds(2))
							.position(Pos.CENTER);
					errorNotification.darkStyle();
					errorNotification.show();
				}
			
		}
	}
	
	/*public void clock(){
		Thread timer = new Thread(){
			
			public void run() {
				
				
				try {
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy hh:mm");
					format.format(date);
					String time = String.valueOf(date.getTime());
					
					lblDate.setText(arg0);
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	}*/

	@override
	public void initialize(URL location, ResourceBundle resources) {
		
		//colUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
		colNote.setCellValueFactory(new PropertyValueFactory<>("notes"));
		colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
		colDateAdded.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
		colPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
		//colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		loadData();
		Timer timer = new Timer();
		timer.schedule(new Reminder(),1,60*15*1000);
	 
	 
	}



	


}

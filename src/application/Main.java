package application;
	
import java.util.Timer;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class Main extends Application {
	
	@FXML
	private Label lblError;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPwd;
	@Override
	public void start(Stage primaryStage) {
		
		/*
		 * Displaying main stage
		 * */
		try {
			Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("TO-DO-LIST");
			primaryStage.setResizable(false);
			primaryStage.show();
			
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}

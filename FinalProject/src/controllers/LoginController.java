package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.LoginModel;

public class LoginController {

	//fx:id in LoginView
	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	private LoginModel model;
	
	

	public LoginController() {
		model = new LoginModel();
	}

	//method to start application by going to LoginView
	public void start() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	//method to login 
	public void login() {

		//get values in text fields
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();

		//validations
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			
			//pop up error dialog box if username or password empty
			Alert alert = new Alert(AlertType.ERROR);
       		alert.setTitle("Error Dialog");
       		alert.setHeaderText("ERROR!");
       		alert.setContentText("Username / Password cannot be empty or spaces");
       		alert.showAndWait();
			
			return;
		}
		if (username == null || username.trim().equals("")) {
       		
			//pop up error dialog box if username empty
			Alert alert = new Alert(AlertType.ERROR);
       		alert.setTitle("Error Dialog");
       		alert.setHeaderText("ERROR!");
       		alert.setContentText("Username cannot be empty or spaces");
       		alert.showAndWait();
       		
			return;
		}
		if (password == null || password.trim().equals("")) {
			
			//pop up error dialog box if password empty
			Alert alert = new Alert(AlertType.ERROR);
       		alert.setTitle("Error Dialog");
       		alert.setHeaderText("ERROR!");
       		alert.setContentText("Password cannot be empty or spaces");
       		alert.showAndWait();
       		
			return;
		}


		//authentication check
		checkCredentials(username, password);

	}

	//method to check the entered credentials
	public void checkCredentials(String username, String password) {
		
		Boolean isValid = model.getCredentials(username, password);
		
		if (!isValid) {
			
			//pop up error dialog box if user does not exist
			Alert alert = new Alert(AlertType.ERROR);
       		alert.setTitle("Error Dialog");
       		alert.setHeaderText("ERROR!");
       		alert.setContentText("User does not exist!");
       		alert.showAndWait();
			
			return;
		}

		try {
			AnchorPane root;
			if (model.isProfessor() && isValid) {
				
				//pop up error dialog box if valid for professor
				Alert alert = new Alert(AlertType.INFORMATION);
	       		alert.setTitle("Information Dialog");
	       		alert.setHeaderText("Login succeeded!");
	       		alert.setContentText("Welcome Professor");
	       		alert.showAndWait();
				
				//if user is a professor, inflate professor view
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ProfessorView.fxml"));
				Main.stage.setTitle("Professor View");

			} else {
				
				//pop up error dialog box if valid for student
				Alert alert = new Alert(AlertType.INFORMATION);
	       		alert.setTitle("Information Dialog");
	       		alert.setHeaderText("Login succeeded!");
	       		alert.setContentText("Welcome Student");
	       		alert.showAndWait();
				
				// If user is student, inflate student view
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentView.fxml"));
				Main.stage.setTitle("Student View");
				
			}

			Scene scene = new Scene(root);
			Main.stage.setScene(scene);

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}

	}
}
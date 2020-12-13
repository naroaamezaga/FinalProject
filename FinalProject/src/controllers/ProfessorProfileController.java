package controllers;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ProfessorProfileController {

	//method to go back to the main menu
	public void goBack() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ProfessorView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Professor View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	//method to logout
	public void logout() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
}

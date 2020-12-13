package controllers;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ProfessorController {

	//method to set up ProfessorProfileView
	public void viewProfile() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ProfessorProfileView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Professor Profile View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
	}
	
	//method to set up ProfessorCoursesView
	public void coursesView() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ProfessorCoursesView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Courses View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
	}
	
	//method to set up ProfessorExamsView
	public void examsView() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ProfessorExamsView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Exams View");
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

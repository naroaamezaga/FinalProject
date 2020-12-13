package controllers;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class StudentController {

	//method to set up StudentProfileView
	public void viewProfile() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentProfileView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Student Profile View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}

	//method to set up StudentCoursesView
	public void coursesView() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentCoursesView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Courses View");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//method to set up StudentExamsView
	public void examsView() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentExamsView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Exams View");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//method to set up StudentEnrollmentView
	public void enrollmentView() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentEnrollmentView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Enrollment View");
		} catch (Exception e) {
			e.printStackTrace();
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

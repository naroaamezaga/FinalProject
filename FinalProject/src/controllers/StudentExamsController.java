package controllers;

import java.sql.Statement;

import application.Main;
import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.Exam;
import models.ExamDB;

public class StudentExamsController {
	
	ExamDB examdb = new ExamDB();
	
	//fx:id in StudentExamsView
	@FXML
	private TextField txtCode;
	
	@FXML
	private Label code;
	@FXML
	private Label year;
	@FXML
	private Label duration;
	@FXML
	private Label completed;
	@FXML
	private Label grade;
	
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;
	
	private Exam searched;
	
	public StudentExamsController() {
		conn = new DBConnect();
	}

	//method to search exam when entering code
	@FXML
	public void searchExam() {

		// SEARCH EXAM IN COURSE TABLE
		Exam exam = new Exam();
		exam.setCourseCode(txtCode.getText().toString());
		clearFields1();

		searched = examdb.searchExam(exam);

		if(searched.getDuration()==0) {
			
			clearFields2();
			
			//pop up error box when no course found
       		Alert alert = new Alert(AlertType.ERROR);
       		alert.setTitle("Error Dialog");
       		alert.setHeaderText("NO COURSE FOUND");
       		alert.setContentText("There is no course with that code!");
       		alert.showAndWait();

		}else {

			code.setText(searched.getCourseCode());
			year.setText(Integer.toString(searched.getYear()));
			duration.setText(Integer.toString(searched.getDuration()));

			//if completed is NULL, print a NO in screen
			if(searched.getCompleted()== null) {
				completed.setText("NO");
			}else {
				completed.setText(searched.getCompleted());
			}
			//if grade is NULL, print No Grade in screen
			if(searched.getGrade()==0) {
				grade.setText("No Grade");
			}else {
				grade.setText(Integer.toString(searched.getGrade()));
			}
		}	
	}
	
	//method to complete exam when entering code
	@FXML
	public void completeExam() {

		int ok=0;
		Exam exam = new Exam();
		
		if(code.getText()=="") {
			
			//pop up error box when no course found
			Alert alert = new Alert(AlertType.ERROR);
       		alert.setTitle("Error Dialog");
       		alert.setHeaderText("CANNOT COMPLETE EXAM");
       		alert.setContentText("No course selected!");
       		alert.showAndWait();
       		
		}else {
			
			exam.setCourseCode(code.getText());
			ok=examdb.completeExam(exam);
			if (ok == 1) {
				completed.setText("YES");
				
				//pop up information box when exam completed successfully
				Alert alert = new Alert(AlertType.INFORMATION);
		   		alert.setTitle("Information Dialog");
		   		alert.setHeaderText("EXAM SUCCESSFULLY COMPLETED");
		   		alert.showAndWait();
			}
			
			
		}

		
	}
	
	//method to go back to the main menu
	public void goBack() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Student View");
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
	
	//methods to clear values in fields
	
	public void clearFields1() {
		txtCode.clear();
	}
	
	public void clearFields2() {
		
		code.setText("");
		year.setText("");
		duration.setText("");
		completed.setText("");
		grade.setText("");

	}

}

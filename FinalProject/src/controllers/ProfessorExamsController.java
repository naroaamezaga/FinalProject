package controllers;

import java.sql.Statement;

import application.Main;
import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.Exam;
import models.ExamDB;

public class ProfessorExamsController {

	ExamDB examdb = new ExamDB();

	//fx:id in AddExamView, DeleteExamView and GradeExamView
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtCourse;
	@FXML
	private TextField txtYear;
	@FXML
	private TextField txtDuration;
	@FXML
	private TextField txtGrade;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public ProfessorExamsController() {
		conn = new DBConnect();
	}

	//method to set up AddExamView
	public void addExamView() {

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AddExamView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Add Exam View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}
	
	//method to add exam
	@FXML
	public void addExam() {

		// ADD EXAM INTO EXAM TABLE
		Exam exam = new Exam();
		exam.setCourseCode(txtCourse.getText().toString());
		exam.setYear(Integer.parseInt(txtYear.getText().toString()));
		exam.setDuration(Integer.parseInt(txtDuration.getText().toString()));
		clearFields1();
		examdb.addExam(exam);
		
		//pop up information box when added correctly
		Alert alert = new Alert(AlertType.INFORMATION);
   		alert.setTitle("Information Dialog");
   		alert.setHeaderText("EXAM SUCCESSFULLY ADDED");
   		alert.showAndWait();		
	}
	
	//method to set up DeleteExamView
	public void deleteExamView() {

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/DeleteExamView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Delete Exam View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
	}
	
	//method to delete exam
	@FXML
	public void deleteExam() {
		
		int ok = 0;

		//DELETE EXAM FROM EXAM TABLE
		Exam exam = new Exam();
		exam.setId(Integer.parseInt(txtId.getText().toString()));
		clearFields2();
		ok = examdb.deleteExam(exam);
		if(ok==1) {
			
			//pop up information box when deleted correctly
			Alert alert = new Alert(AlertType.INFORMATION);
	   		alert.setTitle("Information Dialog");
	   		alert.setHeaderText("EXAM SUCCESSFULLY DELETED");
	   		alert.showAndWait();		
	   		
		}

	}
	
	//method to set up GradeExamView
	public void gradeExamView() {
		
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/GradeExamView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Grade Exam View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}
	
	//method to grade exam
	@FXML
	public void gradeExam() {

		int ok = 0;
		
		//GRADE EXAM
		Exam exam = new Exam();
		exam.setId(Integer.parseInt(txtId.getText().toString()));
		exam.setGrade(Integer.parseInt(txtGrade.getText().toString()));
		clearFields3();
		ok=examdb.gradeExam(exam);
		if(ok==1) {
			
			//pop up information box when graded correctly
			Alert alert = new Alert(AlertType.INFORMATION);
	   		alert.setTitle("Information Dialog");
	   		alert.setHeaderText("EXAM SUCCESSFULLY UPDATED");
	   		alert.showAndWait();		
	   		
		}

	}
	
	//method to go back to ProfessorExamsView
	public void goBackExams() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ProfessorExamsView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Professor View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	//method to go back to main menu
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
	
	//methods to clear values in fields
	
	public void clearFields1() {
		
		txtCourse.clear();
		txtYear.clear();
		txtDuration.clear();

	}
	
	public void clearFields2() {
		
		txtId.clear();
		
	}
	
	public void clearFields3() {
		
		txtId.clear();
		txtGrade.clear();
	}

}




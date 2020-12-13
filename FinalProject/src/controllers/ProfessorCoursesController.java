package controllers;

import java.sql.Statement;

import dao.DBConnect;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.Course;
import models.CourseDB;

public class ProfessorCoursesController {
	
	
	CourseDB coursedb = new CourseDB();
	
	//fx:id in AddCourseView, DeleteCourseView and UpdateCourseView
	@FXML
	private TextField txtCode;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSemester;
	@FXML
	private TextField txtClassroom;
	@FXML
	private TextField txtProfessor;
	@FXML
	private TextField txtCost;
	
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public ProfessorCoursesController() {
		conn = new DBConnect();
	}

	//method to set up AddCourseView
	public void addCourseView() {

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AddCourseView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Add Course View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}
	
	//method to add a course
	@FXML
	public void addCourse() {

		// ADD COURSE INTO COURSE TABLE
		Course course = new Course();
		course.setCode(txtCode.getText().toString());
		course.setName(txtName.getText().toString());
		course.setSemester(Integer.parseInt(txtSemester.getText().toString()));
		course.setClassroom(Integer.parseInt(txtClassroom.getText().toString()));
		course.setProfessor(txtProfessor.getText().toString());
		course.setCost(Double.parseDouble(txtCost.getText().toString()));
		clearFields1();
		coursedb.addCourse(course);
		
		//pop up information box when added correctly
		Alert alert = new Alert(AlertType.INFORMATION);
   		alert.setTitle("Information Dialog");
   		alert.setHeaderText("COURSE SUCCESSFULLY ADDED");
   		alert.showAndWait();		
	}
	
	
	//method to set up DeleteCourseView
	public void deleteCourseView() {

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/DeleteCourseView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Delete Course View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
	}
	
	//method to delete a course
	@FXML
	public void deleteCourse() {
		
		int ok = 0;

		//DELETE COURSE FROM COURSE TABLE
		Course course = new Course();
		course.setCode(txtCode.getText().toString());
		course.setName(txtName.getText().toString());
		clearFields2();
		ok = coursedb.deleteCourse(course);
		if(ok==1) {
			
			//pop up information box when deleted correctly
			Alert alert = new Alert(AlertType.INFORMATION);
	   		alert.setTitle("Information Dialog");
	   		alert.setHeaderText("COURSE SUCCESSFULLY DELETED");
	   		alert.showAndWait();		
	   		
		}

	}
	
	//method to set up UpdateCourseView
	public void updateCourseView() {
		
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/UpdateCourseView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Update Course View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}
	
	//method to update a course
	@FXML
	public void updateCourse() {
		
		int ok = 0;

		//UPDATE COURSE IN COURSE TABLE
		Course course = new Course();
		course.setCode(txtCode.getText().toString());
		course.setName(txtName.getText().toString());
		course.setSemester(Integer.parseInt(txtSemester.getText().toString()));
		course.setClassroom(Integer.parseInt(txtClassroom.getText().toString()));
		course.setProfessor(txtProfessor.getText().toString());
		course.setCost(Double.parseDouble(txtCost.getText().toString()));
		clearFields1();
		ok = coursedb.updateCourse(course);
		if(ok==1) {
			
			//pop up information box when updated correctly
			Alert alert = new Alert(AlertType.INFORMATION);
	   		alert.setTitle("Information Dialog");
	   		alert.setHeaderText("COURSE SUCCESSFULLY UPDATED");
	   		alert.showAndWait();		
	   		
		}

	}
	
	//method to go back to ProfessorCoursesView
	public void goBackCourses() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ProfessorCoursesView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Professor View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
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
	
	//methods to clear values in fields
	
	public void clearFields1() {
		
		txtCode.clear();
		txtName.clear();
		txtSemester.clear();
		txtClassroom.clear();
		txtProfessor.clear();
		txtCost.clear();
		
	}
	public void clearFields2() {
		
		txtCode.clear();
		txtName.clear();
		
	}

}

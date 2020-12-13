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
import models.Course;
import models.CourseDB;

public class StudentCoursesController {

	CourseDB coursedb = new CourseDB();

	//fx:id in StudentCoursesView
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

	@FXML
	private Label code;
	@FXML
	private Label name;
	@FXML
	private Label semester;
	@FXML
	private Label classroom;
	@FXML
	private Label professor;
	@FXML
	private Label cost;


	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	private Course searched;

	public StudentCoursesController() {
		conn = new DBConnect();
	}

	//method to search course when entering code
	@FXML
	public void searchCourse() {

		// SEARCH COURSE IN COURSE TABLE
		Course course = new Course();
		course.setCode(txtCode.getText().toString());
		clearFields1();

		searched = coursedb.searchCourse(course);

		if(searched.getSemester()==0) {

			clearFields2();

			//pop up error box when no course found
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("NO COURSE FOUND");
			alert.setContentText("There is no course with that code!");
			alert.showAndWait();


		}else {

			code.setText(searched.getCode());
			name.setText(searched.getName());
			semester.setText(Integer.toString(searched.getSemester()));
			classroom.setText(Integer.toString(searched.getClassroom()));
			professor.setText(searched.getProfessor());
			cost.setText(Double.toString(searched.getCost()));

		}

	}
	
	//method to set up ShowAllCoursesView
	public void showAllCoursesView() {

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ShowAllCoursesView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("All Courses View");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//method to go back to StudentCoursesView
	public void goBackCourses() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentCoursesView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Student Courses View");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
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
		name.setText("");
		semester.setText("");
		classroom.setText("");
		professor.setText("");
		cost.setText("");

	}

}


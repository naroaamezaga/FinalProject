package controllers;


import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Course;
import models.CourseDB;


public class AllCoursesController {
	
	//fx:id in ShowAllCoursesView
	@FXML
	private TableView<Course> coursesTable;
	@FXML
	private TableColumn<Course, Integer> id;
	@FXML
	private TableColumn<Course, String> code;
	@FXML
	private TableColumn<Course, String> name;
	@FXML
	private TableColumn<Course, Integer> semester;
	@FXML
	private TableColumn<Course, Integer> classroom;
	@FXML
	private TableColumn<Course, String> professor;
	@FXML
	private TableColumn<Course, Double> cost;
	
	//initializing method to show all courses in a table format
	@FXML
	void initialize() {
		CourseDB dao = new CourseDB();

		id.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));
		code.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
		name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
		semester.setCellValueFactory(new PropertyValueFactory<Course, Integer>("semester"));
		classroom.setCellValueFactory(new PropertyValueFactory<Course, Integer>("classroom"));
		professor.setCellValueFactory(new PropertyValueFactory<Course, String>("professor"));
		cost.setCellValueFactory(new PropertyValueFactory<Course, Double>("cost"));

		coursesTable.getItems().setAll(dao.listCourses());
		
    }
	
	//method to show all courses ordered by name in a table format
	@FXML
	void orderCourses() {
		CourseDB dao = new CourseDB();

		id.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));
		code.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
		name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
		semester.setCellValueFactory(new PropertyValueFactory<Course, Integer>("semester"));
		classroom.setCellValueFactory(new PropertyValueFactory<Course, Integer>("classroom"));
		professor.setCellValueFactory(new PropertyValueFactory<Course, String>("professor"));
		cost.setCellValueFactory(new PropertyValueFactory<Course, Double>("cost"));

		coursesTable.getItems().setAll(dao.listCoursesByName());
		
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

}

package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Course;
import models.CourseDB;


public class StudentInfoController {
	
	//fx:id in StudentInfoView
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
	
	@FXML
	private Label totalCost;
	
	//method to initialize by showing the enrolled courses in a table and the total cost
	@FXML 
	void initialize() {
		
		List<String> codes = new ArrayList<String>();
		List<Course> enrolledCourses = new ArrayList<Course>();
		Course course = new Course();
    	CourseDB dao = new CourseDB();
    	double total=0.00;

		id.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));
		code.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
		name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
		semester.setCellValueFactory(new PropertyValueFactory<Course, Integer>("semester"));
		classroom.setCellValueFactory(new PropertyValueFactory<Course, Integer>("classroom"));
        professor.setCellValueFactory(new PropertyValueFactory<Course, String>("professor"));
        cost.setCellValueFactory(new PropertyValueFactory<Course, Double>("cost"));
        
        codes = StudentEnrollmentController.getCodes();
        for(int i = 0; i < codes.size(); i++) {
        	course = dao.getEnrolledCourse(codes.get(i));
        	enrolledCourses.add(course);
        }
             
        if(!enrolledCourses.isEmpty()) {
        	coursesTable.getItems().setAll(enrolledCourses);
            for(int i=0; i < enrolledCourses.size(); i++) {
    			//calculate total cost of enrolled courses
            	total+=enrolledCourses.get(i).getCost();
            }
        }
        else {
			//pop up error box when no courses enrolled found
       		Alert alert = new Alert(AlertType.WARNING);
       		alert.setTitle("Warning Dialog");
       		alert.setHeaderText("NO COURSES FOUND");
       		alert.setContentText("You are not enrolled in any course!");
       		alert.showAndWait();
       		
        }

        String tot = String.format(Locale.US,"%.2f", total);
        totalCost.setText("$ "+tot);
	}

	//method to go back to StudentEnrollmentView
	public void goBackEnrollment() {
		
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentEnrollmentView.fxml"));
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

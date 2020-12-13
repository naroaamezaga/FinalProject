package controllers;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.CourseDB;

public class StudentEnrollmentController {
	
	CourseDB coursedb = new CourseDB();
	static List<String> codes = new ArrayList<String>();

	//fx:id in StudentEnrollmentView
    @FXML
    private ComboBox<String> enroll; 
    @FXML 
    private ComboBox<String> drop; 

	//initializing method 
	@FXML 
    void initialize() {
		fillEnrollBox();
		fillDropBox();
    }
    
	//method to fill the enrolling combo box
    private void fillEnrollBox() {
		enroll.getItems().clear();
		enroll.getItems().addAll(coursedb.listCourseCodes());
	}
    
    //method to fill the drop combo box
    private void fillDropBox() {
		drop.getItems().clear();
		drop.getItems().addAll(codes);
	}
    
    //method to enroll in a course
    @FXML
    private void enrollCourse(){
    	
    	boolean out = true;
    	
    	if(codes.isEmpty()) {
    		
    		codes.add(enroll.getValue());
    		
			//pop up information box when an enrollment is successful
    		Alert alert = new Alert(AlertType.INFORMATION);
       		alert.setTitle("Information Dialog");
       		alert.setHeaderText("SUCCESSFUL ENROLLMENT IN COURSE");
       		alert.setContentText("You have successfully enrolled in the course!");
       		alert.showAndWait();
       		
    	}else {

    		for (int i = 0; i < codes.size() && out; i++) {
    			if(codes.get(i).equals(enroll.getValue())) {
    				out=false;
    				
    				//pop up error box when you are already enrolled in a course
    	       		Alert alert = new Alert(AlertType.ERROR);
    	       		alert.setTitle("Error Dialog");
    	       		alert.setHeaderText("ERROR ENROLLING IN COURSE");
    	       		alert.setContentText("You are already enrolled in this course!");
    	       		alert.showAndWait();
    				
    			}
    		}
    		if (out==true) {
    			codes.add(enroll.getValue());
    			
    			//pop up information box when an enrollment is successful
    			Alert alert = new Alert(AlertType.INFORMATION);
	       		alert.setTitle("Information Dialog");
	       		alert.setHeaderText("SUCCESSFUL ENROLLMENT IN COURSE");
	       		alert.setContentText("You have successfully enrolled in the course!");
	       		alert.showAndWait();
	       		
    		}

    	}
        fillDropBox();
        enroll.getSelectionModel().clearSelection();

    }
    
    //method to drop a course
    @FXML
    private void dropCourse(){
    	
        codes.remove(drop.getValue());
        
		//pop up information box when a drop is successful
        Alert alert = new Alert(AlertType.INFORMATION);
   		alert.setTitle("Information Dialog");
   		alert.setHeaderText("SUCCESSFUL DROP IN COURSE");
   		alert.setContentText("You have successfully dropped off the course!");
   		alert.showAndWait();
        
        drop.getSelectionModel().clearSelection();
        fillDropBox();
    	
    }
    
	//method to set up StudentInfoView
	@FXML
	public void infoView() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentInfoView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Student Info View");
		} catch (Exception e) {
			e.printStackTrace();		
			
		}
	}
    
	//method to go back to main menu
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
	
	//codes getter
	public static List<String> getCodes() {
		return codes;
	} 

}

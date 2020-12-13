package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExamDB {

	//Declare DB objects 
	DBConnect conn = null;
	PreparedStatement  stm = null;

	public ExamDB() {//Create db object instance

		conn = new DBConnect();

	}

	//method to add an exam to database
	public void addExam(Exam exam) {

		try {

			stm = conn.getConnection().prepareStatement("INSERT INTO lakepark_exams(course,year,duration) VALUES (?,?,?);");
			stm.setString(1, exam.getCourseCode());
			stm.setInt(2, exam.getYear());
			stm.setInt(3, exam.getDuration());


			stm.executeUpdate();

			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//method to delete an exam from database
	public int deleteExam(Exam exam) {

		int ok = 0, found = 2;

		try {
			stm = conn.getConnection().prepareStatement("DELETE FROM lakepark_exams WHERE id = ?;");
			stm.setInt(1, exam.getId());

			found=stm.executeUpdate();

			if(found==0) {

				//pop up error box when exam not found
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("ERROR!");
				alert.setContentText("The exam ID does not exist!");
				alert.showAndWait();

			}else {
				ok = 1;
			}

			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok;
	}

	//method to grade an exam from database
	public int gradeExam(Exam exam) {

		int ok = 0,found = 2;

		try {
			stm = conn.getConnection().prepareStatement("UPDATE lakepark_exams SET grade= ? WHERE id= ?;");
			stm.setInt(1, exam.getGrade());

			stm.setInt(2, exam.getId());

			found=stm.executeUpdate();

			if(found==0) {

				//pop up error box when exam not found
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("ERROR!");
				alert.setContentText("The exam ID does not exist!");
				alert.showAndWait();

			}else {
				ok = 1;
			}

			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok;
	}
	
	//method to search exam from database
	public Exam searchExam(Exam exam) {

		Exam searched = new Exam();
		ResultSet rs = null;
		try {
			stm = conn.getConnection().prepareStatement("SELECT * FROM lakepark_exams WHERE course = ?;");
			stm.setString(1, exam.getCourseCode());
			rs = stm.executeQuery();

			if (rs.next() == false) { //ResultSet empty

			} 
			else {

				do {

					searched.setCourseCode(rs.getString("course"));
					searched.setYear(rs.getInt("year"));
					searched.setDuration(rs.getInt("duration"));
					searched.setCompleted(rs.getString("completed"));
					searched.setGrade(rs.getInt("grade"));

				} while (rs.next());
			}


			rs.close();
			stm.close();

		}catch(Exception e){
			e.printStackTrace();             
		}

		return searched;
	}
	
	//method to complete exam from database
	public int completeExam(Exam exam) {

		int ok = 0;
		ResultSet rs = null;
		try {
			stm = conn.getConnection().prepareStatement("SELECT * FROM lakepark_exams WHERE course = ?;");
			stm.setString(1, exam.getCourseCode());
			rs = stm.executeQuery();

			if (rs.next() == false) { //ResultSet empty

			} 
			else {

				do {

					exam.setCourseCode(rs.getString("course"));
					exam.setYear(rs.getInt("year"));
					exam.setDuration(rs.getInt("duration"));
					exam.setCompleted(rs.getString("completed"));
					exam.setGrade(rs.getInt("grade"));

				} while (rs.next());
			}


			rs.close();
			stm.close();

		}catch(Exception e){
			e.printStackTrace();             
		}

		try {

			if(exam.getCompleted()==null || exam.getCompleted().equals("NO")) {
				stm = conn.getConnection().prepareStatement("UPDATE lakepark_exams SET completed= ? WHERE course= ?;");
				stm.setString(1, "YES");

				stm.setString(2, exam.getCourseCode());
				stm.executeUpdate();
				ok = 1;
			}
			else {
				
				//pop up error box when exam is already completed
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("EXAM COMPLETED");
				alert.setContentText("The exam for this course is already completed!");
				alert.showAndWait();

			}


			stm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok;
	}



}

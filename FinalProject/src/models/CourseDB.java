package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import dao.DBConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CourseDB {

	//Declare DB objects 
	DBConnect conn = null;
	PreparedStatement  stm = null;

	public CourseDB() {//Create db object instance

		conn = new DBConnect();

	}

	//method to add a course to database
	public void addCourse(Course course) {

		try {
			
			stm = conn.getConnection().prepareStatement("INSERT INTO lakepark_courses(code,name,semester,classroom,professor,cost) VALUES (?,?,?,?,?,?);");
			stm.setString(1, course.getCode());
			stm.setString(2, course.getName());
			stm.setInt(3, course.getSemester());
			stm.setInt(4, course.getClassroom());
			stm.setString(5, course.getProfessor());
			stm.setDouble(6, course.getCost());

			stm.executeUpdate();

			stm.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	//method to delete a course from database
	public int deleteCourse(Course course) {

		int ok = 0, found = 2;
		
		try {
			stm = conn.getConnection().prepareStatement("DELETE FROM lakepark_courses WHERE code = ? AND name = ?;");
			stm.setString(1, course.getCode());
			stm.setString(2, course.getName());
			
			found=stm.executeUpdate();
			
			
			if(found==0) {
	       		
				//pop up error box when course not found
				Alert alert = new Alert(AlertType.ERROR);
	       		alert.setTitle("Error Dialog");
	       		alert.setHeaderText("ERROR!");
	       		alert.setContentText("That course does not exist!");
	       		alert.showAndWait();
	       		
			}
			else {
				ok = 1;
			}

			stm.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
		
	}
	
	//method to update a course from database
	public int updateCourse(Course course) {
		
		int ok = 0, found = 2;

		try {
			stm = conn.getConnection().prepareStatement("UPDATE lakepark_courses SET code= ?,name= ?,semester= ?,classroom= ?,professor= ?,cost= ? WHERE code= ?;");
			stm.setString(1, course.getCode());
			stm.setString(2, course.getName());
			stm.setInt(3, course.getSemester());
			stm.setInt(4, course.getClassroom());
			stm.setString(5, course.getProfessor());
			stm.setDouble(6, course.getCost());

			stm.setString(7, course.getCode());
			
			found=stm.executeUpdate();
			
			if(found==0) {
	       		
				//pop up error box when course not found
				Alert alert = new Alert(AlertType.ERROR);
	       		alert.setTitle("Error Dialog");
	       		alert.setHeaderText("ERROR!");
	       		alert.setContentText("That course code does not exist!");
	       		alert.showAndWait();
	       		
			}
			else {
				ok = 1;
			}

			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}

	//method to search course from database
	public Course searchCourse(Course course) {

		Course searched = new Course();
		ResultSet rs = null;
		try {
			stm = conn.getConnection().prepareStatement("SELECT * FROM lakepark_courses WHERE code = ?;");
			stm.setString(1, course.getCode());
			rs = stm.executeQuery();

			if (rs.next() == false) { //ResultSet empty
				
	       	} 
			else {

				do {

					searched.setCode(rs.getString("code"));
					searched.setName(rs.getString("name"));
					searched.setSemester(rs.getInt("semester"));
					searched.setClassroom(rs.getInt("classroom"));
					searched.setProfessor(rs.getString("professor"));
					searched.setCost(rs.getDouble("cost"));

				} while (rs.next());
			}

			rs.close();
			stm.close();

		}catch(Exception e){
			e.printStackTrace();             
		}

		return searched;
	}

	//method to list all courses from database
	public List<Course> listCourses() {

		List<Course> courses = new ArrayList<Course>();
		ResultSet rs = null;

		try{
			stm = conn.getConnection().prepareStatement("SELECT * FROM lakepark_courses;");
			rs = stm.executeQuery();

			if (rs.next() == false) { //ResultSet empty
				
			} 
			else {

				do {
					Course course = new Course();

					course.setId(rs.getInt("id"));
					course.setCode(rs.getString("code"));
					course.setName(rs.getString("name"));
					course.setSemester(rs.getInt("semester"));
					course.setClassroom(rs.getInt("classroom"));
					course.setProfessor(rs.getString("professor"));
					course.setCost(rs.getDouble("cost"));

					courses.add(course);

				} while (rs.next());
			}

			rs.close();
			stm.close();

		}catch(Exception e){
			e.printStackTrace();             
		}

		return courses;
	}
	
	//method to list all courses from database ordered by name
	public List<Course> listCoursesByName() {

		List<String> names = new ArrayList<String>();
		List<Course> courses = new ArrayList<Course>();
		ResultSet rs = null;
		
		try{
			
			names = listCourseNames();
			for(int i=0;i<names.size();i++) {
				stm = conn.getConnection().prepareStatement("SELECT * FROM lakepark_courses WHERE name = ?;");
				stm.setString(1, names.get(i));
				rs = stm.executeQuery();

				if (rs.next() == false) { //ResultSet empty
					
				} 
				else {

					do {
						Course course = new Course();

						course.setId(rs.getInt("id"));
						course.setCode(rs.getString("code"));
						course.setName(rs.getString("name"));
						course.setSemester(rs.getInt("semester"));
						course.setClassroom(rs.getInt("classroom"));
						course.setProfessor(rs.getString("professor"));
						course.setCost(rs.getDouble("cost"));

						courses.add(course);

					} while (rs.next());
				}

				rs.close();
				stm.close();

			}
			
		}catch(Exception e){
					e.printStackTrace();             
		}

		return courses;
	}

	//method to list the names of the courses in order
	public List<String> listCourseNames(){
		
		List<String> names = new ArrayList<String>();
		List<Course> courses = listCourses();
		for (int i = 0; i < courses.size(); i++) {
			names.add(courses.get(i).getName());
		}
		Collections.sort(names);
		return names;
	}

	//method to list the codes of the courses 
	public List<String> listCourseCodes(){
		
		List<String> codes = new ArrayList<String>();
		List<Course> courses = listCourses();
		for (int i = 0; i < courses.size(); i++) {
			codes.add(courses.get(i).getCode());
		}
		return codes;
	}

	//method to search a course by the code
	public Course getEnrolledCourse(String code) {

		Course searched = new Course();
		ResultSet rs = null;
		try {
			stm = conn.getConnection().prepareStatement("SELECT * FROM lakepark_courses WHERE code = ?;");
			stm.setString(1, code);
			rs = stm.executeQuery();

			if (rs.next() == false) { //ResultSet empty
				
			} 
			else {

				do {
					
					searched.setId(rs.getInt("id"));
					searched.setCode(rs.getString("code"));
					searched.setName(rs.getString("name"));
					searched.setSemester(rs.getInt("semester"));
					searched.setClassroom(rs.getInt("classroom"));
					searched.setProfessor(rs.getString("professor"));
					searched.setCost(rs.getDouble("cost"));

				} while (rs.next());
			}

			rs.close();
			stm.close();

		}catch(Exception e){
			e.printStackTrace();             
		}

		return searched;
	}

}

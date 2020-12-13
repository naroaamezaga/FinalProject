package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnect;

public class LoginModel extends DBConnect {
 
	//data members 
	private Boolean professor;
	private int id;
 
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean isProfessor() {
		return professor;
	}
	public void setProfessor(Boolean professor) {
		this.professor = professor;
	}
		
	//check credentials in database
	public Boolean getCredentials(String username, String password){
           
        	String query = "SELECT * FROM lakepark_users WHERE uname = ? and passwd = ?;";
            try(PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) { 
                	setId(rs.getInt("id"));
                	setProfessor(rs.getBoolean("role"));
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace();   
             }
			return false;
    }

}
package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			//Set starting view: WelcomeView
			stage = primaryStage;
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/WelcomeView.fxml"));
			Scene scene = new Scene(root);
			 //Take comment off to see the effect of styles.css
			//scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			stage.setTitle("Welcome View");
			stage.setScene(scene);
			stage.show();
		
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

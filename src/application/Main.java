// Lane Parton
package application;
	
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	public static ArrayList<Customer> customerArrayList = new ArrayList<>();
	public static ArrayList<Order> orderArrayList = new ArrayList<>();
	public static boolean DEBUG = true;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// set a title for the Window
			primaryStage.setTitle("Main Drawing Window");
			
			// get an FXML loader and read in the fxml code
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/MainWindow.fxml"));
			AnchorPane mainLayout = (AnchorPane)loader.load();
			
			// Create the scene with the layout in the fxml code, set the scene and show it
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

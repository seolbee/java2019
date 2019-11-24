package application;
	
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Game;
import views.MasterController;


public class Main extends Application {
	public static Main app;
	
	public Game game;
	
	public StackPane stPane;
	
	public Map<String, MasterController> controllerMap = new HashMap();
	
	@Override
	public void start(Stage primaryStage) {
		app = this;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Main.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			FXMLLoader fontLoader = new FXMLLoader();
			fontLoader.setLocation(getClass().getResource("/views/font.fxml"));
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

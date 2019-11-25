package application;
	
import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Game;
import views.MasterController;


public class Main extends Application {
	public static Main app;
	
	public Game game;
	
	public static StackPane stPane;
	
	public Map<String, MasterController> controllerMap = new HashMap();
	
	@Override
	public void start(Stage primaryStage) {
		app = this;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Main.fxml"));
			stPane = loader.load();
			
			MasterController mc = loader.getController();
			mc.setRoot(stPane);
			controllerMap.put("main", mc);
			
			FXMLLoader fontLoader = new FXMLLoader();
			fontLoader.setLocation(getClass().getResource("/views/font.fxml"));
			AnchorPane fontPage = fontLoader.load();
			
			MasterController fc = fontLoader.getController();
			fc.setRoot(fontPage);
			controllerMap.put("font", fc);
			
			FXMLLoader QueueLoader = new FXMLLoader();
			QueueLoader.setLocation(getClass().getResource("/views/Queue.fxml"));
			AnchorPane QueuePage = QueueLoader.load();
			
			MasterController qc = QueueLoader.getController();
			qc.setRoot(QueuePage);
			controllerMap.put("queue", qc);
			
			Scene scene = new Scene(stPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stPane.getChildren().add(QueuePage);
			stPane.getChildren().add(fontPage);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void slideOut(Pane pane) {
		Timeline timeline = new Timeline();
		KeyValue toRight = new KeyValue(pane.translateXProperty(), 800);
		KeyValue fadeOut = new KeyValue(pane.opacityProperty(), 800);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), (e)->{
			System.out.println(pane);
			stPane.getChildren().remove(pane);
		}, toRight, fadeOut);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void loadPane(String name) {
		MasterController c = controllerMap.get(name);
		Pane pane = c.getRoot();
		stPane.getChildren().add(pane);
		
		pane.setTranslateX(-800);
		pane.setOpacity(0);
		Timeline timeline = new Timeline();
		KeyValue toRight = new KeyValue(pane.translateXProperty(), 0);
		KeyValue fadeOut = new KeyValue(pane.opacityProperty(), 1);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), toRight, fadeOut);
		
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
}

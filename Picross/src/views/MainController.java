package views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Game;

public class MainController extends MasterController{
	
	@FXML
	private AnchorPane APane;
	
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private VBox vbox;
	
	@FXML
	private HBox hbox;
	
	@FXML
	private void initialize() {
		System.out.println("게임판 생성");
		Main.app.game = new Game(gameCanvas, 5, vbox, hbox, APane);
		Main.app.game.gameStart();
	}
	
	public void click(MouseEvent e) {
		Main.app.game.click(e);
	}
}

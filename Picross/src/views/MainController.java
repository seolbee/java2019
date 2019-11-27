package views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Game;
import main.Timer;

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
	private Label lblHour;
	
	@FXML
	private Label lblMinute;
	
	@FXML
	private Label lblSecond;
	
	private int length;
	
	private String location;
	
	public Timer timer;
	
	private int id;
	
	@FXML
	private void initialize() {
		System.out.println("게임판 생성");
	}
	
	public void setGame() {
		timer = new Timer(lblHour, lblMinute, lblSecond);
		Main.app.game = new Game(gameCanvas, length, vbox, hbox, APane, timer, location, id);
		timer.setStart(true);
		Main.app.game.gameStart();
	}
	
	public void click(MouseEvent e) {
		Main.app.game.click(e);
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}

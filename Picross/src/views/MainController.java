package views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import main.Game;

public class MainController {
	
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private void initialize() {
		System.out.println("게임판 생성");
		Main.app.game = new Game(gameCanvas, 10);
		Main.app.game.gameStart();
	}
	
	public void click(MouseEvent e) {
		Main.app.game.click(e);
	}
}

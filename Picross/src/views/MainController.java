package views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class MainController {
	
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private void Initialze() {
	}
	
	public void click() {
		Main.app.game.click();
	}
}

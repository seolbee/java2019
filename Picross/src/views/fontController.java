package views;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class fontController extends MasterController{
	@FXML
	private StackPane pane;
	
	@FXML
	private void initialize() {
		this.setRoot(pane);
	}
	
	public void gameStart() {
		
	}
}

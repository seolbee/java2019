package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import task.CountThread;

public class MainController {
	@FXML
	private Label lblHour;
	
	@FXML
	private Label lblMinute;
	
	@FXML
	private Label lblSecond;
	
	private long sec;
	
	@FXML
	private Button startBtn;
	
	@FXML
	private Button stopBtn;
	
	private CountThread t;
	private boolean status = false;
	
	@FXML
	private void initialize() {
		t = new CountThread(lblHour, lblMinute, lblSecond);
	}
	
	public void start() {
		if(!status) {
			t.startCount();
			startBtn.setText("일시정지");
			status = true;
		} else {
			t.pauseCount();
			startBtn.setText("재시작");
			status = false;
		}
	}
	
	public void stop() {
		t.stopCount();
		startBtn.setText("시작");
		status = false;
	}
	
	public void setQuit() {
		t.setQuit();
	}
}

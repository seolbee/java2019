package views;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.MainApp;

public class DayController extends MasterController{
	
	@FXML
	private Label lblDay;
	
	@FXML
	private Label lblCount;
	
	private LocalDate date;
	
	private boolean isFocused = false;
	
	public void setDayLabel(LocalDate date) {
		this.date = date;
		lblDay.setText(String.valueOf(date.getDayOfMonth()));
	}
	
	public void setCountLabel(Integer count) {
		lblCount.setText(count.toString());
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}
	
	public void setFocus() {
		//클릭되었을때 실행하는 함수
		if(isFocused) {
			MainApp.app.loadTodoData(date);
			MainApp.app.loadPane("todo");
			return;
		}
		MainController mc = (MainController) MainApp.app.getController("main");
		mc.resetClickData();
		mc.setTodayInfo(date);
		getRoot().getStyleClass().add("active");
		isFocused = true;
	}
	
	public void outFocus() {
		isFocused = false;
		getRoot().getStyleClass().remove("active");
	}
}

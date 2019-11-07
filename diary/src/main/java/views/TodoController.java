package views;

import java.time.LocalDate;

import domain.TodoVO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TodoController extends MasterController{
	
	@Override
	public void reset() {
		txtTitle.setText("");
		txtContent.setText("");
		todoContent.setText("");
	}
	
	@FXML
	private TextField txtTitle;
	
	@FXML
	private TextArea txtContent;
	
	@FXML
	ListView<TodoVO> todoList;
	
	@FXML
	TextArea todoContent;
	
	private LocalDate date;
	
	public void init (LocalDate date) {
		this.date = date;
	}
	
	public void register() {
		
	}
	
	public void update() {
		
	}
	
	public void close() {
		
	}
}

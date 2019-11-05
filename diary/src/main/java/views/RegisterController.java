package views;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisterController extends MasterController{
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private PasswordField passconfirm;
	
	@FXML
	private TextArea txtInfo;
	
	public void register() {
		
	}
	
	public void cancel() {
		
	}
}

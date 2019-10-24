package views;
import Util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.MainApp;

public class LoginController extends MasterController{
	@FXML
	private TextField txtId;
	
	@FXML
	private PasswordField passField;
	
	@FXML
	private AnchorPane loginPane;
	
	public void loginProcess() {
		String id = txtId.getText();
		
		String pass = passField.getText();
		
		if(id.isEmpty() || pass.isEmpty()) {
			Util.showAlert("에러", "아이디와 비밀번호는 공백 일 수 없습니다.", AlertType.ERROR);
		}
		
		if(id.equals("gondr") && pass.equals("1234")) {
			MainApp.app.slideOut(loginPane);
		} else {
			Util.showAlert("에러", "존재하지 않는 아이디이거나 비밀번호가 틀립니다.", AlertType.ERROR);
		}
	}
}

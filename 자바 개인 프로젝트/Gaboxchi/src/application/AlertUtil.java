package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertUtil {
	
	public static Optional<ButtonType> result; 
	public static Alert a;
	
	public static void utill(String title, String contain) {
		a = new Alert(AlertType.WARNING);
		a.setTitle("사망");
		a.setHeaderText(title);
		a.setContentText(contain);		
		
		a.showAndWait();
	}
	
	public static void choose() {
		a = new Alert(AlertType.CONFIRMATION);
		a.setTitle("인생의 갈림길");
		a.setContentText("개복치가 사망해버렸습니다. 다시 개복치가 세계를 지배할 수 있게 도전하실 건가요?");
		
		result = a.showAndWait();
	}
}


package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Util.JDBCUtil;
import Util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import main.MainApp;
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
	private PasswordField passConfirm;
	
	@FXML
	private TextArea txtInfo;
	
	@Override
	public void reset() {
		txtId.setText("");
		txtName.setText("");
		pass.setText("");
		passConfirm.setText("");
		txtInfo.setText("");
	}
	
	public void register() {
		String id= txtId.getText().trim();
		String name = txtName.getText().trim();
		String strPass = pass.getText().trim();
		String confirm = passConfirm.getText().trim();
		String info = txtInfo.getText().trim();
		
		if(id.isEmpty() || name.isEmpty() || strPass.isEmpty() || info.isEmpty()) {
			Util.showAlert("비어있음", "필수 값이 비어있습니다.", AlertType.INFORMATION);
			return;
		}
		
		if(!strPass.contentEquals(confirm)) {
			Util.showAlert("불일치", "비밀번호와 확인이 다릅니다.", AlertType.INFORMATION);
			return;
		}
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlExist = "SELECT * FROM diary_users WHERE id = ?";
		
		String sqlInsert = "INSERT INTO diary_users(id, name, pass, info) VALUES (?, ?, PASSWORD(?), ?)";
		try {
			pstmt = con.prepareStatement(sqlExist);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Util.showAlert("회원중복", "이미 해당 id의 유저가 존재", AlertType.INFORMATION);
				return;
			}
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			pstmt = con.prepareStatement(sqlInsert);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, strPass);
			pstmt.setString(4, info);
			
			if(pstmt.executeUpdate() != 1) {
				Util.showAlert("에러", "데이터 베이스 입력 도중 오류", AlertType.ERROR);
				return;
			}
			
			Util.showAlert("성공", "성공적으로 회원가입이 되었습니다.", AlertType.INFORMATION);
			MainApp.app.slideOut(getRoot());
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터 베이스 오류", AlertType.ERROR);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void cancel() {
		MainApp.app.slideOut(getRoot());
	}
}

package views;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Util.JDBCUtil;
import Util.Util;
import domain.UserVO;
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
		
		UserVO user = checkLogin(id, pass);
		
		if(user!=null) {
			MainApp.app.slideOut(loginPane);
		} else {
			Util.showAlert("에러", "존재하지 않는 아이디이거나 비밀번호가 틀립니다.", AlertType.ERROR);
		}
	}
	
	private UserVO checkLogin(String id, String pass) {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM diary_users WHERE id = ? AND pass = PASSWORD(?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,  pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				UserVO user = new UserVO();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("pass"));
				user.setInfo(rs.getString("info"));
				return user;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터베이스 오류", AlertType.ERROR);
			return null;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void openRegisterPane() {
		MainApp.app.loadPane("register");
	}	
}

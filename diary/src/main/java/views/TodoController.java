package views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import Util.JDBCUtil;
import Util.Util;
import domain.TodoVO;
import domain.UserVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.MainApp;

public class TodoController extends MasterController{
	
	@Override
	public void reset() {
		txtTitle.setText("");
		txtContent.setText("");
		todoContent.setText("");
		todoTitle.setText("");
	}
	
	@FXML
	private TextField txtTitle;
	
	@FXML
	private TextArea txtContent;
	
	@FXML
	private ListView<TodoVO> todoList;
	
	@FXML
	private TextArea todoContent;
	
	@FXML
	private TextField todoTitle;
	
	private LocalDate date;
	
	private ObservableList<TodoVO> list;
	
	@FXML
	private void initialize() {
		list = FXCollections.observableArrayList();
		todoList.setItems(list);
	}
	
	public void init (LocalDate date) {
		System.out.println(1);
		this.date = date;
		UserVO user = MainApp.app.getLoginUser();
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		list.clear();
		String sql = "SELECT * FROM diary_todo WHERE date = ? AND owner = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(this.date));
			System.out.println(this.date.toString() + ", " + user.getId());
			pstmt.setString(2, user.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TodoVO vo = new TodoVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("date").toLocalDate());
				vo.setOwner(rs.getString("owner"));
				System.out.println(vo.toString());
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터 베이스 쿼리 도중 오류", AlertType.ERROR);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void register() {
		String title = txtTitle.getText();
		String content = txtContent.getText();
		UserVO user = MainApp.app.getLoginUser();
		
		if(title.isEmpty() || content.isEmpty()) {
			Util.showAlert("필수 항목 비어있슴", "제목이나 내용은 비어있을 수 없습니다.", AlertType.INFORMATION);
			return;
		}
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT diary_todo(title, content, date, owner) VALUES(?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setDate(3, Date.valueOf(this.date));
			pstmt.setString(4, user.getId());
			
			int result = pstmt.executeUpdate();
			if(result != 1) {
				Util.showAlert("에러", "데이터 베이스에 정상적으로 입력되지 않았습니다. 재시도 해주세요", AlertType.ERROR);
				return;
			}
			Util.showAlert("성공", "데이터 베이스 성공적으로 입력 완료", AlertType.INFORMATION);
			
			MainApp.app.slideOut(this.getRoot());
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터 베이스 도중 오류", AlertType.ERROR);
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void update() {
		String title = todoTitle.getText();
		String content = todoContent.getText();
		UserVO user = MainApp.app.getLoginUser();
		
		if(title.isEmpty() || content.isEmpty()) {
			Util.showAlert("필수 항목 비어있슴", "제목이나 내용은 비어있을 수 없습니다.", AlertType.INFORMATION);
			return;
		}
		
		int idx = todoList.getSelectionModel().getSelectedIndex();
		if(idx < 0) {
			return;
		}
		
		TodoVO todo = list.get(idx);
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE diary_todo SET title = ?, content = ? WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, todo.getId());
			int result = pstmt.executeUpdate();
			if(result != 1) {
				Util.showAlert("에러", "데이터 베이스 쿼리 도중 오류", AlertType.ERROR);
				return;
			}
			Util.showAlert("성공", "데이터 베이스에 정상적으로 수정되었습니다.", AlertType.INFORMATION);
			MainApp.app.slideOut(this.getRoot());
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터 베이스 쿼리 도중 오류", AlertType.ERROR);
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void close() {
		MainApp.app.slideOut(this.getRoot());
	}
	
	public void loadTodo() {
		int idx = todoList.getSelectionModel().getSelectedIndex();
		if(idx < 0) {
			return;
		}
		
		TodoVO vo = list.get(idx);
		todoContent.setText(vo.getContent());
		todoTitle.setText(vo.getTitle());
	}
}

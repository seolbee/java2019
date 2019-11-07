package views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.JDBCUtil;
import Util.Util;
import domain.UserVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.MainApp;

public class MainController extends MasterController{
	@FXML private Button btnPrev;
	@FXML private Button btnNext;
	@FXML private Label lblDate;
	@FXML private Label lblDay;
	@FXML private Label loginInfo;
	@FXML private GridPane gridCalendar;
	
	private UserVO user;
	
	private List<DayController> list;
	
	private YearMonth currentYM; //현재 년월을 저장할 변수
	private Map<String, String> dayOfWeek = new HashMap<>();
	
	@FXML
	private void initialize() {
		list = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 7; j++) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/views/DayLayout.fxml"));
				try {
					AnchorPane ap = loader.load();
					gridCalendar.add(ap, j, i); //그리드팬에 추가
					DayController dc = loader.getController();
					dc.setRoot(ap);
					list.add(dc);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.printf("j : %d, i : %d 번째 그리는 중 오류 발생", j, i);
				}
			}
		}
		
		String[] engDay = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
		String[] korDay = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
				
		for(int i = 0; i < engDay.length; i++) {
			dayOfWeek.put(engDay[i], korDay[i]); 
		}
		
	}
	
	public void loadMonthData(YearMonth ym) {
		LocalDate cDay = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1);
		while(!cDay.getDayOfWeek().toString().equals("SUNDAY")) {
			cDay = cDay.minusDays(1);
		}
		
		LocalDate first = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1);
		LocalDate last= LocalDate.of(ym.getYear(), ym.getMonthValue() + 1, 1).minusDays(1);
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT date, COUNT(*) AS cnt FROM diary_todo WHERE owner = ? AND date BETWEEN ? AND ? GROUP BY date";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setDate(2, Date.valueOf(first));
			pstmt.setDate(3, Date.valueOf(last));
			
			rs = pstmt.executeQuery();
			Map<LocalDate, Integer> cntMap = new HashMap<>();
			while(rs.next()) {
				LocalDate key = rs.getDate("date").toLocalDate();
				Integer value = rs.getInt("cnt");
				cntMap.put(key, value);
			}
			
			for(DayController day : list) {
				day.setDayLabel(cDay);
				if(cntMap.containsKey(cDay)) {
					day.setCountLabel(cntMap.get(cDay));
				} else {
					day.setCountLabel(0);
				}
				cDay = cDay.plusDays(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터 베이스 쿼리 도중 오류", AlertType.ERROR);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		System.out.println(ym);
		currentYM = ym;
	}
	
	public void setLoginInfo(UserVO user) {
		this.user = user;
		loginInfo.setText(user.getName() + "[" + user.getId() + "]");
		loadMonthData(YearMonth.now());
		setTodayInfo(LocalDate.now());
	}

	public void setTodayInfo(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		lblDate.setText(date.format(dtf));
		String key = date.getDayOfWeek().toString();
		lblDay.setText(dayOfWeek.get(key));
	}
	
	public void logout() {
		this.user = null;
		MainApp.app.loadPane("login");
	}
	public void prevMonth() {
		loadMonthData(currentYM.minusMonths(1));
		LocalDate first = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		setTodayInfo(first);
	}
	public void nextMonth() {
		loadMonthData(currentYM.plusMonths(1));
		LocalDate first = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		setTodayInfo(first);
	}
	@Override
	public void reset() {		
	}
	
	public void resetClickData() {
		for(DayController dc : list) {
			dc.outFocus();
		}
	}
	
	public UserVO getUser() {
		return user;
	}
}

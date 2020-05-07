package views;

import java.util.Random;

import application.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.MainGame;

public class MainController {
	
	private MainGame main;
	
	@FXML
	private Canvas canvas;
	
	@FXML
	private Label geboxchiCnt;
	
	@FXML
	private Label id;
	
	@FXML
	private Label weight;
	
	@FXML
	private AnchorPane box;
	
	@FXML
	public void initialize() {
		ImageView geboxchi = new ImageView();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		main = new MainGame(geboxchi, box, weight, id, geboxchiCnt, gc, canvas);
	}
	
	public void clickHandle(MouseEvent box) {
		int mouseX = (int)box.getX();
		int mouseY = (int)box.getY();
		
		main.clickHandel(mouseX, mouseY);
	}
	
	public void adventure() {
		Random rand = new Random();
		int succes = rand.nextInt(100);
		if(succes > 50) {
			AlertUtil.utill("성공", "모험을 성공적으로 끝 마쳤습니다.");
			main.w+= 3.0;
			main.levelUp();
			return;
		} else{
			main.setDeath(true);
			main.death("복상사", "모험 도중 사망하셨습니다.");
			life();
			return;
		}
	}
	
	public void life() {
		if(AlertUtil.result.get() == ButtonType.OK) {
			main.setDeath(false);
			main.life++;
			initialize();
		}
	}
	
	public void save() {
		main.saveFile();
	}
	
	public void market() {
		
	}
	
	public void spacial() {
		
	}
	
}

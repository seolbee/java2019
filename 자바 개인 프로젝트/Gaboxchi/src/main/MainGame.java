package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.AlertUtil;
import application.CreateFood;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import save.SaveFile;

public class MainGame {
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	public Label geboxchiCnt;
	
	public Label weight;
	
	private AnchorPane pane;
	
	public Integer life = 1;
	
	private Random rand = new Random();
	
	private ImageView geboxchi;
	
	private double ex;
	
	private String image;
	
	private String ident;
	
	private int money;
	
	private boolean death = false;
	
	private int foodCnt = 7;
	
	private CreateFood createFood = null;
	
	public Double w = 0.0;
	
	public Double[] levels = {3.0, 30.7, 80.5, 120.6, 200.65};
	public ArrayList<Double> level = new ArrayList<Double>(Arrays.asList(levels));
	
	public String[] identy = {"별사탕", "아주 어린이", "어린이", "젊은이", "노인네"};
	public ArrayList<String> identity = new ArrayList<String>(Arrays.asList(identy));
	
	public MainGame(ImageView geboxchi, AnchorPane box, Label text, Label ident, Label cnt, GraphicsContext gc, Canvas c) {
		if(death) {
			return;
		}
		this.canvas = c;
		this.gc = gc;
		this.geboxchi = geboxchi;
		pane = box;
		weight = text;
		geboxchiCnt = cnt;
		createFood = new CreateFood(this, 5000, box);
		initGame();
		load();
	}
	
	public void initGame() {
		geboxchiCnt.setText(life.toString()+"대째");
		Image img = new Image(getClass().getResource("/img/babyGeboxchi.png").toString());
		geboxchi.setImage(img);
		pane.getChildren().add(geboxchi);
		geboxchi.setLayoutX(250);
		geboxchi.setLayoutY(400);
		geboxchi.setFitWidth(50.0);
		geboxchi.setFitHeight(50.0);
		createFood.start();
	}
	
	public void load() {
		File file = new File(getClass().getResource("/data/userSave.txt").getFile());
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 오류");
		}
	}
	
	public void clickHandel(int x, int y) {
		if(y > 630) {
			return;
		}
		
		Timeline timeline = new Timeline();
		
		KeyValue keyValueX = new KeyValue(geboxchi.layoutXProperty(), x);
		KeyValue keyValueY = new KeyValue(geboxchi.layoutYProperty(), y);
		
		KeyFrame keyframe = new KeyFrame(Duration.millis(300), e->{
			
			for(ImageView food : createFood.foodList) {
				double left = food.getX();
				double top = food.getY();
//				System.out.println(left + ", " + top + ", " + geboxchi.getLayoutX());
				if(left+20 > geboxchi.getLayoutX() 
						&& top + 20 > geboxchi.getLayoutY()
						&& left < geboxchi.getLayoutX() + geboxchi.getFitWidth()
						&& top < geboxchi.getLayoutY() + geboxchi.getFitHeight()) {
					pane.getChildren().remove(food);
					createFood.foodList.remove(food);
					w+=0.13;
					weight.setText(w.toString());
					levelUp();
				}
			}
			
		} ,keyValueX, keyValueY);
		
		timeline.getKeyFrames().add(keyframe);
		timeline.play();
		
	}

	public boolean isDeath() {
		return death;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}

	public void death(String title, String contain) {
		if(death) {
			Image image = new Image(getClass().getResource("/img/"+this.image+"death.png").toString());
			geboxchi.setImage(image);
			createFood.setOver(true);
			Timeline timeline = new Timeline();
			KeyFrame key = new KeyFrame(Duration.millis(4000), new KeyValue(geboxchi.opacityProperty(), 0));
			timeline.getKeyFrames().add(key);
			timeline.play();
			AlertUtil.utill(title, contain);
			AlertUtil.choose();
			createFood.foodList.forEach(item -> {
				pane.getChildren().remove(item);
			});
			
			createFood.foodList.clear();
		}
	}
	
	public void saveFile() {
		SaveFile save = new SaveFile(ex, w, ident, image);
	}
	
	public void draw(Double maxWeight) {
		gc.setFill(Color.rgb(0,0,0));
		gc.setLineWidth(3);
		gc.setStroke(Color.rgb(255,255,255));
		gc.fillRoundRect( 0, 0, canvas.getWidth(), canvas.getHeight(), 10, 10);
		gc.strokeRoundRect(0, 0,canvas.getWidth(), canvas.getHeight(), 10, 10);
		
		ex = w / maxWeight * 100;
		gc.setFill(Color.rgb(252,150,94));
		gc.fillRoundRect(2,2, ex * canvas.getWidth() / 100, 25, 10, 10);
	}
	
	public void levelUp() {
		for(int i = 0 ; i < level.size(); i++) {
			System.out.println(w);
			if(level.get(i) > w) {
				System.out.println(identity.get(i));
				draw(level.get(i));
				System.out.println(ex);
				break;
			}
		}
	}
}

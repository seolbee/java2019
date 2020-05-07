package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.MainGame;

public class CreateFood extends Thread{
	private MainGame game;
	private boolean over = false;
	private long time = 20000;
	private int i = 1;
	private int foodCnt = 7;
	private boolean stop = false;
	private Random rand = new Random();
	private AnchorPane pane;
	public String[] foodKind = {"scallop.png", "squid.png", "octopus.png", "fish.png"};
	public Double[] foodWeight = {0.23};
	public int food = 1;
	public ArrayList<ImageView> foodList = new ArrayList<>();
	
	public CreateFood(MainGame game, long time, AnchorPane box) {
		this.game = game;
		this.time = time;
		this.pane = box;
	}
	
	@Override
	public void run() {
		this.over = false;
		while(!over) {
			Platform.runLater(()->{
				i++;
				foodMake();
			});
			if(i > foodCnt) {
				time = 500000;
			}
			
			try {
				Thread.sleep(time);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("쓰레드 중 오류 발생");
			}
		}
	}
	
	public void setOver(boolean value) {
		over = value;
	}
	
	public void setInterVal(long time) {
		this.time = time;
	}
	
	public void foodMake() {
		int size = food;
		int i = rand.nextInt(food);
		String imagName = foodKind[i];
		Image img = new Image(getClass().getResource("/foodImage/"+imagName).toString());
		ImageView feed = new ImageView();
		feed.setImage(img);
		feed.setFitHeight(40);
		feed.setFitWidth(40);
		int feedX = rand.nextInt(450);
		int feedY = rand.nextInt(600);
		if(feedY < 100) {
			return;
		}
		feed.setX(feedX);
		feed.setY(feedY);
		pane.getChildren().add(feed);
		foodList.add(feed);
	}
}

package main;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Game {
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	private Block[][] board;
	
	private int length;
	
	private double width;
	
	private int gap = 1;
	
	private String xy = "0:7,1:6,1:7,1:8,2:4,2:5,2:6,2:8,2:9,2:10,3:6,3:7,3:8,4:0,4:7,4:14,5:0,5:7,5:14,6:0,6:2,6:6,6:7,6:8,6:11,6:14,7:0,7:1,7:3,7:6,7:7,7:8,7:11,7:13,7:14,8:0,8:1,8:2,8:3,8:4,8:6,8:7,8:8,8:10,8:11,8:12,8:13,8:14,9:0,9:1,9:2,9:4,9:5,9:6,9:7,9:8,9:9,9:10,9:12,9:13,9:14,10:0,10:1,10:2,10:3,10:4,10:5,10:6,10:7,10:8,10:9,10:10,10:11,10:12,10:13,10:14,11:0,11:1,11:3,11:4,11:5,11:6,11:7,11:8,11:9,11:10,11:11,11:13,11:14,12:1,12:2,12:3,12:4,12:5,12:6,12:7,12:8,12:9,12:10,12:11,12:12,12:13,13:2,13:3,13:5,13:7,13:9,13:11,13:12,14:0,14:1,14:2,14:3,14:4,14:5,14:6,14:7,14:8,14:9,14:10,14:11,14:12,14:13,14:14";
	private String[] pointList;
	
	private boolean gameOver = false;
	
	private boolean gameClear = false;
	
	private VBox vbox;
	
	private HBox hbox;
	
	private AnchorPane pane;
	
	public Timer timer;
	
	public Game(Canvas canvas, int xlength, VBox vbox, HBox hbox, AnchorPane pane, Timer timer) {
		this.canvas = canvas;
		this.width = Math.ceil(this.canvas.getWidth() / (xlength + gap));
		this.length = xlength;
		this.vbox = vbox;
		this.hbox = hbox;
		this.pane = pane;
		this.timer = timer;
		gc = this.canvas.getGraphicsContext2D();
		board = new Block[length][length];
		pointList = xy.split(",");
	}

	public boolean MkBoard(int pointY, int pointX) {
		for(int i = 0; i< pointList.length; i++) {
			String[] point = pointList[i].split(":"); 
			int y =(int) Double.parseDouble(point[0]);
			int x =(int) Double.parseDouble(point[1]);
			if(pointY == y && pointX ==x) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}
	
	public void click(MouseEvent e) {
		if(gameOver || gameClear) return;
		double mouseX = e.getX();
		double mouseY = e.getY();
		
		int bs = (int) this.width + 1;
		
		if(mouseX % bs < gap || mouseY % bs < gap) return;
		int x = (int) mouseX / bs;
		int y = (int) mouseY / bs;
		
		if(x >=this.length || y >=this.length) return;
		
		MouseButton btn = e.getButton();
		if(btn == MouseButton.SECONDARY) {
			if(board[y][x].isCheck()) {
				board[y][x].setColor(Color.DIMGRAY);
				board[y][x].setCheck(false);
			} else {
				board[y][x].setColor(Color.BLACK);
				board[y][x].setCheck(true);
			}
		}else if(btn == MouseButton.PRIMARY){
			if(!board[y][x].isSetBoolean()) {
				this.gameOver = true;
				board[y][x].setColor(Color.RED);
			}else {
				board[y][x].setCheck(true);
				this.checkClear();
				board[y][x].setColor(Color.WHITE);
			}
		}
		this.render(gc);
	}
	
	public void checkClear() {
		boolean clear = true;
		for(int i = 0; i< pointList.length; i++) {
			String[] point = pointList[i].split(":"); 
			int y =(int) Double.parseDouble(point[0]);
			int x =(int) Double.parseDouble(point[1]);
			if(!board[y][x].isCheck()) {
				clear = false;
				break;
			}
		}
		if(clear) {
			this.gameClear = true;
			this.sendRank();
		}
	}
	
	public void render(GraphicsContext gc) {
			for(int i = 0; i < this.length; i++) {
				for(int j = 0; j < this.length; j++) {
					double x = 1 + (width + 1) * j;
					double y = 1 + (width + 1) * i;
					gc.setFill(board[i][j].getColor());
					gc.fillRect(x, y, width, width);
				}
			}
			if(gameOver) {
				gc.setStroke(Color.WHITE);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(new Font("Arial", 40));
				gc.strokeText("game Over", this.canvas.getWidth() / 2 , this.canvas.getHeight()/2);
			}
			if(gameClear) {
				gc.setStroke(Color.WHITE);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(new Font("Arial", 40));
				gc.strokeText("game Clear", this.canvas.getWidth()/2, this.canvas.getHeight()/2);
			}
	}
	
	public void setLabel() {
		for(int i = 0; i<this.length; i++) {
			int count = 0;
			HBox hbox = new HBox();
			hbox.setMinHeight(width);
			hbox.setAlignment(Pos.CENTER_RIGHT);
			for(int j = 0; j<this.length; j++) {
				if(!board[i][j].isSetBoolean()) {
					if(count == 0) continue;
					Label label = new Label();
					label.setText(count+"");
					label.setFont(new Font("Arial", width / 2));
					hbox.getChildren().add(label);
					count = 0;
				} else {
					count++;
				}
			}
			if(count != 0) {
				Label label = new Label();
				label.setText(count+"");
				label.setFont(new Font("Arial", width / 2));
				hbox.getChildren().add(label);
				count = 0;
			}
			vbox.getChildren().add(hbox);
		}
		
		for(int i = 0; i<this.length; i++) {
			int count = 0;
			VBox vbox = new VBox();
			vbox.setMinWidth(width);
			vbox.setAlignment(Pos.CENTER);
			for(int j = 0; j<this.length; j++) {
				if(!board[j][i].isSetBoolean()) {
					if(count == 0) continue;
					Label label = new Label();
					label.setText(count+"");
					label.setFont(new Font("Arial", width / 2));
					vbox.getChildren().add(label);
					count = 0;
				}else {
					count++;
				}
			}
			if(count != 0) {
				Label label = new Label();
				label.setText(count+"");
				label.setFont(new Font("Arial", width / 2));
				vbox.getChildren().add(label);
				count = 0;
			}
			
			hbox.getChildren().add(vbox);
		}
	}
	
	public void gameStart(){
		for(int i = 0; i < this.length; i++) {
			for(int j = 0; j < this.length; j++) {
				double x = 1 + (width + 1) * j;
				double y = 1 + (width + 1) * i;
				gc.fillRect(x, y, width, width);
				board[i][j] = new Block(MkBoard(i, j), Color.BLACK);
			}
		}
		setLabel();
	}
	
	public void sendRank() {
		
	}
}

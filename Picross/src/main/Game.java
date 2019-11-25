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
	
	private String xy = "0:0,0:1,0:2,0:3,0:4";
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
			int x =(int) Double.parseDouble(point[0]);
			int y =(int) Double.parseDouble(point[1]);
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
		
		MouseButton btn = e.getButton();
		if(btn == MouseButton.SECONDARY) {
			board[y][x].setColor(Color.DIMGRAY);
		}else if(btn == MouseButton.PRIMARY){	
			if(x >=this.length || y >=this.length) return;
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
			int x =(int) Double.parseDouble(point[0]);
			int y =(int) Double.parseDouble(point[1]);
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
			hbox.setAlignment(Pos.TOP_RIGHT);
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

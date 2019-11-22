package main;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	
	private String xy = "0:1,2:4,4:0,1:2,0:2,4:1,3:1,3:3";
	private String[] pointList;
	
	private boolean gameOver = false;
	
	private VBox vbox;
	
	private HBox hbox;
	
	public Game(Canvas canvas, int xlength, VBox pane, HBox apane) {
		this.canvas = canvas;
		this.width = this.canvas.getWidth() / (xlength + gap);
		this.length = xlength;
		this.vbox = pane;
		this.hbox = apane;
		System.out.println(this.canvas.getWidth());
		System.out.println(this.width);
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
		if(gameOver) return;
		double mouseX = e.getX();
		double mouseY = e.getY();
		
		int bs = (int) this.width + 1;
		
		if(mouseX % bs < gap || mouseY % bs < gap) return;
		int x = (int) mouseX / bs;
		int y = (int) mouseY / bs;
		if(x >=this.length || y >=this.length) return;
		if(!board[y][x].isSetBoolean()) {
			this.gameOver = true;
			board[y][x].setColor(Color.RED);
		}else {
			board[y][x].setColor(Color.WHITE);
		}
		this.render(gc);
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
				gc.setFont(new Font("Arial", 30));
				gc.strokeText("game Over", this.canvas.getWidth() / 2, this.canvas.getHeight()/2);
			}
	}
	
	public void setLabel() {
		for(int i = 0; i<this.length; i++) {
			int count = 0;
			HBox hbox = new HBox();
			hbox.setMinHeight(width);
			hbox.setAlignment(Pos.CENTER_RIGHT);
			for(int j = 0; j<this.length; j++) {
				if(board[i][j].isSetBoolean()) {
					count++;
				} else {
					if(count == 0) continue;
					Label label = new Label();
					label.setText(count+"");
					label.setFont(new Font("Arial", 20));
					hbox.getChildren().add(label);
					count = 0;
				}
			}
			vbox.getChildren().add(hbox);
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
}

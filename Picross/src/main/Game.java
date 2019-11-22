package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
	
	private Label label;
	
	private String xy = "0:1,2:4,4:0,1:2";
	private String[] pointList;
	
	private boolean gameOver = false;
	
	public Game(Canvas canvas, int xlength, Label label) {
		this.canvas = canvas;
		this.width = this.canvas.getWidth() / (xlength + gap);
		this.length = xlength;
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
			System.out.println(x + ", " + y);
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
		for(int z  = 0; z < this.length; z++) {
			for(int i = 0; i<this.length; i++) {
				int count = 0;
				for(int j = 0; j<this.length; j++) {
					if(board[i][j].isSetBoolean()) {
						count++;
					} else {
						System.out.println(count);
						count = 0;
					}
				}
			}
		}
	}
	
	public void gameStart(){
		for(int i = 0; i < this.length; i++) {
			for(int j = 0; j < this.length; j++) {
				double x = 1 + (width + 1) * j;
				double y = 1 + (width + 1) * i;
				gc.fillRect(x, y, width, width);
				System.out.println(MkBoard(i, j));
				board[i][j] = new Block(MkBoard(i, j), Color.BLACK);
			}
		}
		setLabel();
	}
}

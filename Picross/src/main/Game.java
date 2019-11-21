package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
		System.out.println(board[y][x].isSetBoolean());
		if(x >=this.length || y >=this.length) return;
		if(!board[y][x].isSetBoolean()) gameOver = true;
		board[y][x].setColor(Color.WHITE);
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
	}
}

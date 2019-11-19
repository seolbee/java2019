package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Game {
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	private Block[][] board;
	
	private int length;
	
	private int width;
	
	public Game(Canvas canvas, int xlength) {
		this.canvas = canvas;
		this.width = (int) this.canvas.getWidth();
		this.length = xlength;
		
		gc = this.canvas.getGraphicsContext2D();
		board = new Block[length][length];
	}
	
	public void click(MouseEvent e) {
		int mouseX = (int) e.getX();
		int mouseY = (int) e.getY();
		
		int bs = (int)this.canvas.getWidth()/12 + 5;
		
		int x = mouseX / bs;
		int y = mouseY / bs;
		System.out.println(y+", "+x);
		board[y][x].setColor(Color.WHITE);
		board[y][x].render(gc, width, y, x);
	}
	
	public void gameStart(){
		
		for(int i = 0; i < this.length; i++) {
			for(int j = 0; j < this.length; j++) {
				board[i][j] = new Block(false);
				board[i][j].setColor(Color.BLACK);
				board[i][j].render(gc, width, i, j);
			}
		}
	}
}

package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	private Block[][] board;
	
	private int length;
	
	public Game(Canvas canvas, int xlength) {
		this.canvas = canvas;
		
		this.length = xlength;
		
		gc = this.canvas.getGraphicsContext2D();
		board = new Block[length][length];
	}
	
	public void update() {
		
	}
	
	public void click() {
		
	}
	
	public void gameStart(){
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				board[i][j] = new Block(Color.WHITE, false);
			}
		}
	}
}

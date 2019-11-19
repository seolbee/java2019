package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block {
	
	private Color color;
	
	private int width;
	
	private int height;
	
	private boolean setBoolean;
	
	public Block(boolean bo) {
		this.setBoolean = bo;
	}

	public boolean isSetBoolean() {
		return setBoolean;
	}
	
	public void render(GraphicsContext gc, int width, int i, int j) {
		gc.setFill(this.color);
		int x = 5 + (width / 12 + 5) * j;
		int y = 5 + (width / 12 + 5) * i;
		gc.fillRect(x, y, width/12, width/12);
	}

	public void setColor(Color white) {
		this.color = color;
	}
}

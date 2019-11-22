package main;

import javafx.scene.paint.Color;

public class Block {
	
	private Color color;
	
	private int width;
	
	private int height;
	
	private boolean setBoolean;
	
	public Block(boolean bo, Color color) {
		this.setBoolean = bo;
		this.color = color;
	}

	public boolean isSetBoolean() {
		return setBoolean;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
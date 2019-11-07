package main;

import javafx.scene.paint.Color;

public class Block {
	
	private Color color;
	
	private boolean setBoolean;
	
	public Block(Color color, boolean bo) {
		this.color = color;
		this.setBoolean = bo;
	}

	public boolean isSetBoolean() {
		return setBoolean;
	}
}

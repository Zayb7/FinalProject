package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Reticule{
	public enum Direction {
		UP,DOWN,LEFT,RIGHT;
	}
	//variables
	private int row, col;
	static final int SIZE = 50;
	
	public Reticule() {
		row = col = 0;
	}
	
	//methods
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3F));
		g2d.setColor(Color.BLACK);
		g2d.drawRect(col * SIZE, row * SIZE, SIZE * 2, SIZE);
	}
	
	public void move(Direction d) {
		// TODO Auto-generated method stub

	}
}

package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Reticule {
	static final int ROWS = 15;
	static final int COLS = 8;
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
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.MAGENTA);
		g2d.drawRect(col * SIZE, row * SIZE, SIZE * 2, SIZE);
	}
	
	public void move(Direction d) {
		switch (d) {
		case UP:
			tryMove(row - 1, col);
			break;
		case DOWN:
			tryMove(row + 1, col);
			break;
		case LEFT:
			tryMove(row, col - 1);
			break;
		case RIGHT:
			tryMove(row, col + 1);
			break;
		}
	}

	private void tryMove(int i, int j) {
		if ((i >= 0) && (j >=0) && (i < ROWS) && (j < COLS - 1)) {
			row = i;
			col = j;
		}
	}
}

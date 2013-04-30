package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Reticule {
	// Constants
	static final int ROWS = 12;
	static final int COLS = 7;
	static final int SIZE = 50;
	private int row, col;
	public enum Direction {
		UP,DOWN,LEFT,RIGHT;
	}
	public Reticule() {
		row = col = 0;
	}
	public void draw(Graphics g){
		Random rng = new Random();
		int widthRand = (int) (Math.random()*100)%8;
		int heightRand = (int) (Math.random()*100)%8;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(8));
		g2d.setColor(Color.ORANGE);
		g2d.drawRect(col * SIZE - widthRand/2, row * SIZE - heightRand/2, SIZE * 2 + widthRand, SIZE + heightRand);
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(Color.BLACK);
		g2d.drawRect(col * SIZE - widthRand/2 - 4, row * SIZE - heightRand/2 - 4, SIZE * 2 + widthRand + 8, SIZE + heightRand + 8);
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
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
}
package game;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Bubble {

	private int row, col;
	static final int BUBBLE_SIZE = 50;
	private Color bubbleColor;
	private boolean isEmpty;

	public Bubble(int r, int c){
		Random rng = new Random();
		int randColorInt = rng.nextInt(6);
		row = r;
		col = c;
		isEmpty = false;
		setBubbleColor(Integer.toString(randColorInt));
	}
	
	public Bubble(int row, int col, boolean isEmpty, Color bubbleColor){
		this.row = row;
		this.col = col;
		this.isEmpty = isEmpty;
		this.bubbleColor = bubbleColor;
	}

	public boolean colorEquals(Bubble that) {
		if (this.getBubbleColor() == that.getBubbleColor()) {
			return true;
		}
		return false;
	}

	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	public Color getBubbleColor() {
		return bubbleColor;
	}

	public void setBubbleColor(Color bubbleColor) {
		this.bubbleColor = bubbleColor;
	}

	public void setBubbleColor(String bubbleChar) {
		switch (bubbleChar) {
		case ("R"):
			bubbleColor = Color.RED;
			break;
		case ("B"):
			bubbleColor = Color.BLUE;
			break;
		case ("G"):
			bubbleColor = Color.GREEN;
			break;
		case ("0"):
			bubbleColor = new Color(255,0,0);
		break;
		case ("1"):
			bubbleColor = new Color(0,255,0);
		break;
		case ("2"):
			bubbleColor = new Color(0,0,255);
		break;
		case ("3"):
			bubbleColor = Color.CYAN;
		break;
		case ("4"):
			bubbleColor = Color.MAGENTA;
		break;
		case ("5"):
			bubbleColor = Color.YELLOW;
		break;
		default:
			System.out.println("That's not a color.");
		}
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bubbleColor == null) ? 0 : bubbleColor.hashCode());
		result = prime * result + col;
		result = prime * result + (isEmpty ? 1231 : 1237);
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bubble other = (Bubble) obj;
		if (bubbleColor == null) {
			if (other.bubbleColor != null)
				return false;
		} else if (!bubbleColor.equals(other.bubbleColor))
			return false;
		if (col != other.col)
			return false;
		if (isEmpty != other.isEmpty)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public void draw(Graphics g){
		Random rng = new Random();
		int widthRand = (int) (Math.random()*100)%8;
		int heightRand = (int) (Math.random()*100)%8;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(bubbleColor);
		g2d.fillOval((col * BUBBLE_SIZE) - widthRand/2, (row * BUBBLE_SIZE) - heightRand, BUBBLE_SIZE + widthRand, BUBBLE_SIZE + heightRand);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawOval((col * BUBBLE_SIZE) - widthRand/2, (row * BUBBLE_SIZE) - heightRand, BUBBLE_SIZE + widthRand, BUBBLE_SIZE + heightRand);
		g2d.setColor(Color.WHITE);
		g2d.fillOval((col * BUBBLE_SIZE) + 13- widthRand/2, (row * BUBBLE_SIZE) + 10 - heightRand, 10 + /*widthRand*/rng.nextInt(5), 7 + /*heightRand*/rng.nextInt(5));
	}
}
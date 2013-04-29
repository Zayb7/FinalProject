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
		int randColorInt = rng.nextInt(9);
		row = r;
		col = c;
		isEmpty = false;
		setBubbleColor(Integer.toString(randColorInt));
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

	public int getCol() {
		return col;
	}
	
	public Color getBubbleColor() {
		return bubbleColor;
	}

	public void setBubbleColor(Color bubbleColor) {
		this.bubbleColor = bubbleColor;
	}

	public void setBubbleColor(String bubbleChar) {
		switch (bubbleChar) {
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
			bubbleColor = new Color(255,255,255);
		break;
		case ("4"):
			bubbleColor = new Color(0,0,0);
		break;
		case ("5"):
			bubbleColor = Color.DARK_GRAY;
		break;
		case ("6"):
			bubbleColor = Color.CYAN;
		break;
		case ("7"):
			bubbleColor = Color.YELLOW;
		break;
		case ("8"):
			bubbleColor = Color.MAGENTA;
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
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(bubbleColor);
		g2d.fillOval((col * BUBBLE_SIZE), (row * BUBBLE_SIZE), BUBBLE_SIZE, BUBBLE_SIZE);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawOval((col * BUBBLE_SIZE), (row * BUBBLE_SIZE), BUBBLE_SIZE, BUBBLE_SIZE);
	}
}

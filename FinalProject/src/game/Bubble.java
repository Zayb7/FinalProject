package game;

import java.awt.Graphics;
import java.awt.Color;

public class Bubble {

		private int row, col;
		static final int BUBBLE_SIZE = 50;
		private Color bubbleColor;
		private boolean isEmpty;
		
		public Bubble(int r, int c){
			int randColorInt = (int) ((Math.random() * 100) % 3);
			row = r;
			col = c;
			isEmpty = false;
			setBubbleColor(Integer.toString(randColorInt));
			//bubbleColor = Color.WHITE;
		}
		
		public Bubble(int r, int c, boolean isRand){
			row = r;
			col = c;
			isEmpty = false;
			
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
				bubbleColor = new Color(255,255,255);
				break;
			case ("4"):
				bubbleColor = new Color(0,0,0);
				break;
			default:
				System.out.println("You suck. That's not a color.");
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
			g.setColor(bubbleColor);
			g.fillOval((col * BUBBLE_SIZE), (row * BUBBLE_SIZE), BUBBLE_SIZE, BUBBLE_SIZE);
			g.setColor(Color.BLACK);
			g.drawOval((col * BUBBLE_SIZE), (row * BUBBLE_SIZE), BUBBLE_SIZE, BUBBLE_SIZE);
		}
}

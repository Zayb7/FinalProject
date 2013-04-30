package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel{
	// variables
	private Bubble[][] bubbleBoard;
	private static int ROWS = 15;
	private static int COLS = 8;
	// Will be set based upon timer
	private boolean end;
	public Reticule reticule;
	private int score;

	// methods
	public Board(boolean isRandom) {
		end = false;
		new ArrayList<Bubble>();
		reticule = new Reticule();
		if (isRandom) {
			bubbleBoard = new Bubble[ROWS][COLS];
			Bubble temp = null;
			Random rng = new Random();
			for (int row = 0; row < ROWS; ++row) {
				for (int col = 0; col < COLS; ++col) {
					temp = new Bubble(row, col);
					temp.setEmpty(false);
					temp.setBubbleColor(Integer.toString((rng.nextInt(5))));
					bubbleBoard[row][col] = temp;
				}
			}
		}
		else {
			Scanner inputFile = null;
			ROWS = 10;
			COLS = 20;
			bubbleBoard = new Bubble[ROWS][COLS];
			try {
				inputFile = new Scanner(new FileReader("TEST_BOARD.csv"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int row = 0;
			int col = 0;
			while (inputFile.hasNextLine()) {

				String line = inputFile.nextLine();
				String[] toSplit = line.split(",");
				for (String cellColor : toSplit) {
					Bubble tempBubble = new Bubble(row, col, false, Color.WHITE);
					tempBubble.setBubbleColor(cellColor);
					tempBubble.setEmpty(false);
					bubbleBoard[row][col] = tempBubble;
					col++;
				}
				col = 0;
				row++;
			}
		}
	}


	public void swap(Bubble one, Bubble two) {
		if(one.isEmpty() || two.isEmpty()) {
			return;
		}
		Color tempColor = one.getBubbleColor();
		one.setBubbleColor(two.getBubbleColor());
		two.setBubbleColor(tempColor);
		bubbleBoard[one.getRow()][one.getCol()] = one;
		bubbleBoard[two.getRow()][two.getCol()] = two;
	}
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 5000, 5000);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				bubbleBoard[i][j].draw(g);
			}
		}
		reticule.draw(g);
	}

	public void detectLinear(boolean gameStart) {
		boolean popped = false;
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (!bubbleBoard[row][col].isEmpty()) {
					if (row + 2 < ROWS && bubbleBoard[row][col].getBubbleColor().equals(bubbleBoard[row + 1][col].getBubbleColor()) && bubbleBoard[row + 1][col].getBubbleColor().equals(bubbleBoard[row + 2][col].getBubbleColor())) {
						bubbleBoard[row][col].setEmpty(true);
						bubbleBoard[row + 1][col].setEmpty(true);
						bubbleBoard[row + 2][col].setEmpty(true);
						bubbleBoard[row][col].setBubbleColor(Color.PINK);
						bubbleBoard[row + 1][col].setBubbleColor(Color.PINK);
						bubbleBoard[row + 2][col].setBubbleColor(Color.PINK);
						popped = true;
						if (gameStart) {
							this.calculateScore();
						}
					}
					else if (col + 2 < COLS && bubbleBoard[row][col].getBubbleColor().equals(bubbleBoard[row][col + 1].getBubbleColor()) && bubbleBoard[row][col + 1].getBubbleColor().equals(bubbleBoard[row][col + 2].getBubbleColor())) {
						bubbleBoard[row][col].setEmpty(true);
						bubbleBoard[row][col + 1].setEmpty(true);
						bubbleBoard[row][col + 2].setEmpty(true);
						bubbleBoard[row][col].setBubbleColor(Color.PINK);
						bubbleBoard[row][col + 1].setBubbleColor(Color.PINK);
						bubbleBoard[row][col + 2].setBubbleColor(Color.PINK);
						popped = true;
						if (gameStart) {
							this.calculateScore();
						}
					}
				}
			}
		}
		if(popped){
			try {
				Thread.sleep(500);  
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void fallHelper(Bubble aBubble, int row, int col) {
		if (aBubble.isEmpty()) {
			if (row == 0) {
				aBubble = new Bubble(aBubble.getRow(), aBubble.getCol());
				aBubble.setEmpty(false);
			}
			else {
				if (bubbleBoard[row-1][col].isEmpty())
					fallHelper(aBubble, row-1, col);
				else {
					aBubble.setBubbleColor(bubbleBoard[row-1][col].getBubbleColor());
					aBubble.setEmpty(false);
					bubbleBoard[row-1][col].setEmpty(true);
					bubbleBoard[row-1][col].setBubbleColor(Color.PINK);
					fallHelper(bubbleBoard[aBubble.getRow()][aBubble.getCol()], row-1, col);
				}
			}
		}
	}
	public void fallMaster() {
		for (int j = ROWS - 1; j >= 0; j--) {
			for(int i = 0; i < COLS; i++) {
				fallHelper(bubbleBoard[j][i], j, i);
			}
		}

		for (int j = ROWS - 1; j >= 0; j--) {
			for(int i = 0; i < COLS; i++) {
				if(bubbleBoard[j][i].isEmpty())
					bubbleBoard[j][i] = new Bubble(j, i);

			}
		}
	}

	public void checkEnd() {

	}

	public void calculateScore() {
		score += 100; 
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public static int getRows() {
		return ROWS;
	}

	public static void setRows(int rOWS) {
		ROWS = rOWS;
	}

	public static int getCols() {
		return COLS;
	}

	public static void setCols(int cOLS) {
		COLS = cOLS;
	}

	public Bubble getBubbleAt(int r, int c) {
		return bubbleBoard[r][c];
	}


	public Bubble[][] getBubbleBoard() {
		return bubbleBoard;
	}

}
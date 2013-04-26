package game;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.awt.Color;

import javax.swing.JPanel;

public class Board extends JPanel {
	// variables
	private Bubble[][] bubbleBoard;
	static final int ROWS = 10;
	static final int COLS = 20;
	private int movesMade;
	private int maximum_moves = 10;
	private Timer timer;
	private boolean end;


	private String playerName;
	private int highScore;

	// methods
	public Board(boolean isRandom) {
		bubbleBoard = new Bubble[ROWS][COLS];
		if (isRandom) {
			Bubble temp = null;
			Random rng = new Random();
			Color[] colorsMapIshThing = { Color.RED, Color.BLUE, Color.GREEN };
			for (int row = 0; row < ROWS; ++row) {
				for (int col = 0; col < COLS; ++col) {
					temp = new Bubble(row, col);
					temp.setBubbleColor(Integer.toString((rng.nextInt(3))));
					bubbleBoard[row][col] = temp;
				}
			}
		} else {
			Scanner inputFile = null;
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
					bubbleBoard[row][col] = tempBubble;
					col++;
				}
				col = 0;
				row++;
			}
		}

	}

	public int getIntTimer() {
		return 0;
	}

	public void setIntTimer(int timer) {
		// TODO: setup int to timer conversion
	}

	public void swap(Bubble one, Bubble two) {
		if(one.isEmpty() || two.isEmpty())
			return;
		Bubble tempBubbleOne = new Bubble(one.getRow(), one.getCol(), false, one.getBubbleColor());
		one.setBubbleColor(two.getBubbleColor());
		two.setBubbleColor(tempBubbleOne.getBubbleColor());
		bubbleBoard[one.getRow()][one.getCol()] = one;
		bubbleBoard[two.getRow()][two.getCol()] = two;
	}

	public void draw(Graphics g) {

	}

	public void detectMaster() {

	}

	public ArrayList<Bubble> detectHelper(Bubble aBubble) {
		return null;
	}

	public void fallHelper(Bubble aBubble) {
		if (aBubble.isEmpty()) {
			if(aBubble.getRow() == 0) {
				aBubble = new Bubble(aBubble.getRow(), aBubble.getCol());
			} else {
				aBubble.setBubbleColor(bubbleBoard[aBubble.getRow()-1][aBubble.getCol()].getBubbleColor());
				bubbleBoard[aBubble.getRow()-1][aBubble.getCol()].setEmpty(true);
			}
		}
	}

	public void fallMaster() {
		//needs to iterate through all squares starting with a corner on the bottom row
	}

	public void moveReticle(int newRow, int newCol) {

	}

	public void checkEnd() {

	}

	public int calculateScore() {
		return 0;
	}

	// getters and setters
	public Bubble[][] getBubbleBoard() {
		return bubbleBoard;
	}

	public Bubble getBubble(int row, int col) {
		return bubbleBoard[row][col];
	}

	public void setBubble(Bubble aBubble) {

	}
	public int getMovesMade() {
		return movesMade;
	}

	public void setMovesMade(int movesMade) {
		this.movesMade = movesMade;
	}

	public int getMaximum_moves() {
		return maximum_moves;
	}

	public void setMaximum_moves(int maximum_moves) {
		this.maximum_moves = maximum_moves;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
	
	public static int getRows() {
		return ROWS;
	}

	public static int getCols() {
		return COLS;
	}


	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public void setBubbleBoard(Bubble[][] bubbleBoard) {
		this.bubbleBoard = bubbleBoard;
	}

}
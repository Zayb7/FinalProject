package game;

import javax.swing.JFrame;

public class Game extends JFrame {
	//variables
	private Board board;
	
	
	public void Game(){
		board = new Board(true);
	}
	
	//variables
	private int highScore;
	private final String HIGH_SCORE_FILE ="";
	
	
	//writing the final file
	public void updateHighScores(){
		
	}
	public static void main(String[] args) {
		Game game = new Game();
		
		
	}

}

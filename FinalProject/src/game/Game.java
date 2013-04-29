package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame {
	//variables
	public Board board;
	
	
	public Game(){
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
		
		game.add(game.board, BorderLayout.CENTER);
		
		game.createControlPanel();
		
		game.setTitle("Mustard Bubbles");
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(1280, 768);
		game.setVisible(true);
		
		
	}
	private void createControlPanel() {
		// TODO Auto-generated method stub

	}
}

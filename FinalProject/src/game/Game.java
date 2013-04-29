package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JFrame {
	//variables
	public Board board;
	private JPanel controlPanel;
	
	public Game(){
		board = new Board(true);
		controlPanel = controlPanel();
		
		add(board, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.EAST);
		
		setTitle("Mustard Bubbles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 768);
		setVisible(true);
		
	}
	
	//writing the final file
	public void updateHighScores(){
		
	}
	public static void main(String[] args) {
		new Game();

	}
	
	private JPanel controlPanel() {
		JPanel controlPanel = new JPanel();
		
		
		return controlPanel;
	}
}

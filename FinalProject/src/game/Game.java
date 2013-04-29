package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
		setSize(580, 750);
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
		controlPanel.setLayout(new GridLayout(0, 1));
		JButton start = new JButton("Start Game");
		JButton stop = new JButton("STAHP");
		JButton reset = new JButton("Reset dat bitch");
		
		JPanel scorePanel = new JPanel();
		JTextField score = new JTextField("Score this madafaka");
		JTextField timer = new JTextField("0");
		timer.setEditable(false);
		score.setEditable(false);
		scorePanel.setLayout(new GridLayout(0, 1));
		scorePanel.add(score);
		scorePanel.add(timer, BorderLayout.CENTER);
		scorePanel.setBorder(new TitledBorder(new EtchedBorder(), "Score and Time"));
		
		
		controlPanel.add(start);
		controlPanel.add(stop);
		controlPanel.add(reset);
		controlPanel.add(scorePanel);
		controlPanel.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));
		
		
		return controlPanel;
	}
}

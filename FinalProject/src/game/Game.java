package game;

import game.Reticule.Direction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Game extends JFrame {
	//variables
	public Board board;
	private JPanel controlPanel;

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Game game = new Game();
		InputMap im = game.board.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = game.board.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownArrow");

		am.put("RightArrow", game.new KeyboardAction("RightArrow", game));
		am.put("LeftArrow", game.new KeyboardAction("LeftArrow", game));
		am.put("UpArrow", game.new KeyboardAction("UpArrow", game));
		am.put("DownArrow", game.new KeyboardAction("DownArrow", game));
		
		while (true) {
			game.repaint();
		}
	}

	public class KeyboardAction extends AbstractAction {

		public String cmd;
		public Game game;

		public KeyboardAction(String cmd, Game game) {
			this.cmd = cmd;
			this.game = game;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (cmd.equalsIgnoreCase("LeftArrow")) {
				game.board.reticule.move(Direction.LEFT);
			}
			else if (cmd.equalsIgnoreCase("RightArrow")) {
				game.board.reticule.move(Direction.RIGHT);
			}
			else if (cmd.equalsIgnoreCase("UpArrow")) {
				game.board.reticule.move(Direction.UP);
			}
			else if (cmd.equalsIgnoreCase("DownArrow")) {
				game.board.reticule.move(Direction.DOWN);
			}
		}

	}

	public Game(){
		board = new Board();
		controlPanel = controlPanel();
		add(board, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.EAST);
		setTitle("Mustard Bubbles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 750);
		setVisible(true);

	}
	private JPanel controlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(0, 1));
		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");
		JButton reset = new JButton("Reset");

		JPanel scorePanel = new JPanel();
		JTextField score = new JTextField("Score");
		JTextField timer = new JTextField("0");
		timer.setEditable(false);
		score.setEditable(false);
		scorePanel.setLayout(new GridLayout(0, 1));
		scorePanel.add(score);
		scorePanel.add(timer, BorderLayout.CENTER);
		scorePanel.setBorder(new TitledBorder(new EtchedBorder(), "Info"));


		controlPanel.add(start);
		controlPanel.add(stop);
		controlPanel.add(reset);
		controlPanel.add(scorePanel);
		controlPanel.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));


		return controlPanel;
	}
}

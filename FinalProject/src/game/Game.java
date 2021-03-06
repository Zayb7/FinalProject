package game;

import game.Reticule.Direction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	// Fields
	public Board board;
	private JPanel controlPanel;
	private static JButton start;
	private JButton stop;
	private JButton reset;
	private JButton epilepsyButton;
	public static JTextField score;
	public static JTextField timer;
	public boolean gameStart = false;
	private static boolean isRunning;
	private static boolean justStarted;
	private static final long GAME_LENGTH = 100;
	private static long startTime;
	
	public static void main(String[] args) {
		Game game = new Game();
		prepareMaps(game);
		startTime = System.currentTimeMillis()/1000;

		while(true){
			gameLoop(game);
		}
	}

	private static void prepareMaps(Game game) {
		InputMap im = game.board.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = game.board.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "SpaceTheFinalFrontier");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0), "Shift");

		am.put("RightArrow", game.new KeyboardAction("RightArrow", game));
		am.put("LeftArrow", game.new KeyboardAction("LeftArrow", game));
		am.put("UpArrow", game.new KeyboardAction("UpArrow", game));
		am.put("DownArrow", game.new KeyboardAction("DownArrow", game));
		am.put("SpaceTheFinalFrontier", game.new KeyboardAction("BPress", game));
		am.put("Shift", game.new KeyboardAction("Shift", game));
	}
	

	private static void gameLoop(Game game) {
		while (isRunning) {
			if (justStarted) {
				game.board.setScore(0);
				justStarted = false;
			}
//			
			game.board.detectLinear(game.gameStart);
			game.board.fallMaster();
			game.score.setText(Integer.toString(game.board.getScore()));
			game.timer.setText(Integer.toString((int) (GAME_LENGTH- (System.currentTimeMillis()/1000-startTime))));
			//System.out.println(GAME_LENGTH- (System.currentTimeMillis()/1000-startTime));
			game.repaint();
			
			if ((startTime + GAME_LENGTH) <= System.currentTimeMillis()/1000) {
				isRunning = false;
				game.timer.setText("END!");
				//start.setEnabled(true);
				
			}
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
			else if (cmd.equalsIgnoreCase("UpArrow")) {
				game.board.reticule.move(Direction.UP);
			}
			else if (cmd.equalsIgnoreCase("DownArrow")) {
				game.board.reticule.move(Direction.DOWN);
			}
			else if (cmd.equalsIgnoreCase("RightArrow")) {
				game.board.reticule.move(Direction.RIGHT);
			}
			
			else if (cmd.equalsIgnoreCase("BPress")) {
				int r = game.board.reticule.getRow();
				int c = game.board.reticule.getCol();
				if (game.board.reticule.isHoriz()) {
					game.board.swap(game.board.getBubbleAt(r, c), game.board.getBubbleAt(r, c + 1));
				} else {
					game.board.swap(game.board.getBubbleAt(r, c), game.board.getBubbleAt(r + 1, c));
				}
				game.gameStart = true;
			} else if (cmd.equalsIgnoreCase("Shift")) {
				game.board.reticule.toggleHorizontal();
			}
		}

	}

	public Game(){
		board = new Board(true);
		controlPanel = controlPanel();
		add(board, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		setTitle("Mustard Bubbles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(370, 755);
		setVisible(true);
		isRunning = false;
		//stop.setEnabled(false);


	}
	private JPanel controlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 0));
		start = new JButton("Start");
		stop = new JButton("Stop");
		epilepsyButton = new JButton("Epilepsy?");
		start.addActionListener(new ButtonListener());
		stop.addActionListener(new ButtonListener());
		epilepsyButton.addActionListener(new ButtonListener());
//		start.setFocusable(false);
//		stop.setFocusable(false);
//		epilepsyButton.setFocusable(false);
		
		
		
		
		JPanel holderPanel = new JPanel();
		holderPanel.setLayout(new GridLayout(0,1));
		JPanel scorePanel = new JPanel();
		score = new JTextField("Score");
		timer = new JTextField("0");
		timer.setEditable(false);
		score.setEditable(false);
		//Why??
		//score.setBorder(new TitledBorder("Score: "));
		//timer.setBorder(new TitledBorder("Time Left: "));
		scorePanel.setLayout(new GridLayout(1, 0));
		scorePanel.add(score);
		scorePanel.add(timer, BorderLayout.CENTER);
		scorePanel.setBorder(new TitledBorder(new EtchedBorder(), "Score:       Time: "));
		controlPanel.add(start);
		controlPanel.add(stop);
		holderPanel.add(scorePanel);
		holderPanel.add(epilepsyButton);
		controlPanel.add(holderPanel);
		controlPanel.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));


		return controlPanel;
	}
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().toString().contains("Start")) {
				startTime = System.currentTimeMillis()/1000;
				isRunning = true;
				start.setEnabled(false);
				justStarted = true;
			} else if (e.getSource().toString().contains("Stop")) {
				isRunning = false;
				start.setEnabled(true);
			}else {
				if (board.isEpilepsy()) {
					board.setEpilepsy(false);
				} else {
					board.setEpilepsy(true);
				}
			}
			
		}
		
	}
}

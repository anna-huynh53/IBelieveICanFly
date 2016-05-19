import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Controls the contents and design of the window which displays the program 
 */

public class UIFrame extends JFrame implements KeyListener {
	private JPanel panelContainer;
	private MenuPanel menuScreen; // contains the main menu components	
	private DifficultyPanel difficultyScreen;
	private GamePanel gameScreen; // contains the displayed maze and its components
	private CardLayout panelDeck;

	public UIFrame(Maze m) {
		this.panelContainer = new JPanel();
		this.menuScreen = new MenuPanel();
		this.difficultyScreen = new DifficultyPanel();
		this.gameScreen = new GamePanel(m);
		this.panelDeck = new CardLayout();
		initUIFrame();
		initMenuScreen();
		initDifficultyScreen();
		initGameScreen();
	}

	/**
	 * Initialises the frame and all components it contains
	 */
	private void initUIFrame() {

		// setup the frame
		this.setTitle("I believe I can fly");
		this.setSize(850, 900);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(panelContainer);
		this.setVisible(true);
		BoxLayout boxLayout = new BoxLayout(menuScreen, BoxLayout.Y_AXIS);
		menuScreen.setLayout(boxLayout);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		// setup the panel container
		panelContainer.setLayout(panelDeck);
		panelContainer.add(menuScreen, "menuScreen");
		panelContainer.add(gameScreen, "gameScreen");
		panelContainer.add(difficultyScreen, "difficultyScreen");
		panelDeck.show(panelContainer, "menuScreen");

		addKeyListener(this);
		setFocusable(true);
	}
	
	private void initMenuScreen() {
		// when menuPanel's start button is pressed, show difficulty choices
		menuScreen.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDeck.show(panelContainer, "difficultyScreen");
			}
		});

		// quit - closes the window exits the main menu
		menuScreen.getQuitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * Sets up the difficulty pane
	 */
	private void initDifficultyScreen() {
		BoxLayout boxLayout = new BoxLayout(difficultyScreen, BoxLayout.Y_AXIS);
		difficultyScreen.setLayout(boxLayout);
		
		difficultyScreen.getEasyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.setMaze(new Maze(10, "prim"));
				panelDeck.show(panelContainer, "gameScreen");
			}
		});

		difficultyScreen.getMediumButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.setMaze(new Maze(15, "prim"));
				panelDeck.show(panelContainer, "gameScreen");
			}
		});

		difficultyScreen.getHardButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.setMaze(new Maze(20, "depth"));
				panelDeck.show(panelContainer, "gameScreen");
			}
		});
	}

	private void initGameScreen() {
		// when gamePanel's back button is pressed, return to the menu
		gameScreen.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDeck.show(panelContainer, "menuScreen");
			}
		});
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			gameScreen.getMaze().getPlayer().moveWest();
		} else if (key == KeyEvent.VK_RIGHT) {
			gameScreen.getMaze().getPlayer().moveEast();
		} else if (key == KeyEvent.VK_UP) {
			gameScreen.getMaze().getPlayer().moveNorth();
		} else if (key == KeyEvent.VK_DOWN) {
			gameScreen.getMaze().getPlayer().moveSouth();
		}
		gameScreen.repaint();
		if (gameScreen.getMaze().isGameOver()) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(gameScreen, "Well that was underwhelming. At least you got a 'ding'?");
			panelDeck.show(panelContainer, "menuScreen");
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public GamePanel getGamePanel() {
		return this.gameScreen;
	}
}
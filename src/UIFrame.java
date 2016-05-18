import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Controls the contents and design of the window which displays the program ..
 */

public class UIFrame extends JFrame implements KeyListener {
	private JPanel panelContainer;
	private MenuPanel menuScreen; // the panel that contains the main menu
									// components
	private JPanel gameScreen; // the panel that contains the displayed maze and
								// its components
	private GamePanel gamePanel; // the panel displaying the current maze
	private JPanel difficultyScreen;
	private CardLayout panelDeck;

	public UIFrame(Maze m) {
		this.panelContainer = new JPanel();
		this.gameScreen = new JPanel();
		this.gamePanel = new GamePanel(m);
		this.menuScreen = new MenuPanel();
		this.difficultyScreen = new JPanel();
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
		this.setTitle("Maze Game");
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

	/**
	 * Sets up the difficulty pane
	 */
	private void initDifficultyScreen() {
		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("Hard");

		// when start button is pressed
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.setMaze(new Maze(10, "prim"));
				panelDeck.show(panelContainer, "gameScreen");
			}
		});

		// when instruction button is pressed
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.setMaze(new Maze(15, "prim"));
				panelDeck.show(panelContainer, "gameScreen");
			}
		});

		// when Set Difficulty button is pressed
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.setMaze(new Maze(20, "depth"));
				panelDeck.show(panelContainer, "gameScreen");
			}
		});

		// add the buttons to the panel
		difficultyScreen.add(easy);
		difficultyScreen.add(medium);
		difficultyScreen.add(hard);
		easy.setAlignmentY(Component.CENTER_ALIGNMENT);
		medium.setAlignmentY(Component.CENTER_ALIGNMENT);
		hard.setAlignmentY(Component.CENTER_ALIGNMENT);
	}

	private void initGameScreen() {
		BoxLayout boxLayout = new BoxLayout(gameScreen, BoxLayout.Y_AXIS);
		JButton back = new JButton("Back to Menu");

		// when gamePanel's back button is pressed, return to the menu
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDeck.show(panelContainer, "menuScreen");
			}
		});

		gameScreen.setLayout(boxLayout);
		back.setAlignmentX(CENTER_ALIGNMENT);
		gameScreen.add(back);
		gameScreen.add(gamePanel);
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

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			gamePanel.getMaze().getPlayer().moveWest();
		} else if (key == KeyEvent.VK_RIGHT) {
			gamePanel.getMaze().getPlayer().moveEast();
		} else if (key == KeyEvent.VK_UP) {
			gamePanel.getMaze().getPlayer().moveNorth();
		} else if (key == KeyEvent.VK_DOWN) {
			gamePanel.getMaze().getPlayer().moveSouth();
		}
		gamePanel.repaint();
		if (gamePanel.getMaze().isGameOver()) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(gamePanel, "Well that was underwhelming. At least you got a 'ding'?");
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
}

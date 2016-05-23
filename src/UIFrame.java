import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIFrame extends JFrame {
	private JPanel panelContainer;
	private MenuPanel menuScreen;
	private DifficultyPanel difficultyScreen;
	private GamePanel gameScreen; 
	private CardLayout panelDeck;

	public UIFrame() {
		this.panelContainer = new JPanel();
		this.menuScreen = new MenuPanel();
		this.difficultyScreen = new DifficultyPanel();
		this.gameScreen = new GamePanel();
		this.panelDeck = new CardLayout();
		initUIFrame();
		initMenuScreen();
		initDifficultyScreen();
		initGameScreen();
	}
	
	private void initUIFrame() {
		this.setTitle("I believe I can fly");
		this.setSize(850, 900);
		this.setLayout(new BorderLayout());
		this.add(panelContainer);
		BoxLayout boxLayout = new BoxLayout(menuScreen, BoxLayout.Y_AXIS);
		menuScreen.setLayout(boxLayout);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		panelContainer.setLayout(panelDeck);
		panelContainer.add(menuScreen, "menuScreen");
		panelContainer.add(difficultyScreen, "difficultyScreen");
		panelContainer.add(gameScreen, "gameScreen");
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		panelDeck.show(panelContainer, "menuScreen");
	}
	
	private void initMenuScreen() {
		menuScreen.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDeck.show(panelContainer, "difficultyScreen");
			}
		});

		menuScreen.getQuitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void initDifficultyScreen() {
		BoxLayout boxLayout = new BoxLayout(difficultyScreen, BoxLayout.Y_AXIS);
		difficultyScreen.setLayout(boxLayout);
		
		difficultyScreen.getEasyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.setGameState("easy");
				gameScreen.setRunning(true);
			    gameScreen.requestFocusInWindow();
				panelDeck.show(panelContainer, "gameScreen");
			}
		});

		difficultyScreen.getMediumButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.setGameState("medium");
				panelDeck.show(panelContainer, "gameScreen");
			}
		});

		difficultyScreen.getHardButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.setGameState("hard");
				panelDeck.show(panelContainer, "gameScreen");
			}
		});
	}

	private void initGameScreen() {
		gameScreen.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDeck.show(panelContainer, "menuScreen");
			}
		});
	}
	
	public GamePanel getGamePanel() {
		return this.gameScreen;
	}
}

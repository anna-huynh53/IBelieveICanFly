import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class UIFrame extends JFrame {
	MenuPanel menuScreen;
	DifficultyPanel difficultyScreen;
	GamePanel gameScreen;
	
	public UIFrame() {
		this.menuScreen = new MenuPanel();
		this.difficultyScreen = new DifficultyPanel();
		initFrame();
		runMenuScreen();
	}

	public void initFrame() {
		this.setTitle("I believe I can fly");
		this.setSize(420, 420);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void runMenuScreen() {
		this.setContentPane(menuScreen);
		this.pack();
		menuScreen.setVisible(true);	
		
		menuScreen.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runDifficultyScreen();
			}
		});

		menuScreen.getQuitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void runDifficultyScreen() {
		this.setContentPane(difficultyScreen);
		this.pack();
		difficultyScreen.setFocusable(true);
		difficultyScreen.setVisible(true);

		difficultyScreen.getEasyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen = new GamePanel("easy");
				runGameScreen();
			}
		});

		difficultyScreen.getMediumButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen = new GamePanel("medium");
				runGameScreen();
			}
		});

		difficultyScreen.getHardButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen = new GamePanel("hard");
				runGameScreen();
			}
		});
	}
	
	private void runGameScreen() {
		this.setContentPane(gameScreen);
		this.pack();
		gameScreen.setFocusable(true);
		gameScreen.requestFocusInWindow();
		gameScreen.setVisible(true);
		
		//gameScreen.getThread().addActionListener(new ActionListener()) {
		gameScreen.getRestartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String level = gameScreen.getGameState().getMaze().getLevel();
				gameScreen.endGame();
				gameScreen = new GamePanel(level);
				runGameScreen();
			}
		});
		
		gameScreen.getExitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.endGame();
				runMenuScreen();
			}
		});
	}
}
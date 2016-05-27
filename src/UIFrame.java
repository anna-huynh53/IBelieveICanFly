import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class UIFrame extends JFrame {
	MenuPanel menuScreen;
	HelpPanel helpScreen;
	DifficultyPanel difficultyScreen;
	GamePanel gameScreen;
	
	public UIFrame() {
		this.menuScreen = new MenuPanel();
		this.helpScreen = new HelpPanel();
		this.difficultyScreen = new DifficultyPanel();
		initFrame();
		runMenuScreen();
	}

	public void initFrame() {
		this.setTitle("I Believe I Can Fly");
		this.setSize(540, 540);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void runMenuScreen() {
		this.setContentPane(menuScreen);
		this.pack();
		this.setLocationRelativeTo(null);
		helpScreen.setFocusable(true);
		menuScreen.setVisible(true);	
		
		menuScreen.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runDifficultyScreen();
			}
		});
		
		menuScreen.getHelpButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runHelpScreen();
			}
		});

		menuScreen.getQuitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void runHelpScreen() {
		this.setContentPane(helpScreen);
		this.pack();
		this.setLocationRelativeTo(null);
		helpScreen.setFocusable(true);
		helpScreen.setVisible(true);
		
		helpScreen.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runMenuScreen();
			}
		});	
	}	
	
	private void runDifficultyScreen() {
		this.setContentPane(difficultyScreen);
		this.pack();
		this.setLocationRelativeTo(null);
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
		
		difficultyScreen.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runMenuScreen();
			}
		});
	}
	
	private void runGameScreen() {
		this.setContentPane(gameScreen);
		this.pack();
		this.setLocationRelativeTo(null);		
		gameScreen.setFocusable(true);
		gameScreen.requestFocusInWindow();
		gameScreen.setVisible(true);
		
		gameScreen.getRestartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String level = gameScreen.getGameState().getMaze().getLevel();
				gameScreen.setRunning(false);
				gameScreen = new GamePanel(level);
				runGameScreen();
			}
		});
		
		gameScreen.getExitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen.setRunning(false);
				runMenuScreen();
			}
		});		
	}
}
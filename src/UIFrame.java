import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIFrame extends JFrame {
	MenuPanel menuScreen;
	HelpPanel helpScreen;
	DifficultyPanel difficultyScreen;
	GamePanel gameScreen;
	
	public UIFrame() {
		this.menuScreen = new MenuPanel();
		this.difficultyScreen = new DifficultyPanel();
		this.helpScreen = new HelpPanel();
		initFrame();
		runMenuScreen();
	}
	
	public void initFrame() {
		this.setTitle("I believe I can fly");
		//this.setSize(850, 900);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(menuScreen);
		this.pack();
		this.setVisible(true);	
	}
	
	private void runMenuScreen() {
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
		helpScreen.setFocusable(true);
		helpScreen.setVisible(true);
		
		helpScreen.getPlayButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runDifficultyScreen();
			}
		});
		
		helpScreen.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initFrame();
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
				gameScreen.setPreferredSize(new Dimension(650, 420));
				runGameScreen();
			}
		});

		difficultyScreen.getMediumButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen = new GamePanel("medium");
				gameScreen.setPreferredSize(new Dimension(860, 621));
				runGameScreen();
			}
		});

		difficultyScreen.getHardButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen = new GamePanel("hard");
				gameScreen.setPreferredSize(new Dimension(1100, 820));
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
		
		gameScreen.getHelp().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// insert help screen
			}
		});
		
		gameScreen.getBackMain().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// there may be some issues with closing the thread when running this
				gameScreen.thread.stop();
				initFrame();
				runMenuScreen();
			}
		});
	}
}
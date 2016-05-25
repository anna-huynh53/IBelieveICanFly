import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class UIFrame extends JFrame{
	private Menu menu;
	private GamePanel game;
	private Difficulty diffMenu;
	
	public UIFrame() {
		this.menu = new Menu();
		this.diffMenu = new Difficulty();
		initFrame();
		menu.setFocusable(true);
		runMenuScreen();
	}
	
	private void initFrame() {
		this.setTitle("I believe I can fly");
		this.setSize(850, 900);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setPreferredSize(new Dimension(420, 420));
		this.setContentPane(menu);
		// this.add(new JLabel(new ImageIcon("/home/siriprayook/YEAR 2 UNI/2911Project/res/background.png")));
		this.pack();
		BoxLayout boxLayout = new BoxLayout(menu, BoxLayout.Y_AXIS);
		menu.setLayout(boxLayout);
		this.setVisible(true);
	}
	
	private void runMenuScreen() {
		menu.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runDifficultyScreen();
				setDifficultyContent();
				menu.setFocusable(false);
			}
		});

		menu.getQuitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void runDifficultyScreen() {
		// init diff screen
		diffMenu.setPreferredSize(new Dimension(420, 420));
		BoxLayout boxLayout = new BoxLayout(diffMenu, BoxLayout.Y_AXIS);
		diffMenu.setLayout(boxLayout);
		
		// diff buttons
		diffMenu.getEasyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGameContent();
				diffMenu.setFocusable(false);
			}
		});

		diffMenu.getMediumButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for medium board
			}
		});

		diffMenu.getHardButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for hard board
			}
		});
	}
	
	private void initGameScreen() {
		game.getOptions().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//game.showOptions();
			}
		});
	}
	
	private void setDifficultyContent() {
		diffMenu.setFocusable(true);
		this.setContentPane(diffMenu);
		this.pack();
		this.setVisible(true);
	}
	
	private void setGameContent() {
		this.game = new GamePanel("Easy");
		BoxLayout gameLayout = new BoxLayout(game, BoxLayout.Y_AXIS);
		game.setLayout(gameLayout);
		this.setContentPane(game);
		this.pack();
		this.setVisible(true);
	}
	
	
}

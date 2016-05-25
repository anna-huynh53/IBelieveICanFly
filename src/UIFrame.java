import javax.swing.JFrame;
import java.awt.Dimension;

public class UIFrame {
	GamePanel gameScreen;
	
	public UIFrame() {
		JFrame window = new JFrame("Why");
		initMenuScreen();
		initDifficultyScreen();
		initGameScreen();
		window.setContentPane(gameScreen);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);	
	}
	
	public void initMenuScreen() {
		
	}
	
	public void initDifficultyScreen() {
	
	}
	
	public void initGameScreen() {
		gameScreen = new GamePanel("easy"); // must set difficulty of game panel's game state
		gameScreen.setPreferredSize(new Dimension(420, 420));
		gameScreen.setFocusable(true);
		gameScreen.requestFocus();
	}
}
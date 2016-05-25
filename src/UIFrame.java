import javax.swing.JFrame;
import java.awt.Dimension;

public class UIFrame {
	GamePanel gameScreen;
	
	public UIFrame() {
		JFrame window = new JFrame("Why");
		initGameScreen();
		window.setContentPane(gameScreen);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);	
	}
	
	public void initGameScreen() {
		gameScreen = new GamePanel(); // must set difficulty of game panel's game state
		gameScreen.setPreferredSize(new Dimension(420, 420));
		gameScreen.setFocusable(true);
		gameScreen.requestFocus();
	}
}
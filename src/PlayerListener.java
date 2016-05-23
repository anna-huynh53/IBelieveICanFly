import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerListener implements ActionListener {
	private GamePanel gameScreen;
	
	public PlayerListener(GamePanel gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (gameScreen.getMaze().getPlayer().moveSouth()) {
			gameScreen.getMaze().getPlayer().moveSouth();
		}
	}
}

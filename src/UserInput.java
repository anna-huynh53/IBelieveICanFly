import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserInput extends KeyAdapter {
	private UIFrame UI;
	
	public UserInput(UIFrame ui) {
		this.UI = ui;
	}
	
	public void keyPressed(KeyEvent e) {
		UI.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		UI.keyReleased(e);
	}
}

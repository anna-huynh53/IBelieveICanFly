import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private JButton start = new JButton("Start Game");
	private JButton help = new JButton("Help");
	
	public MenuPanel() {
	    start.setAlignmentY(Component.CENTER_ALIGNMENT);
	    help.setAlignmentY(Component.CENTER_ALIGNMENT);
	    
	    //add the buttons to the panel
	    add(start);
	    add(help);   
	}
	
	public JButton getStartButton() {
		return this.start;
	}

	public JButton getHelpButton() {
		return this.help;
	}
}

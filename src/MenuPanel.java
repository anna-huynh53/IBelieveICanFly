import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

	private JLabel header = new JLabel("I believe I can fly");
	private JButton start = new JButton("Start Game");
	private JButton help = new JButton("Help");
	private JButton quit = new JButton("Quit");
	
	public MenuPanel() {
		this.setPreferredSize(new Dimension(650, 420));
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		
	    header.setAlignmentX(Component.CENTER_ALIGNMENT);
	    start.setAlignmentX(Component.CENTER_ALIGNMENT);
	    help.setAlignmentX(Component.CENTER_ALIGNMENT);
	    quit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // sets fonts
	    header.setFont(new Font("Impact",1,40));
	    start.setFont(new Font("Impact",1,18));
	    help.setFont(new Font("Impact",1,18));
	    quit.setFont(new Font("Impact",1,18));
	    
	    // sets button size
	    Dimension buttonSize = new Dimension(180,40);
	    start.setMaximumSize(buttonSize);
	    help.setMaximumSize(buttonSize);
	    quit.setMaximumSize(buttonSize);
	    
	    // add the buttons to the panel with input blank area as spacing
	    add(Box.createRigidArea(new Dimension(0,20)));
	    add(header);
	    add(Box.createRigidArea(new Dimension(0,50)));
	    add(start);
	    add(Box.createRigidArea(new Dimension(0,25)));
	    add(help);   
	    add(Box.createRigidArea(new Dimension(0,25)));
	    add(quit);
	}
	
	public JButton getStartButton() {
		return this.start;
	}

	public JButton getHelpButton() {
		return this.help;
	}
	
	public JButton getQuitButton() {
		return this.quit;
	}
}
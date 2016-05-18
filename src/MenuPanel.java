import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private JLabel header = new JLabel("Welcome to the Maze Game!!");
	private JButton start = new JButton("Start Game");
	private JButton help = new JButton("Help");
	private JButton quit = new JButton("Quit");
	
	public MenuPanel() {
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
	    start.setAlignmentX(Component.CENTER_ALIGNMENT);
	    help.setAlignmentX(Component.CENTER_ALIGNMENT);
	    quit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    //currently sets font size (type of font doesn't seem to work)
	    header.setFont(new java.awt.Font("Impact",1,40));
	    start.setFont(new java.awt.Font("Impact",1,18));
	    help.setFont(new java.awt.Font("Impact",1,18));
	    quit.setFont(new java.awt.Font("Impact",1,18));
	    
	    //set button size
	    Dimension buttonSize = new Dimension(180,40);
	    start.setMaximumSize(buttonSize);
	    help.setMaximumSize(buttonSize);
	    quit.setMaximumSize(buttonSize);
	    
	    //add the buttons to the panel with input blank area as spacing
	    add(Box.createRigidArea(new Dimension(0,200)));
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

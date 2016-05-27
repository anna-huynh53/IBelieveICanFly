import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DifficultyPanel extends JPanel {
	private JLabel header ;
	private JButton easy;
	private JButton medium;
	private JButton hard;
	
	public DifficultyPanel() {
		this.setPreferredSize(new Dimension(420, 420));
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		
		this.header = new JLabel("Difficulty");
		this.easy = new JButton("Easy");
	    this.medium = new JButton("Medium");
	    this.hard = new JButton("Hard");
	    
	    // set allignment
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
	    easy.setAlignmentX(Component.CENTER_ALIGNMENT);
	    medium.setAlignmentX(Component.CENTER_ALIGNMENT);
	    hard.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // set fonts
	    header.setFont(new Font("Impact",1,40));
	    easy.setFont(new Font("Impact",1,18));
	    medium.setFont(new Font("Impact",1,18));
	    hard.setFont(new Font("Impact",1,18));
	    
	    // set button size
	    Dimension buttonSize = new Dimension(180,40);
	    easy.setMaximumSize(buttonSize);
	    medium.setMaximumSize(buttonSize);
	    hard.setMaximumSize(buttonSize);
	    
	    // add the buttons to the panel with input blank area as spacing
	    add(Box.createRigidArea(new Dimension(0,20)));
	    add(header);
	    add(Box.createRigidArea(new Dimension(0,50)));
	    add(easy);
	    add(Box.createRigidArea(new Dimension(0,25)));
	    add(medium);   
	    add(Box.createRigidArea(new Dimension(0,25)));
	    add(hard);
	}
	
	public JButton getEasyButton() {
		return this.easy;
	}

	public JButton getMediumButton() {
		return this.medium;
	}
	
	public JButton getHardButton() {
		return this.hard;
	}
}
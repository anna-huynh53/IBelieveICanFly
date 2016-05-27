import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DifficultyPanel extends JPanel {

	private JLabel header = new JLabel("Difficulty");
	private JButton easy = new JButton(new ImageIcon("res/gui/easyButtonDefault.png"));
	private JButton medium = new JButton("Medium");
	private JButton hard = new JButton("Hard");
	
	public DifficultyPanel() {
		this.setPreferredSize(new Dimension(420, 420));
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		this.setBackground(new Color(27, 192, 247));

		header.setAlignmentX(Component.CENTER_ALIGNMENT);
	    easy.setAlignmentX(Component.CENTER_ALIGNMENT);
	    medium.setAlignmentX(Component.CENTER_ALIGNMENT);
	    hard.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // sets fonts
	    header.setFont(new Font("Impact",1,40));
	    easy.setFont(new Font("Impact",1,18));
	    medium.setFont(new Font("Impact",1,18));
	    hard.setFont(new Font("Impact",1,18));
	    
	    // sets button size
	    Dimension buttonSize = new Dimension(160,40);
	    easy.setMaximumSize(buttonSize);
	    medium.setMaximumSize(buttonSize);
	    hard.setMaximumSize(buttonSize);
	    
	    //animates buttons according to mouse events
	    easy.setBorderPainted(false);
	    easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/easyButtonHover.png");
				easy.setIcon(i);
			}
		});
		
		easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/easyButtonDefault.png");
				easy.setIcon(i);
			}
		});
		
		easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/easyButtonPressed.png");
				easy.setIcon(i);
			}
		});
		
		easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/easyButtonHover.png");
				easy.setIcon(i);
			}
		});
	    
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
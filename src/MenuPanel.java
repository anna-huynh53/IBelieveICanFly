import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

	private JButton start;
	private JButton help; 
	private JButton quit;
	
	public MenuPanel() {
		this.setPreferredSize(new Dimension(420, 420));
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		Color UIcolor = new Color(181, 229, 251);
		this.setBackground(UIcolor);
		ImageIcon i;
		
		this.start = new JButton();
		this.help = new JButton();
		this.quit = new JButton();
		
		// set allignment
	    start.setAlignmentX(Component.CENTER_ALIGNMENT);
	    help.setAlignmentX(Component.CENTER_ALIGNMENT);
	    quit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	 // set appearance
	    start.setBackground(UIcolor);
	    i = new ImageIcon("res/gui/playButtonDefault.png");
	    start.setIcon(i);
	    start.setBorderPainted(false);
	    help.setBackground(UIcolor);
	    i = new ImageIcon("res/gui/helpButtonDefault.png");
	    help.setIcon(i);
	    help.setBorderPainted(false);
	    quit.setBackground(UIcolor);
	    i = new ImageIcon("res/gui/quitButtonDefault.png");
	    quit.setIcon(i);
	    quit.setBorderPainted(false);
	    
	    // animate buttons
	    start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/playButtonHover.png");
			    start.setIcon(i);
			}
		});
	    
	    
	    help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/helpButtonHover.png");
			    help.setIcon(i);
			}
		});
	    
	    quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/quitButtonHover.png");
			    quit.setIcon(i);
			}
		});
	    
	    start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/playButtonDefault.png");
			    start.setIcon(i);
			}
		});
	    
	    help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/helpButtonDefault.png");
			    help.setIcon(i);
			}
		});
	    
	    quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/quitButtonDefault.png");
			    quit.setIcon(i);
			}
		});
	    

	    // set button size
	    Dimension buttonSize = new Dimension(160,40);
	    start.setMaximumSize(buttonSize);
	    help.setMaximumSize(buttonSize);
	    quit.setMaximumSize(buttonSize);
	    
	    // add the buttons to the panel with input blank area as spacing
	    add(Box.createRigidArea(new Dimension(0,100)));
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
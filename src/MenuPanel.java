import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	private Image background;
	private int width;
	private int height;
	
	private JButton start;
	private JButton help; 
	private JButton quit;
	
	public MenuPanel() {
		this.width = 540;
		this.height = 540;
		this.background = Toolkit.getDefaultToolkit().getImage("res/gui/menuScreen.png");
		this.setPreferredSize(new Dimension(width, height));
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		Color UIcolor = new Color(181, 229, 251);
		this.setBackground(UIcolor);
		
		this.start = new JButton(new ImageIcon("res/gui/playButtonDefault.png"));
		this.help = new JButton(new ImageIcon("res/gui/helpButtonDefault.png"));
		this.quit = new JButton(new ImageIcon("res/gui/quitButtonDefault.png"));
		
		// set allignment
	    start.setAlignmentX(Component.CENTER_ALIGNMENT);
	    help.setAlignmentX(Component.CENTER_ALIGNMENT);
	    quit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // set appearance
	    start.setBackground(UIcolor);
	    start.setBorderPainted(false);
	    help.setBackground(UIcolor);
	    help.setBorderPainted(false);
	    quit.setBackground(new Color(150, 203, 99));
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
	    add(Box.createRigidArea(new Dimension(0,215)));
	    add(start);
	    add(Box.createRigidArea(new Dimension(0,25)));
	    add(help);   
	    add(Box.createRigidArea(new Dimension(0,85)));
	    add(quit);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
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
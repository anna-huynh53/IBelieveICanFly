import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class DifficultyPanel extends JPanel {
	private int width;
	private int height;
	private Image background;
	
	private JButton easy;
	private JButton medium;
	private JButton hard;
	private JButton back;
	
	public DifficultyPanel() {
		this.width = 540;
		this.height = 540;
		this.setPreferredSize(new Dimension(width, height));
		this.background = Toolkit.getDefaultToolkit().getImage("res/gui/difficultyScreen.png");
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		Color UIcolor = new Color(181, 229, 251);
		this.setBackground(UIcolor);
		
		this.easy = new JButton(new ImageIcon("res/gui/easyButtonDefault.png"));
	    this.medium = new JButton(new ImageIcon("res/gui/mediumButtonDefault.png"));
	    this.hard = new JButton(new ImageIcon("res/gui/hardButtonDefault.png"));
	    this.back = new JButton(new ImageIcon("res/gui/backButtonDefault.png"));
	    
	    // set allignment
	    easy.setAlignmentX(Component.CENTER_ALIGNMENT);
	    medium.setAlignmentX(Component.CENTER_ALIGNMENT);
	    hard.setAlignmentX(Component.CENTER_ALIGNMENT);
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // set appearance
	    easy.setBackground(UIcolor);
	    easy.setBorderPainted(false);
	    medium.setBackground(UIcolor);
	    medium.setBorderPainted(false);
	    hard.setBackground(UIcolor);
	    hard.setBorderPainted(false);
	    back.setBackground(new Color(150, 203, 99));
	    back.setBorderPainted(false);
	    
	    // animate buttons
	    easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/easyButtonHover.png");
			    easy.setIcon(i);
			}
		});
	    
	    medium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/mediumButtonHover.png");
			    medium.setIcon(i);
			}
		});
	    
	    hard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/hardButtonHover.png");
			    hard.setIcon(i);
			}
		});
	    
	    back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/backButtonHover.png");
			    back.setIcon(i);
			}
		});
	    
	    easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/easyButtonDefault.png");
			    easy.setIcon(i);
			}
		});
	    
	    medium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/mediumButtonDefault.png");
			    medium.setIcon(i);
			}
		});
	    
	    hard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/hardButtonDefault.png");
			    hard.setIcon(i);
			}
		});
	    
	    back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/backButtonDefault.png");
			    back.setIcon(i);
			}
		});
	    
	    // set button size
	    Dimension buttonSize = new Dimension(160,40);
	    easy.setMaximumSize(buttonSize);
	    medium.setMaximumSize(buttonSize);
	    hard.setMaximumSize(buttonSize);
	    back.setMaximumSize(buttonSize);
	    
	    // add the buttons to the panel with input blank area as spacing
	    add(Box.createRigidArea(new Dimension(0,165)));
	    add(easy);
	    add(Box.createRigidArea(new Dimension(0,25)));
	    add(medium);   
	    add(Box.createRigidArea(new Dimension(0,25)));
	    add(hard);
	    add(Box.createRigidArea(new Dimension(0, 70)));
	    add(back);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
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
	
	public JButton getBackButton() {
		return this.back;
	}
}
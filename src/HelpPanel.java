import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HelpPanel extends JPanel{
	private JButton back;
	Image background;
	
	public HelpPanel() {
		this.background = Toolkit.getDefaultToolkit().getImage("res/gui/helpScreen.png");
		this.setPreferredSize(new Dimension(1280, 800));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.back = new JButton(new ImageIcon("res/gui/backButtonDefault.png"));
		Color UIcolor = new Color(181, 229, 251);
		this.setBackground(UIcolor);
	    
	    // sets button size and adds it in the bottom right corner
	    Dimension buttonSize = new Dimension(160,40);
	    back.setMaximumSize(buttonSize);
	    
	    add(back);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.background, 0, 0, this);
	}

	public JButton getBackButton() {
		return this.back;
	}
}
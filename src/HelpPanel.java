import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class HelpPanel extends JPanel{
	private JButton back;
	private Image background;
	private int width;
	private int height;
	
	public HelpPanel() {
		this.background = Toolkit.getDefaultToolkit().getImage("res/gui/helpScreen.png");
		this.width = 1024;
		this.height = 640;
		this.setSize(new Dimension(width, height));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.back = new JButton(new ImageIcon("res/gui/backButtonDefault.png"));
		
		//set back button's appearance
		back.setBorderPainted(false);
		back.setBackground(new Color(181, 227, 251));
		
	    back.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/backButtonHover.png");
			    back.setIcon(i);
			}
		});
	    
	    back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon i = new ImageIcon("res/gui/backButtonDefault.png");
			    back.setIcon(i);
			}
		});
		
	    // sets button size and adds it in the bottom right corner
	    Dimension buttonSize = new Dimension(160,40);
	    back.setMaximumSize(buttonSize);
	    back.setAlignmentX(BOTTOM_ALIGNMENT);
	    back.setAlignmentY(RIGHT_ALIGNMENT);
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
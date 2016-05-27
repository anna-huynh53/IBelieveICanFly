import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class HelpPanel extends JPanel{
	private JLabel title;
	private JTextArea helpText;
	private JButton play;
	private JButton back;
		
	@SuppressWarnings("resource")
	public HelpPanel() throws IOException {
		this.setPreferredSize(new Dimension(850, 950));
		this.setLayout(new FlowLayout());
		this.title = new JLabel("How to play");
		this.helpText = new JTextArea();
		this.play = new JButton("Play");
		this.back = new JButton("Back");
		this.setBackground(new Color(27, 192, 247));

		//font
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, 
			new FileInputStream(new File("res/sunshineFont2.ttf"))).deriveFont(Font.PLAIN, 22);
		    	helpText.setFont(customFont);
		    	Font customTitle = Font.createFont(Font.TRUETYPE_FONT, 
		    	new FileInputStream(new File("res/sunshineFont.ttf"))).deriveFont(Font.PLAIN, 32	);
		    	title.setFont(customTitle);
		} catch (FontFormatException e) {
			e.printStackTrace();
		}	
		
		// textArea options
	    helpText.setLineWrap(true);
	    helpText.setWrapStyleWord(true);
	    helpText.setOpaque(false);
	    helpText.setEditable(false);
	    helpText.setSize(800, 780);
	    
	    // scan text file for rules
	    Scanner fileScanner = new Scanner(new File("res/helpScreen.txt"),"UTF-8");
	    
	    String text = fileScanner.next();
	    while(fileScanner.hasNextLine()) {
	    	text += fileScanner.nextLine();
	    	text += System.lineSeparator();
	    }
	    helpText.setText(text);
		
	    // sets fonts
	    play.setFont(new Font("Impact",1,18));
	    back.setFont(new Font("Impact",1,18));
	    
	    // sets button size
	    Dimension buttonSize = new Dimension(180,40);
	    play.setMaximumSize(buttonSize);
	    back.setMaximumSize(buttonSize);
	    
	    add(title);
	    add(helpText);
	    add(play);
	    add(back);
	}

	public JButton getPlayButton() {
		return this.play;
	}

	public JButton getBackButton() {
		return this.back;
	}
}
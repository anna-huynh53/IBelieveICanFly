
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HelpPanel extends JPanel{
	JLabel title = new JLabel("How to play the game");
	JTextArea helpText = new JTextArea();
	JButton play = new JButton("Play");
	JButton back = new JButton("Back");
		
	public HelpPanel() throws IOException{
		this.setPreferredSize(new Dimension(850, 950));
		this.setLayout(new FlowLayout());
		
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
	    title.setFont(new Font("Impact",1,40));
	    helpText.setFont(new Font("Calibri",1,18));
	    play.setFont(new Font("Impact",1,18));
	    back.setFont(new Font("Impact",1,18));
	    
	    // sets button size
	    Dimension buttonSize = new Dimension(180,40);
	    play.setMaximumSize(buttonSize);
	    back.setMaximumSize(buttonSize);
	    
	    // add to panel
	    add(title);
	    add(helpText);
	    add(play);
	    add(back);

	}
	
	// get functions
	public JButton getPlayButton() {
		return this.play;
	}

	public JButton getBackButton() {
		return this.back;
	}
}

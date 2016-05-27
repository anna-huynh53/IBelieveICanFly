
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HelpPanel extends JPanel{
	JLabel title = new JLabel("How to play the game");
	JTextArea helpText = new JTextArea("Your goal is to navigate your player to the end of the maze in the shortest time possible, and with the highest score, without dying. You will have to fly, dodge enemies, collect items and fight for survival in this AMazingGame!");
	JButton play = new JButton("Play");
	JButton back = new JButton("Back");
		
	public HelpPanel(){
		this.setPreferredSize(new Dimension(650, 800));
		this.setLayout(new FlowLayout());
		
	    helpText.setLineWrap(true);
	    helpText.setWrapStyleWord(true);
	    helpText.setOpaque(false);
	    helpText.setEditable(false);
	    helpText.setSize(600, 700);
		
		/*
		FileReader file = new FileReader("C:/Users/ben/git/2911Project/res/helpScreen.txt");
		BufferedReader reader = new BufferedReader(file);
		
		String text = "";
		String line = reader.readLine();
		while(line != null) {
			text += line;
			line = reader.readLine();
		}
				
		helpText.setText(text);
		*/
		
		/*
	    title.setAlignmentX(Component.CENTER_ALIGNMENT);
	    helpText.setAlignmentX(Component.CENTER_ALIGNMENT);
	    play.setAlignmentX(Component.CENTER_ALIGNMENT);
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    */
		
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

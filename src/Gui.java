import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * displays maze
 * will take in a size and output a maze
 * ..
 */
public class Gui {	
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel panel;
	
	public Gui() {
		guiMaker();
	}
	
	public static void main (String[] args) {
		Gui mainMenu = new Gui();
		mainMenu.display();
	}
	
	/*
	 * creates the frames, labels and panels to be used in display()
	 */
	
	public void guiMaker() {
		mainFrame = new JFrame("Maze Game!");
		mainFrame.setSize(400,400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		headerLabel = new JLabel("",JLabel.CENTER);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		mainFrame.add(headerLabel);
		mainFrame.add(panel);
		mainFrame.setVisible(true);
	}

	/*
	 * displays GUI
	 * sets the text to be put into labels and buttons
	 * also handles what happens when buttons are pressed
	 * (look here to change what buttons should do)
	 */
	
	public void display() {
		headerLabel.setText("Welcome to the Amazing Maze Game");
		
		JButton start = new JButton("START");
		JButton instructions = new JButton("Instructions");
		JButton difficulty = new JButton("Set Difficultly");
		
	    start.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		headerLabel.setText("Start Button clicked.");
	        }          
	    });

	    instructions.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	headerLabel.setText("Instructions Button clicked.");
	        }
	    });

	    difficulty.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		headerLabel.setText("Difficulty Button clicked.");
	        }
	    });

	    panel.add(start);
	    panel.add(instructions);
	    panel.add(difficulty);       

	    mainFrame.setVisible(true);  
	}
	
}

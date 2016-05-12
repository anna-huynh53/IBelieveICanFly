import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * displays maze
 * will take in a size and output a maze
 * ..
 */

public class Game {	
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel panel;
	private JPanel gamePanel;
	
	public Game() {
		guiMaker();
	}
	
	public static void main (String[] args) {
		Game mainMenu = new Game();
		mainMenu.display();
	}
	
	/*
	 * sets up the frame
	 * creates the frames, labels and panels to be used in display()
	 */
	
	public void guiMaker() {
		mainFrame = new JFrame("Maze Game!");
		mainFrame.setSize(500,500);
		mainFrame.setLayout(new BorderLayout());
		
		// background image, change source of image here
		mainFrame.setContentPane(new JLabel(new ImageIcon("background.jpg")));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		mainFrame.setLayout(new FlowLayout());
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
		
		// when start button is pressed
	    start.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//headerLabel.setText("Start Button clicked.");
	    		playGame();
	        }          
	    });

	    // when instruction button is pressed
	    instructions.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	headerLabel.setText("Instructions Button clicked.");
	        }
	    });
	    
	    // when Set Difficulty button is pressed
	    difficulty.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		headerLabel.setText("Difficulty Button clicked.");
	        }
	    });
	    
	    start.setAlignmentY(Component.CENTER_ALIGNMENT);
	    instructions.setAlignmentY(Component.CENTER_ALIGNMENT);
	    difficulty.setAlignmentY(Component.CENTER_ALIGNMENT);
	    panel.add(start);
	    panel.add(instructions);
	    panel.add(difficulty);       

	    mainFrame.setVisible(true);  
	}
	
	/*
	 * when start button is pressed, it passes playGame()
	 * currently wipes/refreshes frame and has a 'back' button that restarts the program
	 * this should be where the maze is generated and printed
	 */
	public void playGame() {
		mainFrame.remove(panel);
		gamePanel = new JPanel();
		gamePanel.setLayout(new FlowLayout());
		headerLabel.setText("Play Game screen");
		JButton back = new JButton("Back to Menu");
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				Game newGame = new Game();
				newGame.display();
			}
		});
		gamePanel.add(back);
		mainFrame.setContentPane(gamePanel);
		mainFrame.setVisible(true);
	}
	
}

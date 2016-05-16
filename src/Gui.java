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
	private JPanel gamePanel;
	
	public Gui() {
		guiMaker();
	}
	
	/* 
	public static void main (String[] args) {
		Gui mainMenu = new Gui();
		mainMenu.display();
	}
	*/
	
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
		panel.setBorder(BorderFactory.createTitledBorder("Menu"));
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		// panel.setLayout(new FlowLayout());
		panel.setLayout(boxLayout);
		
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);
		mainFrame.add(headerLabel);
		mainFrame.add(panel);
		// mainFrame.pack();
		mainFrame.setVisible(true);
	}

	/*
	 * displays GUI
	 * sets the text to be put into labels and buttons
	 * also handles what happens when buttons are pressed
	 * (look here to change what buttons should do)
	 */
	
	public void display() {
		headerLabel.setFont(new java.awt.Font("Verdana",1,18));
		headerLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		headerLabel.setText("Welcome to the Amazing Maze Game!");
		//mainFrame.setContentPane(new JLabel(new ImageIcon("/home/siriprayook/YEAR 2 UNI/project/background.jpg")));
		
		// initialize buttons and set alignment //
		
		JButton start = new JButton("START");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		//start.setFont(new java.awt.Font("Verdana", 1, 24));

		JButton instructions = new JButton("Instructions");
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton difficulty = new JButton("Set Difficultly");
		difficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton exit = new JButton("Quit");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
	        	// headerLabel.setText("Instructions Button clicked.");
	        	rules();
	        }
	    });
	    
	    // when Set Difficulty button is pressed
	    difficulty.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		headerLabel.setText("Difficulty Button clicked.");
	        }
	    });
	    
	    // when Quit button is pressed
	    exit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    });
	    
	    start.setAlignmentY(Component.CENTER_ALIGNMENT);
	    instructions.setAlignmentY(Component.CENTER_ALIGNMENT);
	    difficulty.setAlignmentY(Component.CENTER_ALIGNMENT);
	
	    Dimension buttonSize = new Dimension(180,30);
	    
	    panel.add(start);
	    panel.add(instructions);
	    panel.add(difficulty);
	    panel.add(exit);
	    start.setMaximumSize(buttonSize);
	    instructions.setMaximumSize(buttonSize);
	    difficulty.setMaximumSize(buttonSize);
	    exit.setMaximumSize(buttonSize);
	    
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
	
	/*
	 * when instruction button is pressed, it passes to rules()
	 * opens new window with helpful text to explain how to play the game
	 * includes back button (maybe a play button)
	 */
	
	public void rules() {
		JFrame gameRules = new JFrame("Instructions");
		gameRules.setSize(700,500);
		gameRules.setLayout(new FlowLayout());
		// gameRules.setContentPane(new JLabel(new ImageIcon("/home/siriprayook/YEAR 2 UNI/project/background.jpg")));
		gameRules.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		gameRules.setLayout(new FlowLayout());
		JLabel header = new JLabel("- How to Play -",JLabel.CENTER);
		JLabel body = new JLabel("insert instructions here... blah blah blah blah blah blah",JLabel.LEFT);
		
		// header.setFont(new java.awt.Font("",1,15));
		header.setFont(new java.awt.Font("Verdana",1,18));
		
		gameRules.add(header);
		gameRules.add(body);
		gameRules.setVisible(true);
	}	
}

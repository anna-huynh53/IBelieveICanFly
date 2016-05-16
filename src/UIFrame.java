import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Controls the contents and design of the window which displays the program 
 * ..
 */

public class UIFrame extends JFrame {	
	private JPanel panelContainer;
	private MenuPanel menuPanel; // the panel containing the main menu
	private GamePanel gamePanel; // the panel displaying the current maze
	private JPanel difficultyPanel;
	private CardLayout panelDeck;
	
	public UIFrame(Maze m) {
		this.panelContainer = new JPanel();
		this.gamePanel = new GamePanel(m);
		this.menuPanel = new MenuPanel();
		this.difficultyPanel = new JPanel();
		this.panelDeck = new CardLayout();
		guiMaker();
	}
	
	/**
	 * Initialises the frame and all components it contains
	 */
	
	public void guiMaker() {
		
		//setup the frame
		this.setTitle("Maze Game");
		this.setSize(850,900);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(panelContainer);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		//setup the panel container
		panelContainer.setLayout(panelDeck);
		panelContainer.add(menuPanel, "menuScreen");
		panelContainer.add(gamePanel, "gameScreen");
		panelContainer.add(difficultyPanel,  "difficultyScreen");
		panelDeck.show(panelContainer, "menuScreen");
		
		//directs the buttons in the panels to the right places
		// when menuPanel's start button is pressed, show difficulty choices
	    menuPanel.getStartButton().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelDeck.show(panelContainer, "difficultyScreen");
	        }          
	    });

	    //when gamePanel's back button is pressed, return to the menu
	    gamePanel.getBackButton().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelDeck.show(panelContainer, "menuScreen");
	        }          
	    });
	    
	    setDifficulty();
	}
	
	/**
	 * Sets up the difficulty pane
	 */
	private void setDifficulty() {
		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("Hard");
		
		// when start button is pressed
	    easy.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		gamePanel.setMaze(new Maze(10, "prim"));
	    		panelDeck.show(panelContainer, "gameScreen");
	    	}
	    });

	    // when instruction button is pressed
	    medium.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	gamePanel.setMaze(new Maze(15, "prim"));
	        	panelDeck.show(panelContainer, "gameScreen");
	        }
	    });
	    
	    // when Set Difficulty button is pressed
	    hard.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		gamePanel.setMaze(new Maze(20, "depth"));
	    		panelDeck.show(panelContainer, "gameScreen");
	        }
	    });
	    
	    //add the buttons to the panel
	    difficultyPanel.add(easy);
	    difficultyPanel.add(medium);
	    difficultyPanel.add(hard);
	    easy.setAlignmentY(Component.CENTER_ALIGNMENT);
	    medium.setAlignmentY(Component.CENTER_ALIGNMENT);
	    hard.setAlignmentY(Component.CENTER_ALIGNMENT);
	}
	
}

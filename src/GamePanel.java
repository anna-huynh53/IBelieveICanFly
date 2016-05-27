import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private GameState gameState;
	
	public Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g; // all images in the game are drawn using this
	//private BufferedImage mazeImage;
	//private Graphics2D gMaze;
	
	private int WIDTH = 840;
	private int HEIGHT = 840;
	public int SCALE = 20;
	
	private JLabel title = new JLabel("Good Luck!!");
	private JLabel timerText = new JLabel("Start");
	private JLabel playerScore = new JLabel("Score: 0");
	private Timer gameTimer;
	private JButton help = new JButton("Help");
	private JButton back = new JButton("Back to Menu");
	
	public GamePanel(String difficulty) {	
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		//mazeImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//gMaze = (Graphics2D) mazeImage.getGraphics();
		gameState = new GameState(difficulty);
		running = true;
		//gameState.drawMaze(gMaze);
		
		BoxLayout gameLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(gameLayout);
		title.setAlignmentX(Component.RIGHT_ALIGNMENT);
		timerText.setAlignmentX((float) 0.9);
		playerScore.setAlignmentX((float) 0.9);
		help.setAlignmentX(Component.RIGHT_ALIGNMENT);
		back.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		// sets fonts
	    title.setFont(new Font("Impact",1,18));
		timerText.setFont(new Font("Impact",1,18));
		playerScore.setFont(new Font("Calibri",1,18));
		help.setFont(new Font("Gill Sans",1,18));
	    back.setFont(new Font("Calibri",1,18));
		
	    // sets button size
	    Dimension buttonSize = new Dimension(160,40);
	    help.setMaximumSize(buttonSize);
	    back.setMaximumSize(buttonSize);
	    
	    // add the buttons to the panel with input blank area as spacing
	    add(Box.createRigidArea(new Dimension(0,40)));
	    add(title);
	    add(Box.createRigidArea(new Dimension(0,40)));
	    add(timerText);
	    add(Box.createRigidArea(new Dimension(0,20)));
	    add(playerScore);
	    add(Box.createRigidArea(new Dimension(0,40)));
	    add(help);
	    add(Box.createRigidArea(new Dimension(0,40)));
	    add(back);
	    
	    // timer setup 
		TimeClass tc = new TimeClass(0);
		gameTimer = new Timer(1000, tc);
		gameTimer.start();
	}
	
	//
	public void addNotify() {
		super.addNotify();
		thread = new Thread(this);
		addKeyListener(this);
		thread.start();
	}

	public void run() {			
		long start;
		long elapsed;
		long wait;
		int score;
		
		while(running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			
			score = gameState.getPlayer().getScore();
			playerScore.setText("Score: " + score);
			
			elapsed = System.nanoTime() - start;
			// target time in milli/fps
			wait = 2500/30 - elapsed/1000000;
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// for the gameTimer
	public class TimeClass implements ActionListener{
		private int counter;
		
		public TimeClass(int counter) {
			this.counter = counter;
		}
	
		public void actionPerformed(ActionEvent tc) {
			counter++;
			if(counter <= 100) {
				timerText.setText("Timer: "+counter);
			} else {
				gameTimer.stop();
				timerText.setText("Times up!");
			}
		}
	}
	
	/**
	 * Updates the game state
	 */
	private void update() {
		gameState.update();
	}
	
	/**
	 * Draws the images in the game state
	 */
	private void draw() {
		gameState.draw(g);
	}
	
	/**
	 * Draws the final image to the screen
	 */
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		//g2.drawImage(mazeImage, 0, 0, WIDTH, HEIGHT, null);
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}

	
	public void keyPressed(KeyEvent key) {
		gameState.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key) {
		gameState.keyReleased(key.getKeyCode());
	}
	
	public void keyTyped(KeyEvent key) {}
	
	public JButton getHelp(){
		return this.help;
	}
	
	public JButton getBackMain(){
		return this.back;
	}
	
}
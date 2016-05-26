import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private GameState gameState;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image; 
	private Graphics2D g; // all images in the game are drawn using this
	private int WIDTH = 840;
	private int HEIGHT = 840;
	
	public int SCALE = 20;
	

	private JLabel title = new JLabel("Good Luck!!  ");
	private JLabel timerText = new JLabel("Start");
	private Timer gameTimer;
	private JButton options = new JButton("Options");

	public GamePanel(String difficulty) {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gameState = new GameState(difficulty);

		
				setPreferredSize(new Dimension(650, 420));
		setFocusable(true);
		requestFocus();
		title.setAlignmentX(Component.RIGHT_ALIGNMENT);
		timerText.setAlignmentX((float) 0.9);
		options.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		// sets fonts
	    	title.setFont(new Font("Impact",1,18));
		timerText.setFont(new Font("Impact",1,18));
		 options.setFont(new Font("Impact",1,18));
	    
	    	// sets button size
	    	Dimension buttonSize = new Dimension(160,40);
	    	options.setMaximumSize(buttonSize);
	    
	    	// add the buttons to the panel with input blank area as spacing
	    	add(Box.createRigidArea(new Dimension(0,40)));
	    	add(title);
	    	add(Box.createRigidArea(new Dimension(0,40)));
	    	add(timerText);
	    	add(Box.createRigidArea(new Dimension(0,40)));
	    	add(options);
	    
	    	// timer setup 
		TimeClass tc = new TimeClass(0);
		gameTimer = new Timer(1000, tc);
		gameTimer.start();

	}
	
	//
	public void addNotify() {
		super.addNotify();
		//if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		//}
	}

	public void run() {		
		long start;
		long elapsed;
		long wait;
		
		while(running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			// target time in milli/fps
			wait = 2000/60 - elapsed/1000000;
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
}

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private GameState gameState;
	public boolean start; // used to draw the maze only once
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g; // all images except the maze in the game are drawn using this
	private BufferedImage mazeImage;
	private Graphics2D gMaze; 
	
	private int width;
	private int height;
	public int SCALE = 20;
	
	private Image bg;
	
	public GamePanel(String difficulty) {
		gameState = new GameState(difficulty);
		width = gameState.getWidth();
		height = gameState.getHeight();
		this.setPreferredSize(new Dimension(width, height));
		
		bg = Toolkit.getDefaultToolkit().getImage("res/background.png").getScaledInstance(width, height,
		        Image.SCALE_SMOOTH);

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		mazeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		gMaze = (Graphics2D) mazeImage.getGraphics();
		gameState.drawMaze(gMaze);
		
		running = true;
		start = true;
	}
	
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
		
		while (running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			// target time in milli/fps
			wait = 2500/30 - elapsed/1000000;
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
			if (gameState.isGameOver()) {
				running = false;
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
		g.drawImage(bg, 0, 0, width, height, null);
		gameState.draw(g);
	}
	
	/**
	 * Draws the final image to the screen
	 */
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(mazeImage, 0, 0, width, height, null);
		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();
	}

	
	public void keyPressed(KeyEvent key) {
		gameState.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key) {
		gameState.keyReleased(key.getKeyCode());
	}
	
	public void keyTyped(KeyEvent key) {}
	
	public Thread getThread() {
		return this.thread;
	}
}

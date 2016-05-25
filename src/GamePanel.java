import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	private GameState gameState;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image; 
	private Graphics2D g; // all images in the game are drawn using this
	private int WIDTH = 840;
	private int HEIGHT = 840;
	
	public int SCALE = 20;
	
	public GamePanel() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gameState = new GameState();
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	private GameState gameState; // contains the maze
	
	private ImageIcon restartIcon;
	private JButton restart;
	private ImageIcon exitIcon;
	private JButton exit;
	
	private Thread thread;
	private boolean running;
	private boolean waiting;
	private boolean end;
	
	private BufferedImage image;
	private Graphics2D g; // all images are drawn using this
	private Image bg; // background image
	
	private int width; // dimensions of panel
	private int height;	
	
	public GamePanel(String difficulty) {
		this.gameState = new GameState(difficulty);
		this.width = gameState.getWidth();
		this.height = gameState.getHeight();
		this.setPreferredSize(new Dimension(width, height));
		
		// restart and back button
		this.restartIcon = new ImageIcon("res/hud/restart.png");
		this.restart = new JButton(restartIcon);
		this.exitIcon = new ImageIcon("res/hud/exit.png");
		this.exit = new JButton(exitIcon);
		
		// set tool tips for buttons
		restart.setToolTipText("restart");
		exit.setToolTipText("exit");
		
		// add buttons to bottom of panel
	    add(Box.createRigidArea(new Dimension(width,height-45)));
	    add(restart);
		add(exit);
		
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.g = (Graphics2D) image.getGraphics();
		if (difficulty.equals("easy")) {
			this.bg = Toolkit.getDefaultToolkit().getImage("res/bgEasy.png");
		} else if (difficulty.equals("medium")) {
			this.bg = Toolkit.getDefaultToolkit().getImage("res/bgMedium.png");
		} else {
			this.bg = Toolkit.getDefaultToolkit().getImage("res/bgHard.png");
		}
		
		this.running = true;
		this.waiting = false;
		this.end = false;
	}
	
	/**
	 * When a panel is run, it will start a new thread that will listen to
	 * a key listener
	 */
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	/**
	 * Runs the game
	 */
	public void run() {		
		long start;
		long elapsed;
		long wait;
		
		while (running) {
			// check if the game is over
			if (gameState.isGameOver()) {
				end = true;
			}
			if (waiting == false) {
				update();
				draw();
				repaint();
			}
			start = System.nanoTime();
			elapsed = System.nanoTime() - start;
			// target time in milli/fps
			wait = 2500/30 - elapsed/1000000;
			if (wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
			if (end) running = false;
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
		g.drawImage(bg, 0, 0, null);
		gameState.draw(g);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0 ,0 ,width, height, null);
	}
	
	/**
	 * Key listener methods
	 */
	public void keyPressed(KeyEvent key) {
		gameState.keyPressed(key.getKeyCode());
		if (key.getKeyCode() == KeyEvent.VK_SPACE) waiting = !waiting;
	}
	
	public void keyReleased(KeyEvent key) {
		gameState.keyReleased(key.getKeyCode());
	}
	
	public void keyTyped(KeyEvent key) {}
	
	/**
	 * Gets game running in the screen
	 * @return game state 
	 */
	public GameState getGameState() {
		return this.gameState;
	}
	
	public JButton getRestartButton() {
		return this.restart;
	}
	
	public JButton getExitButton() {
		return this.exit;
	}
	
	public void setRunning(boolean b) {
		this.running = b;
	}
}
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * When a game panel is started, a new game begins which has contains a game state.
 * The game panel calls upon the game state which in turn, interacts with the maze 
 * and its components.
 *
 */
public class GameState {
	private Maze maze;
	private String level;
	private Player player;
	
	private int width;
	private int height;
	
	public GameState(String difficulty) { 
		if (difficulty.equals("easy")) {
			this.maze = new Maze(8, "depth", "easy");
			this.width = 340;
			this.height = 440;
		} else if (difficulty.equals("medium")) {
			this.maze = new Maze(11, "prim", "medium");
			this.width = 460;
			this.height = 560;
		} else if (difficulty.equals("hard")) {
			this.maze = new Maze(16, "prim", "hard");
			this.width = 660;
			this.height = 760;
		}
		this.player = maze.getPlayer();		
	}	
	
	public void update() {
		maze.update();
		player.update();
	}
	
	public void draw(Graphics g) {
		maze.drawMaze(g);
		maze.draw(g);
		player.draw(g);
	}
	
	public boolean isGameOver() {
		return maze.isGameOver();
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setJumping(true);
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setJumping(false);
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public String getLevel() {
		return this.level;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setMaze(Maze m) {
		this.maze = m;
	}
}
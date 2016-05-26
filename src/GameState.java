import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameState {
	private Maze maze;
	private Player player;
	
	private int width;
	private int height;
	
	public GameState(String difficulty) { 
		if (difficulty.equals("easy")) {
			this.maze = new Maze(8, "depth");
			this.width = 340;
			this.height = 420;
		} else if (difficulty.equals("medium")) {
			this.maze = new Maze(11, "prim");
			this.width = 460;
			this.height = 540;
		} else if (difficulty.equals("hard")) {
			this.maze = new Maze(16, "prim");
			this.width = 660;
			this.height = 740;
		}
		this.player = maze.getPlayer();
	}	
	
	public void update() {
		player.update();
		maze.update();
	}
	
	public void draw(Graphics g) {
		maze.drawMaze(g);
		maze.draw(g);
		player.draw(g);
	}
	
	public void drawMaze(Graphics g) {
		
	}
	
	public boolean isGameOver() {
		return maze.isGameOver();
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public void setMaze(Maze m) {
		this.maze = m;
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
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
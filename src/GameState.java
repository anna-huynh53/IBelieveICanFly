import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameState {
	private Maze maze;
	private Player player;
	
	public GameState(String difficulty) { 
		if (difficulty.equals("easy")) {
			this.maze = new Maze(10, "depth");
		} else if (difficulty.equals("medium")) {
			this.maze = new Maze(15, "prim");
		} else if (difficulty.equals("hard")) {
			this.maze = new Maze(20, "prim");
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
	
	public void drawMaze(Graphics g) {
		
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public Player getPlayer() {
		return this.player;
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
}
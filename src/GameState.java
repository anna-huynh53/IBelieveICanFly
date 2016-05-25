import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameState {
	private Maze maze;
	private Player player;
	
	public GameState() { // add difficulty
		this.maze = new Maze(4, "depth");
		this.player = maze.getPlayer();
	}	
	
	public void update() {
		//maze.update();
		for (Entity e : maze.getEnemies()) {
			e.update();
		}
		player.update();
	}
	
	public void draw(Graphics g) {
		maze.draw(g);
		for (Entity e : maze.getEnemies()) {
			e.draw(g);
		}
		player.draw(g);
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
}
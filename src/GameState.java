import java.awt.Graphics;

public class GameState {
	private Maze maze;
	private Player player;
	
	public GameState(String level) {
		if (level.equals("easy")) {
			this.maze = new Maze(10, "depth");
		} else if (level.equals("medium")) {
			this.maze = new Maze(15, "prim");
		} else if (level.equals("hard")) {
			this.maze = new Maze(17, "prim");
		}
		this.player = maze.getPlayer();
	}	
	
	public void update() {
		//maze.update();
		player.update();
	}
	
	public void draw(Graphics g) {
		maze.draw(g);
		player.draw(g);
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
}
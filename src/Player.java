import java.util.ArrayList;
public class Player {
	private int locX;
	private int locY;
	private ArrayList<Point> pastLocs;
	private Maze maze;
	
	public Player(Maze maze, int locX, int locY) {
		this.locX = locX;
		this.locY = locY;
		this.pastLocs = new ArrayList<Point>(); //all past x and y
		this.maze = maze;
	}
	
	public Point moveLeft() {
		
	}
	
	public Point moveRight() {
		
	}
	
	public Point moveUp() {
		
	}
	
	public Point moveDown() {
		
	}
}

import java.util.ArrayList;

public class Player {
	private Point currentLoc;
	private ArrayList<Point> pastLocs;
	private Maze maze;
	
	public Player(Maze maze, Point loc) {
		this.currentLoc = loc;
		this.pastLocs = new ArrayList<Point>(); //all past x and y
		this.maze = maze;
	}
	
	/**
	 * Moves the current player (if possible)
	 * @return The location of the player
	 */
	public Point moveLeft() {
		Point newLoc = new Point(this.currentLoc.getX()-1, this.currentLoc.getY());
		return this.move(newLoc);
	}
	
	/**
	 * Moves the current player (if possible)
	 * @return The location of the player
	 */
	public Point moveRight() {
		Point newLoc = new Point(this.currentLoc.getX()+1, this.currentLoc.getY());
		return this.move(newLoc);
	}
	
	/**
	 * Moves the current player (if possible)
	 * @return The location of the player
	 */
	public Point moveUp() {
		Point newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()-1);
		return this.move(newLoc);
	}
	
	/**
	 * Moves the current player (if possible)
	 * @return The location of the player
	 */
	public Point moveDown() {
		Point newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()+1);
		return this.move(newLoc);
	}
	
	/**
	 * Get the player's location in the maze
	 * @return The point object of the players location.
	 */
	public Point getLocation() {
		return this.currentLoc;
	}
	
	/**
	 * Move the player to the given location (if possible)
	 * @param newLoc The point to move the player to
	 * @return the new position of the player
	 */
	private Point move(Point newLoc) {
		if (maze.isValidMove(this, newLoc)) {
			this.pastLocs.add(this.currentLoc);
			this.currentLoc = newLoc;
			maze.playerMovementListener(this); // This must be called whenever the player moves
			return newLoc;
		} else {
			return this.currentLoc;
		}
	}
	
	/**
	 * Get the number of moves the player has made
	 * @return The number of moves made succesfully
	 */
	public int getNumberOfMoves() {
		return this.pastLocs.size();
	}
}

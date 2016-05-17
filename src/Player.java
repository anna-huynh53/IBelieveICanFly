import java.util.ArrayList;

public class Player implements Entity {
	private Point currentLoc;
	private ArrayList<Point> pastLocs;
	private Maze maze;
	private int score;
	
	public Player(Maze maze, Point loc) {
		this.currentLoc = loc;
		this.pastLocs = new ArrayList<Point>(); // all past x and y
		this.maze = maze;
		this.score = 0;
	}
	
	/**
	 * Move the player to the given location (if possible)
	 * @param newLoc - point to move the player to
	 * @return new position of the player
	 */
	public Point move(Point newLoc) {
		if (maze.isValidMove(this, newLoc)) {
			this.pastLocs.add(this.currentLoc);
			this.currentLoc = newLoc;
			maze.playerMovementListener(this); // must be called whenever the player moves
			return newLoc;
		} else {
			return this.currentLoc;
		}
	}
	
	/**
	 * Moves the current player (if possible)
	 * @return location of the player
	 */
	public Point moveNorth() {
		Point newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()-1);
		return this.move(newLoc);
	}
	
	public Point moveEast() {
		Point newLoc = new Point(this.currentLoc.getX()+1, this.currentLoc.getY());
		return this.move(newLoc);
	}
	
	public Point moveSouth() {
		Point newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()+1);
		return this.move(newLoc);
	}
	
	public Point moveWest() {
		Point newLoc = new Point(this.currentLoc.getX()-1, this.currentLoc.getY());
		return this.move(newLoc);
	}
	
	/**
	 * Get the player's location in the maze
	 * @return coordinates of the player's location
	 */
	public Point getLocation() {
		return this.currentLoc;
	}
	
	/**
	 * The current tile the player is standing on
	 * @return
	 */
	public Tile getTile() {
		return maze.getTile(this.currentLoc);
	}
	
	/**
	 * Gets list of player's past locations 
	 * @return list of coordinates of past locations player has been on
	 */
	public ArrayList<Point> getPastLocs() {
		return this.pastLocs;
	}
	
	/**
	 * Get the number of moves the player has made
	 * @return number of moves made successfully
	 */
	public int getNumberOfMoves() {
		return this.pastLocs.size();
	}
	
	/**
	 * Get the players score
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set the players score
	 * @param score - the players score
	 */
	public void setScore(int score) {
		this.score = score;
		if (this.score < 0) {
			this.score = 0;
		}
	}
	
	/**
	 * Increase the players score by a value
	 * @param value - the value to increase the score by
	 */
	public void increaseScore(int value) {
		if (this.score < value) {
			this.score = 0;
		} else {
			this.score = this.score + value;
		}
	}
}
import java.util.ArrayList;
import java.awt.*;

public class Player implements Entity {
	private Point currentLoc;
	private ArrayList<Point> pastLocs;
	private Maze maze;
	private int score;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean jumping;
	
	public Player(Maze maze, Point loc) {
		this.currentLoc = loc;
		this.pastLocs = new ArrayList<Point>(); // all past x and y
		this.maze = maze;
		this.score = 0;
	}
					
	public void move() {
		Point newLoc = new Point(0,0);
		if (left) newLoc = new Point(this.currentLoc.getX()-1, this.currentLoc.getY());
		if (right) newLoc = new Point(this.currentLoc.getX()+1, this.currentLoc.getY());
		if (up) newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()-1);
		if (down) newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()+1);
		if (maze.isValidMove(this, newLoc)) {
			this.pastLocs.add(this.currentLoc);
			this.currentLoc = newLoc;
			maze.playerMovementListener(this); // must be called whenever the player moves
		} 
	}
	
	public void update() {
	}
	
	public void setLeft(boolean b) {
		this.left = b;
	}
	
	public void setRight(boolean b) {
		this.right = b;
	}
	
	public void setUp(boolean b) {
		this.up = b;
	}
	
	public void setDown(boolean b) {
		this.down = b;
	}
	
	public void setJumping(boolean b) {
		this.jumping = b;
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
	
	public void draw(Graphics g) {
		Image spriteImage = Toolkit.getDefaultToolkit().getImage("res//sprite.png");
		g.drawImage(spriteImage, maze.getPlayer().getLocation().getX() * 20,
				maze.getPlayer().getLocation().getY() * 20, null);
	}
}
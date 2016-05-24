import java.awt.*;

public class Player implements Entity {
	private Point currentLoc;
	private Maze maze;
	private int score;
	private int health;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	public Player(Maze maze, Point loc) {
		this.currentLoc = loc;
		this.maze = maze;
		this.score = 0;
		this.health = 100;
	}
					
	public void move() {
		Point newLoc = new Point(0,0);
		if (left) newLoc = new Point(this.currentLoc.getX()-1, this.currentLoc.getY());
		if (right) newLoc = new Point(this.currentLoc.getX()+1, this.currentLoc.getY());
		if (up) newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()-1); 
		if (down) newLoc = new Point(this.currentLoc.getX(), this.currentLoc.getY()+1); 
		if (maze.isValidMove(this, newLoc)) {
			this.currentLoc = newLoc;
			maze.playerMovementListener(this); // must be called whenever the player moves
		} 
	}
	
	public void update() {
		move();
	}
	
	public void draw(Graphics g) {
		Image spriteImage = Toolkit.getDefaultToolkit().getImage("res//sprite.png");
		g.drawImage(spriteImage, this.currentLoc.getX() * 20,
				    this.currentLoc.getY() * 20, null);
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

/////////////////////////////////////score/////////////////////////////////////
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
	
/////////////////////////////////////health/////////////////////////////////////
	public int getHealth() {
		return this.health;
	}
	
	public int getDamage() {
		return 0;
	}
	
	public void doDamage(int damage) {
		this.health -= damage; 
	}
}
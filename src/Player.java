import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;

public class Player implements Entity {
	private Maze maze;
	private Point currentLoc;
	private int score;
	private int health;
	
	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;
	
	private Animation animate;
	private ArrayList<Image> walkLeft;
	private ArrayList<Image> walkRight;
	private ArrayList<Image> jump;
	private ArrayList<Image> fall;
	private ArrayList<Image> idle;
	private ArrayList<Image> hit;
	private ArrayList<Image> dead;
	
	
	public Player(Maze maze, Point loc) {
		this.maze = maze;
		this.currentLoc = loc;
		this.score = 0;
		this.health = 100;
		
		animate = new Animation();
		initAnimationSets();
	}
	
	public void initAnimationSets() {
		walkLeft = maze.getImages().getWalkLeft();
		walkRight = maze.getImages().getWalkRight();
		jump = maze.getImages().getJump();
		fall = maze.getImages().getFall();
		idle = maze.getImages().getIdle();
		hit = maze.getImages().getHit();
		dead = maze.getImages().getDead();
	}
					
	public void move() {
		Point newLoc = new Point(0,0);
		int currX = this.currentLoc.getX();
		int currY = this.currentLoc.getY();

		if (left && !jumping) {
			newLoc = new Point(currX-1, currY);
			animate.setFrames(walkLeft);
		} else if (right && !jumping) { 
			newLoc = new Point(currX+1, currY);
			animate.setFrames(walkRight);
		} else if (jumping) {
			if (left) newLoc = new Point (currX-1, currY-1);
			else if (right) newLoc = new Point (currX+1, currY-1);
			else newLoc = new Point(currX, currY-1);
			falling = true;	
			animate.setFrames(jump);
		} else if (!left && !right && !jumping) {
			newLoc = new Point(currX, currY+1);
			if (!maze.isValidMove(this, newLoc)) {
				falling = false;
				animate.setFrames(idle);
			} else {
				animate.setFrames(fall);
			}
		} 
		
		if (maze.isValidMove(this, newLoc)) {
			this.currentLoc = newLoc;
			maze.playerMovementListener(this); // must be called whenever the player moves
		} 
	}
	
	public void update() {
		move();
		animate.update();
	}
	
	public void draw(Graphics g) {
		Image player = animate.getCurrImage();
		g.drawImage(player, this.currentLoc.getX()*Maze.SCALE, this.currentLoc.getY()*Maze.SCALE, null);
	}
	
	public void setLeft(boolean b) {
		this.left = b;
	}
	
	public void setRight(boolean b) {
		this.right = b;
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
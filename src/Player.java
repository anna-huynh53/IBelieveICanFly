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
	
	private Toolkit t;
	private Animation animate;
	private ArrayList<Image> idle;
	private ArrayList<Image> walkingMove;
	private ArrayList<Image> jumpingMove;
	private ArrayList<Image> fallingMove;
	
	public Player(Maze maze, Point loc) {
		this.maze = maze;
		this.currentLoc = loc;
		this.score = 0;
		this.health = 100;
		
		animate = new Animation();
		initAnimationSets();
	}
	
	public void initAnimationSets() {
		t = Toolkit.getDefaultToolkit();
		idle = new ArrayList<Image>();
		walkingMove = new ArrayList<Image>();
		jumpingMove = new ArrayList<Image>();
		fallingMove = new ArrayList<Image>();
		
		idle.add(t.getImage("res/idle.png"));		
		
		walkingMove.add(t.getImage("res/walk1.png"));
		walkingMove.add(t.getImage("res/walk2.png"));
		walkingMove.add(t.getImage("res/walk3.png"));
		walkingMove.add(t.getImage("res/walk4.png"));
		animate.setFrames(walkingMove);
	}
					
	public void move() {
		Point newLoc = new Point(0,0);
		int currX = this.currentLoc.getX();
		int currY = this.currentLoc.getY();

		if (jumping) {
			newLoc = new Point(currX, currY-1);
			falling = true;	
		} else {
			newLoc = new Point(currX, currY+1);
			if (!maze.isValidMove(this, newLoc)) {
				falling = false;
			}
		}
		if (left) {
			newLoc = new Point(currX-1, currY);
		} else if (right) { 
			newLoc = new Point(currX+1, currY);
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
		Image spriteImage = animate.getCurrImage();
		g.drawImage(spriteImage, (int)this.currentLoc.getX() * 20,
				    (int)this.currentLoc.getY() * 20, null);
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
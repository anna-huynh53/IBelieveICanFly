import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;

public class Player implements Entity {
	private Maze maze;
	
	// location and movement
	private Point currentLoc;
	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;
	private boolean damaged;
	private boolean died;
	
	private Animation animate;
	private ArrayList<Image> walkLeft;
	private ArrayList<Image> walkRight;
	private ArrayList<Image> jump;
	private ArrayList<Image> fall;
	private ArrayList<Image> idle;
	private ArrayList<Image> hit;
	private ArrayList<Image> dead;
	
	// player info
	private int score;
	private int maxHealth;
	private int health;
	private ArrayList<Item> powerUps;
	
	public Player(Maze maze, Point loc) {
		this.maze = maze;
		
		this.currentLoc = loc;		
		this.animate = new Animation();
		initAnimationSets();
		
		this.score = 0;
		this.maxHealth = this.health = 100;
		this.powerUps = new ArrayList<Item>();
	}
	
	public void initAnimationSets() {
		this.walkLeft = maze.getImages().getWalkLeft();
		this.walkRight = maze.getImages().getWalkRight();
		this.jump = maze.getImages().getJump();
		this.fall = maze.getImages().getFall();
		this.idle = maze.getImages().getIdle();
		this.hit = maze.getImages().getHit();
		this.dead = maze.getImages().getDead();
		animate.setFrames(idle);
	}
				
	/**
	 * Updates player's movement and animation
	 */
	public void update() {
		move();
		animate.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(animate.getCurrImage(),this.currentLoc.getX()*Maze.SCALE,this.currentLoc.getY()*Maze.SCALE,null);
	}

/////////////////////////////movement and location//////////////////////////////
	
	/**
	 * Moves the player based on the key listener and sets the appropriate animation
	 */
    public void move() {
        int currX = this.currentLoc.getX();
        int currY = this.currentLoc.getY();
        Point newLoc = new Point(currX, currY);

        if (left) {
            newLoc.setX(newLoc.getX()-1);
            if (!maze.isValidMove(this, newLoc)) newLoc.setX(newLoc.getX()+1);
            animate.setFrames(walkLeft);
        }

        if (right) {
            newLoc.setX(newLoc.getX()+1);
            if (!maze.isValidMove(this, newLoc)) newLoc.setX(newLoc.getX()-1);
            animate.setFrames(walkRight);
            if (left) animate.setFrames(idle);
        }
        
        if (jumping) {
            newLoc.setY(newLoc.getY()-1);
            Point jumpLoc = new Point(currX, currY-1);
            if (!maze.isValidMove(this, newLoc) || !maze.isValidMove(this, jumpLoc)) 
            	newLoc.setY(newLoc.getY()+1);
            animate.setFrames(jump);
        }

        // falling logic
        newLoc.setY(newLoc.getY()+1);
        if (maze.isValidMove(this, newLoc) && !jumping) {
            animate.setFrames(fall);
            falling = true;
        } else {
            newLoc.setY(newLoc.getY()-1);
            falling = false;
        }
        
        // if not doing anything, player is idle
        if (!falling && !jumping && !left && !right) animate.setFrames(idle);
        
        // if player is hit by enemy
        if (damaged) {
        	animate.setFrames(hit);
        	damaged = false;
        }
        
        // if player is dead
        if (died)  {
        	animate.setFrames(dead);
        }

        animate.increaseCurrFrame();

        if (maze.isValidMove(this, newLoc)) {
            this.currentLoc = newLoc;
            maze.playerMovementListener(this); // must be called whenever the player moves
        } 
    }
	
	/**
	 * Gets the player's location in the maze
	 * @return player's location
	 */
	public Point getLocation() {
		return this.currentLoc;
	}
	
	/**
	 * Gets the current tile the player is standing on
	 * @return tile player is on
	 */
	public Tile getTile() {
		return maze.getTile(this.currentLoc);
	}
	
	/**
	 * Sets left movement
	 * @param true if going left otherwise false
	 */
	public void setLeft(boolean b) {
		this.left = b;
	}
	
	/**
	 * Sets right movement
	 * @param true if going right otherwise false
	 */
	public void setRight(boolean b) {
		this.right = b;
	}
	
	/**
	 * Sets jumping
	 * @param true if jumping otherwise false
	 */
	public void setJumping(boolean b) {
		this.jumping = b;
	}
	
	/**
	 * Sets if player is currently being attacked
	 * @param true if attacked otherwise false
	 */
	public void setDamaged(boolean b) {
		this.damaged = b;
	}
	
	/**
	 * Sets if player has died
	 * @param true if dead otherwise false 
	 */
	public void setDied(boolean b) {
		this.died = b;
	}

/////////////////////////////////////score/////////////////////////////////////
	
	/**
	 * Increase the players score by a value
	 * @param value - the value to increase the score by
	 */
	public void increaseScore(int value) {
		this.score+=value;
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
	
////////////////////////////////////powerups////////////////////////////////////	
	
	public void addPowerUp(Item powerUp) {
		this.powerUps.add(powerUp);
	}
	
	public void removePowerUp(Item powerUp) {
		this.powerUps.remove(powerUp);
	}
	
	public ArrayList<Item> getPowerUps() {
		return this.powerUps;
	}
	
	
/////////////////////////////////////health/////////////////////////////////////
	
	/**
	 * Inflicts given amount of damage to player
	 * @param damage value
	 */
	public void doDamage(int damage) {
		this.health -= damage; 
	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getDamage() { return 0; } // needed as implements Entity
}
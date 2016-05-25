import java.awt.Graphics;

/**
 * An entity that can interact with the maze.
 * Includes the player and enemies.
 */
public interface Entity {
	
	/**
	 * Updates the entity by calling move and setting other
	 * (this is for stuff like where it is facing and stuff but don't
	 * worry about it for now)
	 */
	public void update();
	
	/**
	 * Draws the entity
	 */
	public void draw(Graphics g);
	
	/**
	 * Gets location of the entity
	 * @return Point of location
	 */
	public Point getLocation();
	
	/**
	 * 
	 * @return
	 */
	public int getDamage();
}
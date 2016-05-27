import java.awt.Graphics;

/**
 * An entity that can interact with the maze including players and enemies.
 */
public interface Entity {
	
	/**
	 * Updates the entity by calling move and animate
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
	 * Gets if the entity has damage value
	 * @return
	 */
	public int getDamage();
}
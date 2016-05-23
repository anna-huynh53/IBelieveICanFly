import java.awt.Graphics;

/**
 * A controllable entity that is able to interact with the maze
 */
public interface Entity {
	
	/**
	 * Move the entity to a new location (if possible)
	 * (compressed all separate movements into one)
	 */
	public void move();
	
	
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
	 * Gets the location of the entity
	 * @return The entity's location
	 */
	public Point getLocation();
	
	/**
	 * The tile the entity is located on
	 * @return Tile
	 */
	public Tile getTile();

}
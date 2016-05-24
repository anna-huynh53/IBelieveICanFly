import java.awt.Graphics;

/**
 * A controllable entity that is able to interact with the maze
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
	
	public Point getLocation();
	
	public int getDamage();
}
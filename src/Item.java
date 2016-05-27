import java.awt.Graphics;

/**
 * Items include pickups and powerups for the player and uninteractable objects
 * such as decoration.
 *
 */
public interface Item {
	
	/**
	 * Called when a player interacts with an item
	 * @param player interacting with item
	 */
	public void playerInteractEvent(Player p);
	
	/**
	 * Updates the item location and/or animation (if necessary)
	 */
	public void update();
	
	/**
	 * Draws the item
	 */
	public void draw(Graphics g);
	
	/**
	 * Gets the item's location
	 * @return Point location of item
	 */
	public Point getLoc();
}
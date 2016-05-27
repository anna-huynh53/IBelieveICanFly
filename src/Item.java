import java.awt.Graphics;

public interface Item {
	
	/**
	 * Called when a player interacts with an item
	 * @param player interacting with tiem
	 */
	public void playerInteractEvent(Player p);
	
	public void update();
	
	/**
	 * Draws the item
	 */
	public void draw(Graphics g);
	
	public Point getLoc();
	
	public Item clone();
}

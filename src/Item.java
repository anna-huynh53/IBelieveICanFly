import java.awt.Graphics;


public interface Item {
	/**
	 * Called when a player interacts with the item
	 * @param player
	 */
	public void playerInteractEvent(Player p);
	
	public void draw(Graphics g);
}

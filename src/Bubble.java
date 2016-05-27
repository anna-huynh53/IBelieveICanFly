import java.awt.Graphics;
import java.awt.Image;

/**
 * A powerup item for a player that protects them from damage by an enemy for one use.
 *
 */
public class Bubble implements Item {
	private Point loc;
	private Image bubble;
	
	public Bubble(Point loc, Image bubble) {
		this.loc = loc;
		this.bubble = bubble;
	}

	/**
	 * If a player steps on a bubble, the bubble is removed from the maze and
	 * is added to their powerups list
	 */
	public void playerInteractEvent(Player p) {
		p.getTile().removeItem();
		p.addPowerUp(this);
	}

	public void update() {} // unused as the bubble has no animation

	public void draw(Graphics g) {
		g.drawImage(bubble,this.loc.getX()*Maze.SCALE,this.loc.getY()*Maze.SCALE,null);
	}

	/**
	 * Gets location of bubble
	 * @return location of bubble
	 */
	public Point getLoc() {
		return this.loc;
	}
}
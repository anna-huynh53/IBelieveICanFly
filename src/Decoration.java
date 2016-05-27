import java.awt.Graphics;
import java.awt.Image;

/**
 * A worthless random ground item used to decorate the maze.
 *
 */
public class Decoration implements Item {
	private int value;
	private Point loc;
	private Image image;
	
	public Decoration(Point loc, Image image) {
		this.value = 0;
		this.loc = loc;
		this.image = image;
	}

	public void playerInteractEvent(Player p) {} // unused as has no interaction
												 // with player
	
	public void update() {} // unused as decorations have no animation
	
	public void draw(Graphics g) {
		g.drawImage(image,this.loc.getX()*Maze.SCALE,this.loc.getY()*Maze.SCALE,null);
	}
	
	/**
	 * Gets value of decoration (0 for all)
	 * @return value of decoration
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Gets location of decoration
	 * @return location of decoration
	 */
	public Point getLoc() {
		return this.loc;
	}
}
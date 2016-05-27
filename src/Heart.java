import java.awt.Graphics;
import java.awt.Image;

/**
 * A pickup item for a player that increases their health.
 *
 */
public class Heart implements Item {
	private int value;
	private Point loc;
	
	private Image heart;
	
	public Heart(Point loc, Image heart) {
		this.value = 20;
		this.loc = loc;		
		this.heart = heart;
	}

	/**
	 * If a player steps on a heart, the heart is removed from the maze and
	 * their health is increased by the value of the heart
	 */
	public void playerInteractEvent(Player p) {
		p.getTile().removeItem();
		p.increaseHealth(value);
	}
	
	public void update() {} // unused as hearts don't have animation
	
	public void draw(Graphics g) {
		g.drawImage(heart,this.loc.getX()*Maze.SCALE,this.loc.getY()*Maze.SCALE,null);
	}
	
	/**
	 * Gets value of heart
	 * @return value of heart
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Gets location of heart
	 * @return location of heart
	 */
	public Point getLoc() {
		return this.loc;
	}
}
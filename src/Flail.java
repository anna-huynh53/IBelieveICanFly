import java.awt.Graphics;
import java.awt.Image;


public class Flail implements Item {
	private Point loc;
	private Image flail;
	
	public Flail(Point loc, Image flail) {
		this.loc = loc;
		this.flail = flail;
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
		g.drawImage(flail,this.loc.getX()*Maze.SCALE,this.loc.getY()*Maze.SCALE,null);
	}

	/**
	 * Gets location of bubble
	 * @return location of bubble
	 */
	public Point getLoc() {
		return this.loc;
	}
}
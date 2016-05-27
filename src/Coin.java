import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;

/**
 * A pickup item for a player that increases their score.
 *
 */
public class Coin implements Item {
	private int value;
	private Point loc;
	
	private Animation animate;
	private ArrayList<Image> coinRot;
	
	public Coin(int value, Point loc, ArrayList<Image> coinRot) {
		this.value = value;
		this.loc = loc;		
		this.animate = new Animation();
		this.coinRot = coinRot;
		initAnimationSet();
	}

	private void initAnimationSet() {
		animate.setFrames(coinRot);
		animate.setDelay(150);
	}

	/**
	 * If a player steps on a coin, the coin is removed from the maze and
	 * their score is increased by the value of the coin 
	 */
	public void playerInteractEvent(Player p) {
		p.getTile().removeItem();
		p.increaseScore(value);
	}
	
	/**
	 * Updates animation
	 */
	public void update() {
		animate.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(animate.getCurrImage(),this.loc.getX()*Maze.SCALE,this.loc.getY()*Maze.SCALE,null);
	}
	
	/**
	 * Gets value of coin
	 * @return value of coin
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Gets location of coin
	 * @return location of coin
	 */
	public Point getLoc() {
		return this.loc;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Coin) { 
		    Coin c = (Coin) o;
		    return (this.value == c.getValue() && this.loc == c.getLoc());
		}
		return false;
	}
	
	public Coin clone() {
		Coin c = new Coin(this.value, this.loc.clone(), this.coinRot);
		return c;
	}
}
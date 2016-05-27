import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Coin implements Item {
	private int value;
	private Point loc;
	
	private Animation animate;
	private ArrayList<Image> coinRot;
	
	public Coin(int value, Point loc) {
		this.value = value;
		this.loc = loc;
		animate = new Animation();
		initAnimationSet();
	}

	private void initAnimationSet() {
		Toolkit t = Toolkit.getDefaultToolkit();
		coinRot = new ArrayList<Image>();
		coinRot.add(t.getImage("res/pickups/coin1.png"));
		coinRot.add(t.getImage("res/pickups/coin2.png"));
		coinRot.add(t.getImage("res/pickups/coin3.png"));
		coinRot.add(t.getImage("res/pickups/coin4.png"));
		animate.setFrames(coinRot);
		animate.setWait(150);
	}

	public void playerInteractEvent(Player p) {
		p.increaseScore(value);
		p.getTile().removeItem();
	}
	
	public void update() {
		animate.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(animate.getCurrImage(), this.loc.getX()*Maze.SCALE, this.loc.getY()*Maze.SCALE, null);
	}
	
	public int getValue() {
		return this.value;
	}
	
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
		Coin c = new Coin(this.value, this.loc.clone());
		return c;
	}
}
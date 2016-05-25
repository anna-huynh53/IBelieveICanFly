import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Coin implements Item {
	private int value;
	private Point loc;
	
	
	
	public Coin(int value, Point loc) {
		this.value = value;
		this.loc = loc;
	}

	public void playerInteractEvent(Player p) {
		p.increaseScore(value);
		p.getTile().removeItem();
	}
	
	public void draw(Graphics g) {
		Image coin = Toolkit.getDefaultToolkit().getImage("res//coin.png");
		g.drawImage(coin, (int)this.loc.getX() * 20, (int)this.loc.getY() * 20, null);
	}
	
	public int getValue() {
		return this.value;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Coin) { 
		    Coin c = (Coin) o;
		    return (this.value == c.value);
		}
		return false;
	}
}
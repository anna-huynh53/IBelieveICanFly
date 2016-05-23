
public class Coin implements Item {
	private int value;
	
	public Coin(int value) {
		this.value = value;
	}

	public void playerInteractEvent(Player p) {
		p.increaseScore(value);
		p.getTile().removeItem();
	}
	
	public boolean equals(Object o) {
		if (o instanceof Coin) { 
		    Coin c = (Coin) o;
		    return (this.value == c.value);
		}
		return false;
	}
}
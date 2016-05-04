
public class Tile {
	private String type;
	private boolean empty;
	private boolean traversable;
	private boolean lethal;
	
	public Tile(String type, boolean empty, boolean traversable, boolean lethalm) {
		this.type = type;
		this.empty = empty;
		this.traversable = traversable;
		this.lethal = lethal;
	}
}

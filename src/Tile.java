/**
 * Represents a tile of the maze.
 * It describes the classification of the tile e.g. wall, lava etc. 
 * and the properties of the tile i.e. if it is traversable or lethal
**/
public class Tile {
	private String classification;
	private boolean traversable;
	private boolean lethal;
	private Item item;
	
	// Classifications //
	public static final String START = "start"; // also a path by definition
	public static final String END = "end"; // also a path by definition
	public static final String WALL = "wall";
	public static final String PATH = "path";
	
	/**
	 * Instantiates a new tile object
	 * @param classification - describes type of tile [only for display]
	 * @param empty - whether or not the tile contains an item/object
	 * @param traversable - whether or not a player can move on or through the tile 
	 * @param lethal - whether or not the tile kills the player 
	**/
	public Tile(String classification) {
		this.classification = classification;
		if (classification.equals(START) || classification.equals(PATH) || classification.equals(END)) {
			this.traversable = true;
			this.lethal = false;
		} else if (classification.equals(WALL)) {
			this.traversable = false;
			this.lethal = false;
		}
		item = null;
	}
	
	/**
	 * Removes the item from the tile
	 * @return item removed
	 */
	public Item removeItem() {
		Item i = this.item;
		this.item = null;
		return i;
	}
	
	/**
	 * Returns the classification of the tile
	 * @return the type of tile
	**/
	public String getClassification() {
		return this.classification;
	}
	
	/**
	 * Returns whether the tile is traversable
	 * @return whether a player can move on or through the tile 
	**/
	public boolean isTraversable() {
		return this.traversable;
	}
	
	/**
	 * Returns whether the tile is lethal
	 * @return whether the tile kills the player
	**/
	public boolean isLethal() {
		return this.lethal;
	}
	
	/**
	 * get the item on the tile
	 * @return item
	 */
	public Item getItem() {
		return this.item;
	}
	
	/**
	 * Sets the tile classification
	 * @param c - classification
	 */
	public void setClassification(String c) {
		this.classification = c;
	}
	
	/**
	 * Sets traversability of tile
	 * @param t - true if can traverse otherwise false
	 */
	public void setTraversable(boolean t) {
		this.traversable = t;
	}
	
	/**
	 * Sets lethalness of tile
	 * @param l - true if lethal otherwise false
	 */
	public void setLethal(boolean l) {
		this.lethal = l;
	}

	/**
	 * Set the item the tile contains
	 * @param i - the Item the tile contains
	 */
	public void setItem(Item i) {
		this.item = i;
	}
	
	/**
	 * Checks if this and another tile are equal
	 * @param tile object
	 * @return true if the two tiles are equal otherwise false
	 */
	public boolean equals(Object o) {
		if (o instanceof Tile) {
			Tile t = (Tile)o;
			return ((this.classification.equals(t.classification)) &&
					(this.isLethal() == t.isLethal()) && 
					(this.isTraversable() == t.isTraversable()));
		}
		return false;
	}
}
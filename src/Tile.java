/**
 * This class represents a tile of the maze.
 * It describes the classification of the tile e.g. wall, lava etc. 
 * and the properties of the tile i.e. if it is traversable, empty, or lethal
**/
public class Tile {
	private String classification;
	private boolean empty;
	private boolean traversable;
	private boolean lethal;
	
	// Classifications //
	public static final String START = "start"; // Also a path by definition
	public static final String END = "end"; // Also a path by definition
	public static final String WALL = "wall";
	public static final String PATH = "path";
	public static final String LAVA = "lava";
	
	/**
	 * Instantiates a new tile object
	 * @param classification - describes type of tile [only for display]
	 * @param empty - whether or not the tile contains an item/object
	 * @param traversable - whether or not a player can move on or through the tile 
	 * @param lethal - whether or not the tile kills the player 
	**/
	public Tile(String classification) {
		this.classification = classification;
		if (classification.equals(START) || classification.equals(PATH)) {
			this.empty = true;
			this.traversable = true;
			this.lethal = false;
		} else if (classification.equals(WALL)) {
			this.empty = false;
			this.traversable = false;
			this.lethal = false;
		}
	}
	
	/**
	 * Returns the classification of the tile
	 * @return the type of tile
	**/
	public String getClassification() {
		return this.classification;
	}
	
	/**
	 * Returns whether the tile is empty
	 * @return whether the tile contains any items/objects
	**/
	public boolean isEmpty() {
		return this.empty;
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
	 * Checks if this and another tile are equal
	 * @param t - tile object
	 * @return if the two tiles are equal
	 */
	public boolean equals(Object o) {
		if (o instanceof Tile) {
			Tile t = (Tile)o;
			return ((this.classification.equals(t.classification)) &&  
					(this.empty == t.isEmpty()) && (this.isLethal() == t.isLethal()) && 
					(this.isTraversable() == t.isTraversable()));
		}
		return false;
	}
}

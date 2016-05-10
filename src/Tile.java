/**
 * This class represents a tile of the maze.
 * It describes the classification of the tile eg. wall, lava etc. 
 * and the properties of the tile ie if it is traversable, empty, or lethal
**/
public class Tile {
	private String classification;
	private boolean empty;
	private boolean traversable;
	private boolean lethal;
	
	//private static final String START = "start";
	//private static final String PATH = "path";
	
	/**
	 * Instantiates a new tile object
	 * @param classification - describes type of tile
	 * @param empty - whether or not the tile contains an item/object
	 * @param traversable - whether or not a player can move on or through the tile 
	 * @param lethal - whether or not the tile kills the player 
	**/
	public Tile(String classification, boolean empty, boolean traversable, boolean lethal) {
		this.classification = classification;
		this.empty = empty;
		this.traversable = traversable;
		this.lethal = lethal;
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
	
	public void setClassification(String c) {
		this.classification = c;
	}
	
	public void setTraversable(boolean t) {
		this.traversable = t;
	}
	
	public void setLethal(boolean l) {
		this.lethal = l;
	}
}
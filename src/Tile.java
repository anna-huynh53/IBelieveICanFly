
/**
 * This class represents a tile of the maze.
 * It describes the classification of the tile eg. wall, lava etc. 
 * And the properties of the tile; ie if it is traversable, empty, or lethal.
**/
public class Tile {
	
	private String classification;
	private boolean empty;
	private boolean traversable;
	private boolean lethal;
	
	/**
	 * Instantiates a new tile object
	 * @preconditions 
	 * @postconditions Instantiates a new tile object
	 * @param classification A String describing the type of tile eg. lava etc.
	 * @param empty A boolean containing whether or not the tile holds an item/object
	 * @param traversable A boolean containing whether or not a player can walk upon the tile
	 * @param lethal A boolean containing whether or not the tile kills the player 
	**/
	public Tile(String classification, boolean empty, boolean traversable, boolean lethal) {
		
		this.classification = classification;
		this.empty = empty;
		this.traversable = traversable;
		this.lethal = lethal;
	
	}
	
	/**
	 * Returns the classification of the tile
	 * @precondition
	 * @postcondition Returns a String containing the classification of the tile 
	**/
	public String getClassification() {
		return this.classification;
	}
	
	/**
	 * Returns whether the tile is traversable
	 * @precondition
	 * @postcondition Returns whehter a player can move on or through the tile 
	**/
	public boolean isTraversable() {
		return this.traversable
	}

	/**
	 * Returns whether the tile is empty
	 * @precondition
	 * @postcondition Returns whehter the tile contains any items/objects
	**/
	public boolean isEmpty() {
		return this.empty;
	}
	
	/**
	 * Returns whether the tile is lethal
	 * @precondition
	 * @postcondition Returns whehter the tile will kill the player
	**/
	public boolean isLethal() {
		return this.lethal;
	}
}

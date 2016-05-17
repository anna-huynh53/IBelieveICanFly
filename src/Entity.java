/**
 * A controllable entity that is able to interact with the maze
 *
 */
public interface Entity {
	/**
	 * Gets the location of the entity
	 * @return The entity's location
	 */
	Point getLocation();
	
	/**
	 * Move the entity to a new location (if possible)
	 * @param newLoc - location to move the entity to
	 * @return location the entity is located at after move
	 */
	Point move(Point newLoc);
	
	/**
	 * Moves the current entity (if possible)
	 * @return The location of the player
	 */
	Point moveNorth();
	
	/**
	 * Moves the current entity (if possible)
	 * @return location of the player
	 */
	Point moveEast();
	
	/**
	 * Moves the current entity (if possible)
	 * @return The location of the player
	 */
	Point moveSouth();
	
	/**
	 * Moves the current entity (if possible)
	 * @return location of the player
	 */
	Point moveWest();
	
	/**
	 * The tile the entity is located on
	 * @return Tile
	 */
	Tile getTile();
}
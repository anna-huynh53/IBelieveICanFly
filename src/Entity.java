
public interface Entity {
	/**
	 * Gets the location of the entity
	 * @return The entity's location
	 */
	Point getLocation();
	
	/**
	 * Move the entity to a new location (if possible)
	 * @param newLoc The location to move the entity to
	 * @return The location the entity is located at after move
	 */
	Point move(Point newLoc);
}

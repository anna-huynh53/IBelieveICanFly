/**
 * Describes a point in the maze in the form (x, y)
 *
 */
public class Point {
	private int x;
	private int y;
	
	/**
	 * Constructor for creating a point
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get X
	 * @return The x coordinate
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Get Y
	 * @return The y coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Set X
	 * @param x The x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set Y
	 * @param y The y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Determines if this point is equal to another point
	 * @param p A point
	 * @return If the points are the same
	 */
	public boolean equals(Object o) {
		if (o instanceof Point) { 
		    Point p = (Point) o;
		    return ((this.x == p.getX()) && (this.y == p.getY()));
		}
		return false;
	}
	
}

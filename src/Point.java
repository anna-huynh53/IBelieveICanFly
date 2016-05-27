/**
 * Describes a point in the maze in the form (x, y)
 */
public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get X
	 * @return x coordinate
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Get Y
	 * @return y coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Set X
	 * @param x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set Y
	 * @param y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Determines if this point is equal to another point
	 * @param point
	 * @return true if points are the same otherwise false
	 */
	public boolean equals(Object o) {
		if (o instanceof Point) { 
		    Point p = (Point) o;
		    return ((this.x == p.getX()) && (this.y == p.getY()));
		}
		return false;
	}
	
	public Point clone() {
		Point p = new Point(this.x, this.y);
		return p;
	}
}
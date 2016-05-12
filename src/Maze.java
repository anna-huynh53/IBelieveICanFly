import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
	private int size;
	private Tile[][] tiles;
	//private Player player;
	
	/**
	 * The constructor to create a new maze object.  
	 * @param size - height/width of the square maze that will be created
	 */
	public Maze(int size) {
		
		// the actual board size will be size x 2 + 1 to account for the 
		// border and the walls needed between each of the tiles
		this.size = size + size + 1;
		this.tiles = new Tile[this.size][this.size]; 
		
		// initialise all tiles to have walls surrounding them i.e.
		// tiles are isolated and not connecting to any other tile
		// points are used to explore the grid, all of which correspond
		// to some tile in the 2d array 
		// toVisit contains all points to visit when doing depth-first search 
		ArrayList<Point> toVisit = new ArrayList<Point>();
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (j % 2 == 0 || i % 2 == 0) {
					tiles[i][j] = new Tile(Tile.WALL);
				} else {
					tiles[i][j] = new Tile(Tile.PATH);
					Point p = new Point(i, j);
					toVisit.add(p);
				}
			}
		}
		
		// set start tile at (1, 1) 
		tiles[1][1].setClassification(Tile.START);
		Point startPoint = new Point(1, 1);
		// start point is initial point to search from
		Point curr = startPoint;
		toVisit.remove(curr);
		
		// to generate the maze using depth-first search, a stack is used to keep
		// track of paths to explore
		Stack<Point> backStack = new Stack<Point>();
		Random rand = new Random();	
		// continue until all points in grid have been visited
		while (!toVisit.isEmpty()) {
			// if there are unvisited neighbours
			ArrayList<Point> neighbours = unvisitedNeighbours(curr, toVisit);
			if (!neighbours.isEmpty()) {
				// choose a random neighbour
				Point neighbour = neighbours.get(rand.nextInt(neighbours.size()));
				// remove the wall between them
				removeWall(curr, neighbour);
				// push the current point to the stack
				backStack.push(curr);
				// the neighbour is now the current point
				curr = neighbour;
				toVisit.remove(curr);
			} else {
				// if the stack is not empty, current is now top of stack
				curr = backStack.pop();
			}
		}
	}		
	
	/**
	 * Finds all unvisited neighbours for a given point and list of points 
	 * that have not been visited (this is because tiles do not store their
	 * own neighbours so they must be found for each point)
	 * @param p - point 
	 * @param toVisit - list of points yet to visit
	 * @return array of neighbour points
	 */
	public ArrayList<Point> unvisitedNeighbours(Point p, ArrayList<Point> toVisit) {
		ArrayList<Point> neighbours = new ArrayList<Point>();
		// difference of 2 due to all tiles separated by a wall
		Point p1 = new Point(p.getX(), p.getY()-2); // point north to current point
		if (toVisit.contains(p1)) neighbours.add(p1);
		Point p2 = new Point(p.getX()+2, p.getY()); // point east
		if (toVisit.contains(p2)) neighbours.add(p2);
		Point p3 = new Point(p.getX(), p.getY()+2); // point south
		if (toVisit.contains(p3)) neighbours.add(p3);
		Point p4 = new Point(p.getX()-2, p.getY()); // point west
		if (toVisit.contains(p4)) neighbours.add(p4);	
		return neighbours;
	}
	/**
	 * Removes a wall between 2 points by replacing it with a path 
	 * @param p1
	 * @param p2
	 */	
	public void removeWall(Point p1, Point p2) {
		int xDiff = p1.getX()-p2.getX();
		int yDiff = p1.getY()-p2.getY();
		if (xDiff == 2 || xDiff == -2) { // remove wall west/east of p1
			tiles[(p1.getX()+p2.getX())/2][p1.getY()] = new Tile(Tile.PATH);	
		} else if (yDiff == 2 || yDiff == -2) { // remove wall to north/south of p1
			tiles[p1.getX()][(p1.getY()+p2.getY())/2] = new Tile(Tile.PATH);
		} 
	}

	public boolean isGameOver(Maze m) {
		//the end tile will be of type end
		return false;
	}
	
	/**
	 * Determines if the player can make the move they are requesting,
	 * determined by maze size and traversability of the tile. It does not include 
	 * players current location, only that the new location is valid.
	 * @param p - player making the move
	 * @param newLoc - new point to move to
	 * @return true if the move is valid otherwise false
	 */
	public boolean isValidMove(Player p, Point newLoc) {
		// check X is valid
		if (newLoc.getX() < 0 || newLoc.getX() > size) {
			return false;
		}
		// check Y is valid
		if (newLoc.getY() < 0 || newLoc.getY() > size) {
			return false;
		}
		// check player can be on tile
		if (!this.getTile(newLoc).isTraversable()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Get the size of the maze
	 * @return height and width of the maze
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Get the tiles array
	 * @return 2D array of tiles
	 */
	public Tile[][] getTiles() {
		return this.tiles;
	}
	
	/**
	 * Get the tile object located at a point
	 * @param p - point at which the tile is located
	 * @return tile object
	 */
	public Tile getTile(Point p) {
		return this.tiles[p.getX()][p.getY()];
	}
	
	/**
	 * Called whenever a Entity moves. Determines deaths/victory.
	 * @param p - entity that moved
	 */
	public void entityMovementListener(Entity p) {
		// Check if Entity should die
		if (this.getTile(p.getLocation()).isLethal()) {
			// Entity should die TODO
		}
		// Check if player has finished maze
		if (p instanceof Player) {
			if (this.getTile(p.getLocation()).getClassification().equals(Tile.END)) {
				// Player has finished the maze TODO
			}
		}
	}
}

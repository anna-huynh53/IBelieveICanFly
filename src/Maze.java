import java.util.ArrayList;
import java.util.Random;

public class Maze {
	private int size;
	private Tile[][] tiles;
	private static String START = "start";
	private static String PATH = "path";
	private static boolean T = true;
	private static boolean F = false;
	//private Player player;
	
	/**
	 * The constructor to create a new maze object.  
	 * @param size The height/width of the square maze that will be created
	 */
	public Maze(int size) {
		tiles = new Tile[size][size];			
		
		// initialise all tiles to have all edges i.e. all walls
		ArrayList<Point> all = new ArrayList<Point>();
		ArrayList<Point> toVisit = new ArrayList<Point>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				tiles[i][j] = new Tile(PATH);
				boolean[] edges = new boolean[4];
				edges[0] = edges[1] = edges[2] = edges[3] = T;
				tiles[i][j].setEdges(edges);
				Point p = new Point(i, j);
				all.add(p);
				toVisit.add(p);
			}
		}
		
		// start tile
		tiles[0][0].setClassification(START);
		Point startPoint = new Point(0, 0);
		Point curr = startPoint;
		toVisit.remove(curr);
		
		
		Stack<Point> backStack = new Stack<Point>();
		Random rand = new Random();	
		while (!toVisit.isEmpty()) {
			ArrayList<Point> neighbours = new ArrayList<Point>();
			Point p1 = new Point(curr.getX(), curr.getY()-1);
			if (all.contains(p1) && toVisit.contains(p1)) neighbours.add(p1);
			Point p2 = new Point(curr.getX()+1, curr.getY());
			if (all.contains(p2) && toVisit.contains(p2)) neighbours.add(p2);
			Point p3 = new Point(curr.getX(), curr.getY()+1);
			if (all.contains(p3) && toVisit.contains(p3)) neighbours.add(p3);
			Point p4 = new Point(curr.getX()-1, curr.getY());
			if (all.contains(p4) && toVisit.contains(p4)) neighbours.add(p4);

			if (neighbours.size() != 0) {
				Point neighbour = neighbours.get(rand.nextInt(neighbours.size()));
				Tile currTile = tiles[curr.getX()][curr.getY()];
				Tile neighbourTile = tiles[neighbour.getX()][neighbour.getY()];
				backStack.push(curr);
				 boolean[] currEdges = currTile.getEdges();
				 boolean[] neighbourEdges = neighbourTile.getEdges();

				if (neighbour.equals(p1)) {
					currEdges[0] = F;
					currTile.setEdges(currEdges);
					neighbourEdges[2] = F; 
					neighbourTile.setEdges(neighbourEdges);
				} else if (neighbour.equals(p2)) {
					currEdges[1] = F; 
					currTile.setEdges(currEdges);
					neighbourEdges[3] = F; 
					neighbourTile.setEdges(neighbourEdges);	
				} else if (neighbour.equals(p3)) {
					currEdges[2] = F; 
					currTile.setEdges(currEdges);
					neighbourEdges[0] = F; 
					neighbourTile.setEdges(neighbourEdges);	
				} else if (neighbour.equals(p4)) {
					currEdges[3] = F;
					currTile.setEdges(currEdges);
					neighbourEdges[1] = F;
					neighbourTile.setEdges(neighbourEdges);	
				}

				curr = neighbour;
				toVisit.remove(curr);
			} else {
				curr = backStack.pop();
			}
		}
	}		

	public boolean isGameOver(Maze m) {
		//the end tile will be of type endTile
		return false;
	}
	
	/**
	 * Determines if the player can make the move they are requesting.
	 * This is determined by maze size, traversible tiles. It does not include players current location, only that the new location is valid.
	 * @param p The player making the move
	 * @param newLoc The new point
	 * @return Boolean if the move is valid
	 */
	public boolean isValidMove(Player p, Point newLoc) {
		// Check X is valid
		if (newLoc.getX() < 0 || newLoc.getX() > size) {
			return false;
		}
		// Check Y is valid
		if (newLoc.getY() < 0 || newLoc.getY() > size) {
			return false;
		}
		// Check player can be on tile
		if (!this.getTile(newLoc).isTraversable()) {
			return false;
		}
		
		// Move is valid
		return true;
	}
	
	/**
	 * Get the size of the maze
	 * @return The height and width of the maze
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Fuction to show the maze for testing purposes
	 */
	public void showMaze () {
		//Tile playerLoc = player.getLocation();
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (this.tiles[i][j].isTraversable()) {
					if (this.tiles[i][j].getClassification().equals(Tile.PATH)) {
						System.out.println("p ");
				    } else if (i == (size-1)-1 && j == (size-1)-1) {
						System.out.print("D"); // destintation
					} else {
						System.out.print("0 "); // start
					}
					
				} else {
					System.out.print("--");	//represents wall
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Get the tile object located at a point
	 * @param p The point at which the tile is located
	 * @return The tile object
	 */
	public Tile getTile(Point p) {
		return this.tiles[p.getX()][p.getY()];
	}
	
	/**
	 * Called whenever a Entity moves. Determines deaths/victory.
	 * @param p The entity that moved
	 */
	public void entityMovementListener(Entity p) {
		// Check if Entity should die
		if (this.getTile(p.getLocation()).isLethal()) {
			// Entity should die TODO
		}
	}
}

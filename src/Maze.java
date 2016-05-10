import java.util.ArrayList;
import java.util.Random;

public class Maze {
	private int size;
	private Tile[][] tiles;
	private Player player;
	private static String START = "start";
	private static String WALL = "wall";
	private static String PATH = "path";
	private static boolean T = true;
	private static boolean F = false;
	
	/**
	 * The constructor to create a new maze object.  
	 * @param size The height/width of the square maze that will be created
	 */
	public Maze(int size) {
		this.size = size;
		tiles = new Tile[size][size];
		this.player = new Player(size, size);			
		
		// initialise all tiles to be wall tiles
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Tile t = new Tile(WALL, F, F, F);
				tiles[i][j] = t; 
			}
		}
		
		// start tile
		Tile startTile = tiles[0][0];
		startTile.setClassification(START);
		startTile.setTraversable(T);
		
		// tiles that have been visited
		ArrayList<Point> visited = new ArrayList<Point>();
		visited.add(startTile);
		toVisit.remove(startTile);
		
		// choose a random neighbour tile from the current tile from the toVisit
		// list and make it a path. Add the visited. Continue until toVisit is empty.
		Tile curr = startTile;
		Point currPoint = new Point(0, 0);
		Random rand = new Random();
		while (!toVisit.isEmpty()) {
			// find random neighbour from the curr in toVisit list
			ArrayList<Point> neighbours = new ArrayList<Point>();
			Point p1 = new Point(currPoint.getX()+1, currPoint.getY());
			
			Tile t1 = tiles[currX+1][currY]; 
			if (toVisit.contains(t1)) neighbours.add(t1);
			Tile t2 = tiles[currX-1][currY];
			if (toVisit.contains(t2)) neighbours.add(t1);
			Tile t3 = tiles[currX][currY+1];
			if (toVisit.contains(t3)) neighbours.add(t1);
			Tile t4 = tiles[currX][currY];
			if (toVisit.contains(t4)) neighbours.add(t1);
			Tile neighbour = neighbours.get(rand.nextInt(neighbours.size()));
			// set the chosen neighbour to a path
			neighbour.setClassification(PATH);
			neighbour.setTraversable(T);
			visited.add(neighbour);
			toVisit.remove(neighbour);
			curr = neighbours.get(rand.nextInt(neighbours.size()));
		}
	}		
	
	public boolean isGameOver(Maze m) {
		//the end tile will be of type endTile
		return false;
	}
	
	public boolean isValidMove(Player p) {
		return false;
	}
}

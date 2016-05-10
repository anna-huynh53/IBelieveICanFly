import java.util.ArrayList;
import java.util.Random;

public class Maze {
	private int size;
	private Tile[][] tiles;
	private Player player;
	private static String WALL = "wall";
	private static String PATH = "path";
	private static boolean T = true;
	private static boolean F = false;
	
	public Maze(int size) {
		this.size = size;
		tiles = new Tile[size][size];
		this.player = player;		
		
		// all tiles
		ArrayList<Tile> toVisit = new ArrayList<Tile>();
		// initialise all tiles to be wall tiles
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Tile t = new Tile(WALL, F, F, F, i, j);
				tiles[i][j] = t; 
				toVisit.add(t);
			}
		}
		
		// tiles that have been visited
		ArrayList<Tile> visited = new ArrayList<Tile>();
		
		// choose random tile as starting point
		int xLoc = (int)Math.random()*size;
		int yLoc = (int)Math.random()*size;
		Tile startTile = new Tile("start", F, T, F, xLoc, yLoc);
		tiles[xLoc][yLoc] = startTile;
		visited.add(startTile);
		toVisit.remove(startTile);
		
		// choose a random neighbour tile from the current tile from the toVisit
		// list and make it a path. Add the visited. Continue until toVisit is empty.
		Tile curr = startTile;
		Random rand = new Random();
		while (!toVisit.isEmpty()) {
			// find random neighbour from the curr in toVisit list
			ArrayList<Tile> neighbours = new ArrayList<Tile>();
			Tile t1 = tiles[curr.getXLoc()+1][curr.getYLoc()]; 
			if (toVisit.contains(t1)) neighbours.add(t1);
			Tile t2 = tiles[curr.getXLoc()-1][curr.getYLoc()];
			if (toVisit.contains(t2))  neighbours.add(t1);
			Tile t3 = tiles[curr.getXLoc()][curr.getYLoc()+1];
			if (toVisit.contains(t3))  neighbours.add(t1);
			Tile t4 = tiles[curr.getXLoc()][curr.getYLoc()-1];
			if (toVisit.contains(t4))  neighbours.add(t1);
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
	}
	
	public boolean isValidMove(Player p) {
		
	}
}

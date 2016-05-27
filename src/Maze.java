import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

public class Maze {
	private int size;
	private Tile[][] tiles;
	private Point startPoint;
	private boolean gameOver;
	private Player player;
	private MapObjects mapObjects;
	private HUD hud;
	
	public static final String PRIM = "prim";
	public static final String DEPTH = "depth";
	
	private Images allImages;
	public final static int SCALE = 20;
	
	/**
	 * The constructor to create a new maze object
	 * @param seed - used to generate size of the maze (size = 2 * seed + 1)
	 * @param type - the type of maze generation [Maze.PRIM, Maze.DEPTH]
	 */
	public Maze(int seed, String type) {
		
		// the actual board size will be size x 2 + 1 to account for the 
		// border and the walls needed between each of the tiles
		this.size = seed + seed + 1;
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
		
		if (type.equals(Maze.DEPTH)) {
			generateDepthFirstMaze(toVisit);
		} else if (type.equals(Maze.PRIM)) {
			generatePrimsMaze();
		}		
		
		//resize();
		// set start tile at bottom right corner 
		startPoint = new Point(size-2, size-2);
		// set end tile at top left corner
		// (the end can be placed anywhere as there is a path between any
		// 2 points; where it is placed will just affect difficulty)
		tiles[1][1].setClassification(Tile.END);
		gameOver = false;
				
		
		// load images
		allImages = new Images();
		// player is automatically created along with the maze
		createPlayer();
		mapObjects = new MapObjects(this);
		hud = new HUD(this);
	}	
	
	public void resize() {
		Tile[][] newTiles = new Tile[size*3][size*3];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newTiles[i*3][j*3] = tiles[i][j];
				newTiles[i*3+1][j*3] = tiles[i][j];
				newTiles[i*3+2][j*3] = tiles[i][j];
				newTiles[i*3][j*3+1] = tiles[i][j];
				newTiles[i*3+1][j*3+1] = tiles[i][j];
				newTiles[i*3+2][j*3+1] = tiles[i][j];
				newTiles[i*3][j*3+2] = tiles[i][j];
				newTiles[i*3+1][j*3+2] = tiles[i][j];
				newTiles[i*3+2][j*3+2] = tiles[i][j];
	
			}
		}
		this.size = size*3;
		this.tiles = newTiles;		
		
		removeExtraWalls();
		removeExtraWalls();
	}
	
    public void removeExtraWalls() {
		for (int i = 2; i < size-3; i++) {
			for (int j = 2; j < size-3; j++) {
				if (tiles[i][j].getClassification().equals(Tile.WALL)) {
					if (tiles[i][j+1].getClassification().equals(Tile.WALL) &&
						tiles[i][j-1].getClassification().equals(Tile.WALL) &&
						tiles[i][j+2].getClassification().equals(Tile.PATH) &&
						tiles[i][j-2].getClassification().equals(Tile.PATH)) {
						tiles[i][j+1] = new Tile(Tile.PATH);
						tiles[i][j-1] = new Tile(Tile.PATH);
					}
					if (tiles[i+1][j].getClassification().equals(Tile.WALL) &&
						tiles[i-1][j].getClassification().equals(Tile.WALL) &&
						tiles[i+2][j].getClassification().equals(Tile.PATH) &&
						tiles[i-2][j].getClassification().equals(Tile.PATH) ) {
						tiles[i+1][j] = new Tile(Tile.PATH);
						tiles[i-1][j] = new Tile(Tile.PATH);
					}
				}
			}
		}
    }
	
	/**
	 * Generates a maze using depth first search with recursive backtracking.
	 * This produces a maze with longer paths.
	 * @param toVisit - list of all points in the grid
	 */
	public void generateDepthFirstMaze(ArrayList<Point> toVisit) {		
		// start point at (1, 1) is initial point to search from
		Point curr = new Point(1, 1);
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
	 * Generates a maze using Prim's algorithm.
	 * This produces a maze with shorter paths and more deadends. 
	 */
	public void generatePrimsMaze() {
		// points that are part of the maze
		ArrayList<Point> mazePoints = new ArrayList<Point>();
		// add start point at (1, 1) to maze
		Point startPoint = new Point(1, 1);
		mazePoints.add(startPoint);
		
		// list of all walls to check
		ArrayList<Point> wallsToCheck = new ArrayList<Point>();
		// add walls of start tile to wall list
		wallsToCheck.addAll(findAdjacentTiles(startPoint));
		
		Random rand = new Random();	
		while(!wallsToCheck.isEmpty()) {
			Point wall = wallsToCheck.get(rand.nextInt(wallsToCheck.size()));
			// either there is exactly one tile on one side of the wall that is  
			// not part of the maze or not
			ArrayList<Point> unvisitedTiles = unvisitedTilesBesideWall(wall, mazePoints);
			// if there is one unvisited tile next to the wall 
			if (unvisitedTiles.size() == 1) {
				// make the wall a path
				tiles[wall.getX()][wall.getY()] = new Tile(Tile.PATH);
				// add unvisited tile to maze 
				mazePoints.add(unvisitedTiles.get(0));
				// add walls of the tile to walls list
				wallsToCheck.addAll(findAdjacentTiles(unvisitedTiles.get(0)));		
			}
			// remove chosen wall from walls list
			wallsToCheck.remove(wall);
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
	
	/** 
	 * Finds all tiles surrounding a tile. If given a tile that is not a wall,
	 * will return walls surrounding the tile.
	 * @param tile point
	 * @return list of all surrounding tiles
	 */
	public ArrayList<Point> findAdjacentTiles(Point p) {
		ArrayList<Point> tiles = new ArrayList<Point>();
		Point w1 = new Point(p.getX(), p.getY()-1); // north wall
		tiles.add(w1);
		Point w2 = new Point(p.getX()+1, p.getY()); // east wall
		tiles.add(w2);
		Point w3 = new Point(p.getX(), p.getY()+1); // south wall
		tiles.add(w3);
		Point w4 = new Point(p.getX()-1, p.getY()); // west wall
		tiles.add(w4);
		return tiles;
	}	
	
	/**
	 * For a given wall, will return the a list of all adjacent unvisited tiles
	 * that is not a wall
	 * @param wall point
	 * @param visitedPoints - a list of visited points in the maze generation so
	 * 						  far to filter out visited points
	 * @return list of unvisited tiles 
	 */
	public ArrayList<Point> unvisitedTilesBesideWall(Point wall, ArrayList<Point> visitedPoints) {
		ArrayList<Point> adjacentTiles = findAdjacentTiles(wall);
		// from the adjacent tiles, check if the point is valid, not been visited
		// and not a wall and if all satisfied, add to unvisited tiles list
		ArrayList<Point> unvisitedTiles = new ArrayList<Point>();
		for (int i = 0; i < adjacentTiles.size(); i++) {
			Point p = adjacentTiles.get(i);
			int x = p.getX();
			int y = p.getY();
			if (x > 0 && x <= size-1 && y > 0 && y <= size-1 && !visitedPoints.contains(p)) {
				if (!tiles[x][y].getClassification().equals(Tile.WALL)) {
					unvisitedTiles.add(p);
				}
			}
		}
		return unvisitedTiles;	
	}
	
	/**
	 * From a tile, finds the tile that lies beyond a given wall (tile here
	 * refers to the point of the tile, not the object Tile). This method
	 * is needed as the direction of the wall randomly chosen is not known
	 * @param tile - point
	 * @param wall - wall adjoining the point
	 * @return
	 */
	/* May use for something else
	public Point findTileAcrossWall(Point tile, Point wall) {
		Point tileAcrossWall = new Point(0, 0);
		int xDiff = tile.getX() - wall.getX();
		int yDiff = tile.getY() - wall.getY();
		if (yDiff < 0) { // tile to north
			tileAcrossWall = new Point(tile.getX(), tile.getY()-2);
		} else if (xDiff > 0) { //tile to east
			tileAcrossWall = new Point(tile.getX()+2, tile.getY());
		} else if (yDiff > 0) { //tile to south
			tileAcrossWall = new Point(tile.getX(), tile.getY()+2);
		} else if (xDiff < 0) { //tile to west
			tileAcrossWall = new Point(tile.getX()-2, tile.getY());	
		}
		return tileAcrossWall;
	}
	*/
	
/////////////////////////////////////player/////////////////////////////////////
	/**
	 * Create a new player and put it at the start of the maze
	 * @return The player created
	 */
	public void createPlayer() {
		this.player = new Player(this, startPoint);
	}	
	
	/**
	 * Determines if the player can make the move they are requesting,
	 * determined by maze size and traversability of the tile. It does not include 
	 * players current location, only that the new location is valid.
	 * @param p - player making the move
	 * @param newLoc - new point to move to
	 * @return true if the move is valid otherwise false
	 */
	public boolean isValidMove(Entity p, Point newLoc) {
		boolean validMove = true;
		// check X is valid
		if (newLoc.getX() < 0 || newLoc.getX() > this.size) 
			validMove = false;
		// check Y is valid
		if (newLoc.getY() < 0 || newLoc.getY() > this.size) 
			validMove = false;
		// check player can be on tile
		if (!this.getTile(newLoc).isTraversable()) 
			validMove = false;
		return validMove;
	}
	
	/**
	 * Called whenever a player moves. Determines deaths/victory.
	 * @param p - player that moved
	 */
	public void playerMovementListener(Player p) {
		Tile tile = this.getTile(p.getLocation());
		// deal damage from enemies
		for (Entity e : mapObjects.getEnemies()) {
			if (e.getLocation().equals(p.getLocation())) {
				boolean noDamage = false;
				for (int i = 0; i < p.getPowerUps().size(); i++) {
					Item item = p.getPowerUps().get(i);
					if (item instanceof Bubble) {
						noDamage = true;
						p.removePowerUp(item);
					}
				}
				if (!noDamage) p.doDamage(e.getDamage());	
			}
		}
		Item item = tile.getItem();
		if (item instanceof Coin) {
			Coin c = (Coin) item;
			c.playerInteractEvent(p);
			mapObjects.getItems().remove(item);
		}	
		if (item instanceof Bubble) {
			Bubble b = (Bubble) item;
			b.playerInteractEvent(p);
		}
		
		if (p.getHealth() == 0 || tile.getClassification().equals(Tile.END)) {
			gameOver = true;
		}
	}
	
	/**
	 * Checks if the game is over (from a player finishing the game and not because
	 * of death)
	 * @return true if player on end tile otherwise false
	 */
	public boolean isGameOver() {
		return gameOver;
	}
	
	/**
	 * Gets the player object associated with the maze
	 * @return player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Removes the player from the maze
	 */
	public void removePlayer() {
		this.player = null;
	}	
/////////////////////////////////////player/////////////////////////////////////
	
	public void update() {
		mapObjects.update();
	}
	
	public void draw(Graphics g) {
		mapObjects.draw(g);
	}
	
	public void drawMaze(Graphics g) {
		Image ground = allImages.getGround();
		Image edgeLeft = allImages.getEdgeLeft();
		Image edgeRight = allImages.getEdgeRight();
		Image vertical = allImages.getVertical();
		Image end = allImages.getEnd();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Tile tile = tiles[i][j];
				String tileName = tile.getClassification();
				int xScale = i * SCALE;
				int yScale = j * SCALE;
				if (tileName.equals(Tile.WALL)) {
					Point p = new Point(i, j);
					if (i == 0 || j == 0 || i == size-1) {
						g.drawImage(vertical, xScale, yScale, null);
					} else if (isVerticalPiece(p)) {
						g.drawImage(vertical, xScale, yScale, null);
					} else if (isEdgePiece(p) == 1) {
						g.drawImage(edgeLeft, xScale, yScale, null);
					} else if (isEdgePiece(p) == 2) {
						g.drawImage(edgeRight, xScale, yScale, null);
					} else {
						g.drawImage(ground, xScale, yScale, null);
					}
				} else if (tileName.equals(Tile.END)) {
					g.drawImage(end, xScale, yScale, null);
				}
			}
		}
		// for HUD display
		for (int i = 0; i < size; i++) {
			for (int j = size; j < size + 3; j++) {
				g.drawImage(vertical, i * SCALE, j * SCALE, null);
			}
		}
		drawHUD(g);
	}
	
	public void drawDecorations(Graphics g) {
		Random rand = new Random();
		ArrayList<Image> decorations = allImages.getDecorations();
		int i = 0;
		while (i <= 10) {
			int x = rand.nextInt(size-1);
			int y = rand.nextInt(size-1);
			int xScale = x * SCALE;
			int yScale = y * SCALE;
			Tile t = tiles[x][y];
			if (t.getClassification().equals(Tile.PATH) && tiles[x][y+1].getClassification().equals(Tile.WALL)) {
				g.drawImage(decorations.get(rand.nextInt(5)), xScale, yScale, null);
				i++;
			}
		}
	}
	
	public void drawHUD(Graphics g) {
		for (int i = 0; i < size; i++) {
			for (int j = size; j < size + 4; j++) {
				if (i >= 1 && i < 6 && j == size) {
					g.drawImage(hud.getHearts().get(i-1), i * SCALE, j * SCALE, null);
				}
				if (i == 4*size/7 && j == size) {
					g.drawImage(allImages.getScore(), i * SCALE, j * SCALE, null);
				}
				if (i == 4*size/7+3 && j == size) {
					g.drawImage(allImages.getDots(), i * SCALE, j * SCALE, null);
				}
				if (i == 4*size/7+5 && j == size) {
					ArrayList<Image> score = hud.getPlayerScore();
					for (int k = 0; k < score.size(); k++) {
						g.drawImage(hud.getPlayerScore().get(k), (i + k) * SCALE, j * SCALE, null);
					}
				}
				if (i == 1 && j == size+1 && !hud.getPowerUps().isEmpty()) {
					g.drawImage(hud.getPowerUps().get(i-1), i * SCALE, j * SCALE, null);
				}
			}
		}
	}
	
	public boolean isVerticalPiece(Point p) {
		boolean vertical = false;
		Point right = new Point(p.getX()+1, p.getY()); 
		Point left = new Point(p.getX()-1, p.getY()); 
		if (isValidPoint(left) && isValidPoint(right)) {
			if (getTile(left).getClassification().equals(Tile.PATH) &&
				getTile(right).getClassification().equals(Tile.PATH)) {
				vertical = true;
			}
		}
		
		return vertical;
	}
	
	/**
	 * Checks if a tile at a point is an edge piece.
	 * 0: not edge
	 * 1: left edge
	 * 2: right edge
	 * @param point
	 * @return 
	 */
	public int isEdgePiece(Point p) {
		int edgeDirection = 0;
		Point right = new Point(p.getX()+1, p.getY()); 
		Point left = new Point(p.getX()-1, p.getY()); 
		if (isValidPoint(left) && isValidPoint(right)) {
			if (getTile(left).getClassification().equals(Tile.PATH) &&
				getTile(right).getClassification().equals(Tile.WALL)) {
				edgeDirection = 1;
			} else 
			if (getTile(left).getClassification().equals(Tile.WALL) &&
				getTile(right).getClassification().equals(Tile.PATH)) {
				edgeDirection = 2;
			}
		}
		return edgeDirection;
	}	
	
	public boolean isValidPoint(Point p) {
		if (p.getX() < 0 || p.getX() >= size || p.getY() < 0 || p.getY() >= size) return false;
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
	
	public Images getImages() {
		return this.allImages;
	}
}
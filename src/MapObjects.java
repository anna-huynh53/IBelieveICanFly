import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 * The various items and enemies in a maze.
 *
 */
public class MapObjects {
	private Maze maze;
	private ArrayList<Item> items;
	private ArrayList<Entity> enemies;
	
	public MapObjects(Maze m) {
		this.maze = m;
		// place items on the maze
		items = new ArrayList<Item>();
		placePickups();	
		// place enemies
		enemies = new ArrayList<Entity>();
		placeEnemies();
	}
	
	/**
	 * Places random pickups in the maze
	 */
	public void placePickups() {
		Images images = this.maze.getImages();
		Random rand = new Random();
		int size = this.maze.getSize();
		Tile[][] tiles = this.maze.getTiles();
		
		int numItems = 0;
		if (maze.getLevel().equals("easy")) {
			numItems = 8;
		} else if (maze.getLevel().equals("medium")) {
			numItems = 16;
		} else if (maze.getLevel().equals("hard")) {
			numItems = 32;
		}
		
		int i = 0;
		while (i <= numItems) {
			int x = rand.nextInt(size-1);
			int y = rand.nextInt(size-1);
			Tile t = tiles[x][y];
			if (t.getClassification().equals(Tile.PATH) &&
				tiles[x][y+1].getClassification().equals(Tile.WALL) && 
				!(t.getItem() instanceof Item)) {
				Point p = new Point(x, y);
				Item item = new Decoration(p, images.getDecorations().get(rand.nextInt(5)));
				t.setItem(item);
				i++;
			}
		}
		
		i = 0;
		while (i <= numItems) {
			int x = rand.nextInt(size-1);
			int y = rand.nextInt(size-1);
			Tile t = tiles[x][y];
			if (t.getClassification().equals(Tile.PATH) &&
				tiles[x][y+1].getClassification().equals(Tile.WALL) && 
				!(t.getItem() instanceof Item)) {
				Point p = new Point(x, y);
				Item item = new Coin(1, p, images.getCoin());
				int num = rand.nextInt(3);
				if (num == 0) item = new Bubble(p, images.getBubble());
				if (num == 1) item = new Flail(p, images.getFlail());
				if (num == 2) item = new Heart(p, images.getFullHeart());
				t.setItem(item);
				items.add(item);
				i++;
			}
		}
	}
	
	/**
	 * Places random enemies in the maze
	 */
	public void placeEnemies() {
		Random rand = new Random();
		int size = this.maze.getSize();
		Tile[][] tiles = this.maze.getTiles();
		
		int numItems = 0;
		if (maze.getLevel().equals("easy")) {
			numItems = 5;
		} else if (maze.getLevel().equals("medium")) {
			numItems = 10;
		} else if (maze.getLevel().equals("hard")) {
			numItems = 15;
		}
		
		int i = 0;
		while (i < numItems) {
			int x = rand.nextInt(size);
			int y = rand.nextInt(size);
			Tile t = tiles[x][y];
			if (t.getClassification().equals(Tile.PATH)) {
				Point p = new Point(x, y);
				Entity e =  new EvilSun(this.maze, p);
				int enemy = rand.nextInt(3);
				if (enemy == 1) e = new EvilThunder(this.maze, p);
				if (enemy == 2) e = new EvilWingMan(this.maze, p);
				enemies.add(e);
				i++;
			}
		}
	}
	
	/**
	 * Updates the items and enemies
	 */
	public void update() {
		for (Item i : items) {
			i.update();
		}
		for (Entity e : enemies) {
			e.update();
		}
	}
	
	/**
	 * Draws the items and enemies
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < maze.getSize(); i++) {
			for (int j = 0; j < maze.getSize(); j++) {
				if(maze.getTiles()[i][j].getItem() != null) {
					maze.getTiles()[i][j].getItem().draw(g);
				}
			}
		}
		for (Entity e : enemies) {
			e.draw(g);
		}
	}
	
	/**
	 * Gets list of items in maze
	 * @return list of items
	 */
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	/**
	 * Gets list of enemies in maze
	 * @return list of enemies
	 */
	public ArrayList<Entity> getEnemies() {
		return this.enemies;
	}
}
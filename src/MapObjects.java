import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class MapObjects {
	private Maze maze;
	private ArrayList<Item> items;
	private ArrayList<Entity> enemies;
	
	public MapObjects(Maze m) {
		this.maze = m;
		// place random coins on the maze
		items = new ArrayList<Item>();
		placePickups();	
		// place enemies
		enemies = new ArrayList<Entity>();
		placeEnemies();
	}
	
	/**
	 * Places random pickups in the maze
	 * (currently, there are only coins)
	 */
	public void placePickups() {
		Random rand = new Random();
		int size = this.maze.getSize();
		Tile[][] tiles = this.maze.getTiles();
		int i = 0;
		while (i <= 10) {
			int x = rand.nextInt(size);
			int y = rand.nextInt(size);
			Tile t = tiles[x][y];
			if (t.getClassification().equals(Tile.PATH) && !(t.getItem() instanceof Item)) {
				Point p = new Point(x, y);
				Coin c = new Coin(1, p);
				t.setItem(c);
				items.add(c);
				i++;
			}
		}
	}
	
	/**
	 * Places enemies randomly
	 */
	public void placeEnemies() {
		Random rand = new Random();
		int size = this.maze.getSize();
		Tile[][] tiles = this.maze.getTiles();
		int i = 0;
		while (i < 2) {
			int x = rand.nextInt(size);
			int y = rand.nextInt(size);
			Tile t = tiles[x][y];
			if (t.getClassification().equals(Tile.PATH)) {
				Point p = new Point(x, y);
				EvilSun e = new EvilSun(this.maze, p);
				enemies.add(e);
				i++;
			}
		}
	}
	
	public void update() {
		for (Item i : items) {
			i.update();
		}
		for (Entity e : enemies) {
			e.update();
		}
	}
	
	public void draw(Graphics g) {
		for (Item i : items) {
			i.draw(g);
		}
		for (Entity e : enemies) {
			e.draw(g);
		}
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public ArrayList<Entity> getEnemies() {
		return this.enemies;
	}
}
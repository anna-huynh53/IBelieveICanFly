import java.awt.Graphics;
import java.awt.Image;
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
			int x = rand.nextInt(size-1);
			int y = rand.nextInt(size-1);
			Tile t = tiles[x][y];
			if (t.getClassification().equals(Tile.PATH) &&
				tiles[x][y+1].getClassification().equals(Tile.WALL) && 
				!(t.getItem() instanceof Item)) {
				Point p = new Point(x, y);
				Item item = new Coin(1, p);
				int num = rand.nextInt(3);
				t.setItem(item);
				items.add(item);
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
		while (i < 5) {
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
	
	public void update() {
		for (Item i : items) {
			i.update();
		}
		for (Entity e : enemies) {
			e.update();
		}
	}
	
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
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public ArrayList<Entity> getEnemies() {
		return this.enemies;
	}
}
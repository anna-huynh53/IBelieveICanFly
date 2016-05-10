
public class Maze {
	private int size;
	private Tile[][] tiles;
	private Player player;
	
	/**
	 * The constructor to create a new maze object.  
	 * @param size The height/width of the square maze that will be created
	 */
	public Maze(int size) {
		this.size = size;
		tiles = new Tile[size][size];
		this.player = new Player(size, size);		
	}	
	
	public boolean isGameOver(Maze m) {
		//the end tile will be of type endTile
	}
	
	public boolean isValidMove(Player p) {
		
	}
}

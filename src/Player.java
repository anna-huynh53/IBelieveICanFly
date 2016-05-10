import java.util.ArrayList;
public class Player {
	private int locX;
	private int locY;
	private ArrayList<Point> pastLocs;
	
	public Player(int locX, int locY) {
		this.locX = locX;
		this.locY = locY;
		pastLocs = new ArrayList<Point>(); //all past x and y or just trail
	}
	
	public void movePlayer() {
		
	}
	
}

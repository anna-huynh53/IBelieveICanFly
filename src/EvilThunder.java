import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

/**
 * Randomly appears in the maze at timed intervals dealing 50 damage.
 *
 */
public class EvilThunder implements Entity {
	private Maze maze;
	private Point currentLoc;
	private int damage;
	
	public EvilThunder(Maze m) {
		this.maze = m;
		this.damage = 50;		
	}
	
	public void nextLocation() {
		Random rand = new Random();
		Point p = new Point(rand.nextInt(this.maze.getSize()), rand.nextInt(maze.getSize()));
		if (maze.isValidMove(this, p)) {
			currentLoc = p;
		}
	}

	public void update() {
		nextLocation();
	}

	public void draw(Graphics g) {
		g.drawImage(maze.getImages().getThunder(), (int)this.currentLoc.getX() * Maze.SCALE,
				    (int)this.currentLoc.getY() * Maze.SCALE, null);
	}

	public Point getLocation() {
		return this.currentLoc;
	}

	public int getDamage() {
		return this.damage;
	}
}
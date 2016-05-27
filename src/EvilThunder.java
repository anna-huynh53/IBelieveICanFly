import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * An entity inthe form of an evil thunderbolt that randomly appears in the maze 
 * at timed intervals dealing 50 damage.
 *
 */
public class EvilThunder implements Entity {
	private Maze maze;
	private Point currentLoc;
	private int damage;
	
	private Image thunder;
	
	public EvilThunder(Maze m, Point p) {
		this.maze = m;
		this.damage = 10;		
		this.currentLoc = p;
		
		this.thunder = maze.getImages().getThunder();
		
		ActionListener timedMove = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move();
			}
		};
		new javax.swing.Timer(1000, timedMove).start();
	}
	
	public void move() {
		Random rand = new Random();
		Point p = new Point(rand.nextInt(this.maze.getSize()), rand.nextInt(maze.getSize()));
		if (maze.isValidMove(this, p)) {
			currentLoc = p;
		}
	}

	public void update() {} // unused as thunder has no animation

	public void draw(Graphics g) {
		g.drawImage(thunder,this.currentLoc.getX()*Maze.SCALE,this.currentLoc.getY()*Maze.SCALE, null);
	}

	/**
	 * Gets location of thunder
	 * @return location of thunder
	 */
	public Point getLocation() {
		return this.currentLoc;
	}

	/**
	 * Gets amount of damage inflicted by thunder
	 * @return damage value of thunder
	 */
	public int getDamage() {
		return this.damage;
	}
}
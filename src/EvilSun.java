import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * An entity in the form of an evil sun that moves randomly and deals 20 damage.
 *
 */
public class EvilSun implements Entity {
	private Maze maze;
	private Point currentLoc;
	private int damage;
	
	private Animation animate;
	private ArrayList<Image> sun;
	
	public EvilSun(Maze maze, Point loc) {
		this.maze = maze;
		this.currentLoc = loc;
		this.damage = 20;
		
		this.animate = new Animation();
		this.sun = maze.getImages().getSun();
		animate.setFrames(sun);
		animate.setDelay(200);
		
		// moves sun on timed intervals
		ActionListener timedMove = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move();
				update();
			}
		};
		new javax.swing.Timer(500, timedMove).start();
	}
	
	/**
	 * Moves sun to a random valid location
	 */
	public void move() {
		Random rand = new Random();
		ArrayList<Point> locs = new ArrayList<Point>();
		locs.add(new Point(this.currentLoc.getX()-1, this.currentLoc.getY()));
		locs.add(new Point(this.currentLoc.getX()+1, this.currentLoc.getY()));
		locs.add(new Point(this.currentLoc.getX(), this.currentLoc.getY()-1)); 
		locs.add(new Point(this.currentLoc.getX(), this.currentLoc.getY()+1)); 
		Point newLoc = locs.get(rand.nextInt(4));
		if (maze.isValidMove(this, newLoc)) {
			this.currentLoc = newLoc;
		} 
	}
	
	/**
	 * Updates animation of sun
	 */
	public void update() {
		animate.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(animate.getCurrImage(), this.currentLoc.getX()*Maze.SCALE,
				    this.currentLoc.getY()*Maze.SCALE, null);
	}
	
	/**
	 * Gets location of sun
	 * @return location of sun
	 */
	public Point getLocation() {
		return this.currentLoc;
	}
	
	/**
	 * Gets amount of damage inflicted by sun
	 * @return damage value of sun
	 */
	public int getDamage() {
		return this.damage;
	}
}
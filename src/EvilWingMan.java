import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class EvilWingMan implements Entity {
	private Maze maze;
	private Point currentLoc;
	private int damage;
	
	private Animation animate;
	private ArrayList<Image> wingMan;
	
	public EvilWingMan(Maze maze, Point loc) {
		this.maze = maze;
		this.currentLoc = loc;
		this.damage = 10;
		
		this.animate = new Animation();
		this.wingMan = maze.getImages().getWingMan();
		animate.setFrames(wingMan);
		animate.setDelay(200);
		
		ActionListener timedMove = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move();
				update();
			}
		};
		new javax.swing.Timer(600, timedMove).start();
	}
	
	/**
	 * Changes the location of the thunder to a random valid new location
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
	 * Updates animation of wingman
	 */
	public void update() {
		animate.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(animate.getCurrImage(), this.currentLoc.getX()*Maze.SCALE,
				    this.currentLoc.getY()*Maze.SCALE, null);
	}
	
	/**
	 * Gets location of wingman
	 * @return location of wingman
	 */
	public Point getLocation() {
		return this.currentLoc;
	}
	
	/**
	 * Gets amount of damage inflicted by wingman
	 * @return damage value of wingman
	 */
	public int getDamage() {
		return this.damage;
	}
}
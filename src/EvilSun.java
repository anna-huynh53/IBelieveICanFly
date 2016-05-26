import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class EvilSun implements Entity {
	private Maze maze;
	private Point currentLoc;
	private int damage;
	
	private Animation animate;
	private ArrayList<Image> sun;
	
	public EvilSun(Maze maze, Point loc) {
		this.maze = maze;
		this.currentLoc = loc;
		this.damage = 10;
		
		ActionListener timedMove = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move();
				animate.update();
			}
		};
		new javax.swing.Timer(300, timedMove).start();
		
		animate = new Animation();
		sun = maze.getImages().getSun();
		animate.setFrames(sun);
	}
	
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
	
	public void update() {
	}
	
	public void draw(Graphics g) {
		g.drawImage(animate.getCurrImage(), this.currentLoc.getX()*Maze.SCALE,
				    this.currentLoc.getY()*Maze.SCALE, null);
	}
	
	public Point getLocation() {
		return this.currentLoc;
	}
	
	public int getDamage() {
		return this.damage;
	}
}
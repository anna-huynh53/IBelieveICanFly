import java.awt.Image;
import java.awt.Toolkit;


public class Images {
	private Image ground;
	private Image edgeLeft;
	private Image edgeRight;
	private Image wall;
	
	private Image sun;
	private Image thunder;
	
	public Images() {
		loadImages();
	}
	
	public void loadImages() {
		Toolkit t = Toolkit.getDefaultToolkit();
		ground = t.getImage("res/ground.png");
		edgeLeft = t.getImage("res/edgeLeft.png");
		edgeRight = t.getImage("res/edgeRight.png");
		wall = t.getImage("res/path.png");
		
		sun = t.getImage("res/evilSun.png");
		thunder = t.getImage("res/thunder.png");
	}
	
	public Image getGround() {
		return this.ground;
	}
	
	public Image getEdgeLeft() {
		return this.edgeLeft;
	}
	
	public Image getEdgeRight() {
		return this.edgeRight;
	}
	
	public Image getWall() {
		return this.wall;
	}
	
	public Image getSun() {
		return this.sun;
	}
	
	public Image getThunder() {
		return this.thunder;
	}
}
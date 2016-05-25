import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

public class Images {
	private Toolkit t; 
	
	private Image ground;
	private Image edgeLeft;
	private Image edgeRight;
	private Image vertical;
	private Image wall;
	
	private ArrayList<Image> sun;
	private Image thunder;
	
	private ArrayList<Image> walkLeft;
	private ArrayList<Image> walkRight;
	private ArrayList<Image> jump;
	private ArrayList<Image> fall;
	private ArrayList<Image> idle;
	private ArrayList<Image> hit;
	private ArrayList<Image> dead;
	
	
	public Images() {
		t = Toolkit.getDefaultToolkit();
		loadTiles();
		loadPickUps();
		loadEnemies();
		loadPlayer();
	}
	
	public void loadTiles() {
		String colour = randTile();
		ground = t.getImage("res/tiles/"+colour+"/ground.png");
		edgeLeft = t.getImage("res/tiles/"+colour+"/edgeLeft.png");
		edgeRight = t.getImage("res/tiles/"+colour+"/edgeRight.png");
		vertical = t.getImage("res/tiles/"+colour+"/vertical.png");
		wall = t.getImage("res/tiles/path.png");
	}
	
	public String randTile() {
		String tile = "";
		Random rand = new Random();
		int i = rand.nextInt(4);
		if (i == 0) tile = "blue";
		if (i == 1) tile = "brown";
		if (i == 2) tile = "green";
		if (i == 3) tile = "pink";
		if (i == 4) tile = "yellow";
		return tile;
	}
	
	public void loadPickUps() {
		
	}
	
	public void loadEnemies() {
		sun = new ArrayList<Image>();
		sun.add(t.getImage("res/enemies/evilSun1.png"));
		sun.add(t.getImage("res/enemies/evilSun2.png"));
		
		thunder = t.getImage("res/enemies/thunder.png");
	}
	
	public void loadPlayer() {		
		// chooses a random player colour and loads player movement images
		String player = randPlayer();
		walkLeft = new ArrayList<Image>();
		walkLeft.add(t.getImage("res/players/"+player+"/walkLeft1.png"));
		walkLeft.add(t.getImage("res/players/"+player+"/walkLeft2.png"));
		walkLeft.add(t.getImage("res/players/"+player+"/walkLeft3.png"));
		walkLeft.add(t.getImage("res/players/"+player+"/walkLeft4.png"));
		
		walkRight = new ArrayList<Image>();
		walkRight.add(t.getImage("res/players/"+player+"/walkRight1.png"));
		walkRight.add(t.getImage("res/players/"+player+"/walkRight2.png"));
		walkRight.add(t.getImage("res/players/"+player+"/walkRight3.png"));
		walkRight.add(t.getImage("res/players/"+player+"/walkRight4.png"));
		
		jump = new ArrayList<Image>();
		jump.add(t.getImage("res/players/"+player+"/up1.png"));
		jump.add(t.getImage("res/players/"+player+"/up2.png"));
		jump.add(t.getImage("res/players/"+player+"/up3.png"));
		
		fall = new ArrayList<Image>();
		fall.add(t.getImage("res/players/"+player+"/fall.png"));
		idle = new ArrayList<Image>();
		idle.add(t.getImage("res/players/"+player+"/idle.png"));
		hit = new ArrayList<Image>();
		hit.add(t.getImage("res/players/"+player+"/hit.png"));
		dead = new ArrayList<Image>();
		dead.add(t.getImage("res/players/"+player+"/dead.png"));
	}
	
	public String randPlayer() {
		String player = "";
		Random rand = new Random();
		int i = rand.nextInt(3);
		if (i == 0) player = "blue";
		if (i == 1) player = "green";
		if (i == 2) player = "grey";
		if (i == 3) player = "red";
		return player;
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
	
	public Image getVertical() {
		return this.vertical;
	}
	
	public Image getWall() {
		return this.wall;
	}
	
	public ArrayList<Image> getSun() {
		return this.sun;
	}
	
	public Image getThunder() {
		return this.thunder;
	}
	
	public ArrayList<Image> getWalkLeft() {
		return this.walkLeft;
	}
	
	public ArrayList<Image> getWalkRight() {
		return this.walkRight;
	}
	
	public ArrayList<Image> getJump() {
		return this.jump;
	}
	
	public ArrayList<Image> getFall() {
		return this.fall;
	}
	
	public ArrayList<Image> getIdle() {
		return this.idle;
	}
	
	public ArrayList<Image> getHit() {
		return this.hit;
	}
	
	public ArrayList<Image> getDead() {
		return this.dead;
	}
}
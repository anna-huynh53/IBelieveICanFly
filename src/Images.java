import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

/**
 * Loads all images needed to draw a maze and its components.
 *
 */
public class Images {
	private Toolkit t; 
	
	private Image ground;
	private Image edgeLeft;
	private Image edgeRight;
	private Image vertical;
	private Image end;
	
	private ArrayList<Image> decorations;
	
	private ArrayList<Image> coin;
	private Image bubble;	
	private Image bubblePower;
	
	private ArrayList<Image> sun;
	private ArrayList<Image> wingMan;
	private Image thunder;
	
	private ArrayList<Image> walkLeft;
	private ArrayList<Image> walkRight;
	private ArrayList<Image> jump;
	private ArrayList<Image> fall;
	private ArrayList<Image> idle;
	private ArrayList<Image> hit;
	private ArrayList<Image> dead;
	
	private Image fullHeart;
	private Image halfHeart;
	private Image emptyHeart;
	private Image score;
	private Image dots;
	private ArrayList<Image> numbers;
	private Image settingButton;
	
	public Images() {
		t = Toolkit.getDefaultToolkit();
		loadTiles();
		loadMazeItems();
		loadPickUps();
		loadEnemies();
		loadPlayer();
		loadHUD();
	}
	
	public void loadTiles() {
		String colour = randTile();
		ground = t.getImage("res/tiles/"+colour+"/ground.png");
		edgeLeft = t.getImage("res/tiles/"+colour+"/edgeLeft.png");
		edgeRight = t.getImage("res/tiles/"+colour+"/edgeRight.png");
		vertical = t.getImage("res/tiles/"+colour+"/vertical.png");
		end = t.getImage("res/tiles/end.png");
	}
	
	public void loadMazeItems() {
		decorations = new ArrayList<Image>();
		decorations.add(t.getImage("res/decoration/dec1.png"));
		decorations.add(t.getImage("res/decoration/dec2.png"));
		decorations.add(t.getImage("res/decoration/dec3.png"));
		decorations.add(t.getImage("res/decoration/dec4.png"));
		decorations.add(t.getImage("res/decoration/dec5.png"));
		decorations.add(t.getImage("res/decoration/dec6.png"));
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
		coin = new ArrayList<Image>();
		coin.add(t.getImage("res/pickups/coin1.png"));
		coin.add(t.getImage("res/pickups/coin2.png"));
		coin.add(t.getImage("res/pickups/coin3.png"));
		coin.add(t.getImage("res/pickups/coin4.png"));
		
		bubble = t.getImage("res/pickups/bubble.png");
		bubblePower = t.getImage("res/hud/bubblePower.png");
	}
	
	public void loadEnemies() {
		sun = new ArrayList<Image>();
		sun.add(t.getImage("res/enemies/evilSun1.png"));
		sun.add(t.getImage("res/enemies/evilSun2.png"));
		
		wingMan = new ArrayList<Image>();
		wingMan.add(t.getImage("res/enemies/wingMan1.png"));
		wingMan.add(t.getImage("res/enemies/wingMan2.png"));
		wingMan.add(t.getImage("res/enemies/wingMan3.png"));
		wingMan.add(t.getImage("res/enemies/wingMan4.png"));
		
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
	
	public void loadHUD() {
		fullHeart = t.getImage("res/hud/fullHeart.png");
		halfHeart = t.getImage("res/hud/halfHeart.png");
		emptyHeart = t.getImage("res/hud/emptyHeart.png");
		score = t.getImage("res/hud/score.png");
		dots = t.getImage("res/hud/dots.png");
		
		numbers = new ArrayList<Image>();
		numbers.add(t.getImage("res/hud/0.png"));
		numbers.add(t.getImage("res/hud/1.png"));
		numbers.add(t.getImage("res/hud/2.png"));
		numbers.add(t.getImage("res/hud/3.png"));
		numbers.add(t.getImage("res/hud/4.png"));
		numbers.add(t.getImage("res/hud/5.png"));
		numbers.add(t.getImage("res/hud/6.png"));
		numbers.add(t.getImage("res/hud/7.png"));
		numbers.add(t.getImage("res/hud/8.png"));
		numbers.add(t.getImage("res/hud/9.png"));
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
	
	public Image getEnd() {
		return this.end;
	}
	
	public ArrayList<Image> getDecorations() {
		return this.decorations;
	}
	
	public ArrayList<Image> getCoin() {
		return this.coin;
	}
	
	public Image getBubble() {
		return this.bubble;
	}
	
	public Image getBubblePower() {
		return this.bubblePower;
	}
	
	public ArrayList<Image> getSun() {
		return this.sun;
	}
	
	public ArrayList<Image> getWingMan() {
		return this.wingMan;
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
	
	public Image getFullHeart() {
		return this.fullHeart;
	}
	
	public Image getHalfHeart() {
		return this.halfHeart;
	}
	
	public Image getEmptyHeart() {
		return this.emptyHeart;
	}
	
	public Image getScore() {
		return this.score;
	}
	
	public Image getDots() {
		return this.dots;
	}
	
	public ArrayList<Image> getNumbers() {
		return this.numbers;
	}
	
	public Image getSettingButton() {
		return this.settingButton;
	}
}
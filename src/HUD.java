import java.awt.Image;
import java.util.ArrayList;

public class HUD {
	private Maze maze;
	private Image fullHeart;
	private Image halfHeart;
	private Image emptyHeart;
	
	public HUD(Maze m) {
		this.maze = m;
		this.fullHeart = maze.getImages().getFullHeart();
		this.halfHeart = maze.getImages().getHalfHeart();
		this.emptyHeart = maze.getImages().getEmptyHeart();
	}
	
	public ArrayList<Image> getHearts() {
		ArrayList<Image> hearts = new ArrayList<Image>();
		int health = maze.getPlayer().getHealth();
		int maxHealth = maze.getPlayer().getMaxHealth();
		int fullHearts = (int) health / 20;
		int halfHearts = (int) health % 20 / 10;
		int emptyHearts = (int) ((maxHealth-health) / 20); 
		int i = 0;
		while (i < fullHearts) {
			hearts.add(fullHeart);
			i++;
		}
		i = 0;
		while (i < halfHearts) {
			hearts.add(halfHeart);
			i++;
		}
		i = 0;
		while (i < emptyHearts) {
			hearts.add(emptyHeart);
			i++;
		}
		return hearts;
	}
	
	public ArrayList<Image> getPlayerScore() {
		ArrayList<Image> score = new ArrayList<Image>();

		Integer currScore = (Integer) (maze.getPlayer().getScore());
		char[] scoreArray = currScore.toString().toCharArray();
		for (int i = 0; i < scoreArray.length; i++) {
			score.add(maze.getImages().getNumbers().get(Integer.parseInt(String.valueOf(scoreArray[i]))));
		}
		return score;
	}
}
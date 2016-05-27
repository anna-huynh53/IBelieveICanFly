import java.awt.Image;
import java.util.ArrayList;

/**
 * Finds player's information including health, score and powerups.
 *
 */
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
	
	/**
	 * Gets the heart images to draw to represent the player's current health
	 * @return list of heart images 
	 */
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
	
	/**
	 * Gets the number images to draw to represnt the player's current score
	 * @return list of number images
	 */
	public ArrayList<Image> getPlayerScore() {
		ArrayList<Image> score = new ArrayList<Image>();
		// probs should find better way of getting the individual number
		Integer currScore = (Integer) (maze.getPlayer().getScore());
		char[] scoreArray = currScore.toString().toCharArray();
		for (int i = 0; i < scoreArray.length; i++) {
			score.add(maze.getImages().getNumbers().get(Integer.parseInt(String.valueOf(scoreArray[i]))));
		}
		return score;
	}
	
	/**
	 * Gets the powerup icons to draw to represent the player's available power ups
	 * @return list of powerup icons
	 */
	public ArrayList<Image> getPowerUps() {
		ArrayList<Image> powers = new ArrayList<Image>();
		Image bubblePower = maze.getImages().getBubblePower();
		for (Item i : this.maze.getPlayer().getPowerUps()) {
			if (i instanceof Bubble) {
				powers.add(bubblePower);
			}
		}
		return powers;	
	}
}
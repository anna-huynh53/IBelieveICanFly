import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	private Maze maze;

	public GamePanel(Maze m) {
		this.maze = m;
	}

	// set the required maze to be displayed on the panel
	public void setMaze(Maze m) {
		this.maze = m;
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		drawMaze(page);
	}

	public Maze getMaze() {
		return this.maze;
	}
	
	/**
	 * Draws the maze in its current state (maybe go in the UI?)
	 */
	private void drawMaze(Graphics g) {
		// read in the images when the paint method is called (does this slow
		// down rendering?? Maybe we should initialise images once in the
		// constructor)
		Image wallImage = Toolkit.getDefaultToolkit().getImage("res//wall.png");
		Image pathImage = Toolkit.getDefaultToolkit().getImage("res//path.png");
		Image endImage = Toolkit.getDefaultToolkit().getImage("res//end.png");
		Image spriteImage = Toolkit.getDefaultToolkit().getImage("res//sprite.png");

		// draw the environment
		for (int i = 0; i < maze.getSize(); i++) {
			for (int j = 0; j < maze.getSize(); j++) {
				if (maze.getTiles()[i][j].getClassification().equals(Tile.WALL)) {
					g.drawImage(wallImage, i * wallImage.getWidth(null), j * wallImage.getHeight(null), null);
				} else if (maze.getTiles()[i][j].getClassification().equals(Tile.PATH)) {
					g.drawImage(pathImage, i * 20, j * 20, null);
				} else if (maze.getTiles()[i][j].getClassification().equals(Tile.START)) {
					g.drawImage(pathImage, i * 20, j * 20, null);
				} else if (maze.getTiles()[i][j].getClassification().equals(Tile.END)) {
					g.drawImage(endImage, i * 20, j * 20, null);
				}
			}
			// draw the character
			g.drawImage(spriteImage, maze.getPlayer().getLocation().getX() * 20,
					maze.getPlayer().getLocation().getY() * 20, null);
		}
	}
	
	
	
}

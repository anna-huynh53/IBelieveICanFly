import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	private Maze maze;
	private JButton restart = new JButton("Restart");
	private JButton help = new JButton("Help");
	private JButton back = new JButton("Back To Menu");

	public GamePanel(Maze m) {
		this.maze = m;
		restart.setAlignmentX(Component.CENTER_ALIGNMENT);
	    help.setAlignmentX(Component.CENTER_ALIGNMENT);
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // sets fonts
	    restart.setFont(new Font("Impact",1,18));
	    help.setFont(new Font("Impact",1,18));
	    back.setFont(new Font("Impact",1,18));
	    
	    // sets button size
	    Dimension buttonSize = new Dimension(90,20);
	    restart.setMaximumSize(buttonSize);
	    help.setMaximumSize(buttonSize);
	    back.setMaximumSize(buttonSize);
	    
	    // add the buttons to the panel with input blank area as spacing
	    add(restart);
	    add(help);   
	    add(back);
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		drawMaze(page);
	}
	
	/**
	 * Draws the maze in its current state (maybe go in the UI?)
	 */
	private void drawMaze(Graphics g) {
		Image wallImage = Toolkit.getDefaultToolkit().getImage("res//wall.png");
		Image pathImage = Toolkit.getDefaultToolkit().getImage("res//path.png");
		Image endImage = Toolkit.getDefaultToolkit().getImage("res//end.png");
		Image spriteImage = Toolkit.getDefaultToolkit().getImage("res//sprite.png");

		// draw the environment
		for (int i = 0; i < maze.getSize(); i++) {
			for (int j = 0; j < maze.getSize(); j++) {
				if (maze.getTiles()[i][j].getClassification().equals(Tile.WALL)) {
					g.drawImage(wallImage, i * 20, j * 20, null);
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
	
	public Image scaleImage(Image img, int width, int height) {
		Image newImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return newImg;
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public JButton getRestartButton() {
		return this.restart;
	}

	public JButton getHelpButton() {
		return this.help;
	}
	
	public JButton getBackButton() {
		return this.back;
	}
	
	// set the required maze to be displayed on the panel
	public void setMaze(Maze m) {
		this.maze = m;
	}
}
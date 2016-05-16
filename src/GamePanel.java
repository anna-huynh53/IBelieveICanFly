import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GamePanel extends JPanel {
	private Maze maze;
	private JButton back = new JButton("Back to Menu");
	
	public GamePanel(Maze m) {
		this.maze = m;
		add(back);
	}

	public JButton getBackButton() {
		return this.back;
	}
	
	//set the required maze to be displayed on the panel
	public void setMaze(Maze m) {
		this.maze = m;
	}
	
	public void paint(Graphics page) {
		super.paint(page);
		maze.drawMaze(page);
	}
}

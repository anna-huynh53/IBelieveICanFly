import javax.swing.JFrame;

/**
 * displays maze
 * will take in a size and output a maze
 * ..
 */
public class Game {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Amazing Maze Game");
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

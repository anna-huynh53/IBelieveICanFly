import javax.swing.JFrame;

/**
 * displays maze
 * will take in a size and output a maze
 * ..
 */
public class Gui {
	
	public Gui(Maze m) {
		
		JFrame frame = new JFrame("Amazing Maze Game");
		frame.setSize(m.getSize(), m.getSize());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class mazeGui {

	private JFrame frame;
	private JPanel mazeGrid;
	private JLayeredPane[][] mazeGridComp;
	private Dimension boxSize;
	
	public mazeGui(Tile[][] tiles, int size) {
		mazeGuiMaker(tiles, size);
	}
	
	//public static void main (String[] args) {
		//mazeGui f = new mazeGui();
		//mainMenu.display();
	//}
	
	public void mazeGuiMaker(Tile[][] tiles, int size) {
		
		frame = new JFrame("What");
		frame.setSize(400,400);
		frame.setVisible(true);
		
		mazeGrid = new JPanel();
		mazeGridComp = new JLayeredPane[size][size];
		boxSize = new Dimension(frame.getWidth()/size, frame.getHeight()/size);
		
		//mazeGrid.setLayout(new GridLayout(size, size));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		for (int col = 0; col < size; col++) {
			gbc.gridy = col; 
            for (int row = 0; row < size; row++) {
            	gbc.gridx = row;
            	
            	JLayeredPane box = new JLayeredPane();
            	mazeGridComp[row][col] = box;
            	
            	box.setPreferredSize(boxSize);
				box.setLayout(new OverlayLayout(box));
				
				if (tiles[row][col].getClassification().equals(Tile.WALL)) {
					//box.setBackground(Color.BLACK);
					box.add(new Button("1"));
				} else {
					//box.setBackground(Color.gray);
				}
				
				mazeGrid.add(box, gbc);
                
            }
        }
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;
		frame.add(mazeGrid, gbc);
		frame.pack();
		
	}
	
}

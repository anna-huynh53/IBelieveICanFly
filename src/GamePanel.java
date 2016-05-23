import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {
	private GameState gameState; 
	
	private boolean running;

	private JButton restart = new JButton("Restart");
	private JButton help = new JButton("Help");
	private JButton back = new JButton("Back To Menu");
	
	public GamePanel() {		
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
	   
	    initKeyBindings(); 
	}	
	
	public void run() {
		long start;
		long elapsed;
		long wait;
		
		while(running) {
			start = System.nanoTime();
			elapsed = System.nanoTime() - start;
			wait = 1000/60 - elapsed/1000000;
			update();
			repaint();
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		gameState.update();
	}
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameState.draw(g);
    }

    public void initKeyBindings() {
        InputMap im = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = this.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

        am.put("left", new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		gameState.getPlayer().setLeft(true);
        	}
        });
        am.put("right", new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		gameState.getPlayer().setRight(true);
        	}
        });
        am.put("up", new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		gameState.getPlayer().setUp(true);
        	}
        });
        am.put("down", new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		gameState.getPlayer().setDown(true);
        	}
        });
    }
    
/*
////////////////////////////////////////////////////////////////////////////////
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		Player player = gameState.getPlayer();
		if (key == KeyEvent.VK_LEFT) player.setLeft(true);
		if (key == KeyEvent.VK_RIGHT) player.setRight(true);
		if (key == KeyEvent.VK_UP) player.setJumping(true);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		Player player = gameState.getPlayer();
		if (key == KeyEvent.VK_LEFT) player.setLeft(false);
		if (key == KeyEvent.VK_RIGHT) player.setRight(false);
		if (key == KeyEvent.VK_UP) player.setJumping(false);
	}

	public void keyTyped(KeyEvent e) {}
////////////////////////////////////////////////////////////////////////////////
*/	
	public GameState getGameState() {
		return this.gameState;
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
	
	public void setGameState(String level) {
		this.gameState = new GameState(level);
	}
	
	public void setRunning(Boolean b) {
		this.running = b;
	}
}
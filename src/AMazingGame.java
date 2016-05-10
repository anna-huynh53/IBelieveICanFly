
public class AMazingGame {

	public static void main(String args[]) {
		int gameScore;
		// take to GUI handler
		// UserInput i = new UserInput();
		runGame();		
	}
	
	public void runGame() {
		// should this be called from amazGame or gui?
		// should rungame be given an int size from gui?
		Maze currentGame = new Maze(size);
		while(!isGameOver(game)) {
			// arrow key input 
			// using keylistener and keyEvents?

			/* something along these lines? e.g. 
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
        			currentGame.move(left);
    			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        			currentGame.move(right);
			else if (e.getKeyCode() == KeyEvent.VK_UP)
				currentGame.move(up);
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        			currentGame.move(down);		
			*/
		}
		generateScore(currentGame);
		// then take to GUI for button press to exit or restart?
	}
	
	public int generateScore(Maze m) {
		return 0;
		
	}
	
	public void restartGame(Maze m) {
		m = new Maze(size); // are we deleting or making new maze?
	}
}


public class AMazingGame {
	
	/**
	 * AmazingGame containing main starting point for the maze game
	 * handles running of the game, but lets GUI handle input
	 * keeps track of score and game state?
	 */
	public static main(String args[]) {
		int gameScore;
		// take to GUI handler
		// UserInput i = new UserInput();
		runGame();		
	}
	

	/**
	 * called from GUI
	 * creates maze from given size, input from user
	 * runs until game over or early termination
	 * @param legal maze size
	 */
	public void runGame(int size) {
		// should this be called from amazGame or gui?
		// should rungame be given an int size from gui?
		// create maze here or somewhere else??
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
	
	/**
	 * given maze, calculate score from time and number of moves
	 * @return integer score
	 */
	public int generateScore(Maze m) {
		return 0;
		
	}
	
	/**
	 * restarting the game
	 * creates a new maze
	 * @return new maze
	 */	
	public Maze restartGame(int size) {
		m = new Maze(size);
	}
}

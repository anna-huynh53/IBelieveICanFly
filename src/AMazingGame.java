
public class AMazingGame {
	
	
	//private static Gui guiFrame;
	@SuppressWarnings("unused")
	private Maze m;
	
	public static void main (String args[]) {
		
		Maze m =  new Maze(30, Maze.DEPTH);
		runGame(m);
		
	}
	
	
	public static void runGame(Maze m) {
		// this initiates the gui
		Gui mainMenu = new Gui();
		mainMenu.display();
		//guiFrame = new Gui(m);
	}
	
}	
	
//	private mazeUI //GUI field/s??
//	
//	//enum  MAZE_SIZES - DEFAULT_SIZE, SMALL_SIZE, MED_SIZE, LARGE_SIZE
//	public AMazingGame() {
//		this.mazeUI = new mazeUI();
//	}
//
//	public static void main(String args[]) {
//		// take to GUI handler
//		// UserInput i = new UserInput();
//		AMazingGame master = new AMazingGame();
//		int currMazeSize;
//		int currMazeScore; //Score object instead?
//		/* while (true) { //program is running
//			currMazeSize = master.chooseSize();
//			currMazeScore = runGame(currMazeSize);
//			displayScore(currMazeScore);
//			clearGame(master);
//		}*/
//	}
//	
//	public void runGame(int size) { //return 'Score' object instead ?? would contain time, movements made, points(from objects) etc.
//		Maze maze = new Maze(size);
//		//dislay maze
//		
//		while(!maze.isComplete()) {
//		// should this be called from amazGame or gui?
//		// should rungame be given an int size from gui?
//			// arrow key input 
//			// using keylistener and keyEvents?
//
//			/* something along these lines? e.g. 
//			if (e.getKeyCode() == KeyEvent.VK_LEFT)
//        			currentGame.move(left);
//    			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
//        			currentGame.move(right);
//			else if (e.getKeyCode() == KeyEvent.VK_UP)
//				currentGame.move(up);
//			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
//        			currentGame.move(down);		
//			*/
//		}
//		//generateScore(currentGame);
//		// then take to GUI for button press to exit or restart?
//	}
//	
//	public int generateScore(Maze m) {
//		return 0;
//	
//	/*public void displayScore(Score s) { displays score object instead 
//		//grabs the data from the score object and displays it onScreen
//		//could add highScore functionality? - read in saved scores from a protected local file?
//		
//		//if (on-screen button is pressed) return;
//	*/
//		
//	}
//		
//	}
//	//public void restartGame(Maze m) {
//	//	m = new Maze(size); // are we deleting or making new maze?
//	//}
//	
//	/*	
//	private int chooseSize() {
//		int mazeSize = DEFAULT_SIZE;
//		//Displays the choice of maze sizes
//		//Waits for the user to select one
//		//Returns the selected size
//		return 
//	}
//	*/
//}

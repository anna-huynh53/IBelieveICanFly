
public class Tester {
	public static void main(String args[]) {
		int seed = 15;
		Maze m = new Maze(seed, Maze.PRIM);
		m.printMaze();
	}
}

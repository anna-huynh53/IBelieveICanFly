
public class Tester {
	public static void main(String args[]) {
		int size = 10;
		Maze m = new Maze(size);
		Tile[][] tiles = m.getTiles();
		
		for (int i = 0; i < size; i++) {
			// draw north edge
			for (int j = 0; j < size; j++) {
				boolean[] edges = tiles[j][i].getEdges();
				System.out.print(edges[0] == true ? "*---" : "*   ");
			}
			System.out.println("*");
			// draw west edge
			for (int j = 0; j < size; j++) {
				boolean[] edges = tiles[j][i].getEdges();
				if (tiles[i][j].getClassification().equals("start")) 
					System.out.print(edges[3] == true ? "| S " : "  S ");
				else 
					System.out.print(edges[3] == true ? "|   " : "    ");
			}
			System.out.println("|");
		}
		// draw bottom line
		for (int j = 0; j < size; j++) {
			System.out.print("*---");
		}
		System.out.println("*");
	}
}

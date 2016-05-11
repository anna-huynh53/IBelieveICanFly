
public class Tester {
	public static void main(String args[]) {
		int size = 10;
		Maze m = new Maze(size);
		int boardSize = size + size + 1;
		Tile[][] tiles = m.getTiles();
		
		int i = 0; int j;
		while (i < boardSize) {
			j = 0;
			while (j < boardSize) {
				if (tiles[i][j].getClassification().equals(Tile.WALL)) {
					System.out.print(" X ");
				} else if (tiles[i][j].getClassification().equals(Tile.PATH)) {
					System.out.print(" . ");
				} else if (tiles[i][j].getClassification().equals(Tile.START)) {
					System.out.print(" S ");
				}
				j++;
			}
			System.out.println("");
			i++;
		}
	}
}


public class Tester {
	public static void main(String args[]) {
		int seed = 5;
		Maze m = new Maze(seed);
		int size = m.getSize();
		Tile[][] tiles = m.getTiles();
		
		int i = 0; int j;
		while (i < size) {
			j = 0;
			while (j < size) {
				if (tiles[i][j].getClassification().equals(Tile.WALL)) {
					System.out.print(" X ");
				} else if (tiles[i][j].getClassification().equals(Tile.PATH)) {
					System.out.print(" . ");
				} else if (tiles[i][j].getClassification().equals(Tile.START)) {
					System.out.print(" S ");
				} else if (tiles[i][j].getClassification().equals(Tile.END)) {
					System.out.print(" E ");
				}
				j++;
			}
			System.out.println("");
			i++;
		}
	}
}

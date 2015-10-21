package gameOfLifeSpecimens;

public class WalledGarden extends Garden {

	public WalledGarden(int width, int height) throws Exception {
		super(width, height);
	}
	
	@Override
	// This is a nice compact approach for a walled garden.
	protected int neighborsForSpecimentAt(int x, int y)  {
		int neighbors = 0;
		int xmin = x == 0 ? x : x-1;
		int xmax = x == WIDTH-1 ? x : x+1;
		int ymin = y == 0 ? y : y-1;
		int ymax = y == HEIGHT-1 ? y : y+1;
		for (int i = xmin; i <= xmax; i++) {
			for (int j = ymin; j <= ymax; j++) {
				if (!(i == x && j == y) && garden[i][j].isAlive) {	
					neighbors++;
				}
			}
		}
		return neighbors;
	}
}

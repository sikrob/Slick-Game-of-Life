package gameOfLifeSpecimens;

public class WrappedGarden extends Garden {

	public WrappedGarden(int width, int height) throws Exception {
		super(width, height);
	}
	
	@Override
	// Less compact but effective approach for wrapped garden.
	protected int neighborsForSpecimentAt(int x, int y) {
		int neighbors = 0;
		int[] xValues = new int[3];
		int[] yValues = new int[3];
		xValues[0] = x == 0 ? WIDTH-1 : x-1;
		xValues[1] = x;
		xValues[2] = x == WIDTH-1 ? 0 : x+1;
		yValues[0] = y == 0 ? HEIGHT-1 : y-1;
		yValues[1] = y;
		yValues[2] = y == HEIGHT-1 ? 0 : y+1;
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				if (!(i == 1 && j == 1) && garden[xValues[i]][yValues[j]].isAlive) {
					neighbors++;
				}
			}
		}		
		return neighbors;
	}
}

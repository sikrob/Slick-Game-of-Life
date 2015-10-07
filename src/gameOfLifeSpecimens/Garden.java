package gameOfLifeSpecimens;

import gameOfLifeSpecimens.Specimen;
import java.util.Random;

public class Garden {
	public final int WIDTH;
	public final int HEIGHT;
	private final boolean WALLED;
	
	private Specimen[][] garden;
	private final int SURVIVAL_THRESHOLD = 2;
	private final int POPULATE_THRESHOLD = 3;
	
	public Garden(int width, int height, boolean walled) throws Exception{
		if (width <= 0 || height <= 0) {
			throw new Exception("Garden must have positive, non-zero dimensions.");
		}
		WIDTH = width;
		HEIGHT = height;
		WALLED = walled;
		
		garden = new Specimen[WIDTH][HEIGHT];
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				garden[x][y] = new Specimen();
			}
		}
	}
	
	public void setInitialState(int[][] liveList) {
		for (int i = 0; i < liveList.length; i++) {
			garden[liveList[i][0]][liveList[i][1]].isAlive = true;
		}
	}

	public void setRandomStates() {
		Random random = new Random();
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				garden[x][y].isAlive = random.nextBoolean();
			}
		}
	}
	
	public boolean specimenAliveAt(int x, int y) {
		return garden[x][y].isAlive;
	}
	
	// This is a nice compact approach for a walled garden.
	private int neighborsForSpecimentAt(int x, int y) {
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
	
	// Less compact but effective approach for wrapped garden.
	private int wrapNeighborsForSpecimenAt(int x, int y) {
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
	
	public void updateFutureLifeAt(int x, int y) {
		int neighbors = WALLED == true ? neighborsForSpecimentAt(x, y) : wrapNeighborsForSpecimenAt(x, y);
		if ((neighbors == SURVIVAL_THRESHOLD && garden[x][y].isAlive) || neighbors == POPULATE_THRESHOLD) {
			garden[x][y].willBeAlive = true;
		} else {
			garden[x][y].willBeAlive = false;
		}
	}
	
	public void updateCurrentLifeAt(int x, int y) {
		garden[x][y].isAlive = garden[x][y].willBeAlive; 
	}
	
	public void update() {
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				updateFutureLifeAt(x,y);
			}
		}
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				updateCurrentLifeAt(x,y);
			}
		}
	}
}

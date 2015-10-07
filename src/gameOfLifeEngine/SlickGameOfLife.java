package gameOfLifeEngine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import gameOfLifeEngine.GameOfLife;

public class SlickGameOfLife {
	
	private static final int GARDEN_WIDTH = 80;
	private static final int GARDEN_HEIGHT = 60;
	private static final int DISPLAY_WIDTH = GARDEN_WIDTH*10+10;
	private static final int DISPLAY_HEIGHT = GARDEN_HEIGHT*10+100;
	private static final boolean GARDEN_WALLED = false;
	
	public static void main(String[] args) {
		try {
			AppGameContainer appGameContainer;
			appGameContainer = new AppGameContainer(new GameOfLife("Game of Life", GARDEN_WIDTH, GARDEN_HEIGHT, GARDEN_WALLED));
			appGameContainer.setTargetFrameRate(60);
			appGameContainer.setShowFPS(false);
			appGameContainer.setDisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT, false);
			appGameContainer.start();
		} catch (SlickException e) {
			Logger.getLogger(SlickGameOfLife.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}

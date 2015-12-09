package gameOfLifeEngine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import gameOfLifeEngine.GameOfLife;
import gameOfLifeEngine.SettingsPropertiesManager;

public class SlickGameOfLife {
	
	private static int gardenWidth = 10;
	private static int gardenHeight = 10;
	private static int displayWidth;
	private static int displayHeight;
	private static final boolean GARDEN_WALLED = false;
	
	private static SettingsPropertiesManager settingsPropertiesManager = new SettingsPropertiesManager();
	
	public static void main(String[] args) {
		
		try {
			gardenWidth = Integer.parseInt(settingsPropertiesManager.stringValueForKey("garden-width"));
			gardenHeight = Integer.parseInt(settingsPropertiesManager.stringValueForKey("garden-height"));
			displayWidth = gardenWidth*10+10;
			displayHeight = gardenHeight*10+100;
			
			AppGameContainer appGameContainer;
			appGameContainer = new AppGameContainer(new GameOfLife("Game of Life", gardenWidth, gardenHeight, GARDEN_WALLED));
			appGameContainer.setTargetFrameRate(60);
			appGameContainer.setShowFPS(false);
			appGameContainer.setDisplayMode(displayWidth, displayHeight, false);
			appGameContainer.start();
		} catch (SlickException e) {
			Logger.getLogger(SlickGameOfLife.class.getName()).log(Level.SEVERE, null, e);
		} catch (NumberFormatException e) {
			Logger.getLogger(SlickGameOfLife.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}

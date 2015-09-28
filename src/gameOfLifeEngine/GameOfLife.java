package gameOfLifeEngine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.Input;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;

import gameOfLifeSpecimens.Garden;

public class GameOfLife extends BasicGame implements InputProviderListener {
	private Garden garden;
	private final int AUTO_RESOLUTION = 3;
	private int autoInterval = 7;
	private int autoTimer = 0;
	private boolean autoRunning = false;
	
	private InputProvider inputProvider;
	private final Command UPDATE = new BasicCommand("update");
	private final Command TOGGLE_AUTO = new BasicCommand("toggleAuto");
	private final Command DECREASE_INTERVAL = new BasicCommand("decreaseInterval");
	private final Command INCREASE_INTERVAL = new BasicCommand("increaseInterval");
	
	public GameOfLife(String title, int gardenWidth, int gardenHeight) {
		super(title);
		try {
			garden = new Garden(gardenWidth, gardenHeight);
			garden.setRandomStates();
		} catch (Exception e) {
			Logger.getLogger(GameOfLife.class.getName()).log(Level.SEVERE, "Exception caught on attempt to instantiate GameOfLife.garden", e);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		inputProvider = new InputProvider(gc.getInput());
		inputProvider.addListener(this);
		inputProvider.bindCommand(new KeyControl(Input.KEY_SPACE), UPDATE);
		inputProvider.bindCommand(new KeyControl(Input.KEY_A), TOGGLE_AUTO);
		inputProvider.bindCommand(new KeyControl(Input.KEY_S), DECREASE_INTERVAL);
		inputProvider.bindCommand(new KeyControl(Input.KEY_D), INCREASE_INTERVAL);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if (autoRunning) {
			if (autoTimer++ >= autoInterval) {
				garden.update();
				autoTimer = 0;
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		renderInfo(g);
		renderGarden(g);
	}

	private void renderInfo(Graphics g) {
		g.drawString("Howdy!", 10, 10);
		g.drawString("Manually Increment: spacebar", 10, 25);
		g.drawString("Toggle Auto Increment: a", 10, 40);
		g.drawString("Speed Up Auto Increment: s", 10, 55);
		g.drawString("Slow Down Auto Increment: d", 10, 70);
		g.drawString("Auto Running:" + String.valueOf(autoRunning), 300, 40);
		g.drawString("Auto Timing Interval:" + String.valueOf(autoInterval), 300, 55);
	}
	
	private void renderGarden(Graphics g) {
		for (int x = 0; x < garden.WIDTH; x++) {
			for (int y = 0; y < garden.HEIGHT; y++) {
				String renderString = "-";				
				if (garden.specimenAliveAt(x, y)) {
					renderString = "ø";
				}
				g.drawString(renderString, x*10+5, y*10+90);
			}
		}
	}
	
	@Override
	public void controlPressed(Command c) {
		// Deliberately doing nothing on press.
	}

	@Override
	public void controlReleased(Command c) {
		if (c == UPDATE) {
			garden.update();
		} else if (c == TOGGLE_AUTO) {
			if (autoRunning == false) {
				autoRunning = true;
			} else {
				autoRunning = false;
			}
		} else if (c == DECREASE_INTERVAL) {
			autoInterval -= AUTO_RESOLUTION;
			if (autoInterval <= 0) {
				autoInterval = 1;
			}
		} else if (c == INCREASE_INTERVAL) {
			autoInterval += AUTO_RESOLUTION;
		}
	}

}

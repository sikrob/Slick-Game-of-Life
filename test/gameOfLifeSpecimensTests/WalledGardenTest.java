package gameOfLifeSpecimensTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameOfLifeSpecimens.WalledGarden;

public class WalledGardenTest {

	@Test
	public void testInitShouldThrowExceptionOnNonPositiveDimensions() {
		try {
			WalledGarden garden = new WalledGarden(0,0);
			fail("WalledGarden initilization should throw exception on non-positive dimensions passed.");
		} catch (Exception e) {
			assertNotNull("WalledGarden initilization should throw exception on non-positive dimensions passed.", e);
		}
	}

	@Test
	public void testSettingInitialStateChangesSpecimens() {
		int[][] liveList = new int[1][2];
		liveList[0][0] = 0;
		liveList[0][1] = 0;
		
		try {
			WalledGarden garden = new WalledGarden(1,1);
			assertFalse("Prior to setting initial state, specimens are not alive.", garden.specimenAliveAt(0, 0));
			garden.setInitialState(liveList);
			assertTrue("Setting initial state should cause the state to change to alive.", garden.specimenAliveAt(0, 0));
		} catch (Exception e) {
			fail("testSettingInitialStateChangesSpecimens exception occured: " + e.getMessage());
		}
	}
	
}

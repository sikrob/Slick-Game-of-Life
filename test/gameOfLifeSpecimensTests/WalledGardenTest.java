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
	
}

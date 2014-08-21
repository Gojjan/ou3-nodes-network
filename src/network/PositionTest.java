package network;

import static org.junit.Assert.*;
import Position;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PositionTest {

	@Test
	public void testPositionGetters() {
		Position p1 = new Position(1,2);
		
		assertEquals(p1.getX(),1);
		assertEquals(p1.getY(),2);
		assertEquals(new Position(0,2), p1.getPosToWest());
		assertEquals(new Position(2,2), p1.getPosToEast());
		assertEquals(new Position(1,1), p1.getPosToNorth());
		assertEquals(new Position(1,3), p1.getPosToSouth());
		assertEquals(new Position(0,2), p1.getPosToWest(1,2));
		assertEquals(new Position(2,2), p1.getPosToEast(1,2));
		assertEquals(new Position(1,1), p1.getPosToNorth(1,2));
		assertEquals(new Position(1,3), p1.getPosToSouth(1,2));
	}
	
	@Test
	public void testPositionEquals() {
		Position p1 = new Position(1,2);
		
		assertTrue(p1.equals(new Position(1,2)));
		assertFalse(p1.equals(new Position(2,3)));
	}

}

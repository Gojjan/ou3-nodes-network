/**
 * 
 */
package network;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author H
 *
 */
public class ShortestPathTest {

	@Test
	public void testPathEmpty() {
		ShortestPath s = new ShortestPath(new Position(1,2));
		assertFalse(s.isEmpty());
		
		s.popNextDirection();
		assertTrue(s.isEmpty());
	}
	
	@Test
	public void testAddDirection() {
		ShortestPath s = new ShortestPath(new Position(1,2));
		assertTrue(s.getDistance()<0.001);
		
		s.addDirection(new Position(2,2));
		assertTrue(s.getNextDirection().equals(new Position(2,2)));
		assertTrue(s.getDistance()>9);
		assertTrue(s.getDistance()<11);
		
		s.addDirection(new Position(3,2));
		assertTrue(s.getNextDirection().equals(new Position(3,2)));
		assertTrue(s.getDistance()>19);
		assertTrue(s.getDistance()<21);
	}

}

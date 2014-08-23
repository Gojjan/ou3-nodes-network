package network;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** ShortestPathTest �r ett unit-test f�r klassen ShortestPath.
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Aug 22 2014
 *
 */
public class ShortestPathTest {

	/** testPathEmpty. 
	 * 
	 * Testar om ShortestPath b�rjar med r�tt antal directions.
	 */
	@Test
	public void testPathEmpty() {
		ShortestPath s = new ShortestPath(new Position(1,2));
		assertFalse(s.isEmpty());
		
		s.popNextDirection();
		assertTrue(s.isEmpty());
	}
	
	/** testAddDirection. 
	 * 
	 * Testar om ShortestPath l�gger till nya directions korrekt.
	 */
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

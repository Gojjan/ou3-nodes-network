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
public class ResponseTest {

	@Test
	public void pathTest() {
		Response r = new Response(new ShortestPath(new Position(1,2)),new Event(3,new Position(4,5),6),new Position(7,8));
		
		assertTrue(r.getNextPostion().equals(new Position(1,2)));
		
		assertFalse(r.getIsHome());
		r.popNextPosition();
		assertTrue(r.getIsHome());
		
	}
	
	@Test
	public void eventTest() {
		Response r = new Response(new ShortestPath(new Position(1,2)),new Event(3,new Position(4,5),6),new Position(7,8));
		
		assertEquals(r.getEvent().getID(),3);
		
	}

}

package network;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** ResponseTest är ett unit-test för klassen Response.
 * 
 * @author Henrik Sjöström
 * @version 1.0 Aug 22 2014
 *
 */
public class ResponseTest {

	/** pathTest. 
	 * 
	 * Testar om Response's ShortestPath relaterade metoder fungerar korrekt.
	 */
	@Test
	public void pathTest() {
		Response r = new Response(new ShortestPath(new Position(1,2)),new Event(3,new Position(4,5),6),new Position(7,8));
		
		assertTrue(r.getNextPostion().equals(new Position(1,2)));
		
		assertFalse(r.getIsHome());
		r.popNextPosition();
		assertTrue(r.getIsHome());
		
	}
	
	/** eventTest. 
	 * 
	 * Testar om Response returnerar korrekt event.
	 */
	@Test
	public void eventTest() {
		Response r = new Response(new ShortestPath(new Position(1,2)),new Event(3,new Position(4,5),6),new Position(7,8));
		
		assertEquals(r.getEvent().getID(),3);
		
	}

}

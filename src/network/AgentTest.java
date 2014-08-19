/**
 * 
 */
package network;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author H
 *
 */
public class AgentTest {

	/**
	 * @throws java.lang.Exception
	 */

	@Test
	public void messageTest() {
		Agent a = new Agent(new Event(1,new Position(2,3),4),5,new Position(6,7));
		
		assertEquals(a.getTimeToLive(),5);
		a.setTimeToLive(8);
		assertEquals(a.getTimeToLive(),8);
		
		assertFalse(a.visitedNode(new Position(1,2)));
		a.visitNode(new Position(1,2));
		assertTrue(a.visitedNode(new Position(1,2)));
	}
	
	@Test
	public void eventTableTest() {
		Agent a = new Agent(new Event(1,new Position(2,3),4),5,new Position(6,7));
		
		assertFalse(a.getEventTable().isEmpty());
		assertFalse(a.getDefinedKeys().isEmpty());
		
		assertTrue(a.getDefinedKeys().size()==1);
		
		a.setDefinedKeys(2);
		
		assertTrue(a.getDefinedKeys().size()==2);
	}

}

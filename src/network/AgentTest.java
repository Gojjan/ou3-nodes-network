/**
 * 
 */
package network;

import static org.junit.Assert.*;

import org.junit.Test;

/** AgentTest är ett unit-test för klassen Agent.
 * 
 * @author Henrik Sjöström
 * @version 1.0 Aug 22 2014
 *
 */
public class AgentTest {

	/** messageTest.
	 * 
	 * Testar om Agent's setters och getters adopterade från klassen Message 
	 * fungerar korrekt. Detta utförs genom att först getta begynnelse värdet,
	 * sedan setta ett nytt värde och sist getta det nya, settade värdet.
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
	
	/** eventTableTest.
	 * 
	 * Testar om Agent's eventTable börjar tomt (vilket den ej ska) och om
	 * definedKeys växer i rätt takt.
	 */
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

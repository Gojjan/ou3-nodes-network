/**
 * 
 */
package network;

import static org.junit.Assert.*;

import org.junit.Test;

/** AgentTest �r ett unit-test f�r klassen Agent.
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Aug 22 2014
 *
 */
public class AgentTest {

	/** messageTest.
	 * 
	 * Testar om Agent's setters och getters adopterade fr�n klassen Message 
	 * fungerar korrekt. Detta utf�rs genom att f�rst getta begynnelse v�rdet,
	 * sedan setta ett nytt v�rde och sist getta det nya, settade v�rdet.
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
	 * Testar om Agent's eventTable b�rjar tomt (vilket den ej ska) och om
	 * definedKeys v�xer i r�tt takt.
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

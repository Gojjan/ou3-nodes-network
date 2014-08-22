package network;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** EventTest är ett unit-test för klassen Event.
 * 
 * @author Henrik Sjöström
 * @version 1.0 Aug 22 2014
 *
 */
public class EventTest {

	/** dateTest. 
	 * 
	 * Testar om Event's dateOfBirth getters och setters fungerar.
	 */
	@Test
	public void dateTest() {
		Event e = new Event(1,new Position(2,3),4);
		assertEquals(e.getDateOfBirth(),4);
		e.setDateOfBirth(5);
		assertEquals(e.getDateOfBirth(),5);
	}

	/** positionTest. 
	 * 
	 * Testar om Event's homePos getter och setter fungerar.
	 */
	@Test
	public void positionTest() {
		Event e = new Event(1,new Position(2,3),4);
		assertTrue(e.getHomePos().equals(new Position(2,3)));
		e.setHomePos(new Position(6,7));
		assertTrue(e.getHomePos().equals(new Position(6,7)));
	}
	
	/** IDTest. 
	 * 
	 * Testar om Event's ID getter och setter fungerar.
	 */
	@Test
	public void IDTest() {
		Event e = new Event(1,new Position(2,3),4);
		assertEquals(e.getID(),1);
		e.setID(8);
		assertEquals(e.getID(),8);
	}
}

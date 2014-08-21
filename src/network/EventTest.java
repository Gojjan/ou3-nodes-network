package network;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventTest {

	@Test
	public void dateTest() {
		Event e = new Event(1,new Position(2,3),4);
		assertEquals(e.getDateOfBirth(),4);
		e.setDateOfBirth(5);
		assertEquals(e.getDateOfBirth(),5);
	}

	@Test
	public void PositionTest() {
		Event e = new Event(1,new Position(2,3),4);
		assertTrue(e.getHomePos().equals(new Position(2,3)));
		e.setHomePos(new Position(6,7));
		assertTrue(e.getHomePos().equals(new Position(6,7)));
	}
	
	@Test
	public void IDTest() {
		Event e = new Event(1,new Position(2,3),4);
		assertEquals(e.getID(),1);
		e.setID(8);
		assertEquals(e.getID(),8);
	}
}

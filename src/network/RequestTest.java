package network;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** RequestTest är ett unit-test för klassen Request.
 * 
 * @author Henrik Sjöström
 * @version 1.0 Aug 22 2014
 *
 */
public class RequestTest {

	/** timeTest. 
	 * 
	 * Testar om Request's TimeToLive getters och setters fungerar.
	 */
	@Test
	public void timeTest() {
		Request r = new Request(1,2,new Position(3,4));
		
		assertEquals(r.getTimeToLive(),2);
		r.setTimeToLive(5);
		assertEquals(r.getTimeToLive(),5);
	}
	

	/** IDTest. 
	 * 
	 * Testar om Request's ID getters och setters fungerar.
	 */
	@Test
	public void IDTest() {
		Request r = new Request(1,2,new Position(3,4));
		assertEquals(r.getTargetId(),1);
	}

}

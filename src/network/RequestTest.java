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
public class RequestTest {

	@Test
	public void messageTest() {
		Request r = new Request(1,2,new Position(3,4));
		
		assertEquals(r.getTimeToLive(),2);
		r.setTimeToLive(5);
		assertEquals(r.getTimeToLive(),5);
	}
	
	@Test
	public void IDTest() {
		Request r = new Request(1,2,new Position(3,4));
		assertEquals(r.getTargetId(),1);
	}

}

package network;

import static org.junit.Assert.*;

import org.junit.Test;

/** QueuedMessageTest �r ett unit-test f�r klassen QueuesMessage.
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Aug 22 2014
 *
 */
public class QueuedMessageTest {

	/** testAgent. 
	 * 
	 * Testar om QueuedMessage som inneh�ller en Agent returnerar r�tt v�rden.
	 */
	@Test
	public void testAgent() {
		QueuedMessage q = new QueuedMessage(new Agent(new Event(1,new Position(2,3),4),5,new Position(6,7)),new Position(8,9));

		assertEquals(q.getType(),1);
		assertTrue(q.getAgent() instanceof Agent);
		
		assertTrue(q.getDestination().equals(new Position(8,9)));
	}

	/** testRequest. 
	 * 
	 * Testar om QueuedMessage som inneh�ller en Request returnerar r�tt v�rden.
	 */
	@Test
	public void testRequest() {
		QueuedMessage q = new QueuedMessage(new Request(1,2,new Position(3,4)),new Position(5,6));

		assertEquals(q.getType(),2);
		assertTrue(q.getRequest() instanceof Request);
		
		assertTrue(q.getDestination().equals(new Position(5,6)));
	}
	
	/** testResponse. 
	 * 
	 * Testar om QueuedMessage som inneh�ller en Response returnerar r�tt v�rden.
	 */
	@Test
	public void testResponse() {
		QueuedMessage q = new QueuedMessage(new Response(new ShortestPath(new Position(1,2)),new Event(3,new Position(4,5),6),new Position(7,8));

		assertEquals(q.getType(),3);
		assertTrue(q.getResponse() instanceof Response);
		
		assertTrue(q.getDestination().equals(new Position(7,8)));
	}
}

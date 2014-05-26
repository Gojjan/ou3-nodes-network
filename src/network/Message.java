package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** Message �r en klass som representerar ett meddelande som skickas mellan 
 * {@link Node} objekt i ett {@link Network} objekt.
 * <p>
 * Klassen anv�nds prim�rt som en fader till {@link Agent}, {@link Request} och {@link Response}.
 * 
 * @author @author Henrik Sj�str�m
 * @version 1.0 Maj 26 2014
 */
public class Message {
	/** Antalet tidssteg tills meddelandet slutar skickas. */
	private int timeToLive;
	/** V�gen tillbaka till platsen meddelandet kom ifr�n. 
	 * @see ShortestPath
	 */
	private ShortestPath pathHome;
	/** En tabell som h�ller reda p� vilka noder som bes�kts. */
	private Hashtable visitedNodes;
	
	/** Skapar ett meddelande.
	 * 
	 */
	public Message(){
		visitedNodes = new Hashtable(4099);
	}
	
	/** Returnerar antalets tidssteg meddelandet har kvar innan det slutar 
	 * skickas.
	 * @return					antal tidssteg kvar
	 */
	public int getTimeToLive(){
		return timeToLive;
	}
	/** Returnerar v�gen tillbaka till platsen meddelandet kom ifr�n.
	 *  
	 * @return					v�gen tillbaka
	 * @see ShortsetPath
	 */
	public ShortestPath getPathHome(){
		return pathHome;
	}
	/** Returnerar tabellen med visiterade noder. 
	 * 
	 * @return					hashtabellen med visiterade noder
	 */
	public Hashtable getVisitedNodes(){
		return visitedNodes;
	}
	/** Best�m hur m�nga tidssteg meddelandet har kvar att skickas p�.
	 *  
	 * @param x					antalet tidssteg meddelandet har kvar
	 */
	public void setTimeToLive(int x){
		timeToLive = x;
	}
	/** Best�m meddelandets v�g tillbaka till platsen den kom ifr�n.
	 * 
	 * @param sp				v�gen tillbaka
	 * @see ShortestPath
	 */
	public void setPathHome(ShortestPath sp){
		pathHome = sp;
	}
	/** Best�m tabellen �ver meddelandets visiterade noder.
	 * 
	 * @param table				hashtabellen med visiterade noder
	 */
	public void setVisitedNodes(Hashtable table){
		visitedNodes = table;
	}
}

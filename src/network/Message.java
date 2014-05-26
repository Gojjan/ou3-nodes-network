package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** Message är en klass som representerar ett meddelande som skickas mellan 
 * {@link Node} objekt i ett {@link Network} objekt.
 * <p>
 * Klassen används primärt som en fader till {@link Agent}, {@link Request} och {@link Response}.
 * 
 * @author @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Message {
	/** Antalet tidssteg tills meddelandet slutar skickas. */
	private int timeToLive;
	/** Vägen tillbaka till platsen meddelandet kom ifrån. 
	 * @see ShortestPath
	 */
	private ShortestPath pathHome;
	/** En tabell som håller reda på vilka noder som besökts. */
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
	/** Returnerar vägen tillbaka till platsen meddelandet kom ifrån.
	 *  
	 * @return					vägen tillbaka
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
	/** Bestäm hur många tidssteg meddelandet har kvar att skickas på.
	 *  
	 * @param x					antalet tidssteg meddelandet har kvar
	 */
	public void setTimeToLive(int x){
		timeToLive = x;
	}
	/** Bestäm meddelandets väg tillbaka till platsen den kom ifrån.
	 * 
	 * @param sp				vägen tillbaka
	 * @see ShortestPath
	 */
	public void setPathHome(ShortestPath sp){
		pathHome = sp;
	}
	/** Bestäm tabellen över meddelandets visiterade noder.
	 * 
	 * @param table				hashtabellen med visiterade noder
	 */
	public void setVisitedNodes(Hashtable table){
		visitedNodes = table;
	}
}

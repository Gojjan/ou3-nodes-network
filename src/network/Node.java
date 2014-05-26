package network;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** Node �r en klass som representerar en sensornod f�r att anv�ndas i en 
 * simulering av ett n�tverk. Sensor nodens beteende �r likt det av en nod i 
 * rumor-routing algoritmen. 
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Maj 27 2014 
 */
public class Node{
	/** En tabell med nodens {@link Event} objekt. */
	private Hashtable eventTable;
	/** En tabell med skickade {@link Message} objekt.*/
	private Hashtable sendRequests;
	/** En flagga f�r om noden ska skicka {@link Request} objekt. */
	private boolean isRepeater = false;
	/** Ber�ttar om noden har ett meddelande.
	 * @see Message
	 */
	private boolean isHoldingMessage;
	/** Nodes position.
	 * @see Position
	 */
	private Position pos;
	/** Lista med grannar. */
	private ArrayList neighbours;
	/** K� med meddelanden.
	 * @see QueuedMessage
	 */
	private Queue<QueuedMessage> sendQueue;
	/** En lista med intressanta nycklar i nodens tabell.*/
	private ArrayList<Integer> definedKeys = new ArrayList<Integer>();
	/** Chans att skapa ett {@link Event}. */
	private double eventChance;
	/** Chans att skapa en {@link Agent}. */
	private double agentChance;
	/** {@link Network} objektet som noden finns plaserad i. */
	private Network network;
	
	/** Skapar en nod.
	 * 
	 * @param npos							nodens position
	 * @param eventchance					nodens chans att skapa ett {@link Event}
	 * @param agentchance					nodens chans att skapa en {@link Agent}
	 * @param nnetwork						n�tverket noden skapades i
	 * @see Position
	 * @see Network
	 */
	public Node(Position npos, double eventchance, double agentchance, Network nnetwork){
		pos = npos;
		eventChance = eventchance;
		agentChance = agentchance;
		network = nnetwork;
		
	}
	/**
	 * 
	 * @param o
	 */
	public void receiveMessage(Object o){
		if(o instanceof Agent){
			Agent agent = (Agent)o;
			agent.setTimeToLive(agent.getTimeToLive()-1);
			Hashtable agentTable = agent.getEventTable();
			ArrayList<Integer> agentKeys = agent.getDefinedKeys();
			for(int i = 0; i < agentKeys.size(); i++){
				ShortestPath sp0 = (ShortestPath) agentTable.get(agentKeys.get(i));
				sp0.addDirection(pos);
				agentTable.put(agentKeys.get(i), sp0);
				boolean defined = false;
				for(int j = 0; j < definedKeys.size(); j++){
					if(agentKeys.get(i) == definedKeys.get(j)){
						ShortestPath sp1 = (ShortestPath) agentTable.get(agentKeys.get(i));
						ShortestPath sp2 = (ShortestPath) eventTable.get(definedKeys.get(j));
						if(sp2.compareDistance(sp1) > 0){
							//sp1 is shorter
							eventTable.put(definedKeys.get(j), sp1);
						} else if(sp2.compareDistance(sp1) < 0){
							//sp1 is longer
							agentTable.put(agentKeys.get(i), sp2);
						}
						defined = true;
					}
				}
				if(!defined){
					eventTable.put(agentKeys.get(i), sp0);
				}
			}
			for(int i = 0; i < definedKeys.size(); i++){
				boolean defined = false;
				for(int j = 0; j < agentKeys.size(); j++){
					if(definedKeys.get(i) == agentKeys.get(j)){
						ShortestPath sp1 = (ShortestPath) eventTable.get(definedKeys.get(i));
						ShortestPath sp2 = (ShortestPath) agentTable.get(agentKeys.get(j));
						if(sp2.compareDistance(sp1) > 0){
							//sp1 is shorter
							eventTable.put(definedKeys.get(j), sp1);
						} else if(sp2.compareDistance(sp1) < 0){
							//sp1 is longer
							agentTable.put(agentKeys.get(i), sp2);
						}
						defined = true;
					}
				}
				if(!defined){
					agentTable.put(definedKeys.get(i), eventTable.get(definedKeys.get(i)));
				}
			}
			if(agent.getTimeToLive() > 1){
				//m�ste �ndra s� att den (om m�jligt) skickar/k�ar till random granne den inte varit hos
				QueuedMessage qdMessage = new QueuedMessage(agent, pos);
				sendQueue.add(qdMessage);
			}
		} else if (o instanceof Request){
			//N�gonstans m�ste det l�ggas till s� att den kollar om distance till eventet �r noll fr�n denna nod och d� g�ra en response.
			Request request = (Request) o;
			Position nextpos;
			boolean isOnTrack = false;
			for(int i = 0; i < definedKeys.size(); i++){
				if(definedKeys.get(i) == request.getTargetId()){
					isOnTrack = true;
				}
			}
			if(isOnTrack){
				ShortestPath sp = (ShortestPath) eventTable.get(request.getTargetId());
				nextpos = sp.getNextDirection();
			} else {
				request.setTimeToLive(request.getTimeToLive()-1);
				if(request.getTimeToLive() > 1){
					request.addPosToPathHome(pos);
					//m�ste �ndra s� att den (om m�jligt) skickar/k�ar till random granne den inte varit hos
					QueuedMessage qdMessage = new QueuedMessage(request, pos);
					sendQueue.add(qdMessage);
				}
			}
		} else if (o instanceof Response){
			Response response = (Response) o;
			response.popNextPosition();
			if(response.getIsHome()){
				Event responseEvent = response.getEvent();
				Position eventpos = responseEvent.getHomePos();
				System.out.println("Event id: "+responseEvent.getID()+", event date of birth: "
						+responseEvent.getDateOfBirth()+", event place of birth: ["+eventpos.getX()+","+eventpos.getY()+"]");
				//asddas, skriv ut skit
			} else {
				Position nextpos = response.getNextPostion();
				QueuedMessage qdMessage = new QueuedMessage(response, nextpos);
				sendQueue.add(qdMessage);
			}
		}
	}
	/** Noden tar ett tidssteg. */
	public void timeTick(){
		if(Math.random() <= eventChance){
			Event event = new Event(network.createUniqueID(),pos,network.getTime());
			if(Math.random() <= agentChance){
				
			}
		}
		//kanske skapa event
		//kanske skapa agent, l�gg agent i k�n
		//kolla om det finns n�got i k�n
		//skicka sak fr�n k�n
		
		//att l�gga till saker i k�n sker automatisk n�r en annan nod skickar till denna, 
	}
	/** Skickar ett {@link Message} objekt. */
	public void sendMessage(QueuedMessage qdm){
		
	}
	/** Returnerar om noden har ett meddelande.
	 * 
	 * @return								nodens meddelande
	 */
	public boolean getIsHoldingMessage(){
		return isHoldingMessage;
	}
	/** Returnerar nodens position.
	 * 
	 * @return								nodens position
	 * @see Position
	 */
	public Position getPosition(){
		return pos;
	}
	/** Flagga att noden skapar {@link Request} objekt. */
	public void setRepeater(){
		isRepeater = true;
	}
	/** Returnerar om noden skapar {@link Request} objekt.
	 * 
	 * @return								om noden skapar {@link Request} 
	 * 										objekt elelr ej
	 */
	public boolean getRepeater(){
		return isRepeater;
	}
}

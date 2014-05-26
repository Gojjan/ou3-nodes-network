package network;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** Node är en klass som representerar en sensornod för att användas i en 
 * simulering av ett nätverk. Sensor nodens beteende är likt det av en nod i 
 * rumor-routing algoritmen. 
 * 
 * @author Henrik Sjöström
 * @version 1.0 Maj 27 2014 
 */
public class Node{
	/** En tabell med nodens {@link Event} objekt. */
	private Hashtable eventTable;
	/** En tabell med skickade {@link Message} objekt.*/
	private Hashtable sentRequestsHT;
	/** Kö med {@link Request} objekt. */
	private ArrayList<Request> requests;
	/** En flagga för om noden ska skicka {@link Request} objekt. */
	private boolean isRepeater = false;
	/** Berättar om noden har ett meddelande.
	 * @see Message
	 */
	private boolean isHoldingMessage;
	/** Nodes position.
	 * @see Position
	 */
	private Position pos;
	/** Lista med grannar. */
	private ArrayList<Position> neighbours = new ArrayList<Position>();
	/** Kö med meddelanden.
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
	/** Lista med {@link Event} objekt. */
	private ArrayList<Event> eventArrayList = new ArrayList<Event>();
	/** Tidsteg kvar tills nästa gång {@link Request} objekt ska skickas. */
	private int repeaterTime;
<<<<<<< HEAD
	/**Konstruktor till en nod i nätverket.
	 * 
	 * @param npos						position där noden placeras.
	 * @param eventchance				chansen att ett event skapas. Sätts till 50% från main.
	 * @param agentchance				chansen att en agent skapas. Sätts till 1% från main.
	 * @param nnetwork					nätverk noden ligger i.
=======
	
	/** Skapar en nod.
	 * 
	 * @param npos							nodens position
	 * @param eventchance					nodens chans att skapa ett {@link Event}
	 * @param agentchance					nodens chans att skapa en {@link Agent}
	 * @param nnetwork						nätverket noden skapades i
	 * @see Position
	 * @see Network
>>>>>>> refs/remotes/origin/master
	 */
	public Node(Position npos, double eventchance, double agentchance, Network nnetwork){
		pos = npos;
		eventChance = eventchance;
		agentChance = agentchance;
		network = nnetwork;
		sendQueue = new LinkedList<QueuedMessage>();
		eventTable = new Hashtable();
	}
	
	/**
	 * 
	 * @param o
	 */
	public void receiveMessage(Object o){
		if(o instanceof QueuedMessage){
			QueuedMessage qd = (QueuedMessage) o;
			if(qd.getType() == 1){
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
					agent.setEventTable(agentTable);
					//måste ändra så att den (om möjligt) skickar/köar till random granne den inte varit hos
					ArrayList<Position> newNeighbours = randomizeOrder();
					Position nextpos = null;
					boolean foundDestination = false;
					int i = 0;
					while(!foundDestination && i < newNeighbours.size()){
						if(!agent.visitedNodeI(newNeighbours.get(i))){
							foundDestination = true;
							nextpos = newNeighbours.get(i);
						}
						i++;
					}
					if(nextpos == null){
						nextpos = newNeighbours.get(0);
					}
					QueuedMessage qdMessage = new QueuedMessage(agent, pos);
					sendQueue.add(qdMessage);
				}
			} else if (qd.getType() == 2){
				//Någonstans måste det läggas till så att den kollar om distance till eventet är noll från denna nod och då göra en response.
				Request request = (Request) o;
				Position nextpos = null;
				boolean isOnTrack = false;
				boolean foundEvent = false;
				for(int i = 0; i < definedKeys.size(); i++){
					if(definedKeys.get(i) == request.getTargetId()){
						isOnTrack = true;
						ShortestPath sp =  (ShortestPath) eventTable.get(definedKeys.get(i));
						if(sp.getDistance() == 0){
							foundEvent = true;
						}
					}
				}
				if(isOnTrack){
					ShortestPath sp = (ShortestPath) eventTable.get(request.getTargetId());
					nextpos = sp.getNextDirection();
					QueuedMessage qdMessage = new QueuedMessage(request, nextpos);
					sendQueue.add(qdMessage);
				} else if (foundEvent != true){
					request.setTimeToLive(request.getTimeToLive()-1);
					if(request.getTimeToLive() > 1){
						request.addPosToPathHome(pos);
						//måste ändra så att den (om möjligt) skickar/köar till random granne den inte varit hos
						ArrayList<Position> newNeighbours = randomizeOrder();
						boolean foundDestination = false;
						int i = 0;
						while(!foundDestination && i < newNeighbours.size()){
							if(!request.visitedNodeI(newNeighbours.get(i))){
								foundDestination = true;
								nextpos = newNeighbours.get(i);
							}
							i++;
						}
						if(nextpos == null){
							nextpos = newNeighbours.get(0);
						}
						QueuedMessage qdMessage = new QueuedMessage(request, nextpos);
						sendQueue.add(qdMessage);
					}
				} else {
					
					//create Response
				}
			} else if (qd.getType() == 3){
				Response response = (Response) o;
				response.popNextPosition();
				if(response.getIsHome()){
					Event responseEvent = response.getEvent();
					Position eventpos = responseEvent.getHomePos();
					System.out.println("Event id: "+responseEvent.getID()+", event date of birth: "
							+responseEvent.getDateOfBirth()+", event place of birth: ["+eventpos.getX()+","+eventpos.getY()+"]\n");
				} else {
					Position nextpos = response.getNextPostion();
					QueuedMessage qdMessage = new QueuedMessage(response, nextpos);
					sendQueue.add(qdMessage);
				}
			}
		}
	}
	/** Noden tar ett tidssteg. */
	public void timeTick(){
		if(neighbours.isEmpty()){
			neighbours = network.checkNeighbours(pos);
		}
		if(isRepeater){
			//lower time by all sent requests by 1
			for(int i = 0; i < requests.size(); i++){
				sentRequestsHT.put(requests.get(i), (int) sentRequestsHT.get(requests.get(i))-1);
				if((int)sentRequestsHT.get(requests.get(i)) == 0){
					Request request = requests.get(i);
					QueuedMessage qdm = new QueuedMessage(request,pos);
					sendQueue.add(qdm);
					sentRequestsHT.remove(requests.get(i));
				}
			}
			if(repeaterTime == 0){
				repeaterTime = 400;
				Position nextpos = null;
				int requestID = network.getRequestID();
				boolean lucky = false;
				Request request = new Request(requestID, network.getRequestTimeToLive(), pos);
				for(int i = 0; i < definedKeys.size(); i++){
					if(requestID == definedKeys.get(i)){
						ShortestPath sp = (ShortestPath) eventTable.get(requestID);
						float distance = sp.getDistance();
						if(distance == 0){
							lucky = true;
						}else{
							nextpos = sp.getNextDirection();
						}
					}
				}
				if(nextpos == null){
					nextpos = (Position) neighbours.get((int) Math.random()*(neighbours.size()));
				}
				QueuedMessage qdm = new QueuedMessage(request, nextpos);
				if(!lucky){
					sendQueue.add(qdm);
					sentRequestsHT.put(request,8*network.getRequestTimeToLive());
				} else {
					for(int i = 0; i < eventArrayList.size(); i++){
						Event event = (Event) eventArrayList.get(i);
						if(event.getID() == requestID){
							System.out.println("Event id: "+requestID+", event date of birth: "
									+event.getDateOfBirth()+", event place of birth: ["+pos.getX()+","+pos.getY()+"]\n");
						}
					}
				}
			}
			if(repeaterTime > 0)
			repeaterTime--;
		}
		if(Math.random() <= eventChance){
			Event event = new Event(network.createUniqueID(),pos,network.getTime());
			eventArrayList.add(event);
			ShortestPath sp = new ShortestPath(pos);
			eventTable.put(event.getID(), sp);
			if(Math.random() <= agentChance){
				Agent agent = new Agent(event, network.getAgentTimeToLive(), pos);
				Position nextpos = (Position) neighbours.get((int) Math.random()*(neighbours.size()-1));
				QueuedMessage qdm = new QueuedMessage(agent, nextpos);
				sendQueue.add(qdm);
			}
		}
		if(sendQueue.poll() != null){
			QueuedMessage qdm = sendQueue.remove();
			sendMessage(qdm);
		}
	}
	
	/** Skickar ett {@link Message} objekt. */
	public boolean sendMessage(QueuedMessage qdm){
		Position posa = qdm.getDestination();
		network.GetNodeAtPosition(posa).receiveMessage(qdm);
		return true;
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
		requests = new ArrayList<Request>();
		sentRequestsHT = new Hashtable();
		repeaterTime = 400;
	}
	/** Returnerar om noden skapar {@link Request} objekt.
	 * 
	 * @return								om noden skapar {@link Request} 
	 * 										objekt elelr ej
	 */
	public boolean getRepeater(){
		return isRepeater;
	}
	private ArrayList<Position> randomizeOrder(){
		ArrayList<Position> reOrdered = new ArrayList<Position>();
		ArrayList<Integer> defined = new ArrayList<Integer>();
		boolean matched = false;
		for(int i = 0; i < neighbours.size(); i++){
			matched = false;
			while(!matched){
				int randomPosIndex = (int) (Math.random()*neighbours.size());
				boolean unDefined = true;
				for(int j = 0; j < defined.size(); j++){
					if(randomPosIndex == defined.get(j)){
						unDefined = false;
					}
				}
				if(unDefined == true){
					Position pos2 = neighbours.get(randomPosIndex);
					reOrdered.add(pos2);
					defined.add(randomPosIndex);
					matched = true;
				}
			}
		}
		
		return reOrdered;
	}
}

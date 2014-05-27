package network;

import java.util.ArrayList;

/** Network är en klass som representerar ett nätverk av {@link Node} objekt. 
 * 
 * @author Viktor Bengtsson
 * @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Network {
	
	/** Matris med nätverkets {@link Node} objekt. */
	private ArrayList<ArrayList<Node>> nodeArray = new ArrayList<ArrayList<Node>>();
	/** Det nuvarande tidssteget. */
	private int currentTime = 0;
	/** Senaste skapade {@link Message} objektets identifikation. Används för 
	 * att se till att inte återanvända samma identifikation.
	 */
	private int lastSentID = 0;
	/** Nätverktes bredd */
	private int x;
	/** Nätverkets höjd */
	private int y;
	private int agentTimeToLive;
	private int requestTimeToLive;
	private int searchedRequests;
	
	
	/** Skapar ett nytt nätverk. Fyller varje position i matrisen med ett 
	 * {@link Node} objekt.
	 * 
	 * @param nx						nätverktes bredd
	 * @param ny						nätverktes höjd
	 * @param eventChance				chansen för skapandet av ett {@link Event} objekt
	 * @param agentChance				chansen för skapandet av ett {@link Agent} objekt
	 */
	public Network (int nx, int ny, double eventChance, double agentChance, int aTTL, int rTTL){
		x = nx;
		y = ny;
		for(int i = 0; i < x; i++){
			nodeArray.add(i, new ArrayList<Node>());
			for(int j = 0; j < y; j++){
				nodeArray.get(i).add(new Node(new Position(i, j), eventChance, agentChance, this));
			}
		}
		int repeatersNotCreated = 4;
		while(repeatersNotCreated != 0){
			for(int i = 0; i < x; i++){
				for(int j = 0; j < y; j++){
					if(Math.random()<0.01 && repeatersNotCreated != 0 && nodeArray.get(i).get(j).getRepeater() != true){
						nodeArray.get(i).get(j).setRepeater();
						System.out.println("repeater created");
						repeatersNotCreated--;
					}
				}
			}
		}
		agentTimeToLive = aTTL;
		requestTimeToLive = rTTL;
	}
	public int getAgentTimeToLive(){
		return agentTimeToLive;
	}
	public int getRequestTimeToLive(){
		return requestTimeToLive;
	}
	/** Utför ett tidssteg i alla {@link Node} objekt i nätverket. */
	public void timeTick(){
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				nodeArray.get(i).get(j).timeTick();
			}
		}
		currentTime++;
	}
	
	/** Returnerar {@link Node} objektet vid en given position.
	 * 
	 * @param pos						positionen som söks
	 * @return							det sökta {@link Node} objektet
	 * @see Position
	 */
	public Node GetNodeAtPosition(Position pos){
		System.out.println(pos.getX());
		System.out.println(pos.getY());
		return nodeArray.get(pos.getX()).get(pos.getY());
	}
	
	/** Undersöker och returnerar ett {@link Node} objekts grannar.
	 * 
	 * @param pos						positionen till {@link Node} objektet som söks
	 * @return							en array med grannars {@link Position}
	 * @see Position
	 */
	public ArrayList<Position> checkNeighbours(Position pos){
		ArrayList<Position> neighbours = new ArrayList<Position>();
		try{
			neighbours.add(pos.getPosToNorth());
		}catch (IndexOutOfBoundsException e){}
		try{
			neighbours.add(pos.getPosToNorthEast());
		}catch (IndexOutOfBoundsException e){}
		try{
			neighbours.add(pos.getPosToEast());
		}catch (IndexOutOfBoundsException e){}
		try{
			neighbours.add(pos.getPosToSouthEast());
		}catch (IndexOutOfBoundsException e){}
		try{
			neighbours.add(pos.getPosToSouth());
		}catch (IndexOutOfBoundsException e){}
		try{
			neighbours.add(pos.getPosToSouthWest());
		}catch (IndexOutOfBoundsException e){}
		try{
			neighbours.add(pos.getPosToWest());
		}catch (IndexOutOfBoundsException e){}
		try{
			neighbours.add(pos.getPosToNorthWest());
		}catch (IndexOutOfBoundsException e){}
		
		return neighbours;
	}
	
	/** Genererar en unikt identifikation till ett {@link Message} objekt.
	 * 
	 * @return							den genererade identifikationen
	 */
	public int createUniqueID(){
		int ID = lastSentID++;
		lastSentID = ID;
		return ID;
	}
	
	/** Returnerar det nuvarande tidssteget.
	 * 
	 * @return							det nuvarande tidssteget
	 */
	public int getTime(){
		return currentTime;
	}
	public int getRequestID(){
		searchedRequests++;
		return searchedRequests%4;
	}
}
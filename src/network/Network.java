package network;

import java.util.ArrayList;

/** Network �r en klass som representerar ett n�tverk av {@link Node} objekt. 
 * 
 * @author Viktor Bengtsson
 * @author Henrik Sj�str�m
 * @version 1.0 Maj 26 2014
 */
public class Network {
	
	/** Matris med n�tverkets {@link Node} objekt. */
	private ArrayList<ArrayList<Node>> nodeArray = new ArrayList<ArrayList<Node>>();
	/** Det nuvarande tidssteget. */
	private int currentTime = 0;
	/** Senaste skapade {@link Message} objektets identifikation. Anv�nds f�r 
	 * att se till att inte �teranv�nda samma identifikation.
	 */
	private int lastSentID = 0;
	/** N�tverktes bredd */
	private int x;
	/** N�tverkets h�jd */
	private int y;
	private int agentTimeToLive;
	private int requestTimeToLive;
	private int searchedRequests;
	
	
	/** Skapar ett nytt n�tverk. Fyller varje position i matrisen med ett 
	 * {@link Node} objekt.
	 * 
	 * @param nx						n�tverktes bredd
	 * @param ny						n�tverktes h�jd
	 * @param eventChance				chansen f�r skapandet av ett {@link Event} objekt
	 * @param agentChance				chansen f�r skapandet av ett {@link Agent} objekt
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
	/** Utf�r ett tidssteg i alla {@link Node} objekt i n�tverket. */
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
	 * @param pos						positionen som s�ks
	 * @return							det s�kta {@link Node} objektet
	 * @see Position
	 */
	public Node GetNodeAtPosition(Position pos){
		System.out.println(pos.getX());
		System.out.println(pos.getY());
		return nodeArray.get(pos.getX()).get(pos.getY());
	}
	
	/** Unders�ker och returnerar ett {@link Node} objekts grannar.
	 * 
	 * @param pos						positionen till {@link Node} objektet som s�ks
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
package network;

import java.util.ArrayList;

public class Network {
	
	private ArrayList<ArrayList<Node>> nodeArray = new ArrayList<ArrayList<Node>>();
	private int currentTime = 0;
	private int lastSentID = 0;
	private int x;
	private int y;
	private int agentTimeToLive;
	private int requestTimeToLive;
	private int searchedRequests;
	
	
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
	public void timeTick(){
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				nodeArray.get(i).get(j).timeTick();
			}
		}
		currentTime++;
	}
	
	public Node GetNodeAtPosition(Position pos){
		return nodeArray.get(pos.getX()).get(pos.getY());
	}
	
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
	public int createUniqueID(){
		int ID = lastSentID++;
		lastSentID = ID;
		return ID;
	}

	public int getTime(){
		return currentTime;
	}
	public int getRequestID(){
		searchedRequests++;
		return searchedRequests%4;
	}
}
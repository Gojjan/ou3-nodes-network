package network;

import java.util.ArrayList;

public class Network {
 private ArrayList<ArrayList<Node>> NodeArray = new ArrayList<ArrayList<Node>>();
 private int currentTime = 0;
 private int lastSentID = 0;
	


 public Node GetNodeAtPosition(Position pos){
	 Node node;
	 node = NodeArray.get(pos.getX()).get(pos.getY());

	 return node;
 }
 
 public ArrayList<Position> checkNeighbours(Position pos){
	 ArrayList<Position> neighbours = new ArrayList<Position>();
	  
	 try{ 
		neighbours.add(pos.getPosToNorth());
	 }catch (IndexOutOfBoundsException e){
	 }
	 try{
		 neighbours.add(pos.getPosToNorthEast());
	 }catch (IndexOutOfBoundsException e){ 
	 }
	 try{
		 neighbours.add(pos.getPosToEast());
	 }catch (IndexOutOfBoundsException e){ 
	 }
	 try{
		 neighbours.add(pos.getPosToSouthEast());
	 }catch (IndexOutOfBoundsException e){
	 }
	 try{
		 neighbours.add(pos.getPosToSouth());
	 }catch (IndexOutOfBoundsException e){ 
	 }
	 try{
		 neighbours.add(pos.getPosToSouthWest());
	 }catch (IndexOutOfBoundsException e){	 
	 }
	 try{
		 neighbours.add(pos.getPosToWest());
	 }catch (IndexOutOfBoundsException e){
	 }
	 try{
		 neighbours.add(pos.getPosToNorthWest());
	 }catch (IndexOutOfBoundsException e){
	 }
	 
	 return neighbours;
 }
 public int createUniqueID(){
	 int ID = lastSentID++;
	 lastSentID = ID;
	 return ID;
 }
 public void timeTick(){
	 currentTime++;
 }
}
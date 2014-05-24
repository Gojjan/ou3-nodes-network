package network;

import java.util.Stack;

public class ShortestPath {
	private Stack<Position> positionStack;
	private int distance;
	
	public ShortestPath(Position pos){
		positionStack = new Stack<Position>();
		positionStack.push(pos);
	}
	public void addDirection(Position pos){
		positionStack.push(pos);
	}
	public Position getNextDirection(){
		return positionStack.peek();
	}
	public void popNextDirection(){
		positionStack.pop();
	}
	public int getDistance(){
		return distance;
	}
	//om sp1.compareDistance(sp2) > 0 d� �r sp1 kortare �n sp2
	public int compareDistance(ShortestPath sp){
		return sp.getDistance() - distance;
	}
}

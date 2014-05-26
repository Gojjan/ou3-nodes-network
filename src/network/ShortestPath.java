package network;

import java.util.Stack;

public class ShortestPath {
	private Stack<Position> positionStack;
	private float distance;
	
	public ShortestPath(Position pos){
		positionStack = new Stack<Position>();
		positionStack.push(pos);
		distance = 0;
	}
	public void addDirection(Position pos2){
		Position pos = positionStack.peek();
		int x1 = pos.getX();
		int x2 = pos2.getX();
		int y1 = pos.getY();
		int y2 = pos2.getY();
		if(x1 < x2){
			if(y1 < y2){
				distance = (float) (distance + Math.sqrt(200));
			}else if(y1 > y2){
				distance = (float) (distance + Math.sqrt(200));
			}else{
				distance = distance + 10;
			}
		}else if (x1 > x2){
			if(y1 < y2){
				distance = (float) (distance + Math.sqrt(200));
			}else if(y1 > y2){
				distance = (float) (distance + Math.sqrt(200));
			}else{
				distance = distance + 10;
			}
		}else{
			if(y1 < y2){
				distance = distance + 10;
			}else if(y1 > y2){
				distance = distance + 10;
			}
		}
		positionStack.push(pos2);
	}
	public Position getNextDirection(){
		return positionStack.peek();
	}
	public void popNextDirection(){
		positionStack.pop();
	}
	public float getDistance(){
		return distance;
	}
	//om sp1.compareDistance(sp2) > 0 då är sp1 kortare än sp2
	public float compareDistance(ShortestPath sp){
		return sp.getDistance() - distance;
	}
	public boolean isEmpty(){
		return positionStack.isEmpty();
	}
}

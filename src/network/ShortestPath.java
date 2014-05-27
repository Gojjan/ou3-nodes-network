package network;

import java.util.Stack;

public class ShortestPath {
	/** Stack d�r positioner f�r kortaste v�gen l�ggs in.*/
	private Stack<Position> positionStack;
	/** Avst�ndet den kortaste v�gen har.*/
	private float distance;
	/** Skapar stacken f�r kortaste v�gen och l�gger in startpositionen.
	 * 
	 * @param pos			Startposition.
	 */
	public ShortestPath(Position pos){
		positionStack = new Stack<Position>();
		positionStack.push(pos);
		distance = 0;
	}
	/** L�gger till n�sta steg i v�gen med en {@link Position} till stacken.
	 * 
	 * @param pos2			{@link Position} som l�ggs till i stacken.
	 */
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
	/** Returnerar n�sta steg i v�gstacken f�r kortaste v�gen.
	 * 
	 * @return				{@link Position} f�r n�sta v�gsteg.
	 */
	public Position getNextDirection(){
		return positionStack.peek();
	}
	/** Tar bort �versta v�rdet i v�gstacken.*/
	public void popNextDirection(){
		
		positionStack.pop();
	}
	public float getDistance(){
		return distance;
	}
	//om sp1.compareDistance(sp2) > 0 d� �r sp1 kortare �n sp2
	public float compareDistance(ShortestPath sp){
		return sp.getDistance() - distance;
	}
	public boolean isEmpty(){
		return positionStack.isEmpty();
	}
}

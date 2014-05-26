package network;

public class Position {
	private int x;
	private int y;
	
	public Position(int nx, int ny){
		x = nx;
		y = ny;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Position getPosToEast(){
		Position pos = new Position(x+1,y);
		return  pos;
	}
	public Position getPosToWest(){
		Position pos = new Position(x-1,y);
		return  pos;
	} 
	public Position getPosToNorth(){
		Position pos = new Position(x,y-1);
		return pos;
	}
	public Position getPosToSouth(){
		Position pos = new Position(x,y+1);
		return pos;
	}
	public Position getPosToNorthEast(){
		Position pos = new Position(x+1,y-1);
		return pos;
	}
	public Position getPosToNorthWest(){
		Position pos = new Position(x-1,y-1);
		return pos;
	}
	public Position getPosToSouthEast(){
		Position pos = new Position(x+1,y+1);
		return pos;
	}
	public Position getPosToSouthWest(){
		Position pos = new Position(x-1,y+1);
		return pos;
	}
	public boolean equals(Position pos){
		if(pos.getX() == x && pos.getY() == y){
			return true;
		} else {
			return false;
		}
	}
}

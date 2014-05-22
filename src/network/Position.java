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
	public boolean equals(Position pos){
		if(pos.getX() == x && pos.getY() == y){
			return true;
		} else {
			return false;
		}
	}
}

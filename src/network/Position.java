package network;

/** Position är en klass som representerar en position på ett två dimentionellt plan.
 * 
 * @author Ludvig Lindkvist
 * @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Position {
	/** Position i x-led. */
	private int x;
	/** Position i y-led. */
	private int y;
	
	/** Skapar en position med givet x- och y-värde.
	 * 
	 * @param nx						x-värde
	 * @param ny						y-värde
	 */
	public Position(int nx, int ny){
		x = nx;
		y = ny;
	}
	/** Returnerar position i x-led.
	 * 
	 * @return							x-värdet
	 */
	public int getX(){
		return x;
	}
	/** Returnerar position i y-led.
	 * 
	 * @return							y-värdet
	 */
	public int getY(){
		return y;
	}
	/** Returnerar positionen 1 steg åt öst.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToEast(int dx, int dy){
		if(x+1 < dx){
			Position pos = new Position(x+1,y);
			return  pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg åt väst.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToWest(int dx, int dy){
		if (x-1 >= 0){
			Position pos = new Position(x-1,y);
			return  pos;
		}else{
			return null;
		}
	} 
	/** Returnerar positionen 1 steg åt norr.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToNorth(int dx, int dy){
		if(y-1 >= 0){
			Position pos = new Position(x,y-1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg åt söder.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToSouth(int dx, int dy){
		if(y+1 < dy){
		Position pos = new Position(x,y+1);
		return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg åt nordöst.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToNorthEast(int dx, int dy){
		if(x+1 < dx && y-1 >= 0){
			Position pos = new Position(x+1,y-1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg åt nordväst.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToNorthWest(int dx, int dy){
		if(x-1 >= 0 && y-1 >= 0){
			Position pos = new Position(x-1,y-1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg åt sydöst.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToSouthEast(int dx, int dy){
		if(x+1 < dx && y+1 < dy){
			Position pos = new Position(x+1,y+1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg åt sydväst.
	 * 
	 * @return							den sökta positionen
	 */
	public Position getPosToSouthWest(int dx, int dy){
		if(x-1 >= 0 && y+1 < dy){
			Position pos = new Position(x-1,y+1);
			return pos;
		}else{
			return null;
		}
	}
	/** Avgör om två positioner är identiska.
	 * 
	 * @param pos						positionen att gemföra med
	 * @return							om positionerna är identiska
	 */
	public boolean equals(Position pos){
		if(pos.getX() == x && pos.getY() == y){
			return true;
		} else {
			return false;
		}
	}
}

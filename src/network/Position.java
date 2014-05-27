package network;

/** Position �r en klass som representerar en position p� ett tv� dimentionellt plan.
 * 
 * @author Ludvig Lindkvist
 * @author Henrik Sj�str�m
 * @version 1.0 Maj 26 2014
 */
public class Position {
	/** Position i x-led. */
	private int x;
	/** Position i y-led. */
	private int y;
	
	/** Skapar en position med givet x- och y-v�rde.
	 * 
	 * @param nx						x-v�rde
	 * @param ny						y-v�rde
	 */
	public Position(int nx, int ny){
		x = nx;
		y = ny;
	}
	/** Returnerar position i x-led.
	 * 
	 * @return							x-v�rdet
	 */
	public int getX(){
		return x;
	}
	/** Returnerar position i y-led.
	 * 
	 * @return							y-v�rdet
	 */
	public int getY(){
		return y;
	}
	/** Returnerar positionen 1 steg �t �st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToEast(int dx, int dy){
		if(x+1 < dx){
			Position pos = new Position(x+1,y);
			return  pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg �t v�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToWest(int dx, int dy){
		if (x-1 >= 0){
			Position pos = new Position(x-1,y);
			return  pos;
		}else{
			return null;
		}
	} 
	/** Returnerar positionen 1 steg �t norr.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToNorth(int dx, int dy){
		if(y-1 >= 0){
			Position pos = new Position(x,y-1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg �t s�der.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToSouth(int dx, int dy){
		if(y+1 < dy){
		Position pos = new Position(x,y+1);
		return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg �t nord�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToNorthEast(int dx, int dy){
		if(x+1 < dx && y-1 >= 0){
			Position pos = new Position(x+1,y-1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg �t nordv�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToNorthWest(int dx, int dy){
		if(x-1 >= 0 && y-1 >= 0){
			Position pos = new Position(x-1,y-1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg �t syd�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToSouthEast(int dx, int dy){
		if(x+1 < dx && y+1 < dy){
			Position pos = new Position(x+1,y+1);
			return pos;
		}else{
			return null;
		}
	}
	/** Returnerar positionen 1 steg �t sydv�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToSouthWest(int dx, int dy){
		if(x-1 >= 0 && y+1 < dy){
			Position pos = new Position(x-1,y+1);
			return pos;
		}else{
			return null;
		}
	}
	/** Avg�r om tv� positioner �r identiska.
	 * 
	 * @param pos						positionen att gemf�ra med
	 * @return							om positionerna �r identiska
	 */
	public boolean equals(Position pos){
		if(pos.getX() == x && pos.getY() == y){
			return true;
		} else {
			return false;
		}
	}
}

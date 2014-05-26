package network;

/** Position �r en klass som representerar en position p� ett tv� dimentionellt plan.
 * 
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
	public Position getPosToEast(){
		Position pos = new Position(x+1,y);
		return  pos;
	}
	/** Returnerar positionen 1 steg �t v�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToWest(){
		Position pos = new Position(x-1,y);
		return  pos;
	} 
	/** Returnerar positionen 1 steg �t norr.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToNorth(){
		Position pos = new Position(x,y-1);
		return pos;
	}
	/** Returnerar positionen 1 steg �t s�der.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToSouth(){
		Position pos = new Position(x,y+1);
		return pos;
	}
	/** Returnerar positionen 1 steg �t nord�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToNorthEast(){
		Position pos = new Position(x+1,y-1);
		return pos;
	}
	/** Returnerar positionen 1 steg �t nordv�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToNorthWest(){
		Position pos = new Position(x-1,y-1);
		return pos;
	}
	/** Returnerar positionen 1 steg �t syd�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToSouthEast(){
		Position pos = new Position(x+1,y+1);
		return pos;
	}
	/** Returnerar positionen 1 steg �t sydv�st.
	 * 
	 * @return							den s�kta positionen
	 */
	public Position getPosToSouthWest(){
		Position pos = new Position(x-1,y+1);
		return pos;
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

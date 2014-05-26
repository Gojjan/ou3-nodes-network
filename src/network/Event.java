package network;

/** Event är en klass som representerar en händelse i ett {@link Network} objekt.
 *  Den används i en implementation av rumor-routing algoritmen.
 *  <p>
 *  Unskiljning mellan händelser sker endast genom ett unikt id. 
 * 
 * @author Viktor Bengtsson
 * @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Event {
	/** Händelsens unika identifikation. */
	private int uniqueID;
	/** {@link Position} där händelsen skedde. */
	private Position homePos;
	/** Under vilket tidssteg som eventet skedde. */
	private int dateOfBirth;
	
	/** Skapar en händelse.
	 * 
	 * @param id			händelsens identifikation	
	 * @param pos			vart händelsen skedde 
	 * @param dob			när händelsen skedde
	 */
	public Event(int id, Position pos, int dob){
		uniqueID = id;
		homePos = pos;
		dateOfBirth = dob;
	}
	public void setID(int id){
		uniqueID = id;
	}
	/** Returnerar händelsens identifikation.
	 * 
	 * @return				händelsens identifikation
	 */
	public int getID(){
		return uniqueID;
	}
	/** Returnerar händelsens födelsetidssteg.
	 * 
	 * @return				tidssteget då händelsen skedde
	 */
	public int getDateOfBirth(){
		return dateOfBirth;
	}
	/** Returnerar händelsens {@link Position} objekt.
	 * 
	 * @return				händelsens {@link Position} objekt
	 */
	public Position getHomePos(){
		return homePos;
	}
	/** Sätter händelsens födelsetidssteg.
	 * 
	 * @param date			födelsetidssteg
	 */
	public void setDateOfBirth(int date){
		dateOfBirth = date;
	}
	/** Sätter händelsens {@link Position} objekt.
	 * 
	 * @param pos			händelsens position.
	 */
	public void setHomePos(Position pos){
		homePos = pos;
	}
}

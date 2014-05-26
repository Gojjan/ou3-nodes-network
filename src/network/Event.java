package network;

/** Event �r en klass som representerar en h�ndelse i ett {@link Network} objekt.
 *  Den anv�nds i en implementation av rumor-routing algoritmen.
 *  <p>
 *  Unskiljning mellan h�ndelser sker endast genom ett unikt id. 
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Maj 26 2014
 */
public class Event {
	/** H�ndelsens unika identifikation. */
	private int uniqueID;
	/** {@link Position} d�r h�ndelsen skedde. */
	private Position homePos;
	/** Under vilket tidssteg som eventet skedde. */
	private int dateOfBirth;
	
	/** Skapar en h�ndelse.
	 * 
	 * @param id			h�ndelsens identifikation	
	 * @param pos			vart h�ndelsen skedde 
	 * @param dob			n�r h�ndelsen skedde
	 */
	public Event(int id, Position pos, int dob){
		uniqueID = id;
		homePos = pos;
		dateOfBirth = dob;
	}
	/** Returnerar h�ndelsens identifikation.
	 * 
	 * @return				h�ndelsens identifikation
	 */
	public int getID(){
		return uniqueID;
	}
	/** Returnerar h�ndelsens f�delsetidssteg.
	 * 
	 * @return				tidssteget d� h�ndelsen skedde
	 */
	public int getDateOfBirth(){
		return dateOfBirth;
	}
	/** Returnerar h�ndelsens {@link Position} objekt.
	 * 
	 * @return				h�ndelsens {@link Position} objekt
	 */
	public Position getHomePos(){
		return homePos;
	}
}

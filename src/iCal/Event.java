package iCal;

import java.sql.Timestamp;
import java.util.Date;

public class Event {

	/**
	 * SUMMARY:
	 * Value Type: String.
	 * Name of the event.
	 */
	private String name;
	
	/**
	 * DTSTAMP:
	 * Value Type: Date-Time.
	 * Time stamp of when the event was made.
	 */
	private String dtstamp;
	
	/**
	 * DSTART / DEND
	 * TSTART / TEND
	 * 
	 * DTSTART, DTEND: Date-Time Start, Date-Time End
	 * Value Type: Date-Time (need to look it up), in the format of:
	 * 19980118T230000, where YYYYMMDDTHHMMSS
	 * Each variable split into two int variables each. Date & Time, so:
	 * DSTART: 19980118, and
	 * TSTART: 230000.
	 * And same for the "end" time variables.
	 */
	private int dstart;
	private int dend;
	private int tstart;
	private int tend;
	/**
	 * GEO: Geographic Position.
	 * Value Type: float.
	 * Format: two semicolon-separated float values.
	 * Specifies latitude and longitude. May be specified up to 6 decimal places.
	 * Optional.
	 * GEO variable split into two float variables. LAT & LONG.
	 */
	private float geoLat;
	private float geoLong;
	
	/**
	 * CLASS: Classification.
	 * Value Type: String.
	 * Values: "PUBLIC" (default), "PRIVATE", "CONFIDENTIAL"
	 */
	private String classification;
	
	/**
	 * COMMENT: Comment.
	 * Value Type: String.
	 * Holds comments. Also for our specific specification, must also hold the Great Circle Distance between two places.
	 * Optional.
	 */
	private String comment;
	
	/**
	 * Initializes an Event object with only name (SUMMARY) and date start & end (DSTART, DEND).
	 * 
	 * Everything else is added by default.
	 * 
	 * @param name String
	 * @param dstart int
	 * @param dend int
	 */
	public Event(String name, int dstart, int dend) {
		this.name = name;
		this.dtstamp = genDTstamp(); // randomly generate when event is created
		this.dstart = dstart;
		this.tstart = 120000; // by default, noon
		this.dend = dend;
		this.tend = 120000; // by default, noon
		this.geoLat = 0;
		this.geoLong = 0;
		this.classification = "PUBLIC"; // by default
		this.comment = "";
	}
	
	/*
		Removed all extra constructors as you can just call a set method if you want to change a property of the item.
	
	*/
	
	// http://www.mkyong.com/java/how-to-get-current-timestamps-in-java/
	private String genDTstamp() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return ts.toString();
	}
	

	/**
	 * setters and getters for all variables
	 * @return
	 */
	
	public int getDstart() {
		return dstart;
	}

	public void setDstart(int dstart) {
		this.dstart = dstart;
	}

	public int getDend() {
		return dend;
	}

	public void setDend(int dend) {
		this.dend = dend;
	}

	public int getTstart() {
		return tstart;
	}

	public void setTstart(int tstart) {
		this.tstart = tstart;
	}

	public int getTend() {
		return tend;
	}

	public void setTend(int tend) {
		this.tend = tend;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float getGeoLat() {
		return geoLat;
	}

	public void setGeoLat(float geoLat) {
		this.geoLat = geoLat;
	}

	public float getGeoLong() {
		return geoLong;
	}

	public void setGeoLong(float geoLong) {
		this.geoLong = geoLong;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Appends a new comment at the end of the previous comment.
	 * @param acomment
	 */
	public void appendComment(String acomment) {
		this.comment += "\n" + acomment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * toString: overwrites basic toString.
	 * Returns string representing the event, according to variables.
	 * Returns a string in the format to be printed into an .ics file.
	 * 
	 */
	public String toString() {
		String event = "BEGIN:VEVENT\n";
		// if both time and date are initialized (they are not the same)
		if (tstart != tend) {
			event += "DTSTART:" + this.dstart + "T" + this.tstart + "\n";
			event += "DTEND:" + this.dend + "T" + this.tend + "\n";
		}
		else { // then only dates are initialized
			event += "DTSTART;VALUE=DATE:" + this.dstart + "\n";
			event += "DTEND;VALUE=DATE:" + this.dend + "\n";
		}
		event += "UID:" + this.dtstamp + "user@email.com\n";
		event += "SUMMARY:" + this.name + "\n";
		event += "GEO:" + this.geoLat + ";" + this.geoLong + "\n";
		event += "CLASS:" + this.classification + "\n";
		event += "END:VEVENT\n";
		return event;
	}
}

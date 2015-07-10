package iCal;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
	private ZonedDateTime dtstamp;
	
	/**
	 * DSTART / DEND
	 * TSTART / TEND
	 * 
	 * DTSTART, DTEND: Date-Time Start, Date-Time End
	 * Value Type: Date-Time (need to look it up), in the format of:
	 * 19980118T230000, where YYYYMMDDTHHMMSS
	 * Using ZonedDateTime class, to store both the date and time.
	 */
	private ZonedDateTime dtstart;
	private ZonedDateTime dtend;
	/**
	 * GEO: Geographic Position.
	 * Value Type: float.
	 * Format: two semicolon-separated float values.
	 * Specifies latitude and longitude. May be specified up to 6 decimal places.
	 * Optional.
	 * Using LatLong class.
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
	public Event(String name, ZonedDateTime dtstart, ZonedDateTime dtend) {
		this.name = name;
		this.dtstamp = genDTstamp(); // randomly generate when event is created
		this.dtstart = dtstart;
		this.dtend = dtend;
		this.geoLat = 0;
		this.geoLong = 0;
		this.classification = "PUBLIC"; // by default
		this.comment = "";
	}
	
	/*
		Removed all extra constructors as you can just call a set method if you want to change a property of the item.
	
	*/
	
	public ZonedDateTime getDtstart() {
		return dtstart;
	}

	public void setDtstart(ZonedDateTime dtstart) {
		this.dtstart = dtstart;
	}

	public ZonedDateTime getDtend() {
		return dtend;
	}

	public void setDtend(ZonedDateTime dtend) {
		this.dtend = dtend;
	}

	// http://www.mkyong.com/java/how-to-get-current-timestamps-in-java/
	private ZonedDateTime genDTstamp() {
		return ZonedDateTime.now();
	}
	

	/**
	 * setters and getters for all variables
	 * @return
	 */
	


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
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYYMMdd");
		DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("YYYYMMdd'T'HHmmss");
		String event = "BEGIN:VEVENT\n";
		event += "DTSTART:" + dtstart.format(datetimeFormat) + "\n";
		// event += "DTSTART;VALUE=DATE:" + dtstart.format(dateFormat) + "\n";
		event += "DTEND:" + dtend.format(datetimeFormat) + "\n";
		// event += "DTEND;VALUE=DATE:" + dtend.format(dateFormat) + "\n";
		// replace user@email.com with the user's name or something
		event += "UID:" + dtstamp.format(datetimeFormat) + "user@email.com\n";
		event += "SUMMARY:" + this.name + "\n";
		event += "GEO:" + this.geoLat + ";" + this.geoLong + "\n";
		event += "CLASS:" + this.classification + "\n";
		event += "END:VEVENT\n";
		return event;
	}
}

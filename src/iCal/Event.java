package iCal;

public class Event {

	/**
	 * NAME:
	 * Value Type: String.
	 * Name of the event.
	 */
	private String name;
	/**
	 * DTSTART / DTEND: DateTime Start, DateTime End
	 * Value Type: Date-Time (need to look it up), can be set to Date value type
	 * Hold the variable.
	 */
	private int dtstart;
	private int dtend;
	/**
	 * GEO: Geographic Position.
	 * Value Type: float.
	 * Format: two semicolon-separated float values.
	 * Specifies latitude and longitude. May be specified up to 6 decimal places.
	 * Optional.
	 */
	private float geo;
	
	/**
	 * CLASS: Classification.
	 * Value Type: String.
	 * Values: "PUBLIC" (default), "PRIVATE", "CONFIDENTIAL", x-name, iana-token, etc 
	 */
	private String classification;
	
	/**
	 * COMMENT: Comment.
	 * Value Type: String.
	 * Holds comments. Also for our specific specification, must also hold the Great Circle Distance between two places.
	 */
	private String comment;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Event() {
		
	}
	

	/**
	 * setters and getters for all variables
	 * @return
	 */
	public int getDtstart() {
		return dtstart;
	}

	public void setDtstart(int dtstart) {
		this.dtstart = dtstart;
	}

	public int getDtend() {
		return dtend;
	}

	public void setDtend(int dtend) {
		this.dtend = dtend;
	}

	public float getGeo() {
		return geo;
	}

	public void setGeo(float geo) {
		this.geo = geo;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * toString: overwrites basic toString.
	 * Returns string representing the event, according to variables.
	 * Must be in the format needed to be printed onto an .ics file.
	 * 
	 */
	public String toString() {
		String event = "";
		return event;
	}
}

package iCal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Calendar {
	
	/**
	 * VERSION: Version.
	 * Value Type: String.
	 * Specifies what version the Calendar app needs to be to read this file.
	 */
	private String version;
	/**
	 * calScale:
	 * Determines calendar type used. Gregorian by default?
	 */
	private String calScale;
	/**
	 * calName:
	 * Stores name of the Calendar. possibility of adding different calendars.
	 */
	private String calName;
	/**
	 * TZID: Time Zone Identifier.
	 * Value Type: String.
	 * Stores the timezone of the Calendar.
	 * Use the naming convention of public-domain TZ database.
	 * Maybe need a drop-down menu to select timezones.
	 */
	private String tzid;
	
	/**
	 * calendar: stores events in an Arraylist. is sortable with Comparator.
	 */
	private ArrayList<Event> calendar;
	
	
	/**
	 *
	 */
	public Calendar(String calScale, String calName, String timezone) {
		this.setVersion("2.0");
		this.setCalScale(calScale);
		this.setCalName(calName);
		this.setTimezone(timezone);
		this.calendar = new ArrayList<Event>();
	}
	

	/** 
	 * Basic getters and setters for all variables of Calendar.
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCalScale() {
		return calScale;
	}

	public void setCalScale(String calScale) {
		this.calScale = calScale;
	}

	public String getCalName() {
		return calName;
	}

	public void setCalName(String calName) {
		this.calName = calName;
	}

	public String getTimezone() {
		return tzid;
	}

	public void setTimezone(String timezone) {
		this.tzid = timezone;
	};

	
	/**
	 * addEvent
	 * @param event
	 * 
	 * Takes an event object and adds it to the Calendar object.
	 * Duplicate events not added.
	 * Either add events at the end and call the sort method, or
	 * iterate through and add it at the correct place. Maybe the former, since it seems like less work to program?
	 */
	public void addEvent(Event event) {
		calendar.add(event);
		sortEvents();
	}
	
	/**
	 * removeEvent
	 * 
	 * Takes an event object and removes instances of it from the Calendar object.
	 * http://www.tutorialspoint.com/java/util/arraylist_remove_object.htm
	 * @param event
	 * @return whether event was removed or not
	 */
	public boolean removeEvent(Event event) {
		return calendar.remove(event);
	}
	
	
	/**
	 * sortEvents
	 * 
	 * Needs some sort of functionality to be able to sort events by their times. Right now, could use Comparators & collections sort, though we can also keep it sorted from the get go in addEvent.
	 * Example of comparator sort: http://java2novice.com/java-collections-and-util/arraylist/sort-comparator/
	 */
	public void sortEvents() {
		Collections.sort(calendar, new EventComparator());
	}
	
	/**
	 * generateFile
	 * 
	 * Generates .ics file with all the events in the calendar.
	 * Needs to print it according to how an .ics file should be specified.
	 */
	public void generateFile() {};
	
}

class EventComparator implements Comparator<Event> {
	
	@Override
	public int compare(Event e1, Event e2) {
		// find difference in start dates
		int dif = e2.getDstart() - e1.getDstart();
		// if they are the same
		if (dif == 0) {
			// find difference in start times
			int tdif = e2.getTstart() - e1.getTstart();
			// if there is no difference, find difference in end time
			if (tdif == 0) {
				int ddif = e2.getTend() - e1.getTend();
				return ddif;
			}
			else {
				return tdif;
			}
		}
		else {
			return dif;
		}
	}
}
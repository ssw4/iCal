package iCal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.TimeZone;

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
	 * Using TimeZone class.
	 */
	private TimeZone tz;
	
	/**
	 * calendar: stores events in an Arraylist. is sortable with Comparator.
	 */
	private ArrayList<Event> calendar;
	
	
	/**
	 *
	 */
	public Calendar(String calScale, String calName, TimeZone timezone) {
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

	public TimeZone getTimezone() {
		return tz;
	}

	public void setTimezone(TimeZone timezone) {
		this.tz = timezone;
	};
	
	public ArrayList<Event> getCalendar() {
		return calendar;
	}

	
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
	public String generateFile() {
		String calFile = "BEGIN:VCALENDAR\r\n";
		calFile += "VERSION:" + this.version + "\r\n";
		calFile += "CALSCALE:" + this.calScale + "\r\n";
		calFile += "X-WR-CALNAME:" + this.calName + "\r\n";
		calFile += "X-WR-TIMEZONE:" +this.tz.getID() + "\r\n";
		for (Event event : calendar) {
			calFile += event.toString();
		}
		calFile += "END:VCALENDAR\r\n";
		return calFile;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return calName + "\n" + version + "\n" + calScale + "\n" + tz;
		
	}
	
	public void calculateDistances() {
		ListIterator<Event> iterator = calendar.listIterator();
		while (iterator.hasNext()) {
			if (iterator.hasPrevious()) {
				Event e1 = calendar.get(iterator.previousIndex()); // gets the previous event
				Event e2 = calendar.get(iterator.nextIndex()); // gets the next event
				
				if (e1.getGeoLat() != 0 && e1.getGeoLong() != 0 && e2.getGeoLat() != 0 && e2.getGeoLong() != 0) {
					String distance = Float.toString(calculateDistance(e1.getGeoLat(), e1.getGeoLong(), e2.getGeoLat(), e2.getGeoLong()));
					e1.appendComment("Distance to next event: " + distance + "m");
				}
			}
			iterator.next();
		}
	}
	
	public float calculateDistance(float e1lat, float e1long, float e2lat, float e2long) {
		double r = 6371000; // earth's radius in meters
		
		// convert everything to radians
	    double radLat1 = Math.toRadians(e1lat);
	    double radLat2 = Math.toRadians(e2lat);
	    double dLat = Math.toRadians(e2lat-e1lat);
	    double dLong =  Math.toRadians(e2long-e1long);

	    // get the cosine and the sine

	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(dLong/2) * Math.sin(dLong/2);
	    
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

	    return (float) (c * r);
	}
	
}

class EventComparator implements Comparator<Event> {
	
	@Override
	public int compare(Event e1, Event e2) {
		return e1.getDtstart().compareTo(e2.getDtstart());
	}
}
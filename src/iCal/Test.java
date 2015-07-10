package iCal;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Class that tests Events and Calendars.
 * 
 * @author Sharon
 *
 */
public class Test {

	public static void main(String[] args) {
		Calendar cal = new Calendar("Gregorian", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYYMMdd");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
		
		ZonedDateTime dtsample = ZonedDateTime.now();
		System.out.println(dtsample.format(timeFormat));
		System.out.println(dtsample.format(dateFormat));
		Event e1 = new Event("Event1", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
		Event e2 = new Event("Event2", ZonedDateTime.now().plusHours(2), ZonedDateTime.now().plusHours(5));
		Event e3 = new Event("Event3", ZonedDateTime.now().minusDays(2), ZonedDateTime.now().plusHours(2));
		cal.addEvent(e1);
		cal.addEvent(e2);
		cal.addEvent(e3);
		System.out.println(cal.generateFile());
	}

}

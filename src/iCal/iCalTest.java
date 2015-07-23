package iCal;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.junit.Test;

public class iCalTest {

	@Test
	public void testWriteICSFile() {
		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("America/Los_Angeles"));
		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
		ZonedDateTime time2 = time1.plusHours(2);
		ZonedDateTime time3 = time2.plusHours(1);
		ZonedDateTime time4 = time3.plusHours(3);
		ZonedDateTime time5 = time4.plusDays(1);
		ZonedDateTime time6 = time5.plusDays(4);
		ZonedDateTime time7 = time6.plusHours(4);
		ZonedDateTime time8 = time7.plusHours(2);
		
		// Hamilton Library
		float geo1lat = (float) 21.300126;
		float geo1long = (float) -157.816246;
		
		// Sinclair Library
		float geo2lat = (float) 21.299222;
		float geo2long = (float) -157.820306;
		
		// Kennedy Theater
		float geo3lat = (float) 21.298978;
		float geo3long = (float) -157.814606;
		
		// QLC
		float geo4lat = (float) 21.300067;
		float geo4long = (float) -157.818548;
		
		// Bookstore
		float geo5lat = (float) 21.298389;
		float geo5long = (float) -157.818995;
		
		Event e1 = new Event("Event1", time1, time2);
		Event e2 = new Event("Event2", time3, time4);
		Event e3 = new Event("Event3", time5, time6);
		Event e4 = new Event("Event4", time7, time8);
		e1.setGeoLat(geo1lat); // Hamilton Library
		e1.setGeoLong(geo1long);
		e3.setGeoLat(geo3lat); // Kennedy Theater
		e3.setGeoLong(geo3long);
		e4.setGeoLat(geo4lat);
		e4.setGeoLong(geo4long);
		cal.addEvent(e2); // switch order it was added
		cal.addEvent(e3);
		cal.addEvent(e1);
		cal.addEvent(e4);
		cal.calculateDistances();
		
		iCal.writeICSFile("Calendar1", cal);
		Calendar cal2 = iCal.openICSFile("Calendar1.ics");
		
		assertEquals(cal2.getCalendar().size(), cal.getCalendar().size());
		assertEquals(cal2.getCalendar().get(0).getName(), cal.getCalendar().get(0).getName());
		assertEquals(cal2.getCalendar().get(1).getName(), cal.getCalendar().get(1).getName());
		assertEquals(cal2.getCalendar().get(2).getName(), cal.getCalendar().get(2).getName());
		assertEquals(cal2.getCalendar().get(3).getName(), cal.getCalendar().get(3).getName());
		assertEquals(cal2.getCalendar().get(0).getComment(), cal.getCalendar().get(0).getComment());
		assertEquals(cal2.getCalendar().get(1).getComment(), cal.getCalendar().get(1).getComment());
		assertEquals(cal2.getCalendar().get(2).getComment(), cal.getCalendar().get(2).getComment());
		assertEquals(cal2.getCalendar().get(3).getComment(), cal.getCalendar().get(3).getComment());
	}

}

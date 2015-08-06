//package iCal;
//
//import static org.junit.Assert.*;
//
//import java.time.ZonedDateTime;
//import java.util.TimeZone;
//
//import org.junit.Test;
//
///**
// * https://courses.cs.washington.edu/courses/cse143/11wi/eclipse-tutorial/junit.shtml
// * @author Sharon
// *
// */
//public class CalendarTest {
//
//	@Test
//	public void testAddEvent() {
//		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
//		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
//		ZonedDateTime time2 = time1.plusHours(2);
//		ZonedDateTime time3 = time2.plusHours(1);
//		ZonedDateTime time4 = time3.plusHours(3);
//		ZonedDateTime time5 = time4.plusDays(1);
//		ZonedDateTime time6 = time5.plusDays(4);
//		
//		Event e1 = new Event("Event1", time1, time2);
//		Event e2 = new Event("Event2", time3, time4);
//		Event e3 = new Event("Event3", time5, time6);
//		cal.addEvent(e1);
//		cal.addEvent(e2);
//		cal.addEvent(e3);
//		
//		assertEquals(3, cal.getCalendar().size());
//		assertEquals(e1, cal.getCalendar().get(0));
//		assertEquals(e2, cal.getCalendar().get(1));
//		assertEquals(e3, cal.getCalendar().get(2));
//		assertTrue(cal.getCalendar().contains(e1));
//		assertTrue(cal.getCalendar().contains(e2));
//		assertTrue(cal.getCalendar().contains(e3));
//		assertFalse(cal.getCalendar().isEmpty());
//	}
//	
//
//	@Test
//	public void testRemoveEvent() {
//		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
//		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
//		ZonedDateTime time2 = time1.plusHours(2);
//		ZonedDateTime time3 = time2.plusHours(1);
//		ZonedDateTime time4 = time3.plusHours(3);
//		ZonedDateTime time5 = time4.plusDays(1);
//		ZonedDateTime time6 = time5.plusDays(4);
//		
//		Event e1 = new Event("Event1", time1, time2);
//		Event e2 = new Event("Event2", time3, time4);
//		Event e3 = new Event("Event3", time5, time6);
//		cal.addEvent(e2); // switch order it was added
//		cal.addEvent(e3);
//		cal.addEvent(e1);
//		
//		assertEquals(3, cal.getCalendar().size());
//		assertEquals(e1, cal.getCalendar().get(0));
//		assertEquals(e2, cal.getCalendar().get(1));
//		assertEquals(e3, cal.getCalendar().get(2));
//		assertTrue(cal.getCalendar().contains(e1));
//		assertTrue(cal.getCalendar().contains(e2));
//		assertTrue(cal.getCalendar().contains(e3));
//		
//		cal.removeEvent(e1);
//		assertEquals(2, cal.getCalendar().size());
//		assertEquals(e2, cal.getCalendar().get(0));
//		assertEquals(e3, cal.getCalendar().get(1));
//		assertFalse(cal.getCalendar().contains(e1));
//		assertTrue(cal.getCalendar().contains(e2));
//		assertTrue(cal.getCalendar().contains(e3));
//		
//		cal.removeEvent(e2);
//		assertEquals(1, cal.getCalendar().size());
//		assertEquals(e3, cal.getCalendar().get(0));
//		assertFalse(cal.getCalendar().contains(e1));
//		assertFalse(cal.getCalendar().contains(e2));
//		assertTrue(cal.getCalendar().contains(e3));
//		
//		cal.removeEvent(e3);
//		assertFalse(cal.getCalendar().contains(e1));
//		assertFalse(cal.getCalendar().contains(e2));
//		assertFalse(cal.getCalendar().contains(e3));
//		assertTrue(cal.getCalendar().isEmpty());
//	}
//
//	@Test
//	public void testSortEvents() {
//		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
//		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
//		ZonedDateTime time2 = time1.plusHours(2);
//		ZonedDateTime time3 = time2.plusHours(1);
//		ZonedDateTime time4 = time3.plusHours(3);
//		ZonedDateTime time5 = time4.plusDays(1);
//		ZonedDateTime time6 = time5.plusDays(4);
//		
//		Event e1 = new Event("Event1", time1, time2);
//		Event e2 = new Event("Event2", time3, time4);
//		Event e3 = new Event("Event3", time5, time6);
//		cal.addEvent(e2); // switch order it was added
//		cal.addEvent(e3);
//		cal.addEvent(e1);
//		
//		assertEquals(3, cal.getCalendar().size());
//		assertEquals(e1, cal.getCalendar().get(0));
//		assertEquals(e2, cal.getCalendar().get(1));
//		assertEquals(e3, cal.getCalendar().get(2));
//		assertTrue(cal.getCalendar().contains(e1));
//		assertTrue(cal.getCalendar().contains(e2));
//		assertTrue(cal.getCalendar().contains(e3));
//		assertFalse(cal.getCalendar().isEmpty());
//	}
//
//	@Test // calculates distances properly given all three have locations
//	public void testCalculateDistances() {
//		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
//		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
//		ZonedDateTime time2 = time1.plusHours(2);
//		ZonedDateTime time3 = time2.plusHours(1);
//		ZonedDateTime time4 = time3.plusHours(3);
//		ZonedDateTime time5 = time4.plusDays(1);
//		ZonedDateTime time6 = time5.plusDays(4);
//		
//		// Hamilton Library
//		float geo1lat = (float) 21.300126;
//		float geo1long = (float) -157.816246;
//		
//		// Sinclair Library
//		float geo2lat = (float) 21.299222;
//		float geo2long = (float) -157.820306;
//		
//		// Kennedy Theater
//		float geo3lat = (float) 21.298978;
//		float geo3long = (float) -157.814606;
//		
//		// QLC
//		float geo4lat = (float) 21.300067;
//		float geo4long = (float) -157.818548;
//		
//		// Bookstore
//		float geo5lat = (float) 21.298389;
//		float geo5long = (float) -157.818995;
//		
//		Event e1 = new Event("Event1", time1, time2);
//		Event e2 = new Event("Event2", time3, time4);
//		Event e3 = new Event("Event3", time5, time6);
//		e1.setGeoLat(geo1lat); // Hamilton Library
//		e1.setGeoLong(geo1long);
//		e2.setGeoLat(geo2lat); // Sinclair Library
//		e2.setGeoLong(geo2long);
//		e3.setGeoLat(geo3lat); // Kennedy Theater
//		e3.setGeoLong(geo3long);
//		cal.addEvent(e2); // switch order it was added
//		cal.addEvent(e3);
//		cal.addEvent(e1);
//		cal.calculateDistances();
//		
//		String geo1togeo2 = Float.toString(cal.calculateDistance(geo1lat, geo1long, geo2lat, geo2long));
//		String geo2togeo3 = Float.toString(cal.calculateDistance(geo2lat, geo2long, geo3lat, geo3long));
//	
//		assertTrue(cal.getCalendar().get(0).getComment().contains(geo1togeo2));
//		assertFalse(cal.getCalendar().get(1).getComment().contains(geo1togeo2));
//		assertTrue(cal.getCalendar().get(1).getComment().contains(geo2togeo3));
//		assertFalse(cal.getCalendar().get(2).getComment().contains(geo2togeo3));
//	}
//	
//	/**
//	 * calculates distances given only the first two have distances
//	 */
//	@Test
//	public void testCalculateDistances2() {
//		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
//		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
//		ZonedDateTime time2 = time1.plusHours(2);
//		ZonedDateTime time3 = time2.plusHours(1);
//		ZonedDateTime time4 = time3.plusHours(3);
//		ZonedDateTime time5 = time4.plusDays(1);
//		ZonedDateTime time6 = time5.plusDays(4);
//		
//		// Hamilton Library
//		float geo1lat = (float) 21.300126;
//		float geo1long = (float) -157.816246;
//		
//		// Sinclair Library
//		float geo2lat = (float) 21.299222;
//		float geo2long = (float) -157.820306;
//		
//		// Kennedy Theater
//		float geo3lat = (float) 21.298978;
//		float geo3long = (float) -157.814606;
//		
//		// QLC
//		float geo4lat = (float) 21.300067;
//		float geo4long = (float) -157.818548;
//		
//		// Bookstore
//		float geo5lat = (float) 21.298389;
//		float geo5long = (float) -157.818995;
//		
//		Event e1 = new Event("Event1", time1, time2);
//		Event e2 = new Event("Event2", time3, time4);
//		Event e3 = new Event("Event3", time5, time6);
//		e1.setGeoLat(geo1lat); // Hamilton Library
//		e1.setGeoLong(geo1long);
//		e2.setGeoLat(geo2lat); // Sinclair Library
//		e2.setGeoLong(geo2long);
//		cal.addEvent(e2); // switch order it was added
//		cal.addEvent(e3);
//		cal.addEvent(e1);
//		cal.calculateDistances();
//		
//		String geo1togeo2 = Float.toString(cal.calculateDistance(geo1lat, geo1long, geo2lat, geo2long));
//		String geo2togeo3 = Float.toString(cal.calculateDistance(geo2lat, geo2long, geo3lat, geo3long));
//	
//		assertTrue(cal.getCalendar().get(0).getComment().contains(geo1togeo2));
//		assertFalse(cal.getCalendar().get(1).getComment().contains(geo1togeo2));
//		assertFalse(cal.getCalendar().get(1).getComment().contains(geo2togeo3));
//		assertFalse(cal.getCalendar().get(2).getComment().contains(geo2togeo3));
//	}
//	
//	@Test // calculates distances given event1 and event3 have locations but event 2 doesn't
//	public void testCalculateDistances3() {
//		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
//		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
//		ZonedDateTime time2 = time1.plusHours(2);
//		ZonedDateTime time3 = time2.plusHours(1);
//		ZonedDateTime time4 = time3.plusHours(3);
//		ZonedDateTime time5 = time4.plusDays(1);
//		ZonedDateTime time6 = time5.plusDays(4);
//		
//		// Hamilton Library
//		float geo1lat = (float) 21.300126;
//		float geo1long = (float) -157.816246;
//		
//		// Sinclair Library
//		float geo2lat = (float) 21.299222;
//		float geo2long = (float) -157.820306;
//		
//		// Kennedy Theater
//		float geo3lat = (float) 21.298978;
//		float geo3long = (float) -157.814606;
//		
//		// QLC
//		float geo4lat = (float) 21.300067;
//		float geo4long = (float) -157.818548;
//		
//		// Bookstore
//		float geo5lat = (float) 21.298389;
//		float geo5long = (float) -157.818995;
//		
//		Event e1 = new Event("Event1", time1, time2);
//		Event e2 = new Event("Event2", time3, time4);
//		Event e3 = new Event("Event3", time5, time6);
//		e1.setGeoLat(geo1lat); // Hamilton Library
//		e1.setGeoLong(geo1long);
//		e3.setGeoLat(geo3lat); // Kennedy Theater
//		e3.setGeoLong(geo3long);
//		cal.addEvent(e2); // switch order it was added
//		cal.addEvent(e3);
//		cal.addEvent(e1);
//		cal.calculateDistances();
//		
//		String geo1togeo2 = Float.toString(cal.calculateDistance(geo1lat, geo1long, geo2lat, geo2long));
//		String geo2togeo3 = Float.toString(cal.calculateDistance(geo2lat, geo2long, geo3lat, geo3long));
//	
//		assertFalse(cal.getCalendar().get(0).getComment().contains(geo1togeo2));
//		assertFalse(cal.getCalendar().get(1).getComment().contains(geo1togeo2));
//		assertFalse(cal.getCalendar().get(1).getComment().contains(geo2togeo3));
//		assertFalse(cal.getCalendar().get(2).getComment().contains(geo2togeo3));
//	}
//	
//	@Test // calculates distances given event1, event3, event4 have locations but event 2 doesn't
//	public void testCalculateDistances4() {
//		Calendar cal = new Calendar("GREGORIAN", "TestCal", TimeZone.getTimeZone("Pacific/Honolulu"));
//		ZonedDateTime time1 = ZonedDateTime.now().plusDays(1);
//		ZonedDateTime time2 = time1.plusHours(2);
//		ZonedDateTime time3 = time2.plusHours(1);
//		ZonedDateTime time4 = time3.plusHours(3);
//		ZonedDateTime time5 = time4.plusDays(1);
//		ZonedDateTime time6 = time5.plusDays(4);
//		ZonedDateTime time7 = time6.plusHours(4);
//		ZonedDateTime time8 = time7.plusHours(2);
//		
//		// Hamilton Library
//		float geo1lat = (float) 21.300126;
//		float geo1long = (float) -157.816246;
//		
//		// Sinclair Library
//		float geo2lat = (float) 21.299222;
//		float geo2long = (float) -157.820306;
//		
//		// Kennedy Theater
//		float geo3lat = (float) 21.298978;
//		float geo3long = (float) -157.814606;
//		
//		// QLC
//		float geo4lat = (float) 21.300067;
//		float geo4long = (float) -157.818548;
//		
//		// Bookstore
//		float geo5lat = (float) 21.298389;
//		float geo5long = (float) -157.818995;
//		
//		Event e1 = new Event("Event1", time1, time2);
//		Event e2 = new Event("Event2", time3, time4);
//		Event e3 = new Event("Event3", time5, time6);
//		Event e4 = new Event("Event4", time7, time8);
//		e1.setGeoLat(geo1lat); // Hamilton Library
//		e1.setGeoLong(geo1long);
//		e3.setGeoLat(geo3lat); // Kennedy Theater
//		e3.setGeoLong(geo3long);
//		e4.setGeoLat(geo4lat);
//		e4.setGeoLong(geo4long);
//		cal.addEvent(e2); // switch order it was added
//		cal.addEvent(e3);
//		cal.addEvent(e1);
//		cal.addEvent(e4);
//		cal.calculateDistances();
//		
//		String geo1togeo2 = Float.toString(cal.calculateDistance(geo1lat, geo1long, geo2lat, geo2long));
//		String geo2togeo3 = Float.toString(cal.calculateDistance(geo2lat, geo2long, geo3lat, geo3long));
//		String geo3togeo4 = Float.toString(cal.calculateDistance(geo3lat, geo3long, geo4lat, geo4long));
//		
//		assertFalse(cal.getCalendar().get(0).getComment().contains(geo1togeo2));
//		assertFalse(cal.getCalendar().get(1).getComment().contains(geo1togeo2));
//		assertFalse(cal.getCalendar().get(1).getComment().contains(geo2togeo3));
//		assertFalse(cal.getCalendar().get(2).getComment().contains(geo2togeo3));
//		assertTrue(cal.getCalendar().get(2).getComment().contains(geo3togeo4));
//		assertFalse(cal.getCalendar().get(3).getComment().contains(geo3togeo4));
//	}
//	
//}

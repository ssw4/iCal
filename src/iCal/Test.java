package iCal;

/**
 * Class that tests Events and Calendars.
 * 
 * @author Sharon
 *
 */
public class Test {

	public static void main(String[] args) {
		Calendar cal = new Calendar("Gregorian", "TestCal", "Pacific/Honolulu");
		Event eve1 = new Event("TestEvent1", 20150715, 20150720);
		Event eve2 = new Event("TestEvent2", 20150720, 20150720);
		Event eve3 = new Event("TestEvent3", 20150721, 20150722);
		cal.addEvent(eve1);
		cal.addEvent(eve2);
		cal.addEvent(eve3);
		System.out.println(cal.generateFile());
	}

}

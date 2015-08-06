package iCal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

/*
 * iCal
 * holds the main function
 * creates user interface
 * creates event files
 * reads event files
*/

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimeZone;

public class iCal {

	// holds the only calendar in the program
	private static Calendar cal;

	public static void main(String[] args) {

		/*
		 * Menu display. > Load .ics files into a placeholder calendar, that's
		 * only for that certain open instance. > Can load events into another
		 * calendar?
		 */

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String eName; // Event name
		String fileName; // Name of file with Calendars
		int sDay = 0, eDay = 0; // Start Day, End Day
		int sMonth = 0, eMonth = 0; // Start Month, End Month
		int sYear = 0, eYear = 0; // Start Year, End Year
//		int hours; // Event hours
		int timeChoice; // Use to decide between a.m and p.m
		int menuChoice = 0; // Displayed menu choice
//		float duration; // Event duration, stored in minutes
		float sTime = -1; // Start Time
		float eTime = -1; // End Time
//		float minutes; // Event duration
		int specification = 0; // event specification
		String spec = "";
		int location = 0;
		int locChoice = 0;
		float geoLat = 0;
		float geoLong = 0;
		int comment = 0;
		String com = "";
		boolean flag = false;
		int cont = 0;
		int save = 0;

		do {
			System.out
					.println("\n(1) Create new event \n(2) Remove Event \n(3) Open New Calender\n(4) Save Calender\n");
			do {
				flag = false;
				try {
					do {
						System.out.println("Please enter the number of your menu choice.");
						menuChoice = input.nextInt();
						input.nextLine();
					} while (menuChoice != 1 && menuChoice != 2 && menuChoice != 3 && menuChoice != 4);
				}

				catch (Exception e) {
					flag = true;
					input.nextLine();
				}
			} while (flag == true);

			/*
			 * Getting info for the new event because user chose option 1
			 */

			if (menuChoice == 1) {
				// need to check for if the calendar already exists. if it does
				// not
				// already exist, use createCalendar(), getting the input for
				// the
				// name of the calendar and the timezone

				if (cal == null) {

					System.out.println("There is no existing calendar. Creating new calendar...");

					System.out.println("Please enter the name for your calendar.");
					String calname = input.nextLine();
					System.out.println(calname);

					/*
					 * Getting TimeZone from the user
					 */

					System.out.println("Please enter the time zone for the calendar by the labeled number:");
					System.out.println("(1) Pacific/Honolulu (Hawaii)");
					System.out.println("(2) America/NewYork (Eastern)");
					System.out.println("(3) America/Chicago (Central)");
					System.out.println("(4) America/Los-Angeles (Pacific)");

					do {
						flag = false;

						try {
							do {
								System.out.println("Please enter the number of your choice.");
								menuChoice = input.nextInt();
								input.nextLine();
							} while (menuChoice != 1 && menuChoice != 2 && menuChoice != 3 && menuChoice != 4);
						}

						catch (Exception e) {
							flag = true;
							input.nextLine();
						}

					} while (flag == true);

					/*
					 * To convert to time zone using the menu option chosen by
					 * the user
					 */

					String timezone = "";
					if (menuChoice == 1)
						timezone = "Pacific/Honolulu";
					else if (menuChoice == 2)
						timezone = "America/New_York";
					else if (menuChoice == 3)
						timezone = "America/Chicago";
					else
						timezone = "America/Los_Angeles";

					createCalendar(calname, timezone);
				}

				System.out.println("Please enter the name of your event.");
				eName = input.nextLine();
				System.out.println(eName);

				/*
				 * Display a menu for user to decided what type of event
				 */

				System.out.println(" (1) General\n (2) To-Do\n (3) Urgent\n");
				do {
					flag = false;
					try {
						do {
							System.out.println("Please specify the classification of " + eName);
							specification = input.nextInt();
							input.nextLine();
						} while (specification != 1 && specification != 2 && specification != 3);

					} catch (Exception e) {
						flag = true;
						input.nextLine();
					}
				} while (flag == true);

				switch (specification) {
				case 1:
					spec = "General";
					break;
				case 2:
					spec = "To-Do";
					break;
				case 3:
					spec = "Urgent";
					break;
				default:
					spec = "General";
					break;
				}

				do {
					flag = false;
					try {
						do {
							System.out.println("Please enter the start date of event, ex 01 15 2015");
							sMonth = input.nextInt();
							sDay = input.nextInt();
							sYear = input.nextInt();
						} while (sMonth > 12 || sMonth < 1 || sDay > 31 || sDay < 1 || sYear > 9999 || sYear < 0);
						flag = false;
					} catch (Exception e) {
						flag = true;
						input.next();
					}
				} while (flag == true);

				/*
				 * Determining if event ends on same day as start date or if
				 * event runs more than 1 day
				 */

				do {
					System.out.println("Enter (1) if " + eName + " ends on " + sMonth + "/" + sDay + "/" + sYear);
					System.out.println("Otherwise enter (2).");
					menuChoice = input.nextInt();
				} while (menuChoice != 1 && menuChoice != 2);

				/*
				 * If the event is during 1 day, set start and end date = to
				 * each other Otherwise get the end date and verify its after
				 * the starting date
				 */

				if (menuChoice == 1) {
					eDay = sDay;
					eMonth = sMonth;
					eYear = sYear;
				} else {
					do {
						flag = false;
						try {
							do {
								System.out.println("Please enter the end date of event, ex 01 15 2015");
								eMonth = input.nextInt();
								eDay = input.nextInt();
								eYear = input.nextInt();

								if ((eDay - sDay < 0 && (eMonth - sMonth) <= 0 && eYear <= sYear) || eYear < sYear) {
									System.out
											.println("End date needs to be after " + sMonth + "/" + sDay + "/" + sYear);
									flag = true;
								}

							} while (eMonth > 12 || eMonth < 1 || eDay > 31 || eDay < 1 || eYear > 9999 || eYear < 0);
							flag = false;
						} catch (Exception e) {
							flag = true;
							input.next();
						}
					} while (flag == true);
				}

				/*
				 * Getting the start time of the event
				 */

				do {
					System.out.println("What time does " + eName + " start? (ex: 7.15)");
					sTime = input.nextFloat();

				} while (sTime > 13 || sTime < 0);

				/*
				 * Determine if event starts in the am or pm
				 */

				do {
					System.out.println("Enter (1) AM or (2) PM");
					timeChoice = input.nextInt();

					if (timeChoice == 2 && (int) sTime != 12) {
						sTime += 12;
					}
				} while (timeChoice != 1 && timeChoice != 2);

				do {
					System.out.println("What time does " + eName + " end? (ex: 10.05)");
					eTime = input.nextFloat();

				} while (eTime > 13 || eTime < 0);

				/*
				 * Determine if event ends in the am or pm
				 */

				do {
					System.out.println("Enter (1) AM or (2) PM");
					timeChoice = input.nextInt();

					if (timeChoice == 2 && (int) eTime != 12) {
						eTime += 12;
					}

				} while (timeChoice != 1 && timeChoice != 2);

//				/*
//				 * Computing the duration of the event
//				 */
//
//				hours = (int) eTime - (int) sTime;
//				minutes = 100 * ((sTime - (int) sTime) - (eTime - (int) eTime));
//
//				if (minutes > 0) {
//					hours--;
//					minutes = 60 - minutes;
//				}
//
//				else {
//					minutes = (100 * ((eTime - (int) eTime) - (sTime - (int) sTime)));
//				}
//
//				duration = (60 * hours) + minutes;
//				/*
//				 * Formatting to correctly display event duration
//				 */
//				if (hours == 0)
//					System.out.printf("Duration: %.0f minutes", minutes);
//				else if (hours == 1)
//					System.out.printf("Duration: %d hour and %.0f minutes", hours, minutes);
//				else
//					System.out.printf("Duration: %d hours and %.0f minutes", hours, minutes);
//				System.out.println();

				do {
					System.out.println("Set event location? (1) Yes (2) No");
					location = input.nextInt();

				} while (location != 1 && location != 2 && location != 0);

				if (location == 1) {
					do {
						System.out.println("Select Preset Location:");
						System.out.println("(1) Hamilton Library");
						System.out.println("(2) Sinclair Library");
						System.out.println("(3) Kennedy Theater");
						System.out.println("(4) QLC");
						System.out.println("(5) Campus Bookstore");

						locChoice = input.nextInt();

					} while (locChoice > 5 || locChoice < 1);

				}

				switch (locChoice) {
				case 1:
					geoLat = (float) 21.300126;
					geoLong = (float) -157.816246;
					break;
				case 2:
					geoLat = (float) 21.299222;
					geoLong = (float) -157.820306;
					break;
				case 3:
					geoLat = (float) 21.298978;
					geoLong = (float) -157.814606;
					break;
				case 4:
					geoLat = (float) 21.300067;
					geoLong = (float) -157.818548;
					break;
				case 5:
					geoLat = (float) 21.298389;
					geoLong = (float) -157.818995;
					break;
				default:
					geoLat = 0;
					geoLong = 0;
					break;
				}

				do {
					System.out.println("Any comments? (1) Yes (2) No");
					comment = input.nextInt();

				} while (comment != 1 && comment != 2 && comment != 0);

				if (comment == 1) {
					do {
						System.out.println("Enter comment.");
						com = input.nextLine();

					} while (com != "");
				}

				// formatting time for event creation

				// sTime
				int sHours = (int) Math.floor(sTime);
				int sMins = (int) ((sTime % 1) * 100);

				ZonedDateTime sT = ZonedDateTime.of(sYear, sMonth, sDay, sHours, sMins, 0, 0,
						ZoneId.of(cal.getTimezone().getID()));

				// eTime
				int eHours = (int) Math.floor(eTime);
				int eMins = (int) ((eTime % 1) * 100);
				ZonedDateTime eT = ZonedDateTime.of(eYear, eMonth, eDay, eHours, eMins, 0, 0,
						ZoneId.of(cal.getTimezone().getID()));

				Event e = new Event(eName, sT, eT);
				e.setClassification(spec);
				e.setGeoLat(geoLat);
				e.setGeoLong(geoLong);
				e.setComment(com);

				cal.addEvent(e);

				System.out.println("Event added to Calendar.");

			} else if (menuChoice == 2) {

				if (cal != null) {
					System.out.println("Please enter the number of the event you would like to remove:");
					displayCalendar(cal);
					int eIndex = input.nextInt();
					try {
						cal.removeEvent(cal.getCalendar().get(eIndex - 1));
						System.out.println("Event has been removed.");
					} catch (Exception e) {
						System.out.println("Event does not exist.");
					}
				} else {
					System.out.println("Calendar does not exist.");
				}

			} else if (menuChoice == 3) {
				System.out.println("Please enter the file name (exactly as it appears) where your calendar is stored");
				fileName = input.nextLine();
				try {
					openICSFile(fileName);
				} catch (Exception e) {

				}
				System.out.println("Calendar has been read.");
			} else if (menuChoice == 4) {
				System.out.println("Please enter the file name where you would like to save your calendar:");
				fileName = input.nextLine();
				if (cal != null) {
					writeICSFile(fileName, cal);
					System.out.println("Calendar saved as: " + fileName + ".ics");
				} else {
					System.out.println("Calendar not saved as a calendar has not been made.");
				}
			}

			do {
				flag = false;
				try {
					do {
						System.out.println("Enter (1) to continue. \nEnter (2) to terminate program.");
						cont = input.nextInt();
						input.nextLine();
					} while (cont != 1 && cont != 2);
					flag = false;
				} catch (Exception e) {
					flag = true;
					input.nextLine();
				}
			} while (flag == true);

		} while (cont == 1);

		if (cal != null) {
			do {
				flag = false;
				try {
					do {
						System.out.println("Save calendar? (1) Yes (2) No");
						save = input.nextInt();
						input.nextLine();
					} while (save != 1 && save != 2);
					flag = false;
				} catch (Exception e) {
					flag = true;
					input.nextLine();
				}
			} while (flag == true);
			if (save == 1) {
				System.out.println("Please enter the file name where you would like to save your calendar:");
				fileName = input.nextLine();
				writeICSFile(fileName, cal);
				System.out.println("Calendar saved as: " + fileName + ".ics");
			}
		}

	}

	/**
	 * Opens a given file with the file name and .ics extension. Can take
	 * multiple .ics files?
	 * 
	 * @param file
	 */
	public static Calendar openICSFile(String file) throws Exception {
		String[][] calProperties = { { "calScale", "CALSCALE", "" }, { "calName", "X-WR-CALNAME", "" },
				{ "tz", "X-WR-TIMEZONE", "" } };
		String[][] eventProperties = { { "dtstart", "DTSTART", "" }, { "dtendd", "DTEND", "" },
				{ "name", "SUMMARY", "" }, { "geo", "GEO", "", "" }, { "classification", "CLASS", "" },
				{ "comment", "DESCRIPTION", "" }, { "last-modified", "LAST-MODIFIED", "" } };
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();

			// initializing the calendar
			while (!line.contains("BEGIN:VEVENT") && line != null) {
				for (String[] p : calProperties) {
					if (line.contains(p[1])) { // if it contains the header
						String[] property = line.split(":"); // get the value of
																// it
						p[2] = property[1].replace("\r\n", ""); // put it as the
																// third value
																// in the array
					}
				}
				line = br.readLine();
			} // end loop when the next line contains BEGIN:VEVENT

			// create Calendar

			// initialize new calendar if not already initialized
			if (cal == null) {
				cal = new Calendar(calProperties[0][2], calProperties[1][2], TimeZone.getTimeZone(calProperties[2][2]));
			}

			// process all events in the calendar
			while (line != null && !line.contains("END:VCALENDAR")) {
				while (line != null && !line.contains("END:VEVENT")) {
					for (String[] e : eventProperties) {
						if (line.contains(e[1])) {
							if (e[0].contains("geo")) {
								String[] property = line.split(":");
								e[2] = property[1].replace("\r\n", "");
								String[] geo = property[1].split(";");// split
																		// the
																		// values
																		// of
																		// geo
								e[2] = geo[0].replace("\r\n", "");
								e[3] = geo[1].replace("\r\n", "");
							} else if (e[0].contains("comment")) {
								String comments[] = line.split(":");
								String comment = "";
								if (comments.length == 2) {
									comment = comments[1];
								}
								line = br.readLine();
								while (line != null && !line.contains("LAST-MODIFIED")) {
									comment += "\n" + line;
									line = br.readLine();
								}
								e[2] = comment;
							} else {
								String[] property = line.split(":");
								e[2] = property[1].replace("\r\n", "");
							}
						}
					}
					line = br.readLine();
				} // process events here

				try {
					ZonedDateTime dtstart = ZonedDateTime.ofInstant(format.parse(eventProperties[0][2]).toInstant(),
							ZoneId.of(cal.getTimezone().getID()));
					ZonedDateTime dtend = ZonedDateTime.ofInstant(format.parse(eventProperties[1][2]).toInstant(),
							ZoneId.of(cal.getTimezone().getID()));
					ZonedDateTime dtstamp = ZonedDateTime.ofInstant(format.parse(eventProperties[6][2]).toInstant(),
							ZoneId.of(cal.getTimezone().getID()));
					String name = eventProperties[2][2];
					Event event = new Event(name, dtstart, dtend);
					if (eventProperties[3][2] != "" && eventProperties[3][3] != "") {
						// if geo is not empty
						event.setGeoLat(Float.parseFloat(eventProperties[3][2]));
						event.setGeoLong(Float.parseFloat(eventProperties[3][3]));
					}
					if (eventProperties[4][2] != "") {
						event.setClassification(eventProperties[4][2]);
					}
					if (eventProperties[5][2] != "") {
						event.setComment(eventProperties[5][2]);
					}
					event.setDtstamp(dtstamp);
					cal.addEvent(event);

				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				line = br.readLine();
			}

			return cal;

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	/**
	 * Writes the current calendar into an .ics file with given filename. If the
	 * file already exists, it will be overwritten.
	 * http://stackoverflow.com/questions/2885173/java-how-to-create-a-file-and-
	 * write-to-a-file
	 * 
	 * @param filename
	 */
	public static void writeICSFile(String filename, Calendar cal) {
		// add file extension
		filename += ".ics";
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {
			writer.write(cal.generateFile());
		} catch (IOException e) {
			System.out.println("Unable to create file.");
		}
	}

	/**
	 * Initializes a new Calendar.
	 */
	public static void createCalendar(String name, String timezone) {
		// create new Calendar object

		String calName = name; // get user input for name
		TimeZone tz = TimeZone.getTimeZone(timezone); // get user input for name
		cal = new Calendar("Gregorian", calName, tz);
	}

	/**
	 * Displays all events (by name) from a given Calendar
	 * 
	 * @param cal
	 */
	public static void displayCalendar(Calendar cal) {
		// displays all events by name
		ArrayList<Event> calendar = cal.getCalendar();

		int count = 1;
		for (Event event : calendar) {
			System.out.println("(" + count + ") " + event.getName());
			count++;
		}
	}

}

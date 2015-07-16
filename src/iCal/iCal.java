package iCal;

import java.io.BufferedWriter;
import java.io.FileOutputStream;

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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimeZone;

public class iCal {
	
	private static Calendar cal;

	public static void main(String[] args) {
		
		
		/*
		 * Menu display.
		 * > Load .ics files into a placeholder calendar, that's only for that certain open instance.
		 * > Can load events into another calendar?
		 */
		 
		Scanner input = new Scanner(System.in);
		String  eName;					//Event name
		String  sDate, eDate; 				//Start Date, End Date
		int     sDay = 0, eDay;				//Start Day, End Day
		int     sMonth = 0, eMonth;			//Start Month, End Month
		int     sYear = 0, eYear;			//Start Year, End Year
		int     hours;					// Event hours
		int     timeChoice;				//Use to decide between a.m and p.m 
		int 	menuChoice = 0;				//Displayed menu choice	
		float   duration;				//Event duration, stored in minutes
		float   sTime;					//Start Time
		float   eTime;					//End Time
		float   time;
		float 	minutes;				//Event duration
		int 	specification;			// event specification
	    	boolean flag = false;	 
        
        	/*
        	 *  Getting the Menu choice from the user
		 */
		 
	  	System.out.println("\n(1) Create new event. \n(2) View current events in calendar.\n(3) Modify event.\n");
	    	do
	    	{
	    		flag = false;
	    		try
	    		{
	    			do
	    			{   
	    				System.out.println("Please enter the number of your menu choice.");
	    				menuChoice=input.nextInt();
	    				input.nextLine();
	    			} while(menuChoice != 1 && menuChoice != 2 && menuChoice != 3);
	    		} 
	    		
	    		catch(Exception e)
	    		{
	    			flag = true;
	    			input.nextLine();
	    		}
	    	}while(flag == true);
		
		
		/*
		 * Getting info for the new event because user chose option 1
		 */
		
		if(menuChoice == 1)
	    	{
	    		System.out.println("Please enter the name of your event.");
			eName = input.nextLine();
			System.out.println(eName);
			
			/*
			 *  Display a menu for user to decided what type of event
			 */
			
			System.out.println(" (1) General\n (2) To-Do\n (3) Urgent\n");
			do
			{
				flag = false;
				try
				{
					do
					{	System.out.println("Please specify the classification of " + eName);
						specification = input.nextInt();
						input.nextLine();
					} while(specification != 1 && specification != 2 && specification != 3);
					
					// Depending on which option they select we can go from there
					// lmk if theres any options you think we should add as far as classification
				}
				catch(Exception e)
				{
					flag = true;
					input.nextLine();
				}
			}while(flag == true);
			
			/*
			 *  Get the start date of the event
			 */
			
			do
		    	{
		    		flag = false;
		    		try
		        	{  
		    			do
		    			{
		    				System.out.println("Please enter the start date of event, ex 01 15 2015");
		    				sMonth = input.nextInt();
		    				sDay = input.nextInt();
		    				sYear = input.nextInt();		        
		  
			 		}while(sMonth > 12 || sMonth < 1 || sDay > 31 || sDay < 1 || sYear > 9999 || sYear < 0);
		    		}
			    	catch(Exception e)
			    	{
			    		flag = true;
			        	input.next();
			    	}
		    	}while(flag == true);
			
			/*
			 *  Determining if event ends on same day as start date or if event runs more than 1 day
			 */
			
			
			do
			{
				System.out.println("Enter (1) if " + eName + " ends on " + sMonth + "/" + sDay + "/" + sYear);
				System.out.println("Otherwise enter (2).");
				menuChoice = input.nextInt();
			}while(menuChoice != 1 && menuChoice != 2);
			
			/*
			 *  If the event is during 1 day, set start and end date = to each other
			 *  Otherwise get the end date and verify its after the starting date
			 */
			
			if(menuChoice == 1)
			{
				eDay = sDay;
				eMonth = sMonth;
				eYear = sYear;
			}
			else
			{
				do
				{
					flag = false;
					try
					{  
						do
						{
							System.out.println("Please enter the end date of event, ex 01 15 2015");
							eMonth = input.nextInt();
							eDay = input.nextInt();
							eYear = input.nextInt();
							
							if((eDay - sDay < 0 && (eMonth - sMonth) <= 0 && eYear <= sYear) || eYear < sYear)
							{
								System.out.println("End date needs to be after " + sMonth + "/" + sDay + "/" + sYear);
								flag = true;
							}
			  
						}while(eMonth > 12 || eMonth < 1 || eDay > 31 || eDay < 1 || eYear > 9999 || eYear < 0);
							
					}
					catch(Exception e)
					{
						flag = true;
						input.next();
					}
				}while(flag == true);
			}
			
			/*
			 *  Getting the start time of the event
			 */
			
			do
			{
				System.out.println("What time does " + eName + " start? (ex: 7.15)" );
				sTime = input.nextFloat();
				
			}while(sTime > 13 || sTime < 0);
			
			
			/*
			 * Determine if event starts in the am or pm
			 */
			
			do
			{
				System.out.println("Enter (1) AM or (2) PM");
				timeChoice = input.nextInt();
				
				if(timeChoice == 2 && (int)sTime != 12)
				{
					sTime += 12;
				}
			}while(timeChoice != 1 && timeChoice != 2 );
			
			/*
			 *  Getting the end time of the event
			 */
			
			do
			{	
				System.out.println("What time does " + eName + " end? (ex: 10.05)" );
				eTime = input.nextFloat();
				
			}while(eTime > 13 || eTime < 0);
			
			/*
			 * Determine if event ends in the am or pm
			 */
			
			do
			{
				System.out.println("Enter (1) AM or (2) PM");
				timeChoice = input.nextInt();
			
				if(timeChoice == 2 && (int)eTime != 12)
				{
					eTime += 12;
				}
				
			}while(timeChoice != 1 && timeChoice != 2);
			
			/*
			 * Computing the duration of the event 
			 */
			
			hours = (int)eTime - (int)sTime;
			minutes = 100 * ((sTime - (int)sTime) - (eTime - (int)eTime));
			
			if(minutes > 0)
			{
				hours --;
				minutes = 60 - minutes;
			}
			
			else
			{
				minutes = (100 * ((eTime - (int)eTime) - (sTime - (int)sTime)));
			}
			
			/*
			 * Storing event duration in minutes
			 */
			 
			duration = (60 * hours) + minutes;
			/*
			 * Formatting to correctly display event duration
			 */
			if(hours == 0)
				System.out.printf("Duration: %.0f minutes", minutes);
			else if (hours == 1)
				System.out.printf("Duration: %d hour and %.0f minutes", hours, minutes );
			else
				System.out.printf("Duration: %d hours and %.0f minutes", hours, minutes);
			
	    }
		
	    else if(menuChoice == 2)
	    {
	    	System.out.println("Method to view current events.");
	    }
	    else
	    {
	    	System.out.println("Modify event method here");
	    }
		
	}
	
	
	
	
	/**
	 *  Opens a given file with the file name and .ics extension. Can take multiple .ics files?
	 * 
	 * @param file
	 */
	public static void openICSFile(String file) { 
		// checks ics file extension
		
		// if no .ics file extension, terminate, file not found
		
		// if it is, read it into a calendar variable, static, in iCal
		
		// close file
		
	}
	
	/**
	 * Writes the current calendar into an .ics file with given filename. If the file already exists, it will be overwritten.
	 * http://stackoverflow.com/questions/2885173/java-how-to-create-a-file-and-write-to-a-file
	 * 
	 * @param filename
	 */
	public static void writeICSFile(String filename, Calendar cal) {
		// add file extension
		filename += ".ics";
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) 
		{ writer.write(cal.generateFile()); } catch (IOException e) {
			System.out.println("Unable to create file.");
		}
	}
	

	/**
	 * Initializes a new Calendar.
	 */
	public static void createCalendar(String name, String timezone) {
		// create new Calendar object
		String calName = "Calendar Name"; // get user input for name
		TimeZone tz = TimeZone.getTimeZone("Pacific/Honolulu"); // get user input for name
		// TimeZone tz = TimeZone.getTimeZone(timezone);
		cal = new Calendar("Gregorian", calName, tz);
		// cal = new Calendar("Gregorian", name, tz);
	}
	
	/**
	 * Displays all events (by name) from a given Calendar
	 * @param cal
	 */
	public static void displayCalendar(Calendar cal) {
		// displays all events by name
		ArrayList<Event> calendar = cal.getCalendar();
		
		int count = 1;
		for (Event event : calendar) {
			System.out.println(count + ". " + event.getName());
			count++;
		}
	}
	
	/**
	 * Initializes a new event in a given Calendar.
	 * @param cal
	 */
	public static void addEventToCal(Calendar cal) {
		
	}
	
	/**
	 * Removes an event from a given Calendar.
	 * @param cal
	 */
	public static void removeEventFromCal(Calendar cal) {
		
	}
	

}

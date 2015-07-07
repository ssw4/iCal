package iCal;

/*
 * iCal
 * holds the main function
 * creates user interface
 * creates event files
 * reads event files
*/

import java.io.IOException;
import java.util.Scanner;

public class iCal {

	public static void main(String[] args) {
		
		
		/*
		 * Menu display.
		 * > Load .ics files into a placeholder calendar, that's only for that certain open instance.
		 * > Can load events into another calendar?
		 */
        	Scanner input = new Scanner(System.in);
		String  eName;
		String  date; 
		int     day;
		int     month;
		int     year;
		int     hours;
		int     minutes = 0;
		float   duration;
		float   sTime;
		float   eTime;
	    	boolean flag = false;
	    
		System.out.println("Please enter the name of your event.");
		eName = input.nextLine();
		System.out.println(eName);
		
		do
	    	{
	    		System.out.println("Please enter the date of event, ex 01 15 2015");
	    		try
	        	{   
	        		flag = false;
	        		month = input.nextInt();
		        	day = input.nextInt();
		        	year = input.nextInt();		        
				 System.out.println("Event Date: " + month +"/" + day  + "/" + year);
		    }
		    catch(Exception e)
		    {
		    	flag = true;
			System.out.println("Please enter date in correct format, (month day year)");
		        input.next();
		    }
		}while(flag == true);
		
		System.out.println("What time does " + eName + " start? (ex: 7.15)" );
		sTime = input.nextFloat();
		System.out.println(sTime);
		System.out.println("Enter (1) AM or (2) PM");
		
		/*
		 *    Initialed here since its the only place we'll use it
		 */
		 
		int choice;
		choice = input.nextInt();
		
		if(choice == 2)
		{
		/*
		 *  Going by 24 hrs,  2 = Pm	
		 */
		   sTime += 12;
		}
		
		System.out.println(sTime);
		
		System.out.println("What time does " + eName + " end? (ex: 10.05)" );
		eTime = input.nextFloat();
		System.out.println(eTime);
		System.out.println("Enter (1) AM or (2) PM");
        	choice = input.nextInt();
        	
        	if(choice == 2)
		{
		/*
		 *  Going by 24 hrs,  2 = Pm	
		 */
		   eTime += 12;
		}
		
		hours = (int)eTime - (int)sTime;
		
		if((eTime - (int)eTime) < (sTime - (int)sTime))
		{
		    double time = 0;
		    //time = 100 * (eTime - (int)eTime);  
		    //System.out.println("eTime: " + time );
		    //time = 100 * (sTime - (int)sTime);
		    //System.out.println(Math.round(time));
	            //minutes = (int)(60 - Math.round(time));
		    //System.out.println("minutes " + minutes);
		    if (hours == 1 || hours == 0) 
	  	    {	
			hours = 0;
			time = 100 * (sTime - (int)sTime);
			System.out.println(Math.round(time));
			//minutes = (int)(60 - Math.round(time));
			minutes = (int)Math.round(time); 
			System.out.println("duration: " + minutes + " minutes");
		        duration = minutes;
		    }
		    
		    else
		    {
			    hours --;
		    }
		}
		
		System.out.println("duration: " + hours + " hours" + minutes + " minutes");
	}
	
	
	
	/**
	 * Initializes a new Calendar.
	 */
	public static void createCalendar() {
		
	}
	
	/**
	 * Displays all events (by name) from a given Calendar
	 * @param cal
	 */
	public static void displayCalendar(Calendar cal) {
		
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

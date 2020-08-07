package database;

import java.io.*;
import java.util.Scanner;

public class DriverDatabase
{
	/* Methods present in this class:
	   
	   public static String getRatingByCarID(String searchTerm_carID);
	   public static void editRatingByCarID(String newTerm, String searchTerm_carID) throwsIOException;
	   public static String getNumberOfRidesByCarID(String searchTerm_carID)
	   public static void editNumberOfRidesByCarID(String newNumberOfRides, String searchTerm_carID) throws IOException
	*/
	
	private static String filepath = "drivers.txt";
	
	//Driver fields
	//Required fields in respective order in the .txt(CSV) file:
	
	// Array indices
	// ;  0   ,  1  ,      2      ;
	// ;carID,rating,nunberOfRides;
	
	private static Scanner fileScanner;
	
	public static String getRatingByCarID(String searchTerm_carID)
	{
		 String carID = ""; String rating = ""; String numberOfRides = "";
		 
		 try
		 {
		 	fileScanner = new Scanner(new File(filepath));
		 	fileScanner.useDelimiter("[,\n]");
		 	
		 	while(fileScanner.hasNext())
		 	{
		 		carID = fileScanner.next();
		 		rating = fileScanner.next();
		 		numberOfRides = fileScanner.next();
		 		
		 		if(carID.equals(searchTerm_carID))
		 		{
		 			fileScanner.close();
		 			return rating;
		 		}
		 	}
		 	
		 	fileScanner.close();
		 	System.out.println("Record with carID " + searchTerm_carID + " NOT found");
			return "Record with carID " + searchTerm_carID + " NOT found";
		}
		catch(IOException ioe)
		{
			fileScanner.close();
			System.out.println("I/O error in checking records.");
			System.out.println("Error in : DriverDatabase.getRatingByCarID()");
			return "IOException";
		}
	}
	
	public static void editRatingByCarID(String newRating, String searchTerm_carID) throws IOException
	{
		boolean found = false;
		String tempFile = "temp.txt";
		String carID = ""; String rating = ""; String numberOfRides = "";
		
		try
		{
			File oldFile = new File(filepath);
			File newFile = new File(tempFile);
			
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			fileScanner = new Scanner(new File(filepath));
			fileScanner.useDelimiter("[,\n]");
			
			while(fileScanner.hasNext())
			{
				carID = fileScanner.next();
				rating = fileScanner.next();
				numberOfRides = fileScanner.next();
				
				if(carID.equals(searchTerm_carID))
				{
					found = true;
					pw.println(carID + "," + newRating + "," + numberOfRides);
				}
				else
				{
					pw.println(carID + "," + rating + "," + numberOfRides);	
				}
			}
				
			fileScanner.close();
			pw.flush();
			pw.close();
			
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			
			if(!found)
			{
				System.out.println("Record " + searchTerm_carID + " NOT found");
				throw new IOException();
			}
			else
			{
				System.out.println("Record's wallet with carID " + searchTerm_carID + 
								" updated by DriverDatabase.editRatingByCarID()");
			}
		}
		catch(IOException ioe)
		{
			System.out.println("I/O error in editing wallet.");
			System.out.println("Error in : DriverDatabase.editRatingByCarID()");
		}
	}
	
	public static String getNumberOfRidesByCarID(String searchTerm_carID)
	{
		 String carID = ""; String rating = ""; String numberOfRides = "";
		 
		 try
		 {
		 	fileScanner = new Scanner(new File(filepath));
		 	fileScanner.useDelimiter("[,\n]");
		 	
		 	while(fileScanner.hasNext())
		 	{
		 		carID = fileScanner.next();
		 		rating = fileScanner.next();
		 		numberOfRides = fileScanner.next();
		 		
		 		if(carID.equals(searchTerm_carID))
		 		{
		 			fileScanner.close();
		 			return numberOfRides;
		 		}
		 	}
		 	
		 	fileScanner.close();
		 	System.out.println("Record with carID " + searchTerm_carID + " NOT found");
			return "Record with carID " + searchTerm_carID + " NOT found";
		}
		catch(IOException ioe)
		{
			fileScanner.close();
			System.out.println("I/O error in checking records.");
			System.out.println("Error in : DriverDatabase.getNumberOfCarsByCarID()");
			return "IOException";
		}
	}
	
	public static void editNumberOfRidesByCarID(String newNumberOfRides, String searchTerm_carID) throws IOException
	{
		boolean found = false;
		String tempFile = "temp.txt";
		String carID = ""; String rating = ""; String numberOfRides = "";
		
		try
		{
			File oldFile = new File(filepath);
			File newFile = new File(tempFile);
			
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			fileScanner = new Scanner(new File(filepath));
			fileScanner.useDelimiter("[,\n]");
			
			while(fileScanner.hasNext())
			{
				carID = fileScanner.next();
				rating = fileScanner.next();
				numberOfRides = fileScanner.next();
				
				if(carID.equals(searchTerm_carID))
				{
					found = true;
					pw.println(carID + "," + rating + "," + newNumberOfRides);
				}
				else
				{
					pw.println(carID + "," + rating + "," + numberOfRides);	
				}
			}
				
			fileScanner.close();
			pw.flush();
			pw.close();
			
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			
			if(!found)
			{
				System.out.println("Record " + searchTerm_carID + " NOT found");
				throw new IOException();
			}
			else
			{
				System.out.println("Record's wallet with carID " + searchTerm_carID + 
								" updated by DriverDatabase.editNumberOfRidesByCarID()");
			}
		}
		catch(IOException ioe)
		{
			System.out.println("I/O error in editing wallet.");
			System.out.println("Error in : DriverDatabase.editNumberOfRidesByCarID()");
		}
	}
}





///////////////

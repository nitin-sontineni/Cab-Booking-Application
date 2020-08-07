package cabops;

import java.io.*;
import database.DriverDatabase;

public class Rating
{
	public static void driverRating(int rating,int carID)
	{
		try
		{
			String presentRating= DriverDatabase.getRatingByCarID(carID+"");
			double presRating = Double.parseDouble(presentRating);
		
			String presentDrives= DriverDatabase.getNumberOfRidesByCarID(carID+"");
			int presDrives = Integer.parseInt(presentDrives);
			
			double newRating=(((presRating*presDrives)+rating)/(presDrives+1));
			presDrives++;
			DriverDatabase.editRatingByCarID(newRating+"",carID+"");
			DriverDatabase.editNumberOfRidesByCarID(presDrives+"",carID+"");
		}
		catch(NumberFormatException nfe)
		{
			nfe.printStackTrace();
		}
		catch(IOException ioe)
		{	
			ioe.printStackTrace();
		}	
	}
}

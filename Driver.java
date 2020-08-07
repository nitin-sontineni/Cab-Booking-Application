import javax.swing.*;
import java.util.*;

import cabops.*;

class Driver 
{
	private java.util.Timer timer;
	private CreateMap cm1 = new CreateMap();
	private RedistributeThread rt1;

	//ArrayList of an ArrayList,  userID            ->ArrayList
	//                            cabAssignInstance ->ArrayList
	public static  ArrayList<ArrayList<CabAssign>> cabAssignList  = new ArrayList<ArrayList<CabAssign>>(2);
	public static ArrayList<String> mapUserIDToCabAssignInstance = new ArrayList<String>();
	public static ArrayList<CabAssign> cabAssignInstanceForEachLoggedInUser = new ArrayList<CabAssign>();
	
	Driver()
	{
		 cm1.mapCreation();
		 cm1.cabCoorCreation();
		 
		 Run.reassign(16, 0, 0, cm1.map, cm1.cabCoor);
		 
		 Login loginPage = new Login(cm1);
		
		 rt1 = new RedistributeThread(cm1);		 
	}

	public static void main(String[] args) 
	{ 
		SwingUtilities.invokeLater
		(
			new Runnable()
			{
				public void run()
				{
					new Driver();	
				}
			}
		);
	}
}

class RedistributeThread extends Thread 
{
	private CreateMap cm1;
	RedistributeThread(CreateMap obj)
	{
		super();
		cm1 = obj;
		start();
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				Run.reassign(16, 0, 0, cm1.map, cm1.cabCoor);
				Thread.sleep(300*1000);
			}
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("An error occurred with cabops.Run.reassign() at Driver.java -- line81");
		}	
	}
}











//////////////////		

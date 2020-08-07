package database;

import java.io.*;
import java.util.Scanner;

public class DatabaseOperations
{
	/*Methods present in this class:
	
	  public static void saveNewRecord(String userID, String userName, String password, String phoneNumber, String emailID);
	  public static String[] fetchRecordByUserID(String searchTerm_userID) throws IOException
	  public static void editWalletByUserID(String newWallet, String searchTerm_userID) throws IOException
	  public static String recordIsPresentWithThisField(String userID, String phoneNumber, String emailID)
	  public static String getWalletByUserID(String userID)
	 	  
	*/
	private static final String filepath = "records.txt";
	
	//login fields 
	//Required fields in respective order in the .txt(CSV) file:
	
	// Array indices
	// ;  0   ,    1   ,    2   ,     3     ,   4   ,   5  ;
	// ;userID,userName,password,phoneNumber,emailID,wallet;
	
	public static void saveNewRecord(String userID, String userName, String password, String phoneNumber, String emailID)
	{
		try
		{
			FileWriter fw = new FileWriter(filepath, true);
		 	BufferedWriter bw = new BufferedWriter(fw);
		 	PrintWriter pw = new PrintWriter(bw);
		 	
		 	pw.println(userID + "," + userName + "," + password + "," + phoneNumber + "," + emailID + "," + "0");
		 	pw.flush();
		 	pw.close();
		 	
		 	System.out.println("Successfully created account for " + userID + " by DatabaseOperations.saveNewRecord()");
		 }
		 catch(IOException ioe)
		 {
		 	System.out.println("An Error Occurred. Account creation failed.");
		 	System.out.println("Error in : DatabaseOperations.saveNewRecord()");
		 }
	}
	
	
	private static Scanner fileScanner;
	
	public static String[] fetchRecordByUserID(String searchTerm_userID) throws IOException
	{
		boolean found = false;
		String userID = ""; String userName = ""; String password = "";
		String phoneNumber = ""; String emailID = ""; String wallet = ""; 
		
		try
		{
			fileScanner = new Scanner(new File(filepath));
			fileScanner.useDelimiter("[,\n]");
			
			String[] recordArr = new String[6];
			
			while(fileScanner.hasNext())
			{
				userID = fileScanner.next();
				userName = fileScanner.next();
				password = fileScanner.next();
				phoneNumber = fileScanner.next();
				emailID = fileScanner.next();
				wallet = fileScanner.next();
			
				if(userID.equals(searchTerm_userID))
				{
					found = true;
					
					recordArr[0] = userID;
					recordArr[1] = userName;
					recordArr[2] = password;
					recordArr[3] = phoneNumber;
					recordArr[4] = emailID;
					recordArr[5] = wallet;
					
					break;
				}
			}
			
			fileScanner.close();
			
			if(!found)
			{
				System.out.println("Record " + searchTerm_userID + " NOT found.");
				throw new IOException();
			}
			else
			{
				System.out.println("Record " + searchTerm_userID + " found successfully.");
				System.out.println("Record " + searchTerm_userID + "found by DatabaseOperations.fetchRecordByUserID()");
			}
				
			return recordArr;
		}
		catch(IOException ioe)
		{
			System.out.println("I/O error in fetching record.");
			System.out.println("Error in : DatabaseOperations.fetchRecordByUserID()");
			return null;
		}			
	}
	
	public static void editWalletByUserID(String newWallet, String searchTerm_userID) throws IOException
	{
		boolean found = false;
		String tempFile = "temp.txt";
		String userID = ""; String userName = ""; String password = "";
		String phoneNumber = ""; String emailID = ""; String wallet = "";
		
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
				userID = fileScanner.next();
				userName = fileScanner.next();
				password = fileScanner.next();
				phoneNumber = fileScanner.next();
				emailID = fileScanner.next();
				wallet = fileScanner.next();
				
				if(userID.equals(searchTerm_userID))
				{
					found = true;
					pw.println(userID + "," + userName + "," + password + "," + phoneNumber + "," + emailID + "," + newWallet);
				}
				else
				{
					pw.println(userID + "," + userName + "," + password + "," + phoneNumber + "," + emailID + "," + wallet);	
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
				System.out.println("Record " + searchTerm_userID + " NOT found");
				throw new IOException();
			}
			else
			{
				System.out.println("Record's wallet with userID " + searchTerm_userID + 
								" updated by DatabaseOperations.editWalletByUserID()");
			}
		}
		catch(IOException ioe)
		{
			System.out.println("I/O error in editing wallet.");
			System.out.println("Error in : DatabaseOperations.editWalletByUserID()");
		}
	}
	
	public static String recordIsPresentWithThisField(String ID, String pNo, String eID)
	{
		String userID = ""; String userName = ""; String password = "";
		String phoneNumber = ""; String emailID = ""; String wallet = "";
		
		try
		{
			fileScanner = new Scanner(new File(filepath));
			fileScanner.useDelimiter("[,\n]");
			
			while(fileScanner.hasNext())
			{
				userID = fileScanner.next();
				userName = fileScanner.next();
				password = fileScanner.next();
				phoneNumber = fileScanner.next();
				emailID = fileScanner.next();
				wallet = fileScanner.next();
				
				//Returns "none" if no field is taken.
				//Returns "userID" if userID is taken.
				//Returns "phoneNumber" if phoneNumber is taken.
				//Returns "emailID" if emailID is taken.
				//Returns "IOException" if an IOException occurred.
				
				if(ID.equals(userID))
				{
					fileScanner.close();
					return "userID";
				}
				else if(pNo.equals(phoneNumber))
				{
					fileScanner.close();
					return "phoneNumber";	
				}
				else if(eID.equals(emailID))
				{
					fileScanner.close();
					return "emailID";
				}
			}
			
			fileScanner.close();
			return "none";		
		}
		catch(IOException ioe)
		{
			fileScanner.close();
			System.out.println("I/O error in checking records.");
			System.out.println("Error in : DatabaseOperations.recordIsPresentWithThisField()");
			return "IOException";
		}		
	}
	
	public static String getWalletByUserID(String searchTerm_userID)
	{
		String userID = ""; String userName = ""; String password = "";
		String phoneNumber = ""; String emailID = ""; String wallet = "";
		
		try
		{
			fileScanner = new Scanner(new File(filepath));
			fileScanner.useDelimiter("[,\n]");
			
			//Returns "IOException" if an IOException occurred
			while(fileScanner.hasNext())
			{
				userID = fileScanner.next();
				userName = fileScanner.next();
				password = fileScanner.next();
				phoneNumber = fileScanner.next();
				emailID = fileScanner.next();
				wallet = fileScanner.next();
				
				if(userID.equals(searchTerm_userID))
				{
					fileScanner.close();
					return wallet;
				}
			}
			
			fileScanner.close();
			System.out.println("Record with userID " + searchTerm_userID + " NOT found");
			return "Record with userID " + searchTerm_userID + " NOT found";		
		}
		catch(IOException ioe)
		{
			fileScanner.close();
			System.out.println("I/O error in checking records.");
			System.out.println("Error in : DatabaseOperations.getWalletByUserID()");
			return "IOException";
		}
	}
}				
			
			
			
		
		
		
		
		
		
		
		
		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////	

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.io.*;

import cabops.*;
import database.DatabaseOperations;
 
class Travel extends JFrame implements ActionListener {
	private Container c;
	private JLabel title;
	private JLabel main;
	private JLabel atime;  //arrival time
	private JLabel tatime;  
	private JLabel driver; 
	private JLabel tdriver;
	private JLabel carid;  
	private JLabel tcarid;
	private CabAssign cb;
	private CreateMap cm1;
	private java.util.Timer timer;
	private int dropX, dropY;
	private String userID;
	private JLabel back;
	private ImageIcon img;
	private JLabel logo;
	private ImageIcon stamp;
	
	public Travel(CreateMap obj1, CabAssign obj2, String uid, int x1, int y1)
	{
		cm1 = obj1;
		cb = obj2;
		userID = uid;
		dropX = x1;
		dropY = y1;
		
		timer = new java.util.Timer();
        timer.schedule(new RemindTask(),  /*initial delay*/1000,  /*subsequent rate*/ 1*1000);
		
		setTitle("Rapid cabs");
		setBounds(200,100,900,600);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
		setResizable(true);
		img = new ImageIcon("travel.jpg");
		stamp = new ImageIcon("logo.png");
		
		c = getContentPane(); 
		c.setLayout(null);
		
		main = new JLabel("Welcome to Rapid Cabs !");
		main.setFont(new Font("Arial", Font.PLAIN, 20));
		main.setSize(300,30);
		main.setLocation(70,130);
		c.add(main);
		
		title = new JLabel("You are now riding with " + cb.cabDriver + "!"); //name has to be modified
		title.setFont(new Font("Arial", Font.PLAIN, 15)); 
		title.setSize(300, 20); 
		title.setLocation(80, 180); 
		c.add(title);
		
		atime = new JLabel("Drop off in    : ");
		atime.setFont(new Font("Arial", Font.PLAIN, 20)); 
		atime.setSize(300, 20); 
		atime.setLocation(50, 250); 
		c.add(atime);
		
		tatime = new JLabel(""); 
		tatime.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tatime.setSize(300, 20); 
		tatime.setLocation(200, 250); 
		c.add(tatime);
		tatime.setText((cb.travelTime - cb.arrivalTime) + " seconds");
		//timer has to be added
		
		driver = new JLabel("Driver Name : ");
		driver.setFont(new Font("Arial", Font.PLAIN, 20)); 
		driver.setSize(300, 20); 
		driver.setLocation(50, 300); 
		c.add(driver);
		
		tdriver = new JLabel(""); 
		tdriver.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tdriver.setSize(300, 20); 
		tdriver.setLocation(200, 300); 
		c.add(tdriver);
		tdriver.setText(cb.cabDriver);  //should be modified accordingly
		
		carid = new JLabel("Car Number  : ");
		carid.setFont(new Font("Arial", Font.PLAIN, 20)); 
		carid.setSize(300, 20); 
		carid.setLocation(50, 350); 
		c.add(carid);
		
		tcarid = new JLabel(""); 
		tcarid.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tcarid.setSize(300, 20); 
		tcarid.setLocation(200, 350); 
		c.add(tcarid);
		tcarid.setText(cb.cabAssigned + "");  //should be modified accordingly
		
		logo = new JLabel("",stamp,JLabel.CENTER);
		logo.setBounds(690,5,205,90);
		c.add(logo);
		
		back = new JLabel("",img,JLabel.CENTER);
		back.setBounds(0,0,900,600);
		c.add(back);
		
		setVisible(true);
		
		}
		
	public void actionPerformed(ActionEvent e) 
	{ 
		
	}
	
	private class RemindTask extends TimerTask
	{
		int initTime = cb.travelTime - cb.arrivalTime;
			
		public void run()
		{
			if(initTime > 0)
			{
				tatime.setText(initTime + " seconds");
				initTime--;
			}
			else if(initTime == 0)
			{
				timer.cancel();
				cb.completedTrip(cm1.map, cm1.cabCoor, dropX, dropY);
				
				for(int i = 0; i < 30; i++)
				{
					System.out.println(cm1.cabCoor[i][0] + " " + cm1.cabCoor[i][1]);
				}
				
				double initMoney = Double.parseDouble(DatabaseOperations.getWalletByUserID(userID));
				double finalMoney = initMoney - cb.cost;
				
				try
				{
					DatabaseOperations.editWalletByUserID(finalMoney + "", userID);
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
				setVisible(false);
				Feedback f11 = new Feedback(cm1, cb, userID);
			}
			else
			{
				timer.cancel();
			}
		}
	}
}


			
		

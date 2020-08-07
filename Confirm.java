//confirm page
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

import java.lang.Math;
import cabops.*;
import database.DatabaseOperations;
import java.util.*;
 
class Confirm extends JFrame implements ActionListener {
	private Container c;
	private JLabel title;
	private JLabel ef;  //expected fare
	private JLabel tef;   //text for expected fare
	private JLabel et;  //expected time
	private JLabel tet;
	private JLabel distance;  
	private JLabel tdistance;
	private JButton confirm;
	private JButton back;
	private JLabel disclaimer;
	private JLabel tdisclaimer;
	private String userID;
	private int pickX, pickY, dropX, dropY; //Co-ordinates from Book page
	private CreateMap cm1;
	private CabAssign cb;
	private java.util.Timer timer;
	private JLabel background;
	private ImageIcon img;
	private JLabel logo;
	private ImageIcon stamp;		
	
	public Confirm(String uid, CreateMap obj, int px, int py, int dx, int dy)
	{
		userID = uid;
		cm1 = obj;
		pickX = px;
		pickY = py;
		dropX = dx;
		dropY = dy;
		
		for(int i = 0; i < Driver.mapUserIDToCabAssignInstance.size(); i++)
		{
			if(Driver.mapUserIDToCabAssignInstance.get(i).equals(userID))
			{
				cb = Driver.cabAssignInstanceForEachLoggedInUser.set(i, new CabAssign());
			}
		}
		
		cb.assign(cm1.map, cm1.cabCoor, cm1.cabDrivers, pickX, pickY, dropX, dropY);
		
		timer = new java.util.Timer();
        timer.schedule(new RemindTask(),  /*initial delay*/1000,  /*subsequent rate*/ 1*1000);  
		
		setTitle("Rapid cabs");
		setBounds(200,100,900,550);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
		setResizable(true);
		img = new ImageIcon("confirm.jpg");
		stamp = new ImageIcon("logo.png");
		
		c = getContentPane(); 
		c.setLayout(null);
		
		title = new JLabel("Confirm Booking");
		title.setFont(new Font("Arial", Font.PLAIN, 25)); 
		title.setSize(300, 30); 
		title.setLocation(100, 130); 
		//title.setForeground(Color.white);
		c.add(title);
		
		ef = new JLabel("Expected Fare: ");
		ef.setFont(new Font("Arial", Font.PLAIN, 20)); 
		ef.setSize(300, 20); 
		ef.setLocation(50, 200);
		//ef.setForeground(Color.white); 
		c.add(ef);
		
		tef = new JLabel(""); 
		tef.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tef.setSize(300, 20); 
		tef.setLocation(220, 200); 
		//tef.setForeground(Color.white);
		c.add(tef);
		tef.setText("Rs. " + String.format("%.1f", cb.cost) + "");   //should be modified accordingly
		
		et = new JLabel("Dropoff in: ");
		et.setFont(new Font("Arial", Font.PLAIN, 20)); 
		et.setSize(300, 20); 
		et.setLocation(50, 250);
		//et.setForeground(Color.white); 
		c.add(et);
		
		tet = new JLabel(""); 
		tet.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tet.setSize(300, 20); 
		tet.setLocation(220, 250);
		//tet.setForeground(Color.white); 
		c.add(tet);
		tet.setText(cb.travelTime + " second(s)");  //should be modified accordingly
		
		distance = new JLabel("Distance: ");
		distance.setFont(new Font("Arial", Font.PLAIN, 20)); 
		distance.setSize(300, 20); 
		distance.setLocation(50, 300); 
		//distance.setForeground(Color.white);
		c.add(distance);
		
		tdistance = new JLabel(""); 
		tdistance.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tdistance.setSize(300, 20); 
		tdistance.setLocation(220, 300); 
		//tdistance.setForeground(Color.white);
		c.add(tdistance);
		tdistance.setText(String.format("%.1f", (cb.travelDist - cb.outDist)) + " km");  //should be modified accordingly = new JLabel("");
		
		confirm = new JButton("Confirm");
		confirm.setFont(new Font("Arial", Font.BOLD, 15));
		confirm.setSize(110,30);
		confirm.setLocation(50,350);
		confirm.addActionListener(this);
		c.add(confirm);
		
		back = new JButton("Back");
		back.setFont(new Font("Arial", Font.BOLD, 15));
		back.setSize(110,30);
		back.setLocation(210,350);
		back.addActionListener(this);
		c.add(back);
		
		disclaimer = new JLabel("This page will expire in ");
		disclaimer.setFont(new Font("Arial", Font.PLAIN, 15)); 
		disclaimer.setSize(300, 20); 
		disclaimer.setLocation(70, 420); 
		c.add(disclaimer);
		
		tdisclaimer = new JLabel(""); 
		tdisclaimer.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tdisclaimer.setSize(300, 20); 
		tdisclaimer.setLocation(220, 420); 
		c.add(tdisclaimer);
		tdisclaimer.setText("30 seconds");
		
		logo = new JLabel("",stamp,JLabel.CENTER);
		logo.setBounds(690,5,205,90);
		c.add(logo);
		
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(0,0,900,550);
		c.add(background);
		
		setVisible(true);
		
	}
		
	public void actionPerformed(ActionEvent e) 
	{ 
		if (e.getSource() == confirm) 
		{
			timer.cancel();
			double money = Double.parseDouble(DatabaseOperations.getWalletByUserID(userID));
			
			if(cb.cost > money)
			{
				cb.notAssign(cm1.map, cm1.cabCoor);
				JOptionPane.showMessageDialog(null, "Insufficient Wallet Balance", "Alert", JOptionPane.WARNING_MESSAGE);
				
				setVisible(false);
				Book b1 = new Book(userID, cm1);
			}
			
			else
			{
				setVisible(false);
				Journey j = new Journey(cm1, cb, userID, dropX, dropY);
				System.out.println(cb.arrivalTime);
			}
		}
		else if(e.getSource() == back)
		{
			timer.cancel();
			cb.notAssign(cm1.map, cm1.cabCoor);
			setVisible(false);
			Book b = new Book(userID, cm1);
		}
	}
	
	private class RemindTask extends TimerTask
	{
		int timeout = 30;
			
		public void run()
		{
			if(timeout > 0)
			{
				tdisclaimer.setText(timeout + " seconds");
				timeout--;
			}
			else if(timeout == 0)
			{
				timer.cancel();
				cb.notAssign(cm1.map, cm1.cabCoor);
				JOptionPane.showMessageDialog(null, "Page Timed out!", "Alert", JOptionPane.WARNING_MESSAGE);
				setVisible(false);
				Book b1 = new Book(userID, cm1);
			}
			else
			{
				cb.notAssign(cm1.map, cm1.cabCoor);
				timer.cancel();
			}
		}
	}
} 


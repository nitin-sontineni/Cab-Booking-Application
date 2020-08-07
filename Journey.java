//travelling page
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.* ;
import java.lang.Math;

import cabops.*;
import database.DriverDatabase;

class Journey extends JFrame implements ActionListener {
	private Container c;
	private JLabel title;
	private JLabel atime;  //arrival time
	private JLabel tatime;  
	private JLabel driver; 
	private JLabel tdriver;
	private JLabel carid;  
	private JLabel tcarid;
	private JLabel otp;
	private JLabel totp;
	private CreateMap cm1;
	private CabAssign cb;
	private JLabel rating;
	private JLabel trating;
	private JButton cancel;
	private java.util.Timer timer;
	private int OTP;
	private int max = 9999;
	private int min = 1111;
	private int dropX, dropY;
	private String userID;
	private JLabel back;
	private ImageIcon img;
	private JLabel logo;
	private ImageIcon stamp;
	
	public Journey(CreateMap obj1, CabAssign obj2, String uid, int x1, int y1)
	{
		cm1 = obj1;
		cb = obj2;
		userID = uid;
		dropX = x1;
		dropY = y1;
		
		timer = new java.util.Timer();
        timer.schedule(new RemindTask(),  /*initial delay*/5*1000,  /*subsequent rate*/ 2*1000);  
        
        OTP = (int)(Math.random()*(max-min) + min);
		
		setTitle("Rapid cabs");
		setBounds(200,100,900,620);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
		setResizable(true);
		img = new ImageIcon("journey.jpg");
		stamp = new ImageIcon("logo.png");
		
		c = getContentPane(); 
		c.setLayout(null);
		
		title = new JLabel("Travelling Details");
		title.setFont(new Font("Arial", Font.PLAIN, 25)); 
		title.setSize(300, 30); 
		title.setLocation(120, 100); 
		c.add(title);
		
		atime = new JLabel("Cab Will Arrive in   : ");
		atime.setFont(new Font("Arial", Font.PLAIN, 20)); 
		atime.setSize(300, 20); 
		atime.setLocation(40, 170); 
		c.add(atime);
		
		tatime = new JLabel(""); 
		tatime.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tatime.setSize(300, 20); 
		tatime.setLocation(255, 170); 
		c.add(tatime);
		tatime.setText(cb.arrivalTime + " seconds");   //should be modified accordingly
		
		driver = new JLabel("Your Driver's Name: ");
		driver.setFont(new Font("Arial", Font.PLAIN, 20)); 
		driver.setSize(300, 20); 
		driver.setLocation(40, 220); 
		c.add(driver);
		
		tdriver = new JLabel(""); 
		tdriver.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tdriver.setSize(300, 20); 
		tdriver.setLocation(255, 220); 
		c.add(tdriver);
		tdriver.setText(cb.cabDriver);  //should be modified accordingly
		
		rating = new JLabel("Your Driver's Rating: ");
		rating.setFont(new Font("Arial", Font.PLAIN, 20)); 
		rating.setSize(300, 21); 
		rating.setLocation(40, 270); 
		c.add(rating);
		
		trating = new JLabel(""); 
		trating.setFont(new Font("Arial", Font.PLAIN, 20)); 
		trating.setSize(300, 20); 
		trating.setLocation(255, 270); 
		c.add(trating);
		double driverRating = Double.parseDouble(DriverDatabase.getRatingByCarID(cb.cabAssigned + ""));
		trating.setText(String.format("%.1f", driverRating));  //should be modified accordingly
		
		
		carid = new JLabel("Car Number    : ");
		carid.setFont(new Font("Arial", Font.PLAIN, 20)); 
		carid.setSize(300, 20); 
		carid.setLocation(80, 320); 
		c.add(carid);
		
		tcarid = new JLabel(""); 
		tcarid.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tcarid.setSize(300, 20); 
		tcarid.setLocation(250, 320); 
		c.add(tcarid);
		tcarid.setText(cb.cabAssigned + "");  //should be modified accordingly
		
		cancel = new JButton("Cancel Ride");
		cancel.setFont(new Font("Arial", Font.PLAIN, 20)); 
		cancel.setSize(200, 30); 
		cancel.setLocation(100, 430);
		cancel.addActionListener(this); 
		c.add(cancel);
		
		otp = new JLabel("Your OTP is    : ");
		otp.setFont(new Font("Arial", Font.PLAIN, 20)); 
		otp.setSize(300, 20); 
		otp.setLocation(75, 370); 
		c.add(otp);
		
		totp = new JLabel(""); 
		totp.setFont(new Font("Arial", Font.PLAIN, 20)); 
		totp.setSize(300, 20); 
		totp.setLocation(250, 370); 
		c.add(totp);
		totp.setText(OTP + "");  //Randomly generated
		
		logo = new JLabel("",stamp,JLabel.CENTER);
		logo.setBounds(5,5,1500,90);
		c.add(logo);
		
		back = new JLabel("",img,JLabel.CENTER);
		back.setBounds(0,0,900,620);
		c.add(back);
		
		
			
		setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getSource() == cancel)
		{
			timer.cancel();
			cb.notAssign(cm1.map, cm1.cabCoor);
			setVisible(false);
			Book b111 = new Book(userID, cm1);
		} 
	}
	
	private class RemindTask extends TimerTask
	{
		int initTime = cb.arrivalTime;
			
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
				setVisible(false);
				Travel t1 = new Travel(cm1, cb, userID, dropX, dropY);
			}
			else
			{
				timer.cancel();
			}
		}
	}
}
	


		


		
		

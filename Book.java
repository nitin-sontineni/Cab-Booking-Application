//book
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

import java.io.*;
import database.DatabaseOperations;
import cabops.*;
 
class Book extends JFrame implements ActionListener {
	private Container c;
	private JLabel title;
	private JLabel note;
	private JLabel wmoney;  //wallet money
	private JLabel money;
	private JTextField twmoney;   //textfield for wallet money
	private JButton add;
	private JLabel pl;  //pickup location
	private JTextField tpl;
	private JLabel dl;  //drop location
	private JTextField tdl;
	private JButton book;
	private JButton logout;
	private JComboBox<String> pickup_x;
	private JComboBox<String> pickup_y;
	private JComboBox<String> drop_x;
	private JComboBox<String> drop_y;
	private JLabel back;
	private ImageIcon img;
	private JLabel logo;
	private ImageIcon stamp;
	
	private String userID;
	private String locations[] = { "0", "1",  "2", "3", "4", "5", 
								"6", "7", "8", "9", "10", "11",
								"12", "13", "14", "15", "16",
								"17", "18", "19", "20", "21",
								"22", "23", "24", "25", "26",
								"27", "28", "29", "30", "31" };
								
	private CreateMap cm1;
	
	public Book(String uID, CreateMap obj)
	{
		userID = uID;
		cm1 = obj;
		
		for(int i = 0; i < 30; i++)
		{
			System.out.println(cm1.cabCoor[i][0] + " " + cm1.cabCoor[i][1]);
		}
		
		double showMoney = Double.parseDouble(DatabaseOperations.getWalletByUserID(uID));
		
		System.out.println(uID);
		System.out.println(DatabaseOperations.getWalletByUserID(uID));
		
		setTitle("Rapid cabs");
		setBounds(200,100,900,500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
		setResizable(true);
		img = new ImageIcon("book.png");
		stamp = new ImageIcon("logo.png");
		
		c = getContentPane(); 
		c.setLayout(null);
		
		title = new JLabel("Book Your Cab");
		title.setFont(new Font("Arial", Font.PLAIN, 30)); 
		title.setSize(300, 30); 
		title.setLocation(150, 50);
		//title.setForeground(Color.white); 
		c.add(title);
		
		//Note: Minimum wallet balance is Rs.300
		note = new JLabel("Minimum required wallet balance is Rs.300");
		note.setFont(new Font("Arial", Font.PLAIN, 20));
		note.setSize(400, 25);
		note.setLocation(80, 100);
		//note.setForeground(Color.white);
		c.add(note);
		
		wmoney = new JLabel("Wallet Money:");
		wmoney.setFont(new Font("Arial", Font.PLAIN, 20)); 
		wmoney.setSize(300, 20); 
		wmoney.setLocation(100, 150);
		//wmoney.setForeground(Color.white); 
		c.add(wmoney);
		
		money = new JLabel(""); 
		money.setFont(new Font("Arial", Font.PLAIN, 20)); 
		money.setSize(300, 20); 
		money.setLocation(250, 150);
		//money.setForeground(Color.white); 
		c.add(money);
		money.setText("Rs." + String.format("%.1f", showMoney));   //should be modified according to the userid
		
		pl = new JLabel("PickUp Location");
		pl.setFont(new Font("Arial", Font.PLAIN, 20)); 
		pl.setSize(190, 25); 
		pl.setLocation(100, 200); 
		//pl.setForeground(Color.white);
		c.add(pl);
		
		add = new JButton("Add");
		add.setFont(new Font("Arial", Font.PLAIN , 15));
		add.setSize(70,20);
		add.setLocation(380,150);
		add.addActionListener(this);
		//add.setForeground(Color.white);
		c.add(add);
		
		/*tpl = new JTextField(); 
		tpl.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tpl.setSize(190, 20); 
		tpl.setLocation(180, 150); 
		c.add(tpl);*/
		
		pickup_x = new JComboBox<>(locations); 
		pickup_x.setFont(new Font("Arial", Font.PLAIN, 15)); 
		pickup_x.setSize(60, 20); 
		pickup_x.setLocation(280, 200); 
		//pickup_x.setForeground(Color.white);
		c.add(pickup_x);
		
		pickup_y = new JComboBox<>(locations); 
		pickup_y.setFont(new Font("Arial", Font.PLAIN, 15)); 
		pickup_y.setSize(60, 20); 
		pickup_y.setLocation(350, 200); 
		//pickup_y.setForeground(Color.white);
		c.add(pickup_y);
		
		dl = new JLabel("Drop Location");
		dl.setFont(new Font("Arial", Font.PLAIN, 20)); 
		dl.setSize(190, 25); 
		dl.setLocation(100, 250); 
		//dl.setForeground(Color.white);
		c.add(dl);
		
		/*tdl = new JTextField(); 
		tdl.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tdl.setSize(190, 20); 
		tdl.setLocation(180, 200); 
		c.add(tdl);*/
		
		drop_x = new JComboBox<>(locations); 
		drop_x.setFont(new Font("Arial", Font.PLAIN, 15)); 
		drop_x.setSize(60, 20); 
		drop_x.setLocation(280, 250); 
		//drop_x.setForeground(Color.white);
		c.add(drop_x);
		
		drop_y = new JComboBox<>(locations); 
		drop_y.setFont(new Font("Arial", Font.PLAIN, 15)); 
		drop_y.setSize(60, 20); 
		drop_y.setLocation(350, 250); 
		//drop_y.setForeground(Color.white);
		c.add(drop_y);
		
		
		//Book button
		book = new JButton("Book");
		book.setFont(new Font("Arial", Font.BOLD, 15));
		book.setSize(110,30);
		book.setLocation(130,300);
		book.addActionListener(this);
		c.add(book);
		
		//Log Out button
		logout = new JButton("Log Out");
		logout.setFont(new Font("Arial", Font.BOLD, 15));
		logout.setSize(110,30);
		logout.setLocation(270,300);
		logout.addActionListener(this);
		c.add(logout);
		
		logo = new JLabel("",stamp,JLabel.CENTER);
		logo.setBounds(5,5,1500,90);
		c.add(logo);
		
		back = new JLabel("",img,JLabel.CENTER);
		back.setBounds(0,0,900,500);
		c.add(back);
		
		setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent e) 
	{ 
		if (e.getSource() == add)
		{
			boolean valid = true;
			String moneyString = JOptionPane.showInputDialog(null, "Enter the amount you need to add");
			System.out.println(moneyString);
			
			if(moneyString == null)
				valid = false;
			
			while(valid)
			{
				try
				{											
					String initWallet = DatabaseOperations.getWalletByUserID(userID);
					double addMoney = Double.parseDouble(moneyString);
					double initMoney = Double.parseDouble(initWallet);
					double finalMoney = initMoney+addMoney;
					DatabaseOperations.editWalletByUserID(finalMoney + "" , userID);
					break;
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Invalid input, Please try again by clicking on 'add' button");
					break;	
				}
				catch(IOException ioe)
				{
					System.out.println("I/O error in editing wallet.");
					System.out.println("Error in : Book.actionPerformed()");
				}
			}
			money.setText("Rs." + String.format("%.1f", Double.parseDouble(DatabaseOperations.getWalletByUserID(userID))));
		}
		else if(e.getSource() == book)
		{
			String initWallet = DatabaseOperations.getWalletByUserID(userID);
			int money = ((int)(Double.parseDouble(initWallet) + 0.5));
	
			if(money < 300)
			{
				JOptionPane.showMessageDialog(null, "Minimum required wallet balance is Rs.300. Please add money to your wallet.", "Alert",
				 								JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				//Confirm Booking page
				//JOptionPane.showMessageDialog(null, "More functionality coming soon");
				int x0 = Integer.parseInt((String)pickup_x.getSelectedItem());
				int y0 = Integer.parseInt((String)pickup_y.getSelectedItem());
				int x1 = Integer.parseInt((String)drop_x.getSelectedItem());
				int y1 = Integer.parseInt((String)drop_y.getSelectedItem());
				
				if(x0 == x1 && y0 == y1)
				{
					JOptionPane.showMessageDialog(null, "Fun Fact! Walking is good for health. :)", "Fun Fact!", 
													JOptionPane.INFORMATION_MESSAGE);
				}
				else 
				{	
					Confirm f5 = new Confirm(userID, cm1, x0, y0, x1, y1);
					setVisible(false);
				}
			}
		}
		else if(e.getSource() == logout)
		{
			for(int i = 0; i < Driver.mapUserIDToCabAssignInstance.size(); i++)
			{
				if(Driver.mapUserIDToCabAssignInstance.get(i).equals(userID))
				{
					Driver.mapUserIDToCabAssignInstance.remove(i);
					Driver.cabAssignInstanceForEachLoggedInUser.remove(i);
					System.out.println("Removing CabAssign object for user with userID " + userID);
				}
			}
			
			setVisible(false);
			System.out.println("User "+ userID +" successfully logged out");
		}
	}
} 
	



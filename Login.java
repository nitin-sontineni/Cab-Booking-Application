//HomePage
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

import java.io.*;
import database.DatabaseOperations;
import cabops.*;


class Login extends JFrame implements ActionListener { 
	private Container c;
	private JLabel title;
	private JLabel userid;
	private JTextField tuserid;
	private JLabel password;
	private JPasswordField tpassword;
	//private JCheckBox keep;
	private JLabel newuser;
	private JButton login;
	private JButton signup;
	public JLabel text;
	private JTextArea resadd;
	private CreateMap cm1;
	private JLabel back;
	private ImageIcon img;
	private JLabel logo;
	private ImageIcon stamp;
	
	public Login(CreateMap obj)
	{	
		cm1 = obj;
		setTitle("Rapid Cabs");
		setBounds(200,100,1000,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setResizable(true);
		img = new ImageIcon("login.jpg");
		stamp = new ImageIcon("logo.png");
		
		c = getContentPane(); 
		c.setLayout(null);
		
		title = new JLabel("Login page");
		title.setFont(new Font("Arial", Font.PLAIN, 30)); 
		title.setSize(300, 30); 
		title.setLocation(450, 30);
		title.setForeground(Color.white); 
		c.add(title);
		
		userid = new JLabel("UserID"); 
		userid.setFont(new Font("Arial", Font.PLAIN, 20)); 
		userid.setSize(100, 20); 
		userid.setLocation(150, 200); 
		c.add(userid);
		
		tuserid = new JTextField(); 
		tuserid.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tuserid.setSize(190, 30); 
		tuserid.setLocation(250, 200); 
		c.add(tuserid);
		
		password = new JLabel("Password"); 
		password.setFont(new Font("Arial", Font.PLAIN, 20)); 
		password.setSize(100, 20); 
		password.setLocation(150, 250); 
		c.add(password); 

		tpassword = new JPasswordField(); 
		tpassword.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tpassword.setSize(190, 30); 
		tpassword.setLocation(250, 250); 
		c.add(tpassword);
		
		/*keep = new JCheckBox("Keep Me Signed In"); 
		keep.setFont(new Font("Arial", Font.PLAIN, 15)); 
		keep.setSize(200, 20); 
		keep.setLocation(200, 200); 
		c.add(keep);*/
		
		login = new JButton("Login"); 
		login.setFont(new Font("Arial", Font.PLAIN, 15)); 
		login.setSize(100, 30); 
		login.setLocation(225, 310); 
		login.addActionListener(this); 
		c.add(login); 
		
		newuser = new JLabel("New User?"); 
		newuser.setFont(new Font("Arial", Font.PLAIN, 20)); 
		newuser.setSize(110, 30); 
		newuser.setLocation(150, 400); 
		c.add(newuser);

		signup = new JButton("SignUp"); 
		signup.setFont(new Font("Arial", Font.PLAIN, 15)); 
		signup.setSize(100, 30); 
		signup.setLocation(260, 400); 
		signup.addActionListener(this); 
		c.add(signup);
		
		text = new JLabel(""); 
		text.setFont(new Font("Arial", Font.PLAIN, 20)); 
		text.setSize(500, 20); 
		text.setLocation(250, 450); 
		c.add(text);
		
		logo = new JLabel("",stamp,JLabel.CENTER);
		logo.setBounds(5,5,210,90);
		c.add(logo);
		
		back = new JLabel("",img,JLabel.CENTER);
		back.setBounds(0,0,1000,600);
		c.add(back);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{ 
		if (e.getSource() == login) 
		{ 
			try
			{
				boolean loggedIn = false;
				String[] record = DatabaseOperations.fetchRecordByUserID(tuserid.getText());
				String password = String.valueOf(tpassword.getPassword());
				
				for(int i = 0; i < Driver.mapUserIDToCabAssignInstance.size(); i++)
				{
					if(Driver.mapUserIDToCabAssignInstance.get(i).equals(record[0]))
					{
						loggedIn = true;
						JOptionPane.showMessageDialog(null, "This UserID is already logged in!", "Alert"
															,JOptionPane.WARNING_MESSAGE);												
					}
				}
				
				if(!loggedIn)
				{
					if(record == null)
					{
						JOptionPane.showMessageDialog(null, "Invalid UserID. Please try again.");
						tuserid.setText("");
						tpassword.setText("");
					}
					else if(record[2].equals(password))
					{
						System.out.println("Logged in user with userID " + record[0] + "."); 
						Driver.mapUserIDToCabAssignInstance.add(record[0]);
						Driver.cabAssignInstanceForEachLoggedInUser.add(new CabAssign());
						Book f1 = new Book(record[0], cm1);
						System.out.println("Successfully created CabAssign object for user " + record[0] + ".");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid password. Please try again.");
						tpassword.setText("");
					}
				}
				tuserid.setText("");
				tpassword.setText("");
			}
			catch(IOException ioe)
			{
				JOptionPane.showMessageDialog(null, "Invalid Username or password. Please try again.");
				System.out.println("IOException at Login.actionPerformed()");
			}
		}
		else if(e.getSource() == signup) {
			SignUp f2 = new SignUp();
		}
	}
}	

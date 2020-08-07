//SignUp
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

import java.io.*;
import database.DatabaseOperations;

class SignUp extends JFrame implements ActionListener {
	private Container c;
	private JLabel title;
	private JLabel username;
	private JTextField tusername;
	private JLabel userid;
	private JTextField tuserid;
	private JLabel number;
	private JTextField tnumber; //Number
	private JLabel email;
	private JTextField temail;
	private JLabel password;
	private JPasswordField tpassword;
	private JLabel confirm;
	private JPasswordField tconfirm;
	private JButton signup;
	private JLabel res;
	private JLabel res2;
	private JTextArea resadd;
	private JLabel disc;
	private JLabel disc2;
	private JLabel logo;
	private ImageIcon stamp;
	
	private String disclaimer1 = "IMPORTANT Note: Fields cannot contain commas (',')";
	private String disclaimer2 = "Emails addresses must have '@' symbol";
	private String disclaimer3 = "Fields cannot contain commas (',')";
	
	public SignUp()
	{
		setTitle("Rapid Cabs");
		setBounds(100,100,700,700);
		setDefaultCloseOperation(HIDE_ON_CLOSE); 
		setResizable(true);
		stamp = new ImageIcon("logo.png");
		
		c = getContentPane(); 
		c.setLayout(null);
		
		title = new JLabel("Sign up");
		title.setFont(new Font("Arial", Font.PLAIN, 30)); 
		title.setSize(300, 30); 
		title.setLocation(300, 130); 
		c.add(title);
		
		username = new JLabel("User Name");
		username.setFont(new Font("Arial", Font.PLAIN, 20)); 
		username.setSize(200, 20); 
		username.setLocation(100, 200); 
		c.add(username);
		
		tusername = new JTextField(); 
		tusername.setFont(new Font("Arial", Font.PLAIN, 20)); 
		tusername.setSize(300, 30); 
		tusername.setLocation(300, 200); 
		c.add(tusername);
		
		userid = new JLabel("User ID");
		userid.setFont(new Font("Arial", Font.PLAIN, 20)); 
		userid.setSize(200, 20); 
		userid.setLocation(100, 250); 
		c.add(userid);
		
		tuserid = new JTextField(); 
		tuserid.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tuserid.setSize(300, 30); 
		tuserid.setLocation(300, 250); 
		c.add(tuserid);
		
		number = new JLabel("Mobile Number");
		number.setFont(new Font("Arial", Font.PLAIN, 20)); 
		number.setSize(300, 20); 
		number.setLocation(100, 300); 
		c.add(number);
		
		tnumber = new JTextField(); 
		tnumber.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tnumber.setSize(300, 30); 
		tnumber.setLocation(300, 300); 
		c.add(tnumber);
		
		email = new JLabel("Email");
		email.setFont(new Font("Arial", Font.PLAIN, 20)); 
		email.setSize(300, 20); 
		email.setLocation(100, 350); 
		c.add(email);
		
		temail = new JTextField(); 
		temail.setFont(new Font("Arial", Font.PLAIN, 15)); 
		temail.setSize(300, 30); 
		temail.setLocation(300, 350); 
		c.add(temail);
		
		password = new JLabel("Password");
		password.setFont(new Font("Arial", Font.PLAIN,20));
		password.setSize(300,20);
		password.setLocation(100,400);
		c.add(password);
		
		tpassword = new JPasswordField();
		tpassword.setFont(new Font("Arial", Font.PLAIN,20));
		tpassword.setSize(300,30);
		tpassword.setLocation(300,400);
		c.add(tpassword);
		
		confirm = new JLabel("Confirm Password");
		confirm.setFont(new Font("Arial", Font.PLAIN,20));
		confirm.setSize(300,20);
		confirm.setLocation(100,450);
		c.add(confirm);
		
		tconfirm = new JPasswordField();
		tconfirm.setFont(new Font("Arial", Font.PLAIN,20));
		tconfirm.setSize(300,30);
		tconfirm.setLocation(300,450);
		c.add(tconfirm);
		
		signup = new JButton("SignUp"); 
		signup.setFont(new Font("Arial", Font.PLAIN, 15)); 
		signup.setSize(100, 30); 
		signup.setLocation(300, 500); 
		signup.addActionListener(this); 
		c.add(signup);
		
		res = new JLabel(""); 
		res.setFont(new Font("Arial", Font.PLAIN, 20)); 
		res.setSize(500, 20); 
		res.setLocation(125, 550); 
		c.add(res);
		
		//Extra field to give more error messages
		res2 = new JLabel(""); 
		res2.setFont(new Font("Arial", Font.PLAIN, 18)); 
		res2.setSize(500, 20); 
		res2.setLocation(125, 570); 
		c.add(res2);
		
		//Note: Fields cannot contain commas (',') 
		disc = new JLabel("");
		disc.setText(disclaimer1);
		disc.setFont(new Font("Arial", Font.PLAIN, 15)); 
		disc.setSize(500, 20); 
		disc.setLocation(125, 600); 
		c.add(disc);
		
		//Note: Fields cannot contain commas ('@')
		disc2 = new JLabel("");
		disc2.setText(disclaimer2);
		disc2.setFont(new Font("Arial", Font.PLAIN, 15)); 
		disc2.setSize(400, 20); 
		disc2.setLocation(125, 620); 
		c.add(disc2);
		
		tusername.setText("");
 		tuserid.setText("");
 		tnumber.setText("");
 		temail.setText("");
 		tpassword.setText("");
 		tconfirm.setText("");
 		
 		logo = new JLabel("",stamp,JLabel.CENTER);
		logo.setBounds(280,10,210,90);
		c.add(logo);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{ 
		if (e.getSource() == signup) 
		{ 		
		 	res.setFont(new Font("Arial", Font.PLAIN, 20));
		 	res2.setText("");
		 	int l = tpassword.getPassword().length; 
		 	char[] tnumberArr = tnumber.getText().toCharArray();
		 	int numLen = tnumberArr.length;
		 	
		 	if(numLen != 10)
		 	{
		 		res.setText("Mobile number must consist of 10 DIGITS.");
		 		tnumber.setText("");
		 	}
		 	else if(!isInteger(tnumber.getText()))
		 	{
		 		res.setText("Mobile number must consist of digits.");
		 		tnumber.setText("");
		 	}
		 	else if(fieldsAreEmpty() == true)
		 	{
		 		res.setText("One or more fields are empty, please check.");
		 	}
		 	else if(foundIllegalCharactersMethod() == true)
		 	{
		 		res.setFont(new Font("Arial", Font.PLAIN, 18));
		 		res.setText(disclaimer3);
		 		res2.setText(disclaimer2);
		 		tusername.setText("");
		 		tuserid.setText("");
		 		tnumber.setText("");
		 		temail.setText("");
		 		tpassword.setText("");
		 		tconfirm.setText("");
		 	}
		 	else if(DatabaseOperations.recordIsPresentWithThisField(tuserid.getText(), tnumber.getText(), temail.getText()) != "none")
		 	{
		 		String repeat = DatabaseOperations.recordIsPresentWithThisField(tuserid.getText(), tnumber.getText(), temail.getText());
		 		res.setText("This " + repeat + " is already taken! Please Try Again.");
		 		tuserid.setText("");
		 		tnumber.setText("");
		 		temail.setText("");
		 		tpassword.setText("");
		 		tconfirm.setText("");
		 	}	
			else if(l<=5)
			{
				res.setText("Password should have minimum 6 characters");
				tpassword.setText("");
		 		tconfirm.setText("");
		 	}
		 	else if(isEqual(tpassword.getPassword(), tconfirm.getPassword()) && l>5)
			{
				//res.setText("SignUp Successful...please login to use your account");
				String password = String.valueOf(tpassword.getPassword());
				DatabaseOperations.saveNewRecord(tuserid.getText(), tusername.getText(), password, tnumber.getText(), temail.getText());
				JOptionPane.showMessageDialog(null, "SignUp Successful...Please login to use your account.");
				setVisible(false);
			} 
			else
			{
				res.setText("Passwords do NOT match. Please check.");
				tpassword.setText("");
		 		tconfirm.setText("");
		 	}
		}
	}
	
	private boolean isEqual(char[] arr1, char[] arr2)
	{
		if(arr1.length == arr2.length)
		{
			for(int i = 0; i < arr1.length; i++)
			{
				if(arr1[i] != arr2[i])
				{
					return false;
				}
			}
			return true;
		}
		else
			return false;
	}
	
	private boolean containsComma(String text)
	{
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == ',')
				return true;
		}
		return false;
	}
	
	private boolean containsComma(char[] text)
	{
		for(int i = 0; i < text.length; i++)
		{
			if(text[i] == ',')
				return true;
		}
		return false;
	}
	
	private boolean doesNotContainAtSymbol(String text)
	{
		for(int i = 0; i < text.length(); i++)
		{	
			if(text.charAt(i) == '@')
				return false;
		}
		return true;
	}
	
	private boolean foundIllegalCharactersMethod()
	{	
	
		boolean[] foundIllegalChars = {false, false, false, false, false, false};
	 	foundIllegalChars[0] = containsComma(tusername.getText());
	 	foundIllegalChars[1] = containsComma(tuserid.getText());
	 	foundIllegalChars[2] = containsComma(tnumber.getText());
	 	foundIllegalChars[3] = containsComma(temail.getText());
	 	foundIllegalChars[4] = doesNotContainAtSymbol(temail.getText());
	 	foundIllegalChars[5] = containsComma(tpassword.getPassword());
	 	
	 	for(int i = 0; i < 6; i++)
	 	{	
	 		if(foundIllegalChars[i] == true)
	 			return true;
	 	}
	 	return false;
	 }
	 
	 private boolean fieldsAreEmpty()
	 {
	 	char[] tusernameArr = tusername.getText().toCharArray();
	 	char[] tuseridArr = tuserid.getText().toCharArray();
	 	char[] tnumberArr = tnumber.getText().toCharArray();
	 	char[] temailArr = temail.getText().toCharArray();
	 	
	 	if(tusernameArr.length == 0)
			return true;
		if(tuseridArr.length == 0)
			return true;
		if(tnumberArr.length == 0)
			return true;
		if(temailArr.length == 0)
			return true;
		if(tpassword.getPassword().length == 0)
			return true;
		if(tconfirm.getPassword().length == 0)
			return true;
		else
			return false;
	}
	
	//ASCII 0: 48 to 9: 57
	
	private boolean isInteger(String num)
	{
		for(int i = 0; i < num.length(); i++)
		{
			if(!(num.charAt(i) <= 57 && num.charAt(i)>=48))
				return false;
		}
		return true;
	}				 		
}



 
		
		
		
		
		
		
		
		
		

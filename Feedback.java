import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

import cabops.*;

class Feedback extends JFrame implements ActionListener {
	private Container c;
	private JLabel title;
	private JLabel feedback;
	private JLabel sentence;
	private JComboBox<String> tfeedback;
	private JButton submit;
	private JLabel logo;
	private ImageIcon stamp;
	private String ratings[] = { "1","2","3","4","5" };
	private CreateMap cm1;
	private CabAssign cb;
	private String userID;
	private JLabel back;
	private ImageIcon img;
	
	public Feedback(CreateMap obj1, CabAssign obj2, String uid)
	{
		cm1 = obj1;
		cb = obj2;
		userID = uid;
		
		setTitle("Rapid cabs");
		setBounds(300,100,500,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setResizable(true);
		img = new ImageIcon("feedback.png");
		stamp = new ImageIcon("logo.png");
		
		c = getContentPane(); 
		c.setLayout(null);
		
		title = new JLabel("Your feedback is valuable");
		title.setFont(new Font("Arial", Font.PLAIN, 20)); 
		title.setSize(300, 25); 
		title.setLocation(45, 170);
		//title.setForeground(Color.white); 
		c.add(title);
		
		sentence = new JLabel("How was your ride with " + cb.cabDriver + "?");
		sentence.setFont(new Font("Arial", Font.PLAIN,15));
		sentence.setSize(300,25);
		sentence.setLocation(20,120);
		c.add(sentence);
		
		feedback = new JLabel("Rating");
		feedback.setFont(new Font("Arial",Font.PLAIN,20));
		feedback.setSize(200,22);
		feedback.setLocation(80,220);
		c.add(feedback);
		
		tfeedback = new JComboBox<>(ratings); 
		tfeedback.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tfeedback.setSize(60, 20); 
		tfeedback.setLocation(170, 220); 
		c.add(tfeedback);
		
		submit = new JButton("Submit");
		submit.setFont(new Font("Arial",Font.PLAIN, 15));
		submit.setSize(100,30);
		submit.setLocation(120,270);
		submit.addActionListener(this);
		c.add(submit);
		
		logo = new JLabel("",stamp,JLabel.CENTER);
		logo.setBounds(100,5,205,90);
		c.add(logo);
		
		back = new JLabel("",img,JLabel.CENTER);
		back.setBounds(0,0,500,350);
		c.add(back);
		
		setVisible(true);
	}	
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getSource() == submit )
		{
			JOptionPane.showMessageDialog(null, "Thank you for your feedback! Redirecting to Booking page.....");
			
			int feedbackRating = Integer.parseInt((String)tfeedback.getSelectedItem());
			Rating.driverRating(feedbackRating, cb.cabAssigned);
			System.out.println("Rating updated");
			
			setVisible(false);
			Book f111 = new Book(userID, cm1); 
		}
	}
}
	

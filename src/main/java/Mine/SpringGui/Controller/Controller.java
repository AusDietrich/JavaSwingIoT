package Mine.SpringGui.Controller;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller implements ActionListener {

	   JFrame frame=new JFrame();
	    JButton button=new JButton("Led ON");
	    JButton button2=new JButton("Led OFF");
	 
	    Controller(){
	        prepareGUI();
	        buttonProperties();
	        button2Properties();
	    }
	 
	    public void prepareGUI(){
	        frame.setTitle("My Window");
	        frame.getContentPane().setLayout(null);
	        frame.setVisible(true);
	        frame.setBounds(200,200,400,400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    public void buttonProperties(){
	        button.setBounds(10,200,100,40);
	        frame.add(button);
	        button.addActionListener(this);
	    }
	    public void button2Properties(){
	        button2.setBounds(120,200,100,40);
	        frame.add(button2);
	        button2.addActionListener(this);
	    }
	    //Overriding actionPerformed() method
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println(e.getActionCommand());
	    	RestTemplate restTemplate = new RestTemplate();
	    	String emp = "";
	    	if(e.getActionCommand().toString().equals("Led ON")) {
	    		System.out.println("LED SHOULD BE ON!!!");
	    		emp = restTemplate.getForObject("http://192.168.0.20/on", String.class);
	    	} else if (e.getActionCommand().toString().equals("Led OFF")) {
	    		emp = restTemplate.getForObject("http://192.168.0.20/off", String.class);
	    	}
			System.out.println(emp);
	    	frame.getContentPane().setBackground(Color.red);
	    }
	 
	    public static void main(String[] args)
	    {
	        new Controller();
	    }
	}
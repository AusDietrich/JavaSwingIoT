package mine.springGui.controller;
import java.awt.*; 
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import mine.springGui.creepyWebCrawly.WebCrawler;
import mine.springGui.restCalls.Rest;

@RestController
public class Controller implements ActionListener {

	@Autowired
	Rest rest;
	   JFrame frame=new JFrame();
	    JButton button=new JButton("Led ON");
	    JButton button2=new JButton("Led OFF");
	    JButton button3=new JButton("Creepy Crawly");
	    JLabel l1 = new JLabel();
	    Controller(){
	        prepareGUI();
	        buttonProperties();
	        button2Properties();
	        button3Properties();
	        label1Properties();
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
	    public void button3Properties() {
	    	button3.setBounds(220,200,100,40);
	    	frame.add(button3);
	    	button3.addActionListener(this);
	    }
	    public void label1Properties() {
	    	l1.setBounds(10,10,400,40);
	    	frame.add(l1);
	    }
	    //Overriding actionPerformed() method
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	rest.Weather();
	    	System.out.println(e.getActionCommand());
	    	RestTemplate restTemplate = new RestTemplate();
	    	WebCrawler webCrawler = new WebCrawler();
	    	String emp = "";
	    	String temper = "";
	    	if(e.getActionCommand().toString().equals("Led ON")) {
	    		emp = restTemplate.getForObject("http://192.168.0.20/on", String.class);
	    	} else if (e.getActionCommand().toString().equals("Led OFF")) {
	    		emp = restTemplate.getForObject("http://192.168.0.20/off", String.class);
	    	} else if (e.getActionCommand().toString().equals("Creepy Crawly")) {
	    		temper = webCrawler.getPageLinks("https://www.wunderground.com/weather/us/az/tempe");
	    		l1.setText(temper);
	    	}
	    	
	    	File directoryPath = new File(System.getProperty("user.dir"));
	        //List of all files and directories
	        String contents[] = directoryPath.list();
	        System.out.println("List of files and directories in the specified directory:");
	        for(int i=0; i<contents.length; i++) {
	           System.out.println(contents[i]);
	        }
	        try {
				Process proc = Runtime.getRuntime().exec("java -jar SpringGui-0.0.1-SNAPSHOT.jar");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	    	frame.getContentPane().setBackground(Color.red);
	    }
	 
	    public static void main(String[] args)
	    {
	        new Controller();
	    }
	}
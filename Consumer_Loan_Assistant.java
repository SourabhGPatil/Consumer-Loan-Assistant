//Importing necessary libraries for the program
package com.suven.consultancy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Defining a class "Consumer_Loan_Assistant" which extends "JFrame" and implements "ActionListener"
public class Consumer_Loan_Assistant extends JFrame implements ActionListener {
    //Defining labels, text fields, buttons, text areas and fonts to be used
    JLabel l1,l2,l3,l4,la;
    JTextField tf1,tf2,tf3,tf4;
    JButton b1,b2,x1,x2,exit;
    JTextArea ta;
    Font flabel,fbutton;
    Boolean tf3enabled=false,tf4enabled=true;

    //Constructor of the class
    Consumer_Loan_Assistant(){
        super("Consumer Loan Assistant");

        //Setting properties of JTextArea
        ta=new JTextArea("");
        ta.setBounds(400,40,300,150);
        ta.setFont(new Font("Segoe Script",Font.PLAIN,14));
//        ta.setBackground(Color.YELLOW);
        ta.setForeground(Color.BLACK);
        ta.setEditable(false);
        ta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(ta);

        //Defining fonts to be used
        flabel =new Font("Arial", Font.PLAIN,16);
        fbutton=new Font("SANS_SERIF",Font.BOLD,13);

        //Defining and setting properties of labels
        la = new JLabel("Loan Analysis: ");
        l1=new JLabel("Loan Balance");
        l2=new JLabel("Interest Rate");
        l3=new JLabel("Number of Payments");
        l4=new JLabel("Monthly Payment");
        
        
        //Defining and setting properties of text fields
        tf1=new JTextField();
        tf2=new JTextField();
        tf3=new JTextField();
        tf4=new JTextField();
        
        //Defining and setting properties of buttons
        b1=new JButton("Compute Monthly Payment");
        b2=new JButton("New Loan Analysis");

        //Setting layout of the frame and its dimensions
        setLayout(null);
        setSize(800,300);
//        getContentPane().setBackground(Color.RED);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Setting positions and fonts of labels and adding them to the frame
        la.setBounds(400,0,150,50);
        la.setFont(flabel);
        add(la);

        l1.setBounds(20,0,100,50);
        l1.setFont(flabel);
        add(l1);

        l2.setBounds(20,30,150,50);
        l2.setFont(flabel);
        add(l2);

        l3.setBounds(20,60,150,50);
        l3.setFont(flabel);
        add(l3);

        l4.setBounds(20,90,150,50);
        l4.setFont(flabel);
        add(l4);

        //Setting positions and fonts of buttons and adding them to the frame
        b1.setBounds(50,140,250,30);
        b1.setFont(fbutton);
        add(b1);

        b2.setBounds(75,190,200,30);
        b2.setFont(fbutton);
        b2.setEnabled(false);
        add(b2);

        //Adding action listeners to buttons
        b1.addActionListener(this);
        b2.addActionListener(this);

        //Setting positions, fonts, and other properties of text fields and adding them to the frame
        tf1.setBounds(170,15,100,20);
        tf1.setFont(flabel);
        tf1.setHorizontalAlignment(JTextField.RIGHT);
        add(tf1);

        tf2.setBounds(170,45,100,20);
        tf2.setHorizontalAlignment(JTextField.RIGHT);
        tf2.setFont(flabel);
        add(tf2);

        tf3.setBounds(170,80,100,20);
        tf3.setHorizontalAlignment(JTextField.RIGHT);
        tf3.setFont(flabel);
        add(tf3);

        tf4.setBounds(170,110,100,20);
        tf4.setHorizontalAlignment(JTextField.RIGHT);
        tf4.setFont(flabel);
        tf4.setEditable(false);
        tf4.setForeground(Color.BLACK);
        tf4.setBackground(Color.YELLOW);
        add(tf4);

        // Creates two buttons with "X" label and sets their bounds and font
        x1=new JButton("X");
        x1.setBounds(300,70,50,25);
        x1.setFont(fbutton);
        add(x1);

        x2=new JButton("X");
        x2.setBounds(300,110,50,25);
        x2.setFont(fbutton);
        add(x2);
        
        // Sets x2 button to be initially invisible
        x2.setVisible(false);

        // Adds action listeners to x1, x2, and exit buttons
        x1.addActionListener(this);
        x2.addActionListener(this);

        exit= new JButton("Exit");
        exit.setBounds(500,220,100,30);
        exit.setFont(fbutton);
        add(exit);
        exit.addActionListener(this);
    }

    // Handles action events of the buttons
    public void actionPerformed(ActionEvent e) {
        // Executes if b1 button is pressed
        if(e.getSource()==b1) {
            // Executes if tf2 text field is empty or has a value of "0"
            try {
                if(tf2.getText().equals("") || tf2.getText().equals("0")) {
                JOptionPane.showMessageDialog(null,"Interest Rate cannot be 0%");
                }
                // Executes if one of the text fields is empty
                if ((tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("")) && (tf1.getText().equals("") || tf2.getText().equals("") || tf4.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Fill All The Required Details");
                }
                
                // Calculates the number of payments
                if (tf4.getText().equals("")) {
                    float A = Float.parseFloat(tf1.getText());
                    float i = Float.parseFloat(tf2.getText());
                    float n = Float.parseFloat(tf3.getText());
                    float I = i / 1200;
                    float P = (float) (I * A / (1 - Math.pow((1 + I), -n)));
                    tf4.setText(P + "");
                    String str="Loan Amount : $"+A+"\n";
                    str+="Interest Rate : "+I*1200+"%\n\n";
                    str+=n+" Payments of : $"+P;
                    ta.setText(str);
                }
                
                // Calculates the loan period
                if (tf3.getText().equals("")) {
                    float A = Float.parseFloat(tf1.getText());
                    float i = Float.parseFloat(tf2.getText());
                    float P = Float.parseFloat(tf4.getText());
                    float I = i / 1200;
                    float n = (float) (-(Math.log10(1 - I * A / P)) / Math.log10(1 + I));
                    tf3.setText(n + "");
                    String str="Loan Amount : $"+A+"\n";
                    str+="Interest Rate : "+I*1200+"%\n\n";
                    str+=n+" Payments of : $"+P;
                    ta.setText(str);
                }
//                String As = ""+A;
                
                // Disables b1 button and enables b2 button
                b1.setEnabled(false);
                b2.setEnabled(true);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
        
        // Executes if b2 button is pressed
        if(e.getSource()==b2) {
            // Clears the text field that is currently enabled
            if (tf4enabled) {
                tf4.setText(null);
//            tf3.setText(null);
            }
            if(tf3enabled){
                tf3.setText(null);
            }
            // Enables b1 button and disables b2 button
            b1.setEnabled(true);
            b2.setEnabled(false);
        }
        
        // Executes if x1 button is pressed
        if(e.getSource()==x1){
            // Sets x1 button to be invisible and x2 button to be visible
            x1.setVisible(false);
            x2.setVisible(true);
            tf4.setEditable(true);
            tf3.setEditable(false);
            tf3.setBackground(Color.YELLOW);
            tf4.setBackground(Color.white);
            tf3enabled=true;
            tf4enabled=false;
            tf3.setText(null);

            // Enables button 1 and disables button 2
            b1.setEnabled(true);
            b2.setEnabled(false);
        }
        
        // Executes if x2 button is pressed
        if(e.getSource()==x2){
            // Sets x2 button to be invisible and x1 button to be visible
            x1.setVisible(true);
            x2.setVisible(false);
            tf4.setEditable(false);
            tf3.setEditable(true);
            tf3.setBackground(Color.white);
            tf4.setBackground(Color.yellow);
            tf3enabled=false;
            tf4enabled=true;
            tf4.setText(null);

            // Enables button 1 and disables button 2
            b1.setEnabled(true);
            b2.setEnabled(false);
        }
        
        // Executes if the exit button is clicked, i.e, it exits the program
        if(e.getSource()==exit){
            System.exit(0);
        }
    }
    
    // Main function to create an object of "Consumer_Loan_Assistant" class 
    // and start the program
    public static void main(String[] args) {
         new Consumer_Loan_Assistant();
    }

}

/**
 * The StarterGUI class creates the start screen that lets the player input a name and start the game.
 * @author Tony Krystofiak
 * @date 5/1/2023
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StarterMenu implements ActionListener {

 private boolean start;
 private boolean hasName = false;
 private String playerName;

 private int width = 300;
 private int height = 200;
 
 private JButton buttonStart;
 private JButton buttonSubmit;
 
 private JTextField textField;
 
 private JFrame frame;
 
 private JPanel panel;
 
 /**
  * The StarterMenu constructor creates the StarterGUI.
  */
  public StarterMenu() { 
  setStart(false);
  
  panel = new JPanel();
  panel.setBounds(0, 0, width, height);
  panel.setLayout(new FlowLayout()); //
  
  JLabel label = new JLabel();
  label.setText("Player Name: ");
  label.setBounds(130, 10, 100, 30);
  
  buttonStart = new JButton("Start");
  buttonStart.addActionListener(this);
  buttonStart.setBounds(10, 10, 100, 30);
  
  buttonSubmit = new JButton("Submit");
  buttonSubmit.addActionListener(this);
  buttonSubmit.setBounds(10, 50, 100, 30);
  
  
  textField = new JTextField();
  textField.setPreferredSize(new Dimension(50, 20));
  textField.setBounds(130, 50, 100, 30);
  
  panel.add(buttonStart);
  panel.add(buttonSubmit);
  panel.add(label);
  panel.add(textField);
  panel.setLayout(null); //manual creation of object layouts
  
  frame = new JFrame();
  frame.setTitle("Start");
  frame.setSize(width, height);
  frame.setBackground(new Color(100, 150, 100));
  frame.setVisible(true);
  frame.setResizable(false);
  frame.setLocationRelativeTo(null);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.add(panel, BorderLayout.CENTER);
 }
  /**
   * Returns boolean start, which allows the player to start the game
   * @return boolean start
   */
  public boolean getStart() {
   return start;
  }
/**
   * Sets the boolean variable start
   * @param boolean start is the start condition
   */
  public void setStart(boolean start) {
   this.start = start;
  }
  /**
   * Returns String playerName, the name of the player
   * @return String playerName
   */
  public String getPlayerName() {
   return playerName;
  }
  /**
   * Sets the name of the player
   * @param String playerName is the new name of the player
   */
  public void setPlayerName(String playerName) {
   this.playerName = playerName;
  }
/**
   * Registers the button input of the user to start the game and set a name if the input is valid.
   * @param ActionEvent e registers when a button is clicked.
   * Start: starts the game if a valid name is inputtet.
   * Submit: sets playerName if valid input is given.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
   
   if (e.getSource() == buttonStart && hasName) {
    setStart(true);
    System.out.println("Game started");
    System.out.println("start: " + getStart());
    System.out.println("hasName: " + hasName);
    frame.dispose();
    
   } else if (e.getSource() == buttonStart && !hasName) {
    System.out.println("Type in a player name");
   }
   if (e.getSource() == buttonSubmit) {
    playerName = textField.getText();
    if (playerName.equals("")) {
      System.out.println("Player name cannot be blank.");
      hasName = false;
    } else {
      try {
        Double.parseDouble(playerName);
        System.out.println("Player name cannot be a number.");
        hasName = false;
      } catch (NumberFormatException nfe) {
        playerName = textField.getText();
        hasName = true;
      }
    }
    
//    System.out.println(playerName);
//    System.out.println(hasName);
   }
   
  }
}


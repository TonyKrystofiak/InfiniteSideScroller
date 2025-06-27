/**
 * The GameWindow class creates the game window.
 * @author Tony Krystofiak
 * 4/18/2023
 */
package main;

import javax.swing.JFrame;

public class GameWindow {
 private JFrame jframe;
 
 /**
  * The GameWindow constructor creates a new instance of the GameWindow class
  * @param GamePanel gamePanel is the GamePanel object being displayed onto the game window
  */
 public GameWindow(GamePanel gamePanel) { //GamePanel gamePanel
  
  jframe = new JFrame();
  
  jframe.setTitle("Little Man Guy");
  jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  jframe.setSize(1280, 800);
  jframe.add(gamePanel); //adds the gamePanel onto the frame
  jframe.setLocationRelativeTo(null); //sets location of game panel to the center of the screen
  jframe.setResizable(false); //users cannot resize the window
  jframe.setVisible(true); //make the frame visible
 }
}

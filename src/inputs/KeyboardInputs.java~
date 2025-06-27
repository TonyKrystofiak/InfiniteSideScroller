/**
 * The KeyboardInputs class handles instances in which the keyboard inputs are registered.
 * @author Tony Krystofiak
 * 4/20/2023
 */
package inputs;

import main.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener{
  private int jumpSpeed = 12;
  private GamePanel gamePanel;
  
  /**
   * Keyboard Inputs class
   * @param GamePanel gamePanel
   */
  public KeyboardInputs(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
    gamePanel.setPressedRight(false);
  }
  /**
   * Registers when a certain key is pressed
   * @param KeyEvent e registers when a key is pressed
   */
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    if (e.getKeyChar() == 'a') {
      gamePanel.setPressedLeft(true);
      //System.out.println("left " + isPressedLeft);
    } 
    else if (e.getKeyChar() == 'd') {
      gamePanel.setPressedRight(true);
      //System.out.println("right " + isPressedRight);
    } 
    else if (e.getKeyChar() == 'w') {
      gamePanel.setPressedUp(true);
      //System.out.println("up " + isPressedUp);
    } 
    else if (e.getKeyChar() == 's') {
      gamePanel.setPressedDown(true);
      //System.out.println("down " + isPressedDown);
    }
  }
  /**
   * Registers when a certain key is released
   * @param KeyEvent e registers when a key is released
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_A) {
      gamePanel.setPressedLeft(false);
      //System.out.println("left " + gamePanel.isPressedLeft);
    }
    else if (e.getKeyCode() == KeyEvent.VK_D) {
      gamePanel.setPressedRight(false);
      //System.out.println("right " + isPressedRight);
    } 
    else if (e.getKeyCode() == KeyEvent.VK_W) {
      gamePanel.setPressedUp(false);
      //System.out.println("up " + isPressedUp);
    } 
    else if (e.getKeyCode() == KeyEvent.VK_S) {
      gamePanel.setPressedDown(false);
      //System.out.println("down " + isPressedDown);
    } 
  }
  /**
   * Registers when a certain key is pressed
   * @param KeyEvent e registers when there is a keyInput
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      System.out.println("You are pressing the Space key");
      if (!gamePanel.getJumping() && !gamePanel.getFalling()) {
        gamePanel.gravity(-1 * jumpSpeed);
        gamePanel.setFalling(true);
      }
    }
  }
}

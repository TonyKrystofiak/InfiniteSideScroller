/**
 * The GamePanel class handles the display within the game loop, logic within the game loop, and interactions between other objects in the game loop.
 * @author Tony Krystofiak comments by Sam Edwards
 * 4/18/2023
 */
package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Interactives.Platform;
import Interactives.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
  private static final int PANELWIDTH = 1280;
  private static final int PANELHEIGHT = 800;
  private static final int FLOORHEIGHT = PANELHEIGHT - 140;
  public static final int MAX_SPEED = 20;
  private StarterMenu menu = new StarterMenu();
  private float gravity = 0.3f;
  private int yVel = 0;
  private int playerSpeed = 3;
  private int platformSpeed = -3;
  private boolean isPressedRight;
  private boolean isPressedLeft;
  private boolean isPressedUp;
  private boolean isPressedDown;
  private boolean isJumping = false;
  private boolean isFalling = true;
  private int count = 0;
  private int countLimit = 150;
  private int score;
  
  private Random rand = new Random();
  Player player = new Player(PANELWIDTH/2-40, 0, playerSpeed, yVel, menu.getPlayerName()); 
  ArrayList<Platform> platforms = new ArrayList<Platform>();
  
  /**
   * Initializes the GamePanel and gives it key and mouse listeners
   */
  public GamePanel() {  
    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(new MouseInputs(this));
  }
  /**
   * Defines the size of the panel the graphics will appear on
   */
  private void setPanelSize() {
    Dimension size = new Dimension(1280, 800);
    setMinimumSize(size);
    setPreferredSize(size);
    setMaximumSize(size);
  }
  /**
   * Where the magic happens, paints objects onto the game window.
   * @param Graphics g acts as the paintbrush that creates visuals for every object.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    if (player.getAlive()) {
      score++;
      g.setFont(new Font("Arial", Font.BOLD, 30));
      g.drawString("SCORE " + score, PANELWIDTH/2 - 50, 30);
      
      playerSpeed = 3;
      g.fillRect(0, PANELHEIGHT - 140, PANELWIDTH, FLOORHEIGHT);
      
      if (count == countLimit) {
        platformGeneration((int)player.getyPos());
        count = 0;
      }
      
      movementLoop("d", isPressedRight);
      movementLoop("a", isPressedLeft);
      //movementLoop("w", isPressedUp); //used for error testing in collision
      //movementLoop("s", isPressedDown); //used for error testing in collision
      
      gravity(player.getyVel());
      
      Graphics g2d = (Graphics2D) g;
      g.setColor(Color.red);
      ((Graphics2D) g2d).draw(player.getBoundsBottom());
      ((Graphics2D) g2d).draw(player.getBoundsRight());
      ((Graphics2D) g2d).draw(player.getBoundsLeft());
      ((Graphics2D) g2d).draw(player.getBoundsTop());
      border();
      g.setColor(Color.black);
      
      for (int i = 0; i < platforms.size(); i++) { 
        platforms.get(i).moveLeft(platformSpeed);
        if (platforms.get(i).getHarmful()) {
          g.setColor(Color.red);
        } else {
          g.setColor(Color.black);
        }
        
        g.fillRect((int)platforms.get(i).getxPos(), (int)platforms.get(i).getyPos(), (int)platforms.get(i).getWidth(), (int)platforms.get(i).getHeight()); 
        
        collision(platforms.get(i));
        if (platforms.get(i).getxPos() + platforms.get(i).getWidth() < 0 && platforms.size() > 1) { //if platform's right side reaches the left border, delete it from the arraylist
          //System.out.println("#cancelled");
          platforms.remove(i);
          i--;
        }
      }
      g.setColor(Color.black);
      //System.out.println(player.getyVel());
      g.drawRect((int)player.getxPos(), (int)player.getyPos(), (int)player.getWidth(), (int)player.getHeight());
      //updateGame();
      count++;
    } else {
      System.out.println("player name: " + Game.getPlayerName());
      System.out.println("count: " + getScore());
      printToFile();
      System.exit(0);
    }
  }
  /**
   * Updates the game by setting the position of the player
   */
  public void updateGame() {
    setRectPos(player.getxPos(), player.getyPos());
  }
  /**
   * Sets the position of the player and refreshes the GamePanel.
   */
  public void setRectPos(float xPos, float yPos) {
    player.setxPos(xPos);
    player.setyPos(yPos);
    repaint();
  }
  /**
   * Generates platforms at a random y level and determines whether it will be harmful or not
   * @param int yPos is the yPos of player
   */
  public void platformGeneration(int yPos) {
    int yDifference = 50;
    int harmfulCheck = rand.nextInt(4);
    
    if (player.getyPos() + player.getHeight() >= FLOORHEIGHT - yDifference) {
      //System.out.println("in range");
      
      if (harmfulCheck >= 0 && harmfulCheck < 3) {
        platforms.add(new Platform(PANELWIDTH, rand.nextInt(yPos - (yPos - yDifference)) + (yPos - yDifference), platformSpeed, 0, rand.nextInt(50) + 50, rand.nextInt(yDifference - 30) + 30, false));
      } else {
        platforms.add(new Platform(PANELWIDTH, rand.nextInt(yPos - (yPos - yDifference)) + (yPos - yDifference), platformSpeed, 0, rand.nextInt(50) + 50, rand.nextInt(yDifference - 30) + 30, true));
      }
    } else {
      if (harmfulCheck >= 0 && harmfulCheck < 3) {
        platforms.add(new Platform(PANELWIDTH, rand.nextInt((yPos + yDifference) - (yPos - yDifference)) + (yPos - yDifference), platformSpeed, 0, rand.nextInt(50) + 50, rand.nextInt(40) + 30, false));
      } else {
        platforms.add(new Platform(PANELWIDTH, rand.nextInt((yPos + yDifference) - (yPos - yDifference)) + (yPos - yDifference), platformSpeed, 0, rand.nextInt(50) + 50, rand.nextInt(40) + 30, true));
      }
    }
    //System.out.println("size: " + platforms.size());
  }
  /**
   * Creates a new platform
   * @param int xPos
   * @param int yPos
   */
  public void createPlatform(int xPos, int yPos) {
    platforms.add(new Platform(xPos, yPos, platformSpeed, 0, 100, 100, false));
  }
  /**
   * Applies a downward acceleration to the player unless contacting the top of a platform
   * @param float yVel is yVelocity applied to the player
   */
  public void gravity(float yVel) {
    if (yVel < 0 && !isFalling && !isJumping) { //if negative velocity(jumping) and not already jumping and falling
      setJumping(true); //it is jumping
      setFalling(true); //it is falling
    } else {
      setJumping(false); //it is not jumping
    }
    if (getFalling()) { //if it is falling
      player.setyPos(player.getyPos() + yVel); //move it
      player.setyVel(yVel); //set velocity
      player.setyVel(player.getyVel() + gravity); //change velocity
    } else {
      //System.out.println("no velocity");
      player.setyVel(0); //velocity equals 0
    } 
//  System.out.println(yVel);
//  System.out.println(player.getyVel());
//  System.out.println("jumping: " + getJumping());
//  System.out.println("falling: " + getFalling());
  }
  /**
   * Determines the type of movement that is applied to different objects.
   * @param String dir is the direction or type of movement
   * @param boolean isPressed determines whether a key is held down or not.
   */
  public void movementLoop(String dir, boolean isPressed) {
    if (dir == "a" && isPressed) {
      move("x", -1*playerSpeed);
    } else if (dir == "d" && isPressed){
      move("x", playerSpeed);
    }else if (dir == "w" && isPressed){
      move("y", -1*playerSpeed);
    } else if (dir == "s" && isPressed){
      move("y", playerSpeed);
    } else if (dir == "platform"){
      move("x", -3);
    }
  }
  /**
   * Moves the objects around on the screen.
   * @param String axis is the axis the movement occurs on.
   * @param int speed is the speed of the movement
   */
  public void move(String axis, int speed) {
    if (axis.equalsIgnoreCase("x")) {
      player.setxPos(player.getxPos()+speed);;
    } else if (axis.equalsIgnoreCase("y")){
      player.setyPos(player.getyPos()+speed);;
    } else {
      System.out.println("Not a valid axis");
    }
  }  
  /**
   * Sets the game border of the panel
   */
  public void border() {
    if (player.getxPos() <= 0) { //left border
      player.setxPos(0);
      player.setAlive(false);
    } else if (player.getxPos() >= PANELWIDTH - player.getWidth()) { //right border
      player.setxPos(PANELWIDTH - player.getWidth() - playerSpeed);
    } 
    if (player.getyPos() < 0) { //top border
      player.setyPos(0);
      player.setyVel(0);
    } else if (player.getyPos() >= PANELHEIGHT - player.getHeight() - 140) { //bottom border
      player.setyPos(PANELHEIGHT - player.getHeight() - 140);
      setFalling(false);
      setJumping(false);
      //System.out.println("on ground\tfall: " + player.getFalling() + "\tjump: " + player.getJumping());
    } else {
      setFalling(true);
    }
  }
  /**
   * Checks for collision between platforms and player
   * @param Platform p is the current platform being checked for collision.
   */
  public void collision(Platform p) {
    boolean isColliding = false;
    if (player.getBoundsBottom().intersects(p.getBounds(p))) {
      player.setyVel(0);
      setFalling(false);
      setJumping(false);
      player.setyPos(p.getyPos() - player.getHeight() + 2);
      movementLoop("platform", true);
      //System.out.println("colliding with top");
      isColliding = true;
      
    } else if (player.getBoundsTop().intersects(p.getBounds(p))){
      player.setyPos(p.getyPos() + p.getHeight());
      player.setyVel(0);
      isColliding = true;
    }
    if (player.getBoundsRight().intersects(p.getBounds(p))) {
      player.setxPos(p.getxPos() - player.getWidth());
      playerSpeed = 0;
      isColliding = true;
    }
    if (player.getBoundsLeft().intersects(p.getBounds(p))) {
      player.setxPos(p.getxPos() + p.getWidth());
      playerSpeed = 0;
      isColliding = true;
    }
    
    if (isColliding && p.getHarmful()) {
      player.setAlive(false);
    }
  }
  /**
   * Prints player information (score and name) to the file "output.txt"
   */
  public void printToFile() {
    try {
      PrintStream output = new PrintStream("output.txt");
      output.println("Player Name: " + Game.getPlayerName()); //prints player name and score to output.txt
      output.println("Score: " + getScore());
      output.close();
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("File doesn't exist.");
    }
  }
  /**
   * Returns boolean isPressedRight
   * @return boolean isPressedRight
   */ 
  public boolean isPressedRight() {
    return isPressedRight;
  }
  /**
   * Sets isPressedRight
   * @param boolean isPressedRight
   */
  public void setPressedRight(boolean isPressedRight) {
    this.isPressedRight = isPressedRight;
  }
  /**
   * Returns boolean isPressedLeft
   * @return boolean isPressedLeft
   */
  public boolean isPressedLeft() {
    return isPressedLeft;
  }
  /**
   * Sets isPressedLeft
   * @param boolean isPressedLeft
   */
  public void setPressedLeft(boolean isPressedLeft) {
    this.isPressedLeft = isPressedLeft;
  }
  /**
   * Returns boolean isPressedUp
   * @return boolean isPressedUp
   */
  public boolean isPressedUp() {
    return isPressedUp;
  }
  /**
   * Sets isPressedUp
   * @param boolean isPressedUp
   */
  public void setPressedUp(boolean isPressedUp) {
    this.isPressedUp = isPressedUp;
  }
  /**
   * Returns boolean isPressedDown
   * @return boolean isPressedDown
   */
  public boolean isPressedDown() {
    return isPressedDown;
  }
  /**
   * Sets isPressedDown
   * @param boolean isPressedDown
   */
  public void setPressedDown(boolean isPressedDown) {
    this.isPressedDown = isPressedDown;
  }
  /**
   * Returns boolean isJumping
   * @return boolean isJumping
   */
  public boolean getJumping() {
    return isJumping;
  }
  /**
   * Sets isJumping
   * @param boolean isJumping
   */
  public void setJumping(boolean isJumping) {
    this.isJumping = isJumping;
  }
  /**
   * Returns boolean isFalling
   * @return boolean isFalling
   */
  public boolean getFalling() {
    return isFalling;
  }
  /**
   * Sets isFalling
   * @param boolean isFalling
   */
  public void setFalling(boolean isFalling) {
    this.isFalling = isFalling;
  }
  /**
   * Returns int score
   * @return int score
   */
  public int getScore() {
    return score;
  }
  /**
   * Sets score
   * @param int score is the score of the player
   */
  public void setScore(int score) {
    this.score = score;
  }
}
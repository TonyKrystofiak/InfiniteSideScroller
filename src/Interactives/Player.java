/**
 * The player class inherits the abstract class entity and stores information about the player and its hitboxes.
 * @author Tony Krystofiak comments by Sam Edwards
 * 4/25/2023
 */
package Interactives;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{
  private String name;
  private boolean jumping;
  private boolean falling;
  private boolean alive;
  
  /**
   * Player constructor
   * @param float xPos
   * @param float yPos
   * @param float  xVel
   * @param float yVel
   * @param String name
   */
  public Player(float xPos, float yPos, float xVel, float yVel, String name) {
    super(xPos, yPos, xVel, yVel, 40, 60);
    this.name = name;
    jumping = false;
    falling = true;
    setAlive(true);
  }
  /**
   * Returns the name of the player
   * @return String name
   */ 
  public String getName() {
    return name;
  }
  /**
   * Sets the name of the player
   * @param String name
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * Checks if jumping
   * @return boolean jumping
   */
  public boolean getJumping() {
    return jumping;
  }
  /**
   * Setter for boolean jumping
   * @param boolean jumping
   */ 
  public void setJumping(boolean jumping) {
    this.jumping = jumping;
  }
  /**
   * Checks if falling
   * @return boolean falling
   */
  public boolean getFalling() {
    return falling;
  }
  /**
   * Setter for boolean falling 
   * @param boolean  falling
   */
  public void setFalling(boolean falling) {
    this.falling = falling;
  }
  /**
   * Creates top hitbox
   * @return rectangle hitbox
   */
  public Rectangle getBoundsTop() {
    return new Rectangle((int) super.getxPos() + super.getWidth()/6, (int) super.getyPos(), 2 * super.getWidth()/3, super.getHeight()/2);
  }
  /**
   * Creates left hitbox
   * @return rectangle hitbox
   */
  public Rectangle getBoundsLeft() {
    return new Rectangle((int) super.getxPos(), (int) super.getyPos() + 4, super.getWidth()/6, super.getHeight() - 8);
  }
  /**
   * Creates right hitbox
   * @return rectange hitbox
   */
  public Rectangle getBoundsRight() {
    return new Rectangle((int) super.getxPos() + super.getWidth() - super.getWidth()/6, (int) super.getyPos() + 4,  super.getWidth()/6, super.getHeight() - 8);
  }
  /**
   * Creates bottom hitbox
   * @return rectangle hitbox
   */
  public Rectangle getBoundsBottom() {
    return new Rectangle((int) super.getxPos() + super.getWidth()/6, (int) super.getyPos() + super.getHeight()/2, 2 * super.getWidth()/3,  super.getHeight()/2);
  }
  /**
   * Returns boolean alive
   * @return boolean alive
   */
  public boolean getAlive() {
    return alive;
  }
  /**
   * Setter for boolean alive
   * @param boolean alive
   */ 
  public void setAlive(boolean alive) {
    this.alive = alive;
  }
}

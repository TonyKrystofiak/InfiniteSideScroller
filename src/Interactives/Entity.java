/**
 * The Entity abstract class creates the framework for all on-screen objects such as the player and platforms.
 * @author Tony Krystofiak comments by Sam Edwards
 * 4/23/2023
 */

package Interactives;

public abstract class Entity {
  private float xPos;
  private float yPos;
  private float xVel;
  private float yVel;
  private int width;
  private int height;
  
  /**
   * Entity Constructor
   * @param float xPos
   * @param float yPos
   * @param float xVel
   * @param float yVel
   * @param int width
   * @param int height
   */
  public Entity(float xPos, float yPos, float xVel, float yVel, int width, int height) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.xVel = xVel;
    this.yVel = yVel;
    this.width = width;
    this.height = height;
  }
  /**
   * Gets entity x position
   * @return float xPos
   */
  public float getxPos() {
    return xPos;
  }
  /**
   * Sets entity x position
   * @param float xPos
   */
  public void setxPos(float xPos) {
    this.xPos = xPos;
  }
  /**
   * Gets entity y position
   * @return float yPos
   */
  public float getyPos() {
    return yPos;
  }
  /**
   * Sets entity y position
   * @param float yPos
   */
  public void setyPos(float yPos) {
    this.yPos = yPos;
  }
  /**
   * Gets entity x velocity
   * @return float xVel
   */
  public float getxVel() {
    return xVel;
  }
  /**
   * Sets entity x velocity
   * @param float xVel
   */
  public void setxVel(float xVel) {
    this.xVel = xVel;
  }
  /**
   * Gets entity y velocity
   * @return float yVel
   */
  public float getyVel() {
    return yVel;
  }
  /**
   * Sets entity y velocity
   * @param float yVel
   */
  public void setyVel(float yVel) {
    this.yVel = yVel;
  }
  /**
   * Gets entity width
   * @return int width
   */
  public int getWidth() {
    return width;
  }
  /**
   * Sets entity width
   * @param int width
   */
  public void setWidth(int width) {
    this.width = width;
  }
  /**
   * Gets entity height
   * @return int height
   */
  public int getHeight() {
    return height;
  }
  /**
   * Sets entity height
   * @param int height
   */
  public void setHeight(int height) {
    this.height = height;
  }
}

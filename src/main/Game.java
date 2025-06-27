/**
 * The Game class initializes and combines the StarterGUI, GameWindow, and GamePanel classes. 
 * Contains the game loop and implements the Runnable interface to determine the UPS and FPS of the game.
 * @author Tony Krystofiak comments by Sam Edwards
 * 4/18/2023
 */
package main;

public class Game implements Runnable{
 
 private StarterMenu starterGUI;
 private GameWindow gameWindow;
 private GamePanel gamePanel;
 private Thread gameThread;
 private static String playerName;
 private final int FPS_SET = 60; //120
 private final int UPS_SET = 144; //200
 
 public Game() {
   starterGUI = new StarterMenu();
   while (!starterGUI.getStart()) {
     System.out.print(""); //makes the loop run, I don't know why
     if (starterGUI.getStart()) { //spawns in starterGUI
       setPlayerName(starterGUI.getPlayerName());
       System.out.println("starting game");
       gamePanel = new GamePanel();
       gameWindow = new GameWindow(gamePanel);
       gamePanel.requestFocusInWindow();//gives gamePanel the focus of user input
       startGameLoop();
     }
   }
 }
 /**
  * GAME LOOP, startGameLoop, and update BY KAARIN GAMING 
  */
 /*********************************************************************************/
 /**
  * Starts the game loop
  */
 private void startGameLoop() {
   gameThread = new Thread(this);
   gameThread.start();
 }
 /**
  * Updates the game 
  */
 public void update() {
   gamePanel.updateGame(); 
 }
 /**
  * Sets the FPS and UPS of the game.
  */
 @Override
 public void run() { 
   double timePerFrame = 1_000_000_000.0 / FPS_SET; //stores duration of each frame in nanoseconds.
   double timePerUpdate = 1_000_000_000.0 / UPS_SET; //stores duration of each frame in nanoseconds.
   
   long previousTime = System.nanoTime();
   
   int frames = 0;
   int updates = 0;
   long lastCheck = System.currentTimeMillis();
   
   double deltaU = 0;
   double deltaF = 0;
   
   while(true) {
     long currentTime = System.nanoTime();
     
     deltaU += (currentTime - previousTime) / timePerUpdate;
     deltaF += (currentTime - previousTime) / timePerFrame;
     previousTime = currentTime;
     
     if(deltaU >= 1) {
       update();
       updates++;
       deltaU--;
     }
     if(deltaF >= 1) {
       gamePanel.repaint();
       deltaF--;
       frames++;
     }
     if(System.currentTimeMillis() - lastCheck >= 1000) {
       lastCheck = System.currentTimeMillis();
       System.out.println("FPS: " + frames + " | UPS: " + updates);
       frames = 0;
       updates = 0;
     }
   }
 }
 /*********************************************************************************/
 
 /**
  * Retrieves the name of the Player that is inputted through the GUI.
  * @return String playerName, the name of the player.
  */
 public static String getPlayerName() {
   return playerName;
 }
 /**
  * Sets the name of the player to the given value.
  * @param String playerName, the name of the player.
  */
 public void setPlayerName(String playerName) {
   this.playerName = playerName;
 }
}

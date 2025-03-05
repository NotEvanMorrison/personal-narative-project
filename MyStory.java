import org.code.theater.*;
import org.code.media.*;

public class MyStory extends Scene {

  /* 
   * 2d arrays of the baked food and the times I made them
   */
  private String[][] bakedFoods;
  private int[][] timesMade;

  /* 
   * The constructor to call MyStory
   */
  public  MyStory(String[][] bakedFoods, int[][] timesMade) {
    this.bakedFoods = bakedFoods;
    this.timesMade = timesMade;
 }
  
/* 
 * Accessor methods for bakedFoods and timesMade 
 */
  public String[][] getBakedFoods() {
    return bakedFoods;
  }

  public int[][] getTimesMade() {
    return timesMade;
  }

  /*
   *Calls and draws all of my scenes in my story in the proper order
   */
  public void drawScenes() {
    drawIntro();
    drawSweetInt();
    drawSweet();
    drawSavoryInt();
    drawSavory();
    drawBye();
  }

  /* 
   * draws the intro scene
   */
  public void drawIntro() {
  playSound("welcome.wav");
  ImagePlus image = new ImagePlus("welcome.jpg");
  drawImage(image, 0,0,400);
  pause(2);
  String welcome = new String ("Welcome!");
  drawText(welcome.toUpperCase(), 150, 280);
  image.makeRandom();
  drawImage(image,0,0,400);
  pause(3.0);
  clear("white");
  }
  
  /*
   * draws the sweet intro scene
   */
  public void drawSweetInt() {
  playSound("jazz.wav");
  drawImage("sweet.jpg", 0,0,400);
  String sweetInt = new String ("Sweet Foods I Have Made!");
  drawText(sweetInt.toUpperCase(), 65, 280);
  pause(7.0);
  clear("white");
  }
  
    /*
   * draws the sweets scene
   */
  public void drawSweet() {
    setTextHeight(30);
    for(int row = 0; row < bakedFoods.length; row++) {
      setBack(row,0);
      ImagePlus image = new ImagePlus(bakedFoods[row][0]+".jpg");
      playSound("transition.wav");
      drawImage(image, 50, 0, 300);
      drawText(bakedFoods[row][0], 10, 380);
      drawTimesMade(row,0);
      pause(1.0);
      image.colorize();
      drawImage(image,50,0,300);
      pause(1.0);
      clear("white");
    }
  }
  
  /*
   * draws the savory intro scene
   */
  public void drawSavoryInt() {
  playSound("jazz.wav");
  drawImage("savory.jpg", 100,0,200);
  String savoryInt = new String ("Savory Foods I Have Made!");
  drawText(savoryInt, 20, 280);
  pause(7.0);
  clear("white");
  }
  
   /*
   * draws the savory scene
   */
  public void drawSavory() {
    setTextHeight(30);
    for(int row = 0; row < bakedFoods.length; row++) {
      setBack(row,1);
      ImagePlus image = new ImagePlus(bakedFoods[row][1]+".jpg");
      playSound("transition.wav");
      drawImage(image, 50, 0, 300);
      drawText(bakedFoods[row][1], 10, 380);
      drawTimesMade(row,1);
      pause(1.0);
      image.emboss();
      drawImage(image,50,0,300);
      pause(1.0);
      clear("white");
    }
  }

  /*
   * draws the  goodbye scene
   */
    public void drawBye() {
   ImagePlus image = new ImagePlus("goodbye.jpg");
    drawImage(image, 0,0,400);
      pause(1.5);
      image.makeNegative();
    drawImage(image,0,0,400);
    String goodbye = new String ("Goodbye!");
  drawText(goodbye.toUpperCase(), 150, 320);
 pause(1.5);
  }
  
 /*
  * draws the text for how many times an item was made
  */
  public void drawTimesMade(int row, int col) {
    drawText("Times Made:" + timesMade[row][col], 10, 330);
  }
  /*
  * makes a random  colored background depending on how many times I made an item
  */
  public void drawBack(int timesMade) {
    if (timesMade >= 2){
      int ranR = (int) (Math.random() * 256);
      int ranG = (int) (Math.random() * 256);
      int ranB = (int) (Math.random() * 256);
      Color c = new Color (ranR, ranG, ranB);
      clear(c);
    } else if (timesMade > 4) {
      clear ("yellow");
    } else {
      clear("red");
    }
  }  
 
  /*
  * Sets the background color to determined color using the drawBack
  */
  public void setBack(int row, int col) {
    drawBack(timesMade[row][col]);
  }
}
import org.code.theater.*;
import org.code.media.*;

public class TheaterRunner {
  public static void main(String[] args) {

  /*
   *bakedFoods 2d String Array
   */
String[][] bakedFoods = { 
  {"cookies", "potatoes"},
  {"cheesecake", "pizza"},
  {"applePie", "roastChicken"},
  {"chocolateCake", "macCheese"},
  
};
 /*
  *timesMade 2d int Array
  */
int[][] timesMade = {
  {7, 2},
  {2, 4},
  {3, 1},
  {2, 2},
};
    MyStory story = new MyStory(bakedFoods, timesMade);
   
    /*
     *Plays the scene
     */
    story.drawScenes();
    
    Theater.playScenes(story);


    

    
    
  }
}
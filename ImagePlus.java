import org.code.theater.*;
import org.code.media.*;

/*
 * Represents an image that can be modified with filters and effects
 */
public class ImagePlus extends Image {

  private Pixel[][] imagePixels;     // The 2D array of Pixel objects that make up the image

  /*
   * Initializes photo from a specified image file and calls
   * the getImagePixels() method to initialize imagePixels
   */
  public ImagePlus(String imageFile) {
    super(imageFile);
    imagePixels = getPixelsFromImage();
  }

  /*
   * Returns the 2D array of Pixel objects that make up the image
   */
  public Pixel[][] getImagePixels() {
    return imagePixels;
  }

  /*
   * Returns a 2D array of Pixel objects that make up the Image object
   */
  public Pixel[][] getPixelsFromImage() {
    Pixel[][] imagePixels = new Pixel[getHeight()][getWidth()];

    for (int row = 0; row < imagePixels.length; row++) {
      for (int col = 0; col < imagePixels[0].length; col++) {
        imagePixels[row][col] = getPixel(col, row);
      }
    }

    return imagePixels;
  }

  /*
   * Detects the edges of objects in an image
   */
  public void detectEdges(int edgeDistance) {
    for (int row = 0; row < imagePixels.length; row++) {
      for (int col = 0; col < imagePixels[0].length - 1; col++) {
        Pixel leftPixel = imagePixels[row][col];
        Pixel rightPixel = imagePixels[row][col + 1];

        if (getDistance(leftPixel.getColor(), rightPixel.getColor()) > edgeDistance) {
          leftPixel.setColor(Color.BLACK);
        }
        else {
          leftPixel.setColor(Color.WHITE);
        }
      }
    }
  }

  /*
   * Different approach to detecting edges in an image
   */
  public void anotherEdgeDetection(int edgeDistance) {
    for (int row = 0; row < imagePixels.length - 1; row++) {
      for (int col = 0; col < imagePixels[0].length - 1; col++) {
        Pixel currentPixel = imagePixels[row][col];
        Pixel bottomPixel = imagePixels[row + 1][col];
        Pixel rightPixel = imagePixels[row][col + 1];

        int currentIntensity = getAverage(currentPixel);
        int bottomIntensity = getAverage(bottomPixel);
        int rightIntensity = getAverage(rightPixel);

        int bottomDiff = Math.abs(currentIntensity - bottomIntensity);
        int rightDiff = Math.abs(currentIntensity - rightIntensity);

        if (bottomDiff > edgeDistance || rightDiff > edgeDistance) {
          currentPixel.setColor(Color.BLACK);
        }
        else {
          currentPixel.setColor(Color.WHITE);
        }
      }
    }
  }

  /*
   * Returns the distance between firstColor and secondColor
   */
  public double getDistance(Color firstColor, Color secondColor) {
    double redDistance = firstColor.getRed() - secondColor.getRed();
    double greenDistance = firstColor.getGreen() - secondColor.getGreen();
    double blueDistance = firstColor.getBlue() - secondColor.getBlue();
    double distance = Math.sqrt(redDistance * redDistance + greenDistance * greenDistance + blueDistance * blueDistance);
    return distance;
  }

    /*
   * Applies a colorize filter by converting each Pixel to grayscale and applying
   * a color to it based on its grayscale value
   */
  public void colorize() {

    Pixel[][] arr = getImagePixels();

     for(int row = 0; row < arr.length; row++){
           for(int col = 0; col < arr[0].length; col++){
           Pixel currentPixel = arr[row][col];
             
        int currentRed = currentPixel.getRed();
        int currentGreen = currentPixel.getGreen();
        int currentBlue = currentPixel.getBlue();
/* Calculating the average */
        int average = (currentRed + currentGreen + currentBlue) / 3;

/* If the average is less than the 85 value*/
            if (average < 85) {
                currentPixel.setRed(255);
                currentPixel.setGreen(0);
                currentPixel.setBlue(0);
/* If the average is less than the 170 value*/
            } else if (average < 170 ) {
                currentPixel.setRed(0);
                currentPixel.setGreen(255);
                currentPixel.setBlue(0);
/* If the previous two do not apply*/
              } else {
                currentPixel.setRed(0);
                currentPixel.setGreen(0);
                currentPixel.setBlue(255);
            }
           }
         }
         }

  /*
   * Applies an emboss filter by calculating the difference between
   * the red, green, and blue color values of the current and neighboring
   * Pixel objects and setting the current Pixel object to the result
   */
  public void emboss() {
    
    Pixel[][] pixels = getImagePixels();

    for (int row = 1; row < pixels.length; row++) {
      for (int col = 1; col < pixels[0].length; col++) {
        Pixel currentPixel = pixels[row][col];
        Pixel topLeftPixel = pixels[row - 1][col - 1];

        int diffRed = currentPixel.getRed() - topLeftPixel.getRed();
        int diffGreen = currentPixel.getGreen() - topLeftPixel.getGreen();
        int diffBlue = currentPixel.getBlue() - topLeftPixel.getBlue();

        int maxDiff = 0;

        if (Math.abs(diffRed) > Math.abs(diffGreen) && Math.abs(diffRed) > Math.abs(diffBlue)) {
          maxDiff = Math.abs(diffRed);
        }
        else if (Math.abs(diffGreen) > Math.abs(diffRed) && Math.abs(diffGreen) > Math.abs(diffBlue)) {
          maxDiff = Math.abs(diffGreen);
        }
        else {
          maxDiff = Math.abs(diffBlue);
        }

        int newColor = 128 + maxDiff;
        currentPixel.setRed(newColor);
        currentPixel.setGreen(newColor);
        currentPixel.setBlue(newColor);
      }
    }
  }
      /*
   * Inverts the colors in the image by setting the red,
   * green, and blue color values of each Pixel object to
   * the result of 255 minus their current values
   */
  public void makeNegative() {
//instantiated an array that will cycle through our image pixels
    Pixel[][] arr = getImagePixels();

     /* outer for loop goes through each element / pixel in the array in the row as long as it 
    is less than the pixels length. inner for loop goes through each element / pixel in the array 
    in the column as long as it is less than the pixels length */
    for(int row = 0; row < arr.length; row++) {
      for(int col = 0; col < arr[0].length; col++) {
    /* To store each individual pixel from the array from the row and column */
        Pixel currentPixel = arr [row][col];
   /* To set the current RGB whatever that may be from 255, we use currentPixel 
   because that is where it is stord*/
        currentPixel.setRed(255 - currentPixel.getRed());
        currentPixel.setBlue(255 - currentPixel.getBlue());
        currentPixel.setGreen(255 - currentPixel.getGreen());
        
      }
    } 
  }
  /*
   * New filter code that makes every single pixel a new RGB color!
   */
 public void makeRandom() {
    Pixel[][] arr = getImagePixels();
    for (int row = 0; row < arr.length; row++) {
        for (int col = 0; col < arr[0].length; col++) {
            Pixel currentPixel = arr[row][col];
            
          /*
           * Generate random values for the red, green, and blue pixel colors 
           */
            int randomRed = (int) (Math.random() * 256);  
            int randomGreen = (int) (Math.random() * 256); 
            int randomBlue = (int) (Math.random() * 256); 

          /*
           * Set random values
           */            
            currentPixel.setRed(randomRed);
            currentPixel.setGreen(randomGreen);
            currentPixel.setBlue(randomBlue);
        }
    }
}
  /*
   * Returns the average of the red, green, and blue color values
   * of the specified Pixel object
   */
  public int getAverage(Pixel thePixel) {
    return (thePixel.getRed() + thePixel.getGreen() + thePixel.getBlue()) / 3;
  }
}
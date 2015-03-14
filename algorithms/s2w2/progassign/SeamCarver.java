import java.awt.Color;
//import com.javamex.classmexer.MemoryUtil;

public class SeamCarver {
  private static final double INFINITY = 200000000.0;
  private int width;
  private int height;
  private int [][] pic;
  
  public SeamCarver(Picture picture) {
    width  = picture.width();
    height = picture.height();
    
    pic = new int[width][height];
    for (int i = 0; i < width; i++)
      for (int j = 0; j < height; j++)
    {
        pic[i][j] = picture.get(i, j).getRGB();
    }
  }
  
  // return current picture
  public Picture picture() {   
    Picture picture = new Picture(width, height);
    for (int i = 0; i < width; i++)
      for (int j = 0; j < height; j++) {
        Color tmp = new Color(pic[i][j]);
        picture.set(i, j, tmp);
    }
    return picture;
  }
  
  public int width() { return width; }   // width of current picture
  
  public int height() { return height; } // height of current picture
  
  // energy of pixel at column x and row y
  public double energy(int x, int y) {       
    if ((x < 0) || (x >= width) || (y < 0) || (y >= height))
      throw new IndexOutOfBoundsException();
    
    if ((x == 0) || (x == width - 1) || (y == 0) || (y == height -1))
      return 195705.0; // ~3*255^2
    
    Color xm1 = new Color(pic[x-1][y  ]);
    Color xp1 = new Color(pic[x+1][y  ]);
    Color ym1 = new Color(pic[x  ][y-1]);
    Color yp1 = new Color(pic[x  ][y+1]);
    
    return (colorDiff2(xm1, xp1) + colorDiff2(ym1, yp1));   
  }
  
  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {  
    rotatePic(false);  // true clockwise
    int [] reverseSeam = findVerticalSeam();
    rotatePic(true); // false ccw
    
    int [] seam = new int [reverseSeam.length];
    for (int i = 0; i < seam.length; i++)
      seam[i] = reverseSeam[seam.length -1 -i];
    reverseSeam = null;
    return seam;
  }
  
  // sequence of indices for vertical seam
  public int[] findVerticalSeam() { 
    int [] verticalSeam = new int [height]; 
    int [][] edgeFrom   = new int [width][height];
    double [][] distTo  = new double [width][height];
    double min = 200000000;
    int index = -1;

    for (int i = 0; i < width; i++)
      for (int j = 0; j < height; j++) {
        edgeFrom[i][j] = 0;
        if (j == 0)
          distTo[i][j] = 0;
        else
          distTo[i][j] = INFINITY;
    }
       
    for (int j = 1; j < height; j++) {
      // Update the reacheable x'es in j'th row
      double minRow = 200000000;
      for (int i = 0; i < width; i++) {
        for (int k = -1; k <= 1; k++) {
          if (((i+k) < 0) || ((i+k) > width -1))
            continue;
          if (distTo[i][j] > distTo[i+k][j-1] + energy(i, j)) {
            distTo[i][j] = distTo[i+k][j-1] + energy(i, j);
            edgeFrom[i][j] = i+k;
          }   
        }
        if (distTo[i][height-1] < min) {
          min = distTo[i][height-1];
          index = i;
        }        
      }
    }
    for (int j = height -1; j >= 0; j--) {
      verticalSeam[j] = index;
      index = edgeFrom[index][j];
    }
    return verticalSeam;   
  }
  
  public void removeHorizontalSeam(int[] a) {  // remove horizontal seam from picture
    if ((a.length != width) || (height == 0))
      throw new IllegalArgumentException();
    
    for (int i = 0; i < width; i++) { // Do this for every column
      for (int j = a[i]; j < height -1; j++) {
        pic[i][j] = pic[i][j+1];
      }
    }
    height--;   
  }
  
  // remove vertical seam from picture
  public void removeVerticalSeam(int[] a) {
    if ((a.length != height) || (width == 0))
      throw new IllegalArgumentException();
    
    for (int j = 0; j < height; j++) { // Do this for every row
      for (int i = a[j]; i < width -1; i++) {
        pic[i][j] = pic[i+1][j];
      }
    }
    width--;
  }
  
  private double colorDiff2(Color a, Color b) {
    return  (a.getRed()   - b.getRed()  ) * (a.getRed()   - b.getRed()  )
          + (a.getGreen() - b.getGreen()) * (a.getGreen() - b.getGreen())
          + (a.getBlue()  - b.getBlue() ) * (a.getBlue()  - b.getBlue() );
  }
  
  private void rotatePic(boolean clockwise)
  {
    int newWidth  = height;
    int newHeight = width;
    
    int [][] tmp = new int[newWidth][newHeight];
    for (int i = 0; i < width; i++)
      for (int j = 0; j < height; j++)
        if (clockwise) {
          tmp[newWidth-1-j][i] = pic[i][j];
        }
        else {
          tmp[j][newHeight-1-i] = pic[i][j];
        }      
    pic = tmp;
    height = newHeight;
    width = newWidth;
  }
  
    public static void main(String [] args) {
      /*
      Picture inputImg = SCUtility.randomPicture(100,100);
      SeamCarver sc = new SeamCarver(inputImg);
      long mem = MemoryUtil.deepMemoryUsageOf(sc);
      System.out.println("Initial memory usage of SeamCarver: " + mem);

      for (int i = 0; i < 5; i++) {
        int[] horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
      }
      mem = MemoryUtil.deepMemoryUsageOf(sc);
      System.out.println("Memory usage of SeamCarver after: " + mem);
      */
    }
}
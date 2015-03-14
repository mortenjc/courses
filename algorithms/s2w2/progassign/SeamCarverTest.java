import org.junit.Test;
import static org.junit.Assert.assertEquals;
//import com.javamex.classmexer.MemoryUtil;

public class SeamCarverTest
{
  private final double maxval = 195705.0;
  
  @Test
  public void testConstructor() {
    Picture inputImg = new Picture("seamCarving/HJocean.png");
    SeamCarver sc = new SeamCarver(inputImg);
    assertEquals("Width  == 768", 768, sc.width());
    assertEquals("Height == 432", 432, sc.height());
  }
  
  @Test (expected = IndexOutOfBoundsException.class)
  public void testIndexOutsideImage1() {
    Picture inputImg = new Picture("seamCarving/6x5.png");
    SeamCarver sc = new SeamCarver(inputImg);
    sc.energy(-1,0);
  }
  
  @Test (expected = IndexOutOfBoundsException.class)
  public void testIndexOutsideImage2() {
    Picture inputImg = new Picture("seamCarving/6x5.png");
    SeamCarver sc = new SeamCarver(inputImg);
    sc.energy(0,-1);
  }
  
  @Test (expected = IndexOutOfBoundsException.class)
  public void testIndexOutsideImage3() {
    Picture inputImg = new Picture("seamCarving/6x5.png");
    SeamCarver sc = new SeamCarver(inputImg);
    sc.energy(0,5);
  }
  
  @Test (expected = IndexOutOfBoundsException.class)
  public void testIndexOutsideImage4() {
    Picture inputImg = new Picture("seamCarving/6x5.png");
    SeamCarver sc = new SeamCarver(inputImg);
    sc.energy(6,0);
  }
  
  @Test 
  public void testEnergyFunction() {
    Picture inputImg = new Picture("seamCarving/6x5.png");
    SeamCarver sc = new SeamCarver(inputImg);
    for (int i = 0; i < sc.width(); i++) {
      assertEquals(maxval, sc.energy(i,0), 0.01);
      assertEquals(maxval, sc.energy(i,sc.height() -1), 0.01);
    }
    for (int j = 0; j < sc.height(); j++) {
      assertEquals(maxval, sc.energy(0,j), 0.01);
      assertEquals(maxval, sc.energy(sc.width()-1,j), 0.01);
    }
    assertEquals(51304.0, sc.energy(2,1), 0.01);
    assertEquals(61346.0, sc.energy(2,2), 0.01);
    assertEquals(23346.0, sc.energy(1,1), 0.01);   
  }
  
  @Test
  public void testFindVerticalSeamArraySize() {
    Picture inputImg = new Picture("seamCarving/6x5.png");
    SeamCarver sc = new SeamCarver(inputImg);
    int [] iarr = sc.findVerticalSeam();
    assertEquals("returned array has <height> size", sc.height(), iarr.length);
  }  
  
  @Test
  public void testFindVerticalSeam1() {
    Picture inputImg = new Picture("seamCarving/4x6.png");
    SeamCarver sc = new SeamCarver(inputImg);
    int [] iarr = sc.findVerticalSeam();
    assertEquals("returned array has <height> size", sc.height(), iarr.length);
  }  
  
  @Test
  public void testFindHorizontalSeam1() {
    Picture inputImg = new Picture("seamCarving/6x5.png");
    SeamCarver sc = new SeamCarver(inputImg);
    int [] iarr = sc.findHorizontalSeam();
    assertEquals("returned array has <height> size", sc.width(), iarr.length);
  }  
  
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveVerticalException() {
    Picture inputImg = new Picture("seamCarving/HJocean.png");
    SeamCarver sc = new SeamCarver(inputImg);
    int iarr [] = new int [200];
    sc.removeVerticalSeam(iarr);
  }
  
  @Test
  public void testRemoveVerticalDecWidth() {
    Picture inputImg = new Picture("seamCarving/HJocean.png");
    SeamCarver sc = new SeamCarver(inputImg);
    //long mem = MemoryUtil.memoryUsageOf(sc);
    //System.out.println("Initial memory usage of SeamCarver: " + mem);
    int iarr [] = new int [inputImg.height()];
    for (int i = 0; i < iarr.length; i++)
      iarr[i] = 1;
    sc.removeVerticalSeam(iarr);
    //mem = MemoryUtil.deepMemoryUsageOf(sc);
    //System.out.println("Final memory usage of SeamCarver: " + mem);
    assertEquals("removevercial decrease width by one", sc.width(), inputImg.width()-1);

  }
}
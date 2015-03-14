import java.awt.Color;


public class BouncingBalls
{
  public static void main(String[] args)
  {
    int N = Integer.parseInt(args[0]);
    
    BallMjc [] balls = new BallMjc[N];
    balls[0] = new BallMjc(0.5, 0.5, 0.0, 0.0, 0.03, 1.0, Color.BLACK);
    for (int i = 1; i < N; i++)
      balls[i] = new Ball();
    while (true)
    {
      StdDraw.clear();
      StdDraw.setPenRadius(0.005);
      StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
      
      for (int i = 0; i < N; i++)
      {
        balls[i].move(0.01);
        balls[i].draw();
      }
      StdDraw.show(50);
    }
  }
}
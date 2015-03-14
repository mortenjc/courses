
import java.awt.Color;


class BallDistributions 
{   
    public static BallMjc leftHalf()
    {
        Double x,y,vx,vy,r,m;
        x = Math.random()/3.0;
        y = Math.random();
        vx = 0.01*(Math.random() -0.5);
        vy = 0.01*(Math.random() -0.5);
        r = 0.005;
        m = 0.5;
        return new BallMjc(x,y,vx,vy,r,m, Color.BLACK);
    }
    
    public static BallMjc[] upperLower(int N)
    {
        BallMjc [] balls = new BallMjc[N];
            for (int i = 0; i < N; i++) 
            {
                Double x,y;
                Double vx, vy;
                Double mass, r;
                Color color;
                x = Math.random();
                if (i > N/2) {
                  y = Math.random()/2;
                  color = Color.RED;
                }
                else {
                  y = Math.random()/2 +0.5;
                  color = Color.BLUE;
                }
                r = 0.01;
                mass = 1.0;
                vx = 0.01 * (Math.random() -0.5);
                vy = 0.01 * (Math.random() -0.5);
                balls[i] = new BallMjc(x,y,vx,vy, r,mass, color);
                //StdOut.println("x,y: " + x + "  "+ y); 
            }
            return balls;
    }
     
    public static BallMjc[] sameVelocity(int N)
    {
        Double vel = 0.01;
        BallMjc [] balls = new BallMjc[N];
            for (int i = 0; i < N; i++) 
            {
                Double x,y;
                Double vx, vy;
                Double mass, r;
                Color color;
                x = Math.random();
                y = Math.random();
                color = Color.BLACK;

                r = 0.01;
                mass = 1.0;
                vx = vel * Math.cos((Math.random()*2*Math.PI));
                vy = vel * Math.sin((Math.random()*2*Math.PI));
                balls[i] = new BallMjc(x,y,vx,vy, r, mass, color);
                //StdOut.println("x,y: " + x + "  "+ y); 
            }
            return balls;
    }
    
    public static BallMjc[] brownianMotion(int N)
    {
        BallMjc [] balls = new BallMjc[N];
        for (int i = 0; i < N; i++) 
          balls [i] = new BallMjc();
        balls[0] = new BallMjc(0.5, 0.5, 0.0, 0.0, 0.03, 3.0, Color.BLUE);
        balls[1] = new BallMjc(0.2, 0.2, 0.0, 0.0, 0.03, 3.0, Color.RED);
        return balls;
    }
     
    public static BallMjc[] newtonCradle()
    {
        Double r = 0.03, mass = 3.0;
        
        BallMjc [] balls = new BallMjc[5];
        balls[0] = new BallMjc(0.03,   0.5, 0.01, 0.0, r, mass, Color.BLACK);
        balls[1] = new BallMjc(0.40,   0.5, 0.0,  0.0, r, mass, Color.BLACK);
        balls[2] = new BallMjc(0.4601, 0.5, 0.0,  0.0, r, mass, Color.BLACK);
        balls[3] = new BallMjc(0.5202, 0.5, 0.0,  0.0, r, mass, Color.BLACK);
        balls[4] = new BallMjc(0.5803, 0.5, 0.0,  0.0, r, mass, Color.BLACK);
        return balls;
    }
    
    public static BallMjc[] diffusion(int N)
    {
        Double r = 0.02;
              
        BallMjc [] balls = new BallMjc[N];
  
        for (int i = 0; i < N; i++) 
           balls [i] = leftHalf();
        for (int i = 0; i < 22; i++)
            balls[i] = new BallMjc(0.5, 2.0001*(i+1)*r -r, 0.0, 0.0, r, 1000000.0, Color.BLACK);

        return balls;
    }
    
}

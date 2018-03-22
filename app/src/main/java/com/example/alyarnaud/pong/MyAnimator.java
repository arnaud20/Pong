package com.example.alyarnaud.pong;

/**
 * Created by alyarnaud on 3/20/2018.
 */

        import android.graphics.*;
        import android.view.MotionEvent;
        import java.util.ArrayList;
        import java.util.Random;


/**
 * class that animates a ball bouncing in a 3 walled rectangle
 * simple background
 * balls appear when the user clicks the screen
 *
 * @author Alyssa Arnaud
 */
public class MyAnimator implements Animator {

    // instance variables
    private int BOX_WIDTH = 2560; //width of tablet
    private int BOX_HEIGHT = 1600; // height of tablet

    private Random r = new Random();
    /** External Citation
    * Date: 3/21/2018
    * Problem: unsure of how to use an ArrayList
    * Resource: http://www.dummies.com/programming/java/use-array-lists-in-java/
    * Solution: I followed the tutorial to set up my arraylist
    */

    ArrayList<Ball> theBalls = new ArrayList<Ball>();


    /**
     * Interval between animation frames: .03 seconds (i.e., about 33 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 30;
    }

    /**
     * The background color: a light blue.
     *
     * @return the background color onto which we will draw the image.
     */
    public int backgroundColor() {
        // create/return the background color
        return Color.rgb(180, 200, 255);
    }

    /**
     * Tells the animation whether to go backwards.
     *
     *
     */

    /** External Citation
     * Date: 3/19/2018
     * Problem: unsure of how to get the balls to bounce
     * Resource: https://www.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html
     * Solution: I used the example code to figure out the x,y wall relationships
     */
    /** External Citation
     * Date: 3/21/2018
     * Problem: i had some wonkyness with the balls appearing all over
     * Resource: Vegdahl
     * Solution: changed a whole bunch of stuff and stopped using count to determine the speed
     */


    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {
        //Will iterate over the arrayList of Balls and animate all of them
        for (Ball b :theBalls) {
            //moves the ball according to its assigned speed
            b.numX = b.numX + b.xSpeed;
            b.numY = b.numY + b.ySpeed;
            //check if the ball hits the top or bottom of the screen
            if (b.numY + 60 > BOX_HEIGHT) b.reverseYSpeed();
            if (b.numY - 60 < 0) b.reverseYSpeed();
            //check of ball hits right side of screen
            if (b.numX + 60 > BOX_WIDTH) b.reverseXSpeed();
            //if the ball hits the bottom right corner
            if ((b.numY - 60 < 0) && (b.numX + 60 > BOX_WIDTH)) {
                b.reverseYSpeed();
                b.reverseXSpeed();
            }
            //if the ball hits the top right corner
            if ((b.numY + 60 > BOX_HEIGHT) && (b.numX + 60 > BOX_WIDTH)) {
                b.reverseYSpeed();
                b.reverseXSpeed();
            }
            //if the ball hits the paddle
            if ((b.numX - 60 < 0) && (b.numY <= 1000) && (b.numY >= 600)) b.reverseXSpeed();

            //if the ball hits the left side but not the paddle respawn in a
            // random location and with random speed
            if ((b.numX - 60 < 0) && ((b.numY > 1000) || (b.numY < 600))) {
                //random location
                b.numX = r.nextInt(2400) + 100;
                b.numY = r.nextInt(1400) + 100;
                //random speed
                //makes a random speed for the ball in (-60,-15)U(15,60)
                int n = r.nextInt(91) - 45;
                if (n < 0) {
                    n = n - 15;
                } else {
                    n = n + 15;
                }
                b.ySpeed = n;
                n = r.nextInt(91) - 45;
                if (n < 0) {
                    n = n - 15;
                } else {
                    n = n + 15;
                }
                b.xSpeed = n;

            }

            // Draw the ball in the correct position.
            Paint redPaint = new Paint();
            redPaint.setColor(Color.RED);
            g.drawCircle(b.numX, b.numY, 60, redPaint);
            redPaint.setColor(0xff0000ff);
        }

        //Draw a paddle
        // Will eventually be animated
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        g.drawRect(0, 600, 10, 1000, blackPaint);

    }

    /**
     * Tells that we never pause.
     *
     * @return indication of whether to pause
     */
    public boolean doPause() {
        return false;
    }

    /**
     * Tells that we never stop the animation.
     *
     * @return indication of whether to quit.
     */
    public boolean doQuit() {
        return false;
    }

    /**
     * make a new ball when the screen is touched
     * @param event what the user does to the screen
     */
    public void onTouch(MotionEvent event)
    {
        {
            //coordinates of the finger touch
         int initialX = (int) event.getX();
         int initialY = (int) event.getY();
            //make a new Ball at the place touched
         Ball ball = new Ball(initialX, initialY);
            //add the ball to the array list
         theBalls.add(ball);
        }
    }



}//class TextAnimator


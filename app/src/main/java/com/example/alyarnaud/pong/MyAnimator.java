package com.example.alyarnaud.pong;

/**
 * Created by alyarnaud on 3/20/2018.
 */

        import android.graphics.*;
        import android.view.MotionEvent;
        import java.util.Random;


/**
 * Pong game!
 * class that animates a ball bouncing
 * simple background
 * two paddles on edges the ball will bounce off
 *
 * @author Alyssa Arnaud
 */
public class MyAnimator implements Animator {

    // instance variables
    private int BOX_WIDTH = 2560; //width of tablet
    private int BOX_HEIGHT = 1600; // height of tablet
    private Ball b = new Ball(15, 15); //my ball
    private Random r = new Random();
    private int touchedY = 600; //controlls the paddle location
    //dictate computer paddle movement
    private int compY = 600;
    private int compSpeed = 15;


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


    /** External Citation
     * Date: 3/19/2018
     * Problem: unsure of how to get the balls to bounce
     * Resource: https://www.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html
     * Solution: I used the example code to figure out the x,y wall relationships
     */
    /** External Citation
     * Date: 3/21/2018
     * Problem: i had some wonkiness with the balls appearing all over
     * Resource: Vegdahl
     * Solution: changed a whole bunch of stuff and stopped using count to determine the speed
     */


    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {
        //moves the ball according to its assigned speed
        b.numX = b.numX + b.xSpeed;
        b.numY = b.numY + b.ySpeed;
        //moves the computer paddle
        this.compY = this.compY + this.compSpeed;
        if ((compY >= BOX_HEIGHT - 200) || compY <= 200) {
            this.reverseCompSpeed();
        }
        //check if the ball hits the top or bottom of the screen
        if (hitSides()) b.reverseYSpeed();
        //check if the ball hits the user or computer paddles
        if (hitMyPaddle()) {
            b.reverseXSpeed();
        }
        if (hitCompPaddle()) {
            b.reverseXSpeed();
        }
        // Draw the ball in the correct position.
        this.drawBall(b.numX, b.numY, g);
        //Draw the paddles
        this.drawMyPaddle(this.touchedY, g);
        this.drawCompPaddle(this.compY, g);
    }

    //The following are helper methods for the animator
    //check if the ball hits the sides of the screen and bounce if they do
    private boolean hitSides() {
        if (b.numY + 60 >= BOX_HEIGHT || (b.numY - 60 <= 0)) {
            return true;
        } else {
            return false;
        }
    }

    //check if the ball hits either of the paddles
    private boolean hitMyPaddle() {
        if (b.numX - 60 <= 0) {
            if ((b.numY <= (this.touchedY + 260)) && (b.numY >= (this.touchedY - 260))) {
            } else {
                this.wentOffWall();
            }
            return true;
        } else {
            return false;
        }
    }
    private boolean hitCompPaddle() {
        if (b.numX + 60 >= BOX_WIDTH) {
            if ((b.numY <= (this.compY + 260)) && (b.numY >= (this.compY - 260))) {
            } else {
                this.wentOffWall();
            }
            return true;
        } else {
            return false;
        }
    }

    //check if it hits the walls but not the paddles
    //if it does respawn the ball with a random location and speed
    private void wentOffWall() {
        //random location
        b.numX = r.nextInt(2400) + 100;
        b.numY = r.nextInt(1400) + 100;
        //random speed
        b.xSpeed = this.newSpeed();
        b.ySpeed = this.newSpeed();
    }

    //creates the random speed to be used above
    private int newSpeed() {
        //makes a random speed for the ball in (-50,-15)U(15,50)
        int n = r.nextInt(71) - 35;
        if (n < 0) {
            n = n - 15;
        } else {
            n = n + 15;
        }
        return n;
    }
    private void drawBall(int xCoor, int yCoor, Canvas g) {

        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        g.drawCircle(xCoor, yCoor, 60, redPaint);
        redPaint.setColor(0xff0000ff);
    }

    private void drawMyPaddle(int yCoor, Canvas g) {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        g.drawRect(0, yCoor - 200, 10, yCoor + 200, blackPaint);
    }

    private void drawCompPaddle(int compY, Canvas g) {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        g.drawRect(BOX_WIDTH, compY - 200, BOX_WIDTH - 10, compY + 200, blackPaint);
    }

    //makes the computer bounce when it hits the sides of the screen
    private void reverseCompSpeed() {
        this.compSpeed = -this.compSpeed;
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
     * @param event what the user does to the screen
     */
    public void onTouch(MotionEvent event) {
        {
            //coordinates of the finger touch
            this.touchedY = (int) event.getY();

        }
    }
}

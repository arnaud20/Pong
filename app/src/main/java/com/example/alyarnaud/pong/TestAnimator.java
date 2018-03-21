package com.example.alyarnaud.pong;

/**
 * Created by alyarnaud on 3/20/2018.
 */

        import android.graphics.*;
        import android.util.Log;
        import android.view.MotionEvent;

        import java.util.Random;


/**
 * class that animates a ball repeatedly moving diagonally on
 * simple background
 *
 * @author Steve Vegdahl
 * @author Andrew Nuxoll
 * @version February 2016
 */
public class TestAnimator implements Animator {

    // instance variables
    //private int xcount = 0; // counts the number of logical clock ticks
    //private int ycount = 0; // counts the number of logical clock ticks

    private int numX;
    private int numY;

    private boolean reverseX = false; // whether clock is ticking backwards
    private boolean reverseY = false; // whether clock is ticking backwards

    private int BOX_WIDTH = 2560; //width of tablet
    private int BOX_HEIGHT = 1600; // height of tablet

    private Random randomX = new Random();
    private Random randomY = new Random();

    private int xSpeed=15;
    private int ySpeed=15;



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
    public void reverseXSpeed() {
        // set our instance variable
        xSpeed = -xSpeed;
    }
    public void reverseYSpeed(){
        ySpeed = -ySpeed;

    }

    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    /**
     * I was unsure of how to get the balls to bounce off of the
     * walls so i looked on stack overflow for inspiration
     * source: https://www.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html
     * date: 3/20/18
     * */
    public void tick(Canvas g) {
        // bump our count either up or down by one, depending on whether
        // we are in "backwards mode".
//        if (reverseX) {
//            xcount--;
//        }
//        else {
//            xcount++;
//        }
//        if (reverseY) {
//            ycount--;
//        }
//        else {
//            ycount++;
//        }


        // Determine the pixel position of our ball.  Multiplying by 15
        // has the effect of moving 15 pixel per frame.  Modding by 600
        // (with the appropriate correction if the value was negative)
        // has the effect of "wrapping around" when we get to either end
        // (since our canvas size is 600 in each dimension).
        numX = numX+xSpeed;
        numY = numY+ySpeed;
        //check if the ball hits the top or bottom of the screen
        if (numY+60 > BOX_HEIGHT) this.reverseYSpeed();
        if(numY-60 < 0) this.reverseYSpeed();
        //check of ball hits right side of screen
        if (numX+60 > BOX_WIDTH) this.reverseXSpeed();
        //if the ball hits the bottom right corner
        if ((numY-60 < 0)&&(numX+60 > BOX_WIDTH)){
            this.reverseYSpeed() ;
            this.reverseXSpeed();
        }

        //if the ball hits the top right corner
        if ((numY+60 > BOX_HEIGHT)&&(numX+60 > BOX_WIDTH)){
            this.reverseYSpeed() ;
            this.reverseXSpeed();
        }
        //if the ball hits the paddle
        if((numX-60 < 0)&&(numY<=1000)&&(numY>=600)) this.reverseXSpeed();

        //if the ball hits the left side but not the paddle
        if ((numX-60 < 0)&&((numY>1000)||(numY<600))){
            //Log.i("not paddle", numX+","+numY);
            this.numX = randomX.nextInt(2400)+100;
            this.numY = randomX.nextInt(1400)+100;
            //random speed
            this.xSpeed=randomX.nextInt(100)-50;
            int n= randomX.nextInt(91)-45;
            if (n<0){
                n=n-15;
            }
            else{
                n=n+15;
            }
            this.ySpeed=n;
            n= randomX.nextInt(91)-45;
            if (n<0){
                n=n-15;
            }
            else{
                n=n+15;
            }
            this.xSpeed=n;

        }

//ball.xSpeed when you make the array list
//speed and num instance variables will go to the ball object


        // Draw the ball in the correct position.
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        g.drawCircle(numX, numY, 60, redPaint);
        redPaint.setColor(0xff0000ff);


        //Draw a paddle
        // Will eventually be animated
        Paint blackPaint = new Paint();
        redPaint.setColor(Color.BLACK);
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
     * reverse the ball's direction when the screen is tapped
     */
    public void onTouch(MotionEvent event)
    {
        /**if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            use an arraylist of ball
         loop through all the balls and do the same thing
         create a ball object (class) with 4 intance variables speeds and positions

        }
         */
    }



}//class TextAnimator


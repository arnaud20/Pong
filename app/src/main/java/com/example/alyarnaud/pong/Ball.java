package com.example.alyarnaud.pong;


/**
 * Ball object to use in the arraylist
 * @author Alyssa Arnaud
 */
public class Ball {

    public int numX; //x position of the ball
    public int numY;//y position of the ball

    public int xSpeed=15;
    public int ySpeed=15;



    public Ball(int xClick, int yClick ){
        this.numX = xClick;
        this.numY = yClick;
    }
    //switch ball directions
    public void reverseXSpeed() {
        xSpeed = -xSpeed;
    }
    public void reverseYSpeed(){
        ySpeed = -ySpeed;

    }
}

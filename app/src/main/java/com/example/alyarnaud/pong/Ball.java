package com.example.alyarnaud.pong;

import java.util.Random;

/**
 * Created by alyarnaud on 3/21/2018.
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
    public void reverseXSpeed() {
        // set our instance variable
        xSpeed = -xSpeed;
    }
    public void reverseYSpeed(){
        ySpeed = -ySpeed;

    }
}

/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private GRect myPaddle;
	private GOval myBall;
	private RandomGenerator rgen = new RandomGenerator();
	private double xVel, yVel;
	private int turn = 0;
	private int brickCounter = 0;
/* Method: run() */
/** Runs the Breakout program. */
	
	
	public void run() {
		
		//GCanvas myCanvas = new GCanvas();

		
		setBricks();
		initPaddle();
		putBall();
		startGame();

	}
	
	public void setBricks(){
		
		setSize(WIDTH, HEIGHT);
		
		double xCenter = getWidth()/2;
		double rowStart;
		double colStart;
		
		double rowPosition = BRICK_WIDTH + BRICK_SEP;
		double colPosition = BRICK_HEIGHT + BRICK_SEP;
		
		for (int i=0; i<NBRICKS_PER_ROW; i++) {
			
			for (int j=1; j<NBRICK_ROWS+1; j++) {
				
			rowStart = i * (rowPosition);
		    colStart = BRICK_Y_OFFSET +(j * colPosition);
		    
		GRect myBricks = new GRect (rowStart, colStart, BRICK_WIDTH, BRICK_HEIGHT);
		add(myBricks);
		myBricks.setFilled(true);
		
		if (j == 1 || j==2) {
			myBricks.setColor(Color.RED);
		} 
		if (j == 3 || j ==4){
			myBricks.setColor(Color.ORANGE);
		}
		if (j == 5 || j == 6){
			myBricks.setColor(Color.YELLOW);		
		}
		if (j == 7 || j == 8){
			myBricks.setColor(Color.GREEN);		
		}
		if (j == 9 || j == 10){
			myBricks.setColor(Color.CYAN);		
			}
			
		}
	}
}
	
	public void initPaddle(){
				
		double paddleX = (getWidth()/2) - (PADDLE_WIDTH/2); 
		double paddleY = getHeight() - PADDLE_Y_OFFSET;
		
		myPaddle = new GRect (paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		myPaddle.setFilled(true);
		myPaddle.setColor(Color.BLACK);
		add(myPaddle);
		addMouseListeners();	

	}
	
	public void mouseMoved(MouseEvent e) {
	double paddleY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
	
	if((e.getX() >= 0) && (e.getX() < getWidth() - PADDLE_WIDTH)) {
		myPaddle.setLocation((e.getX()), paddleY);
	}
}

	private void putBall() {
		
		myBall = new GOval (getWidth()/2, getHeight()/2, BALL_RADIUS, BALL_RADIUS);
		myBall.setFilled(true);
		add(myBall);
		addMouseListeners();
	}

	
	private void startGame() {
		while (turn < NTURNS){
		waitForClick();
		getBallVel();
        while (brickCounter < (NBRICK_ROWS * NBRICKS_PER_ROW)){
        	moveBall();
            checkForCollision();
            pause(50);
            if (myBall.getY() == getHeight() - BALL_RADIUS*2){
                remove(myBall);
                turn ++;
                break;		
                }
        	}
		}
	}
	
	private void getBallVel() {
		xVel = rgen.nextDouble(1.0, 20.0);
		yVel = 7.0;
		if  (rgen.nextBoolean(0.5)){
			xVel = -xVel;
		}
	}
	

    		 
	private void moveBall() {
		myBall.move(xVel, yVel);
		if (myBall.getY() <= 0) yVel *= -1;
		if((myBall.getX() + BALL_RADIUS * 2) >= getWidth() || myBall.getX() <= 0) xVel *= -1;
	}
	
    private void checkForCollision(){
        GObject collider = getCollidingObject();
        if (collider == myPaddle){
            yVel*=-1;
        }
        else if (collider != null){
            yVel*=-1;
            remove(collider);
            brickCounter++;
        }
    }
    // captures the object with which the ball has just collide
    private GObject getCollidingObject(){
        if (getElementAt(myBall.getX(), myBall.getY()) != null)  return getElementAt(myBall.getX(), myBall.getY());
        else if (getElementAt(myBall.getX() + 2*BALL_RADIUS, myBall.getY()) != null) return getElementAt(myBall.getX() + 2*BALL_RADIUS, myBall.getY()); 
        else if (getElementAt(myBall.getX(), myBall.getY() + 2*BALL_RADIUS) != null) return getElementAt(myBall.getX(), myBall.getY() + 2*BALL_RADIUS);
        else if (getElementAt(myBall.getX() + 2*BALL_RADIUS, myBall.getY() + 2*BALL_RADIUS) != null) return getElementAt(myBall.getX() + 2*BALL_RADIUS, myBall.getY() + 2*BALL_RADIUS);
        else return null;
    }
 	

}
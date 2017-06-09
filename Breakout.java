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

/* Method: run() */
/** Runs the Breakout program. */
	
	
	public void run() {
		
		//GCanvas myCanvas = new GCanvas();
		setSize(WIDTH, HEIGHT);
		
		setBricks();
		initPaddle();

		
	}
	private double xCenter = WIDTH/2;
	private double yCenter = HEIGHT/2;

	public void setBricks(){
			
		println(xCenter + "  " + yCenter + BRICK_WIDTH);
		
		double rowStart = xCenter - (BRICK_WIDTH * NBRICKS_PER_ROW)/2;
		double colStart = BRICK_Y_OFFSET;
		
		double rowPosition = BRICK_WIDTH;
		double colPosition = BRICK_HEIGHT + BRICK_SEP;
		
		for (int i=1; i<NBRICKS_PER_ROW+1; i++) {
			
			for (int j=1; j<NBRICK_ROWS+1; j++) {
				
			rowStart = i * (rowPosition);
		    colStart = j * (colPosition);
		    
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
		
		
		double paddleX = (WIDTH/2) - (BRICK_WIDTH/2) ; 
		double paddleY = (HEIGHT - PADDLE_HEIGHT/2)  - PADDLE_Y_OFFSET;
		
		GRect myPaddle = new GRect (paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		myPaddle.setFilled(true);
		myPaddle.setColor(Color.BLACK);
		add(myPaddle);
	}
}


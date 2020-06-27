//filename: "ExplosionTask.java"

/**
Student Name: Owen Burnett
Student ID Number: *20281934
Object Oriented Programming
Course number: CMSC 3103
Course ID: CRN 20133, Spring 2011
Due: May 4, 2011
Assignment: Term Project
 */

/* Portions of the code in this project came from a program file "SpaceInvader.java" written by
 * Dr. Hong Sung, professor of Computer Science at the University of Central Oklahoma. */

import java.awt.Graphics2D;

public class ExplosionTask extends java.util.TimerTask {
    public ExplosionTask(double x, double y, Graphics2D g2){
        this.x = x;
        this.y = y;
        this.g2 = g2;
    }
    
    public void run() {
        g2.drawImage(MissileExchange.explosionAnimation.get(explosionPicture), (int)x, (int)y, null);
		explosionPicture++;
		
		if(explosionPicture >= 5) {
	            this.cancel();
		}    //end if
    }   //end method run
    
    private int explosionPicture = 0;
    private double x;
    private double y;
    private Graphics2D g2;
}   //end class ExplosionTask
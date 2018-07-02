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
 * Dr. Hong Sung, professor of Computer Science at the University of Central Oklahoma.
 * Dr. Sung made the "SpaceInvader.java" file available to me.  There are sections of code
 * that were directly copied from the file "SpaceInvader.java" that Dr. Sung wrote.  I have made
 * changes to some of the sections of copied code and I added code of
 * my own.  I give credit to Dr. Sung for writing "SpaceInvader.java", a program file containing code
 * which I have used to make this game, "Missile Exchange".  I give credit to Dr. Sung for
 * teaching me object-oriented programming design concepts that I have used to make this game.
 * It would not have been possible for me to make this program if he had not taught me those concepts.
 */

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
	
	if(explosionPicture >= 5)
            this.cancel();
        //end if
    }   //end method run
    private int explosionPicture = 0;
    private double x;
    private double y;
    private Graphics2D g2;
}   //end class ExplosionTask
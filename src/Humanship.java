import java.awt.Image;

//filename: "Humanship.java"

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

public class Humanship extends Spaceship implements Destroyable{
    public Humanship(int x, int y, int width, int height, boolean travelingToLeft, int shipSpeed, Image itemImage) {
	super(x, y, width, height, false);
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.travelingToLeft = travelingToLeft;
	this.itemImage = itemImage;
	this.shipSpeed = shipSpeed;
    }   //end constructor

    public synchronized void update() {
	if(travelingToLeft == false){
	    if(x + width > RIGHT_BOUND) return;
	    setFrame(x + shipSpeed, getY(), width, height);
	} else{
	    if(x <= LEFT_BOUND) return;
	    setFrame(x - shipSpeed, getY(), width, height);
	}   //end if
    }   //end method update

    public void getDestroyed() {
	MissileExchange.gameItems.remove(this);
	MissileExchange.humanLives--;
	MissileExchange.regenerateHumanship = true;
    }   //end method getDestroyed
    private final int LEFT_BOUND = 0;
    private final int RIGHT_BOUND = 500;
    private double shipSpeed = 0.0;
}   //end class Humanship
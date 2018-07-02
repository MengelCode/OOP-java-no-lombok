
import java.awt.Image;

//filename: "UFOSpaceship1.java"

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

public class UFOSpaceship1 extends UFOSpaceship implements Destroyable {
    public UFOSpaceship1(int x, int y, int w, int h, boolean travelingToLeft, Image itemImage, double missileSpeed, Image missileImage) {
	super(x, y, w, h, travelingToLeft, missileSpeed, missileImage);
	width = w;
	this.travelingToLeft = travelingToLeft;
	this.itemImage = itemImage;
	this.speed = UFO_SPACESHIP1_SPEED_MULTIPLICAND * ufoSpeedMultiplier;
    }   //end constructor

    public void update() {
	if(travelingToLeft == false)
	    setFrame(getX() + speed, getY(), getWidth(), getHeight());
	else
	    setFrame(getX() - speed, getY(), getWidth(), getHeight());
    }   //end method update
    public static final double UFO_SPACESHIP1_SPEED_MULTIPLICAND = 1;
}   //end class UFOSpaceship1
//filename: "UFOSpaceship2.java"

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

import java.awt.Image;

public class UFOSpaceship2 extends UFOSpaceship implements Destroyable {
    public UFOSpaceship2(int x, int y, int w, int h, boolean travelingToLeft, Image itemImage, double missileSpeed, Image missileImage) {
		super(x, y, w, h, travelingToLeft, missileSpeed, missileImage);
		width = w;
		this.setTravelingToLeft(travelingToLeft);
		this.setItemImage(itemImage);
		this.setSpeed(UFO_SPACESHIP2_SPEED_MULTIPLICAND * getUfoSpeedMultiplier());
		this.setHealthPoints(getHealthPoints() + 1);
    }   //end constructor

    public void update() {
		if(isTravelingToLeft() == false)
		    setFrame(getX() + getSpeed(), getY(), getWidth(), getHeight());
		else
		    setFrame(getX() - getSpeed(), getY(), getWidth(), getHeight());
    }   //end method update
    
    public static final double UFO_SPACESHIP2_SPEED_MULTIPLICAND = 1.4;
}   //end class UFOSpaceship2
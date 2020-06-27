//filename: "UFOSpaceship3.java"

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

public class UFOSpaceship3 extends UFOSpaceship implements Destroyable {
    public UFOSpaceship3(int x, int y, int w, int h, boolean travelingToLeft, Image itemImage, double missileSpeed, Image missileImage) {
		super(x, y, w, h, travelingToLeft, missileSpeed, missileImage);
		width = w;
		this.travelingToLeft = travelingToLeft;
		this.itemImage = itemImage;
		this.speed = UFO_SPACESHIP3_SPEED_MULTIPLICAND * ufoSpeedMultiplier;
		this.healthPoints += 2;
    }   //end constructor

    public void update() {
		if(travelingToLeft == false)
		    setFrame(getX() + speed, getY(), getWidth(), getHeight());
		else
		    setFrame(getX() - speed, getY(), getWidth(), getHeight());
	}   //end method update
    
    public static final double UFO_SPACESHIP3_SPEED_MULTIPLICAND = 1.8;
}   //end class UFOSpaceship3
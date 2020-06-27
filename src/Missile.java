
import java.awt.Image;

//filename: "Missile.java"

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

 public class Missile extends GameWeapon {
    public Missile(int x, int y, int w, int h,boolean travelingToLeft, boolean humanMissile, double missileSpeed, Image itemImage) {
		super(x, y, w, h, travelingToLeft, missileSpeed);
		this.humanMissile = humanMissile;
		this.itemImage = itemImage;
		this.missileSpeed = missileSpeed;
    }	//end constructor

    public boolean isHumanMissile() {
    	return humanMissile;
    }	//end method setMissileSpeed
   
    private boolean humanMissile;
}	//end class Missile
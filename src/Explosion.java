//filename: "Explosion.java"

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

import java.awt.*;

public class Explosion extends GameItem {
	private int animationPictureIndex;
	
	public Explosion(int x, int y, int w, int h, boolean travelingToLeft){
	    super(x, y, w, h, travelingToLeft, 0.0);
	    animationPictureIndex = 0;
	    this.setItemImage(MissileExchange.getFireball2Picture());
	}   //end default constructor

	public synchronized void update(){
	    setItemImage(MissileExchange.getExplosionImage(animationPictureIndex));
	    animationPictureIndex++;
	}   //end method update
}	//end class Explosion
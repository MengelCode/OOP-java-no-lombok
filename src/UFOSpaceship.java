
import java.awt.Image;

//filename: "UFOSpaceship.java"

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

public abstract class UFOSpaceship extends Spaceship {
    public UFOSpaceship(int x, int y, int w, int h, boolean travelingToLeft, double missileSpeed, Image missileImage) {
	super(x, y, w, h, travelingToLeft);
	this.missileImage = missileImage;
	this.missileSpeed = missileSpeed;
    }   //end constructor

    public abstract void update();

    public void getDestroyed() {
	MissileExchange.gameItems.remove(this);
	d = (Destroyable)(this);
	MissileExchange.ufos.remove(d);
	MissileExchange.numberOfUFOSOnScreen--;
	MissileExchange.ufosDestroyedThisLevel++;
    }   //end method getDestroyed
    
    public synchronized void fireMissile() {
	m = new Missile((int)(this.getX() + this.getWidth() / 2), (int)(getY() + height + 10), 5, 10, false, false, missileSpeed, missileImage);
	MissileExchange.gameItems.add(m);
    }   //end method fireMissile
    public double ufoSpaceshipSpeedMultiplicand = 0.0;
    public static double ufoSpeedMultiplier = 0;
    private Missile m;
    private Destroyable d;
    private Image missileImage;
    private double missileSpeed;
}	//end class UFOSpaceship
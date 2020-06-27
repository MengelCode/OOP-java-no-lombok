
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
 * Dr. Hong Sung, professor of Computer Science at the University of Central Oklahoma. */

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
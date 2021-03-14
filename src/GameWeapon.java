//filename: "GameWeapon.java"

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

public abstract class GameWeapon extends GameItem {
    private boolean exploded = false;
    private double weaponSpeed = 0.0;
    private double missileSpeed = 0.0;
    
    public double getWeaponSpeed() {
		return weaponSpeed;
	}

	public void setWeaponSpeed(double weaponSpeed) {
		this.weaponSpeed = weaponSpeed;
	}

	public double getMissileSpeed() {
		return missileSpeed;
	}

	public void setMissileSpeed(double missileSpeed) {
		this.missileSpeed = missileSpeed;
	}
	
    public void setExploded(boolean b) {
    	exploded = b;
    }	//end method setExploded

    public boolean isExploded() {
    	return exploded;
    }	//end method isExploded
	
    public GameWeapon(int x, int y, int w, int h, boolean travelingToLeft, double weaponSpeed) {
		super(x, y, w, h, travelingToLeft, weaponSpeed);
		this.weaponSpeed = weaponSpeed;
    }   //end default constructor
}   //end class GameWeapon
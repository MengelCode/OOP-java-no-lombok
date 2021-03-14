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
 * Dr. Hong Sung, professor of Computer Science at the University of Central Oklahoma. */

public class Humanship extends Spaceship implements Destroyable{
	private final int LEFT_BOUND = 0;
    private final int RIGHT_BOUND = 500;
    private double shipSpeed = 0.0;
	
    public double getShipSpeed() {
		return shipSpeed;
	}

	public void setShipSpeed(double shipSpeed) {
		this.shipSpeed = shipSpeed;
	}
    
	public Humanship(int x, int y, int width, int height, boolean travelingToLeft, int shipSpeed, Image itemImage) {
		super(x, y, width, height, false);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setTravelingToLeft(travelingToLeft);
		this.setItemImage(itemImage);
		this.shipSpeed = shipSpeed;
    }   //end constructor

    public synchronized void update() {
		if(isTravelingToLeft() == false){
		    if(x + width > RIGHT_BOUND) return;
		    setFrame(x + shipSpeed, getY(), width, height);
		} else{
		    if(x <= LEFT_BOUND) return;
		    setFrame(x - shipSpeed, getY(), width, height);
		}   //end if
    }   //end method update

    public void getDestroyed() {
		MissileExchange.getGameItems().remove(this);
		MissileExchange.setHumanLives(MissileExchange.getHumanLives() - 1);
		MissileExchange.setRegenerateHumanship(true);
    }   //end method getDestroyed
}   //end class Humanship
//filename: "GameItem.java"

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

import java.awt.*;
import java.awt.geom.*;

public abstract class GameItem extends Rectangle2D.Double {
    public GameItem(int x, int y, int w, int h, boolean travelingToLeft, double itemSpeed) {
	super(x, y, w, h);
	this.travelingToLeft = travelingToLeft;
	this.itemSpeed = itemSpeed;
    }   //end constructor

    public synchronized boolean isOutOfWindow() {
	x = getX();
	y = getY();
	width = getWidth();
	height = getHeight();

	if(x >= MissileExchange.WINDOW_WIDTH || x + width <= 0 || y + height <= 0
	 || y >= MissileExchange.WINDOW_HEIGHT)
	    return true;
	else
	    return false;
	//end if
    }   //end method isOutOfWindow

    public int getHealthPoints() {
	return healthPoints;
    }   //end method getHealthPoints

    public void removeHealthPoints(int pointsToRemove) {
	healthPoints -= pointsToRemove;
	System.out.println("healthPoint removed");
    }   //end method removeHealthPoints

    public boolean isDestroyed(){
	if(healthPoints <= 0)
            return true;
	else 
	    return false;
	//end if
    }   //end method isDestroyed

    public void translateVertically() {

	if(this instanceof Missile) {
	    m = (Missile)(this);
	    if(m.isHumanMissile() == true) {
		setFrame(getX(), getY() - itemSpeed, getWidth(), getHeight());
	    } else {
		setFrame(getX(), getY() + itemSpeed, getWidth(), getHeight());
	    }   //end if
	}   //end if

	checkForCollision(this);
    }	//end method translate

    public void checkForCollision(GameItem g){
	for (int i = 0; i < MissileExchange.gameItems.size(); i++) {
	    GameItem t = MissileExchange.gameItems.get(i);

	    if(t == null)
		continue;
	    //end if

	    if(g instanceof Missile) {
		m = (Missile)(g);
		if(t.intersects(m) && m.equals(t) == false
			&& t instanceof Missile == false && t instanceof Explosion == false
			&& ((m.isHumanMissile() == true  && (t instanceof UFOSpaceship))
			|| (m.isHumanMissile() == false && (t instanceof Humanship)))) {

		    if(t instanceof UFOSpaceship)
			scheduleExplosion((UFOSpaceship)(t));
		    else if(t instanceof Humanship)
			scheduleExplosion((Humanship)(t));
		    //end if

		    System.out.println("Explosion");
		    m.isExploded();
		    indexOfItem = MissileExchange.gameItems.lastIndexOf(m);
		    MissileExchange.gameItems.remove(m);

		    if(t instanceof UFOSpaceship || t instanceof Humanship){
			t.healthPoints--;

			if(t.isDestroyed()) {
			    if(t instanceof UFOSpaceship) {
				ufo = (UFOSpaceship)(t);
				ufo.getDestroyed();
			    } else {
				MissileExchange.human.getDestroyed();
			    }   //end if
			}	//end if
		    }   //end if
		}	//end if
	    }	//end if

	    if(g instanceof Nuke) {
		n = (Nuke)(g);
		if(t.intersects(n) && n.equals(t) == false
			&& t instanceof Missile == false && t instanceof Explosion == false) {

		    if(t instanceof UFOSpaceship)
			scheduleExplosion((UFOSpaceship)(t));
		    else if(t instanceof Humanship)
			scheduleExplosion((Humanship)(t));
		    //end if

		    System.out.println("Explosion");
		    m.isExploded();
		    indexOfItem = MissileExchange.gameItems.lastIndexOf(m);
		    MissileExchange.gameItems.remove(m);

		    if(t instanceof UFOSpaceship || t instanceof Humanship){
			t.healthPoints = 0;

			if(t.isDestroyed()) {
			    ship = (Spaceship)(t);
			    ship.getDestroyed();
			}	//end if
		    }   //end if
		}	//end if
	    }	//end if
	}    //end for
    }   //end method checkForCollision

    public void scheduleExplosion(Spaceship s){
	explosionsToAdd[explosionsWaiting] = new Explosion((int)(s.x + 10), (int)(s.y - 1), 30, 30, false);
	explosionsAdded++;
	explosionsWaiting++;
    }	//end method scheduleExplosion
    public Image itemImage;
    public boolean travelingToLeft;
    public double speed;
    public int healthPoints = 1;
    private double itemSpeed = 0.0;
    public static int explosionsWaiting = 0;
    private static int explosionsAdded = 0;
    private UFOSpaceship ufo;
    private boolean beforeGameItem = false;
    private int indexOfItem = 0;
    public static GameItem[] explosionsToAdd = new GameItem[100];
    public Missile m;
    public Nuke n;
    public static Spaceship ship;
} //end class GameItem
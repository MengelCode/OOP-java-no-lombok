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
 * Dr. Hong Sung, professor of Computer Science at the University of Central Oklahoma. */

import java.awt.*;
import java.awt.geom.*;

public abstract class GameItem extends Rectangle2D.Double {
    private Image itemImage;
	private boolean travelingToLeft;
    private double speed;
    private int healthPoints = 1;
    private double itemSpeed = 0.0;
    private static int explosionsWaiting = 0;
    private static int explosionsAdded = 0;
    private UFOSpaceship ufo;
    private boolean beforeGameItem = false;
    private int indexOfItem = 0;
    private static GameItem[] explosionsToAdd = new GameItem[100];
    private Missile m;
    private Nuke n;
    private static Spaceship ship;
	
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
	
		if( x >= MissileExchange.getWindowWidth() || x + width <= 0 || y + height <= 0
		 || y >= MissileExchange.getWindowHeight() )
		    return true;
		else
		    return false;
		//end if
    }   //end method isOutOfWindow

    public void removeHealthPoints(int pointsToRemove) {
		healthPoints -= pointsToRemove;
		//System.out.println("healthPoint removed");
    }   //end method removeHealthPoints

    public boolean isDestroyed(){
		if(healthPoints <= 0) return true;
		else return false;
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
    }	//end method translateVertically

    public void checkForCollision(GameItem g){
		for (int i = 0; i < MissileExchange.getGameItems().size(); i++) {
		    GameItem t = MissileExchange.getGameItems().get(i);
	
		    if ( t == null ) continue;
	
		    if( g instanceof Missile ) {
		    	m = (Missile)(g);
			
				if ( t.intersects(m) && m.equals(t) == false
					&& t instanceof Missile == false && t instanceof Explosion == false
					&& ( (m.isHumanMissile() == true  && (t instanceof UFOSpaceship) )
					|| (m.isHumanMissile() == false && (t instanceof Humanship) ) ) ) {
		
				    if ( t instanceof UFOSpaceship ) scheduleExplosion((UFOSpaceship)(t));
				    else if ( t instanceof Humanship ) scheduleExplosion((Humanship)(t));
				    //end if
		
				    //System.out.println("Explosion");
				    m.isExploded();
				    indexOfItem = MissileExchange.getGameItems().lastIndexOf(m);
				    MissileExchange.getGameItems().remove(m);
		
				    if(t instanceof UFOSpaceship || t instanceof Humanship){
				    	t.healthPoints--;
		
						if(t.isDestroyed()) {
						    if(t instanceof UFOSpaceship) {
						    	ufo = (UFOSpaceship)(t);
						    	ufo.getDestroyed();
						    } else {
						    	MissileExchange.getHuman().getDestroyed();
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
				    indexOfItem = MissileExchange.getGameItems().lastIndexOf(m);
				    MissileExchange.getGameItems().remove(m);
		
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
    
    public Image getItemImage() {
		return itemImage;
	}

	public void setItemImage(Image itemImage) {
		this.itemImage = itemImage;
	}

	public boolean isTravelingToLeft() {
		return travelingToLeft;
	}

	public void setTravelingToLeft(boolean travelingToLeft) {
		this.travelingToLeft = travelingToLeft;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getItemSpeed() {
		return itemSpeed;
	}

	public void setItemSpeed(double itemSpeed) {
		this.itemSpeed = itemSpeed;
	}

	public static int getExplosionsWaiting() {
		return explosionsWaiting;
	}

	public static void setExplosionsWaiting(int explosionsWaiting) {
		GameItem.explosionsWaiting = explosionsWaiting;
	}

	public static int getExplosionsAdded() {
		return explosionsAdded;
	}

	public static void setExplosionsAdded(int explosionsAdded) {
		GameItem.explosionsAdded = explosionsAdded;
	}

	public UFOSpaceship getUfo() {
		return ufo;
	}

	public void setUfo(UFOSpaceship ufo) {
		this.ufo = ufo;
	}

	public boolean isBeforeGameItem() {
		return beforeGameItem;
	}

	public void setBeforeGameItem(boolean beforeGameItem) {
		this.beforeGameItem = beforeGameItem;
	}

	public int getIndexOfItem() {
		return indexOfItem;
	}

	public void setIndexOfItem(int indexOfItem) {
		this.indexOfItem = indexOfItem;
	}

	public static GameItem[] getExplosionsToAdd() {
		return explosionsToAdd;
	}

	public static void setExplosionsToAdd(GameItem[] explosionsToAdd) {
		GameItem.explosionsToAdd = explosionsToAdd;
	}
	
	public static GameItem getExplosionToAdd(int index) {
		return explosionsToAdd[index];
	}

	public Missile getM() {
		return m;
	}

	public void setM(Missile m) {
		this.m = m;
	}

	public Nuke getN() {
		return n;
	}

	public void setN(Nuke n) {
		this.n = n;
	}

	public static Spaceship getShip() {
		return ship;
	}

	public static void setShip(Spaceship ship) {
		GameItem.ship = ship;
	}
	
    public int getHealthPoints() {
    	return healthPoints;
    }   //end method getHealthPoints

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
} //end class GameItem
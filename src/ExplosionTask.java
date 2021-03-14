//filename: "ExplosionTask.java"

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

import java.awt.Graphics2D;

public class ExplosionTask extends java.util.TimerTask {
	private int explosionPicture = 0;
    private double x;
    private double y;
    private Graphics2D g2;
	
    public int getExplosionPicture() {
		return explosionPicture;
	}

	public void setExplosionPicture(int explosionPicture) {
		this.explosionPicture = explosionPicture;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Graphics2D getG2() {
		return g2;
	}

	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}
    
	public ExplosionTask(double x, double y, Graphics2D g2){
        this.x = x;
        this.y = y;
        this.g2 = g2;
    }
    
    public void run() {
        g2.drawImage(MissileExchange.getExplosionAnimation().get(explosionPicture), (int)x, (int)y, null);
		explosionPicture++;
		
		if(explosionPicture >= 5) {
	            this.cancel();
		}    //end if
    }   //end method run
}   //end class ExplosionTask
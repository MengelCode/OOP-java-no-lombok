
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
    private double ufoSpaceshipSpeedMultiplicand = 0.0;
	private static double ufoSpeedMultiplier = 0;
    private Missile missile1;
    private Destroyable d;
    private Image missileImage;
    private double missileSpeed;
    
    public double getUfoSpaceshipSpeedMultiplicand() {
		return ufoSpaceshipSpeedMultiplicand;
	}

	public void setUfoSpaceshipSpeedMultiplicand(double ufoSpaceshipSpeedMultiplicand) {
		this.ufoSpaceshipSpeedMultiplicand = ufoSpaceshipSpeedMultiplicand;
	}

	public static double getUfoSpeedMultiplier() {
		return ufoSpeedMultiplier;
	}

	public static void setUfoSpeedMultiplier(double ufoSpeedMultiplier) {
		UFOSpaceship.ufoSpeedMultiplier = ufoSpeedMultiplier;
	}

	public Missile getMissile1() {
		return missile1;
	}

	public void setMissile1(Missile missile1) {
		this.missile1 = missile1;
	}

	public Destroyable getD() {
		return d;
	}

	public void setD(Destroyable d) {
		this.d = d;
	}

	public Image getMissileImage() {
		return missileImage;
	}

	public void setMissileImage(Image missileImage) {
		this.missileImage = missileImage;
	}

	public double getMissileSpeed() {
		return missileSpeed;
	}

	public void setMissileSpeed(double missileSpeed) {
		this.missileSpeed = missileSpeed;
	}
    
	public UFOSpaceship(int x, int y, int w, int h, boolean travelingToLeft, double missileSpeed, Image missileImage) {
		super(x, y, w, h, travelingToLeft);
		this.missileImage = missileImage;
		this.missileSpeed = missileSpeed;
    }   //end constructor

    public abstract void update();

    public void getDestroyed() {
		MissileExchange.getGameItems().remove(this);
		d = (Destroyable)(this);
		MissileExchange.getUfos().remove(d);
		MissileExchange.setNumberOfUFOSOnScreen(MissileExchange.getNumberOfUFOSOnScreen() - 1);
		MissileExchange.setUfosDestroyedThisLevel(MissileExchange.getUfosDestroyedThisLevel() + 1);
    }   //end method getDestroyed
    
    public synchronized void fireMissile() {
		setM(new Missile((int)(this.getX() + this.getWidth() / 2), (int)(getY() + height + 10), 5, 10, false, false, missileSpeed, missileImage));
		MissileExchange.getGameItems().add(getM());
    }   //end method fireMissile
}	//end class UFOSpaceship
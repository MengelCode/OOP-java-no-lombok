//filename: "LevelStarter.java"

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

public class LevelStarter {
    public static void startLevel(){
	if(MissileExchange.numberOfUFOSOnScreen <= 0){
		MissileExchange.switchingLevels = false;

		switch(MissileExchange.currentLevel){
		    case 1:
			System.out.println("before setting speed multiplier");
			UFOSpaceship.ufoSpeedMultiplier = LEVEL1_UFO_SPACESHIP_SPEED_MULTIPLIER;
			System.out.println("***** Level 1 starting, ufoSpaceshipSpeedMultiplier *****");
			MissileExchange.l1 = new Level1();
			MissileExchange.levelTimer.schedule(MissileExchange.l1, 0, 3000);
			break;
		    case 2:
			UFOSpaceship.ufoSpeedMultiplier = LEVEL2_UFO_SPACESHIP_SPEED_MULTIPLIER;
			System.out.println("***** Level 2 starting, ufoSpaceshipSpeedMultiplier *****");
			MissileExchange.l2 = new Level2();
			MissileExchange.levelTimer.schedule(MissileExchange.l2, 0, 3000);
			break;
		    case 3:
			UFOSpaceship.ufoSpeedMultiplier = LEVEL3_UFO_SPACESHIP_SPEED_MULTIPLIER;
			System.out.println("***** Level 3 starting, ufoSpaceshipSpeedMultiplier *****");
			MissileExchange.l3 = new Level3();
			MissileExchange.levelTimer.schedule(MissileExchange.l3, 0, 3000);
			break;
		    case 4:
			UFOSpaceship.ufoSpeedMultiplier = LEVEL4_UFO_SPACESHIP_SPEED_MULTIPLIER;
			System.out.println("***** Level 4 starting, ufoSpaceshipSpeedMultiplier *****");
			MissileExchange.l4 = new Level4();
			MissileExchange.levelTimer.schedule(MissileExchange.l4, 0, 3000);
			break;
		    case 5:
			UFOSpaceship.ufoSpeedMultiplier = LEVEL5_UFO_SPACESHIP_SPEED_MULTIPLIER;
			System.out.println("***** Level 5 starting *****");
			MissileExchange.l5 = new Level5();
			MissileExchange.levelTimer.schedule(MissileExchange.l5, 0, 3000);
			break;
		}   //end switch
	}   //end if
    }   //end method run
    public static final int LEVEL1_UFO_SPACESHIP_SPEED_MULTIPLIER = 8;
    public static final int LEVEL2_UFO_SPACESHIP_SPEED_MULTIPLIER = 9;
    public static final int LEVEL3_UFO_SPACESHIP_SPEED_MULTIPLIER = 10;
    public static final int LEVEL4_UFO_SPACESHIP_SPEED_MULTIPLIER = 11;
    public static final int LEVEL5_UFO_SPACESHIP_SPEED_MULTIPLIER = 12;
}   //end class LevelScheduler
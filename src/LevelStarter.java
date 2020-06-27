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
 * Dr. Hong Sung, professor of Computer Science at the University of Central Oklahoma. */

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
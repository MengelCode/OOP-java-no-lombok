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
    private static final int LEVEL1_UFO_SPACESHIP_SPEED_MULTIPLIER = 8;
    private static final int LEVEL2_UFO_SPACESHIP_SPEED_MULTIPLIER = 9;
    private static final int LEVEL3_UFO_SPACESHIP_SPEED_MULTIPLIER = 10;
    private static final int LEVEL4_UFO_SPACESHIP_SPEED_MULTIPLIER = 11;
    private static final int LEVEL5_UFO_SPACESHIP_SPEED_MULTIPLIER = 12;
    
    public static void startLevel(){
		if ( MissileExchange.getNumberOfUFOSOnScreen() <= 0 ) {
			MissileExchange.setSwitchingLevels(false); //In process of switching levels, no need to switch again
	
			switch ( MissileExchange.getCurrentLevel() ){
			    case 1: //level 1
					//System.out.println("before setting speed multiplier");
					UFOSpaceship.setUfoSpeedMultiplier(LEVEL1_UFO_SPACESHIP_SPEED_MULTIPLIER);
					//System.out.println("***** Level 1 starting, ufoSpaceshipSpeedMultiplier *****");
					MissileExchange.setLevel1(new Level1());
					MissileExchange.getLevelTimer().schedule(MissileExchange.getLevel1(), 0, 3000);
					break;
			    case 2: //level 2
					UFOSpaceship.setUfoSpeedMultiplier(LEVEL2_UFO_SPACESHIP_SPEED_MULTIPLIER);
					//System.out.println("***** Level 2 starting, ufoSpaceshipSpeedMultiplier *****");
					MissileExchange.setLevel2(new Level2());
					MissileExchange.getLevelTimer().schedule(MissileExchange.getLevel2(), 0, 3000);
					break;
			    case 3:
					UFOSpaceship.setUfoSpeedMultiplier(LEVEL3_UFO_SPACESHIP_SPEED_MULTIPLIER);
					//System.out.println("***** Level 3 starting, ufoSpaceshipSpeedMultiplier *****");
					MissileExchange.setLevel3(new Level3());
					MissileExchange.getLevelTimer().schedule(MissileExchange.getLevel3(), 0, 3000);
					break;
			    case 4:
					UFOSpaceship.setUfoSpeedMultiplier(LEVEL4_UFO_SPACESHIP_SPEED_MULTIPLIER);
					//System.out.println("***** Level 4 starting, ufoSpaceshipSpeedMultiplier *****");
					MissileExchange.setLevel4(new Level4());
					MissileExchange.getLevelTimer().schedule(MissileExchange.getLevel4(), 0, 3000);
					break;
			    case 5:
					UFOSpaceship.setUfoSpeedMultiplier(LEVEL5_UFO_SPACESHIP_SPEED_MULTIPLIER);
					//System.out.println("***** Level 5 starting *****");
					MissileExchange.setLevel5(new Level5());
					MissileExchange.getLevelTimer().schedule(MissileExchange.getLevel5(), 0, 3000);
					break;
			}   //end switch
		}   //end if
    }   //end method startLevel
}   //end class LevelStarter
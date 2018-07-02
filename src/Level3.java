//filename: "Level3.java"

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

public class Level3 extends java.util.TimerTask {
   Level3(){
	MissileExchange.ufosDestroyedThisLevel = 0;
	MissileExchange.numberOfUFOSOnScreen = 0;
	MissileExchange.ufosGeneratedThisLevel = 0;
    }   //end default constructor

    public void run(){
	if(MissileExchange.numberOfUFOSOnScreen <= UFO_SCREEN_LIMIT){
	    if(MissileExchange.ufosGeneratedThisLevel + 3 <= LEVEL_THREE_UFOS_TO_DEFEAT) {
		MissileExchange.numberOfUFOSOnScreen += 3;
		MissileExchange.ufosGeneratedThisLevel += 3;
		System.out.println("UFOs generated this level" + MissileExchange.ufosGeneratedThisLevel);
		UFOSpaceship u1 = new UFOSpaceship1(0, 30, 33, 20, false, MissileExchange.UFOSpaceship1Picture, MissileExchange.UFO_MISSILE_SPEED, MissileExchange.missilePicture);
                UFOSpaceship u2 = new UFOSpaceship2(250, 70, 33, 20, true, MissileExchange.UFOSpaceship2Picture, MissileExchange.UFO_MISSILE_SPEED, MissileExchange.missilePicture);
                UFOSpaceship u3 = new UFOSpaceship3(500, 110, 33, 20, true, MissileExchange.UFOSpaceship3Picture, MissileExchange.UFO_MISSILE_SPEED, MissileExchange.missilePicture);
		MissileExchange.gameItems.add(u1);
		MissileExchange.gameItems.add(u2);
		MissileExchange.gameItems.add(u3);
		Destroyable du1 = (Destroyable) (u1);
		Destroyable du2 = (Destroyable) (u2);
		Destroyable du3 = (Destroyable) (u3);
		MissileExchange.ufos.add(du1);
		MissileExchange.ufos.add(du2);
		MissileExchange.ufos.add(du3);
	    } else if(MissileExchange.ufosGeneratedThisLevel + 2 <= LEVEL_THREE_UFOS_TO_DEFEAT) {
		MissileExchange.numberOfUFOSOnScreen += 2;
		MissileExchange.ufosGeneratedThisLevel += 2;
		System.out.println("UFOs generated this level" + MissileExchange.ufosGeneratedThisLevel);
		UFOSpaceship u1 = new UFOSpaceship1(0, 30, 33, 20, false, MissileExchange.UFOSpaceship1Picture, MissileExchange.UFO_MISSILE_SPEED, MissileExchange.missilePicture);
                UFOSpaceship u2 = new UFOSpaceship2(250, 70, 33, 20, true, MissileExchange.UFOSpaceship2Picture, MissileExchange.UFO_MISSILE_SPEED, MissileExchange.missilePicture);
		MissileExchange.gameItems.add(u1);
		MissileExchange.gameItems.add(u2);
		Destroyable du1 = (Destroyable) (u1);
		Destroyable du2 = (Destroyable) (u2);
		MissileExchange.ufos.add(du1);
		MissileExchange.ufos.add(du2);
	    } else if(MissileExchange.ufosGeneratedThisLevel + 1 <= LEVEL_THREE_UFOS_TO_DEFEAT) {
		MissileExchange.numberOfUFOSOnScreen += 1;
		MissileExchange.ufosGeneratedThisLevel += 1;
		System.out.println("UFOs generated this level" + MissileExchange.ufosGeneratedThisLevel);
		UFOSpaceship u1 = new UFOSpaceship1(0, 30, 33, 20, false, MissileExchange.UFOSpaceship1Picture, MissileExchange.UFO_MISSILE_SPEED, MissileExchange.missilePicture);
		MissileExchange.gameItems.add(u1);
		Destroyable du1 = (Destroyable) (u1);
		MissileExchange.ufos.add(du1);
	    }   //end if
	}   //end if

	if(MissileExchange.ufosDestroyedThisLevel >= LEVEL_THREE_UFOS_TO_DEFEAT) {
	    MissileExchange.switchingLevels = true;
	    MissileExchange.currentLevel++;
	    System.out.println("***************level three successfully completed************");
	    cancel();
	}   //end if
    }   //end method run
    public static final int UFO_SCREEN_LIMIT = 6;
    public static final int NUMBER_OF_UFOS_TO_GENERATE = 3;
    public static final int LEVEL_THREE_UFOS_TO_DEFEAT = 15;
}	//end class Level3

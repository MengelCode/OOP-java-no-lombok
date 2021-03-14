//filename: "MissileExchange.java"

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
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JPanel;

public class MissileExchange extends JFrame implements KeyListener {
    private static java.util.ArrayList<GameItem> gameItems;
    private static java.util.ArrayList<Missile> pressedHumanMissiles;
    private static java.util.ArrayList<Missile> typedHumanMissiles;
    private static java.util.ArrayList<Nuke> pressedNukes;
    private static ArrayList<Explosion> explosions;
    private static ArrayList<Image> explosionAnimation;
    private static java.util.ArrayList<Destroyable> ufos; //holds UFOs which are displayed on screen
    private static java.util.Timer gameTimer = new java.util.Timer();
    private static java.util.Timer levelTimer = new java.util.Timer();
    private static java.util.Timer explosionTimer = new java.util.Timer();
    private static Random generator = new Random();
    private LevelStarter levelStarter = new LevelStarter();
    private static boolean nukePressed = false;
    private static boolean switchingLevels = false; //when true the next level will start
    private static boolean victoryFlag = false;
    private static boolean regenerateHumanship = false; //when true human has lost a life and Humanship will be regenerated
    private boolean spacebarStop = true;
    private static Humanship human;
    private static UFOSpaceship ufoSpaceshipObj;
    private static Destroyable selectedUFO;
    private static Missile missileObj;
    private static Nuke nukeObj1;
    private static int numberOfUFOSOnScreen = 0;
    private static int ufosDestroyedThisLevel = 0;
    private static int ufosGeneratedThisLevel = 0;
    private int UFOSpaceshipType = 1;
    private static int playerScore = 0;
    private static int selectedUfo1 = 0;
    private static int selectedUfo2 = 0;
    private static int selectedUfo3 = 0;
    private static int spacebarFrames = 0;
    private static int spacebarGate = 0;
    private static int currentLevel = 1;
    private static int gameScore = 0;
    private static int humanLives = 3;
    private static int upgradeNumber = 0;
    private static int outerspaceWidth;
    private static int outerspaceHeight;
    private JPanel drawPanel;
    private static Image outerspacePicture;
    private static Image missilePicture;
    private static Image nukePicture;
    private static Image humanshipPicture;
    private static Image UFOSpaceship1Picture;
    private static Image UFOSpaceship2Picture;
    private static Image UFOSpaceship3Picture;
    private static Image fireball1Picture;
    private static Image fireball2Picture;
    private static Image fireball3Picture;
    private static Image fireball4Picture;
    private static Image fireball5Picture;
    private static Level level; //Level object for current level
    private final int HUMAN_SPACESHIP_SPEED = 15;
    private static final int NUMBER_OF_LEVELS = 5;
    private static final double UFO_MISSILE_SPEED = 8.0;
    private static final double HUMAN_MISSILE_SPEED = 20.0;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int UFO_SCREEN_LIMIT = 6;
    private static final int NUMBER_OF_UFOS_TO_GENERATE = 3;
    private static final int LEVEL_ONE_UFOS_TO_DEFEAT = 9;
    private static final int LEVEL_TWO_UFOS_TO_DEFEAT = 12;
    private static final int LEVEL_THREE_UFOS_TO_DEFEAT = 15;
    private static final int LEVEL_FOUR_UFOS_TO_DEFEAT = 18;
    private static final int LEVEL_FIVE_UFOS_TO_DEFEAT = 21;
	
    public static void main(String[] args) {
    	JFrame frame = new MissileExchange();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    }	//end method main

    public MissileExchange() {
    	explosionAnimation = new ArrayList<Image>();
        explosions = new ArrayList<Explosion>();
        gameItems = new ArrayList<GameItem>();
        ufos = new ArrayList<Destroyable>();
        pressedNukes = new ArrayList<Nuke>();
        pressedHumanMissiles = new ArrayList<Missile>();
        typedHumanMissiles = new ArrayList<Missile>();

        getImages();
	
        explosionAnimation.add(fireball1Picture);
        explosionAnimation.add(fireball2Picture);
        explosionAnimation.add(fireball3Picture);
        explosionAnimation.add(fireball4Picture);
        explosionAnimation.add(fireball5Picture);

        setUndecorated(true);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        drawPanel = new DrawPanel();
        drawPanel.setFocusable(true); // receives keyboard data
        drawPanel.addKeyListener(this);

        Container contentPane = getContentPane();
        contentPane.add(drawPanel, "Center");

        //int x, int y, int width, int height, boolean travelingToLeft, int shipSpeed, Image itemImage
        human = new Humanship(230, 465, 60, 20, false, HUMAN_SPACESHIP_SPEED, humanshipPicture);
        gameItems.add(human);

        levelStarter.startLevel();
        
        //schedule(TimerTask, delay in milliseconds, period of time in milliseconds between successive task executions)
        gameTimer.schedule(new Animate(), 0, 50);
        gameTimer.schedule(new SpacebarTrafficLight(), 20, 333);
        gameTimer.schedule(new VictoryChecker(), 35, 1000);
        gameTimer.schedule(new UFOAttack(), 25, 1000);
    } //end default constructor    

    public void getImages(){
        outerspacePicture = new ImageIcon("ProjectPictures4-18\\Outerspace.png").getImage();
        missilePicture = new ImageIcon("ProjectPictures4-18\\Missile.png").getImage();
        humanshipPicture = new ImageIcon("ProjectPictures4-18\\HumanShip.png").getImage();
        UFOSpaceship1Picture = new ImageIcon("ProjectPictures4-18\\UFOSpaceship1.png").getImage();
        UFOSpaceship2Picture = new ImageIcon("ProjectPictures4-18\\UFOSpaceship2.png").getImage();
        UFOSpaceship3Picture = new ImageIcon("ProjectPictures4-18\\UFOSpaceship3.png").getImage();
        fireball1Picture = new ImageIcon("ProjectPictures4-18\\fireball1.png").getImage();
        fireball2Picture = new ImageIcon("ProjectPictures4-18\\fireball2.png").getImage();
        fireball3Picture = new ImageIcon("ProjectPictures4-18\\fireball3.png").getImage();
        fireball4Picture = new ImageIcon("ProjectPictures4-18\\fireball4.png").getImage();
        fireball5Picture = new ImageIcon("ProjectPictures4-18\\fireball5.png").getImage();
        nukePicture = new ImageIcon("ProjectPictures4-18\\nuke.png").getImage();
    }   //end method getImages

    public static Image getExplosionImage(int pictureIndex) {
    	return explosionAnimation.get(pictureIndex);
    }	//end method getExplosionImage

    public void keyPressed(KeyEvent event1) {
    	int keyCode = event1.getKeyCode();

    	//identify key that was pressed
    	if(keyCode == KeyEvent.VK_SPACE) {
    		spacebarFrames++; //units of time that space bar was held down for

    		if(spacebarFrames > 1) { //space bar has been pressed two or more times
    			if(spacebarGate % 10 == 0){ //spacebarGate is divisible by 10
    				//int x, int y, int w, int h,boolean travelingToLeft, boolean humanMissile, double missileSpeed, Image itemImage
    				missileObj = new Missile((int)(human.getX() + human.width / 2 - 4), 430, 5, 10, false, true, HUMAN_MISSILE_SPEED, missilePicture);
    				pressedHumanMissiles.add(missileObj); //stores human missiles for which space bar was pressed
    			}	//end if
    		}	//end if

    		spacebarGate++;
    	} else if(keyCode == KeyEvent.VK_0) { //zero key was pressed, generate a nuke
    		//int x, int y, int w, int h,boolean travelingToLeft, boolean humanMissile, double missileSpeed, Image itemImage
    		nukeObj1 = new Nuke((int)(human.getX() + human.width / 2 - 4), 430, 5, 10, false, true, HUMAN_MISSILE_SPEED, nukePicture);
    		pressedNukes.add(nukeObj1); //store nuke for which zero key was pressed
    	} else if(keyCode == KeyEvent.VK_LEFT) { //left arrow pressed, move human to left
    		human.setTravelingToLeft(true);
    		human.update(); //move the image left
    	} else if(keyCode == KeyEvent.VK_RIGHT) { //right arrow pressed, move human to right
    		human.setTravelingToLeft(false);
    		human.update(); //move image right
    	}   //end if
    }	//end method keyPressed

    public void keyReleased(KeyEvent e) {
		if(spacebarFrames > 1) { //space bar has been held down for two or more units of time
		    spacebarGate = 0;
		    //System.out.println("frames > 1");
		} else if(spacebarFrames == 1) { //space bar pressed only one time
		    if(spacebarStop == true) {
		    	//int x, int y, int w, int h,boolean travelingToLeft, boolean humanMissile, double missileSpeed, Image itemImage
		    	missileObj = new Missile((int)(human.getX() + human.width / 2 - 4), 430, 5, 10, false, true, HUMAN_MISSILE_SPEED, missilePicture);
		    	pressedHumanMissiles.add(missileObj);
		    	spacebarStop = false; //now able to press space bar again because set to false
		    }	//end if
		}   //end if
		spacebarFrames = 0; //reset
    }	//end method keyReleased

    public void keyTyped(KeyEvent e) {}

    private class Animate extends java.util.TimerTask {
		public void run() {
		    drawPanel.repaint();
		}   //end method run
    }	//end class Animate

    private class SpacebarTrafficLight extends java.util.TimerTask {
		public void run() {
		    spacebarStop = true; //disable ability to create new missiles
		}   //end method run
    }	//end class SpacebarTrafficLight

    private class UFOAttack extends java.util.TimerTask {
		public void run() {
		    if(MissileExchange.ufos.size() > 0){
				for(int j = 0; j < 2; j++){ //use j < 2 because we want two different UFOs to fire missiles
					//ufos holds all UFOs on the screen
					//randomly select a UFO on the screen. Selected UFO fires a missile
				    selectedUFO = MissileExchange.ufos.get(generator.nextInt(MissileExchange.ufos.size()));
				    ufoSpaceshipObj = (UFOSpaceship)(selectedUFO);
				    ufoSpaceshipObj.fireMissile();
				}   //end for
		    }	//end if    
		}   //end method run
    }	//end class UfoAttack

    private class VictoryChecker extends java.util.TimerTask {
		public void run() {
		    if ( humanLives > 0 ) { //human player has at least one life left
		    	if ( regenerateHumanship == true ) { //human has lost one life
		    		regenerateHumanship = false; //disable human regeneration
		    		//System.out.println(MissileExchange.humanLives + " lives remaining");
		    		human = new Humanship(230, 465, 60, 20, false, HUMAN_SPACESHIP_SPEED, humanshipPicture);
		    		gameItems.add(human);
		    	}	//end if
		    }	//end if
		    
		    if ( humanLives <= 0 || victoryFlag == true ) { //either the human player is out of lives or victory has been achieved	
		    	gameTimer.cancel(); //cancel the timers
		    	explosionTimer.cancel();
		    	levelTimer.cancel();
		    }	//end if
	
		    if ( switchingLevels == true ) { //victory was achieved so go to next level
		    	levelStarter.startLevel();
		    	switchingLevels = false;
		    }	//end if
		}	//end method run
    }	//end class VictoryChecker

    private class DrawPanel extends JPanel {
	    int imageX = 0;
	    int imageY = 0;
    	
	    public void paintComponent(Graphics graphics1) {
	        super.paintComponent(graphics1);
			Graphics2D graphics2 = (Graphics2D) graphics1;
		
			while ( GameItem.getExplosionsWaiting() > 0 ) { //there is at least one explosion that needs to be added
			    gameItems.add(Missile.getExplosionToAdd(GameItem.getExplosionsWaiting() - 1)); //add explosion to gameItems ArrayList
			    //System.out.println("explosion added");
			    GameItem.setExplosionsWaiting(GameItem.getExplosionsWaiting() - 1); //decrement explosionsWaiting by one
			}   //end while
		
			for ( int w = 0; w < pressedHumanMissiles.size(); w++ ) {
			    Missile missileObj2 = pressedHumanMissiles.get(w);
		
			    if ( missileObj2 == null ) {
					//System.out.println("null");
					continue;
				} //end if
		
			    gameItems.add(missileObj2);
			    pressedHumanMissiles.remove(missileObj2); //pressedHumanMissiles holds missiles that need to be added to gameItems
			}   //end for
		
			for ( int w = 0; w < pressedNukes.size(); w++ ) {
			    Nuke nukeObj1 = pressedNukes.get(w);
		
			    if ( nukeObj1 == null ){
			    	//System.out.println("null");
			    	continue;
			    } //end if
		
			    gameItems.add(nukeObj1);
			    pressedNukes.remove(nukeObj1);
			}   //end for
		
			graphics2.drawImage(outerspacePicture, 0, 0, null);
		
			for ( int i = 0; i < gameItems.size(); i++ ) {
			    GameItem gameItemObj1 = gameItems.get(i);
		
			     if ( gameItemObj1 == null ) {
			    	//System.out.println("null");
			    	continue;
				 } //end if
		
			     if ( gameItemObj1 instanceof UFOSpaceship ) {
			    	 UFOSpaceship ufoSpaceshipA = (UFOSpaceship)(gameItemObj1);
			    	 ufoSpaceshipA.update();
			     }	//end if
		
			     if ( gameItemObj1 instanceof Missile ) {
			    	 GameItem gameItemObj2 = (GameItem)(gameItemObj1);
			    	 gameItemObj2.translateVertically();
				 }	//end if
		
			    if ( gameItemObj1.isOutOfWindow() ) {
					if ( gameItemObj1 instanceof UFOSpaceship ){ //Change the direction spaceship is traveling
					    if ( gameItemObj1.isTravelingToLeft() == false ) gameItemObj1.setTravelingToLeft(true);
					    else gameItemObj1.setTravelingToLeft(false);
					}	//end if
		
					if ( gameItemObj1 instanceof Missile ){ //Missile has gone off screen, remove missile
					    gameItems.remove(gameItemObj1);
					}	//end if
			    }   //end if
		
			    imageX = (int)gameItemObj1.x;
			    imageY = (int)gameItemObj1.y;
		
			    graphics2.drawImage(gameItemObj1.getItemImage(), imageX, imageY, null);
		
	            if ( gameItemObj1 instanceof Explosion ) {
	                explosionTimer.schedule(new ExplosionTask(gameItemObj1.x, gameItemObj1.y, graphics2), 0, 50);
	                gameItems.remove(i);
	            }   //end if
			}   //end for
	    }   //end method paintComponent
    } //end class DrawPanel
    
    private class LevelStarter {
        private static final int LEVEL1_UFO_SPACESHIP_SPEED_MULTIPLIER = 8;
        private static final int LEVEL2_UFO_SPACESHIP_SPEED_MULTIPLIER = 9;
        private static final int LEVEL3_UFO_SPACESHIP_SPEED_MULTIPLIER = 10;
        private static final int LEVEL4_UFO_SPACESHIP_SPEED_MULTIPLIER = 11;
        private static final int LEVEL5_UFO_SPACESHIP_SPEED_MULTIPLIER = 12;
        
        public void startLevel(){
    		if ( getNumberOfUFOSOnScreen() <= 0 ) {
    			setSwitchingLevels(false); //In process of switching levels, no need to switch again
    	
    			switch ( getCurrentLevel() ){
    			    case 1: //level 1
    					//System.out.println("before setting speed multiplier");
    					UFOSpaceship.setUfoSpeedMultiplier(LEVEL1_UFO_SPACESHIP_SPEED_MULTIPLIER);
    					//System.out.println("***** Level 1 starting, ufoSpaceshipSpeedMultiplier *****");
    					break;
    			    case 2: //level 2
    					UFOSpaceship.setUfoSpeedMultiplier(LEVEL2_UFO_SPACESHIP_SPEED_MULTIPLIER);
    					//System.out.println("***** Level 2 starting, ufoSpaceshipSpeedMultiplier *****");
    					break;
    			    case 3:
    					UFOSpaceship.setUfoSpeedMultiplier(LEVEL3_UFO_SPACESHIP_SPEED_MULTIPLIER);
    					//System.out.println("***** Level 3 starting, ufoSpaceshipSpeedMultiplier *****");
    					break;
    			    case 4:
    					UFOSpaceship.setUfoSpeedMultiplier(LEVEL4_UFO_SPACESHIP_SPEED_MULTIPLIER);
    					//System.out.println("***** Level 4 starting, ufoSpaceshipSpeedMultiplier *****");
    					break;
    			    case 5:
    					UFOSpaceship.setUfoSpeedMultiplier(LEVEL5_UFO_SPACESHIP_SPEED_MULTIPLIER);
    					//System.out.println("***** Level 5 starting *****");
    					break;
    			}   //end switch
    			
    			setLevel(new Level());
				getLevelTimer().schedule(getLevel(), 0, 3000);
    		}   //end if
        }   //end method startLevel
    }   //end class LevelStarter
    
    public class Level extends java.util.TimerTask {
        
        Level() {
        	setUfosDestroyedThisLevel(0);
            setNumberOfUFOSOnScreen(0);
            setUfosGeneratedThisLevel(0);
        }   //end default constructor

        public void run() {
            if ( getNumberOfUFOSOnScreen() <= UFO_SCREEN_LIMIT ) { //if num of UFOs on screen has not exceeded limit
                if ( getUfosGeneratedThisLevel() + 3 <= LEVEL_ONE_UFOS_TO_DEFEAT ) { //human needs to destroy at least 3 more UFOs
                    setNumberOfUFOSOnScreen(getNumberOfUFOSOnScreen() + 3);
                    setUfosGeneratedThisLevel(getUfosGeneratedThisLevel() + 3);
                    //System.out.println("UFOs generated this level" + ufosGeneratedThisLevel);
                    //generate more UFOs
                    UFOSpaceship u1 = new UFOSpaceship1(0, 30, 33, 20, false, getUFOSpaceship1Picture(), getUfoMissileSpeed(), getMissilePicture());
                    UFOSpaceship u2 = new UFOSpaceship2(250, 70, 33, 20, true,  getUFOSpaceship2Picture(), getUfoMissileSpeed(), getMissilePicture());
                    UFOSpaceship u3 = new UFOSpaceship3(500, 110, 33, 20, true, getUFOSpaceship3Picture(), getUfoMissileSpeed(), getMissilePicture());
                    getGameItems().add(u1);
                    getGameItems().add(u2);
                    getGameItems().add(u3);
    				Destroyable du1 = (Destroyable) (u1);
    				Destroyable du2 = (Destroyable) (u2);
    				Destroyable du3 = (Destroyable) (u3);
    				getUfos().add(du1);
    				getUfos().add(du2);
    				getUfos().add(du3);
                } else if ( getUfosGeneratedThisLevel() + 2 <= LEVEL_ONE_UFOS_TO_DEFEAT ) { //human needs to destroy 2 more UFOs
                    setNumberOfUFOSOnScreen(getNumberOfUFOSOnScreen() + 2);
                    setUfosGeneratedThisLevel(getUfosGeneratedThisLevel() + 2);
                    //System.out.println("UFOs generated this level" + ufosGeneratedThisLevel);
                    //generate more UFOs
                    UFOSpaceship u1 = new UFOSpaceship1(0, 30, 33, 20, false, getUFOSpaceship1Picture(), getUfoMissileSpeed(), getMissilePicture());
                    UFOSpaceship u2 = new UFOSpaceship2(250, 70, 33, 20, true,  getUFOSpaceship2Picture(), getUfoMissileSpeed(), getMissilePicture());
                    getGameItems().add(u1);
                    getGameItems().add(u2);
    				Destroyable du1 = (Destroyable) (u1);
    				Destroyable du2 = (Destroyable) (u2);
    				getUfos().add(du1);
    				getUfos().add(du2);
                } else if ( getUfosGeneratedThisLevel() + 1 <= LEVEL_ONE_UFOS_TO_DEFEAT ) { //human needs to destroy 1 more UFO
                    setNumberOfUFOSOnScreen(getNumberOfUFOSOnScreen() + 1);
                    setUfosGeneratedThisLevel(getUfosGeneratedThisLevel() + 1);
                    //System.out.println("UFOs generated this level" + ufosGeneratedThisLevel);
                    //generate more UFOs
                    UFOSpaceship u1 = new UFOSpaceship1(0, 30, 33, 20, false, getUFOSpaceship1Picture(), getUfoMissileSpeed(), getMissilePicture());
                    getGameItems().add(u1);
    				Destroyable du1 = (Destroyable) (u1);
    				getUfos().add(du1);
                }   //end if
            }   //end if

            if ( getUfosDestroyedThisLevel() >= LEVEL_ONE_UFOS_TO_DEFEAT ) { //human has destroyed all the UFOs
                setSwitchingLevels(true); //ready to switch levels
                setCurrentLevel(getCurrentLevel() + 1); //go to next level
                //System.out.println("***************level one successfully completed************");
                cancel(); //cancels this timer task
            }   //end if
        }   //end method run
    }   //end class Level1
    
    public static java.util.ArrayList<GameItem> getGameItems() {
		return gameItems;
	}

	public static void setGameItems(java.util.ArrayList<GameItem> gameItems) {
		MissileExchange.gameItems = gameItems;
	}

	public static java.util.ArrayList<Missile> getPressedHumanMissiles() {
		return pressedHumanMissiles;
	}

	public static void setPressedHumanMissiles(java.util.ArrayList<Missile> pressedHumanMissiles) {
		MissileExchange.pressedHumanMissiles = pressedHumanMissiles;
	}

	public static java.util.ArrayList<Missile> getTypedHumanMissiles() {
		return typedHumanMissiles;
	}

	public static void setTypedHumanMissiles(java.util.ArrayList<Missile> typedHumanMissiles) {
		MissileExchange.typedHumanMissiles = typedHumanMissiles;
	}

	public static java.util.ArrayList<Nuke> getPressedNukes() {
		return pressedNukes;
	}

	public static void setPressedNukes(java.util.ArrayList<Nuke> pressedNukes) {
		MissileExchange.pressedNukes = pressedNukes;
	}

	public static boolean isNukePressed() {
		return nukePressed;
	}

	public static void setNukePressed(boolean nukePressed) {
		MissileExchange.nukePressed = nukePressed;
	}

	public static java.util.Timer getGameTimer() {
		return gameTimer;
	}

	public static void setGameTimer(java.util.Timer gameTimer) {
		MissileExchange.gameTimer = gameTimer;
	}

	public static java.util.Timer getLevelTimer() {
		return levelTimer;
	}

	public static void setLevelTimer(java.util.Timer levelTimer) {
		MissileExchange.levelTimer = levelTimer;
	}

	public static java.util.Timer getExplosionTimer() {
		return explosionTimer;
	}

	public static void setExplosionTimer(java.util.Timer explosionTimer) {
		MissileExchange.explosionTimer = explosionTimer;
	}

	public static Random getGenerator() {
		return generator;
	}

	public static void setGenerator(Random generator) {
		MissileExchange.generator = generator;
	}

	public static boolean isSwitchingLevels() {
		return switchingLevels;
	}

	public static void setSwitchingLevels(boolean switchingLevels) {
		MissileExchange.switchingLevels = switchingLevels;
	}

	public static int getUfosDestroyedThisLevel() {
		return ufosDestroyedThisLevel;
	}

	public static void setUfosDestroyedThisLevel(int ufosDestroyedThisLevel) {
		MissileExchange.ufosDestroyedThisLevel = ufosDestroyedThisLevel;
	}

	public static int getUfosGeneratedThisLevel() {
		return ufosGeneratedThisLevel;
	}

	public static void setUfosGeneratedThisLevel(int ufosGeneratedThisLevel) {
		MissileExchange.ufosGeneratedThisLevel = ufosGeneratedThisLevel;
	}

	public static boolean isVictoryFlag() {
		return victoryFlag;
	}

	public static void setVictoryFlag(boolean victoryFlag) {
		MissileExchange.victoryFlag = victoryFlag;
	}

	public static int getUpgradeNumber() {
		return upgradeNumber;
	}

	public static void setUpgradeNumber(int upgradeNumber) {
		MissileExchange.upgradeNumber = upgradeNumber;
	}

	public static boolean isRegenerateHumanship() {
		return regenerateHumanship;
	}

	public static void setRegenerateHumanship(boolean regenerateHumanship) {
		MissileExchange.regenerateHumanship = regenerateHumanship;
	}

	public static Humanship getHuman() {
		return human;
	}

	public static void setHuman(Humanship human) {
		MissileExchange.human = human;
	}

	public static int getNumberOfUFOSOnScreen() {
		return numberOfUFOSOnScreen;
	}

	public static void setNumberOfUFOSOnScreen(int numberOfUFOSOnScreen) {
		MissileExchange.numberOfUFOSOnScreen = numberOfUFOSOnScreen;
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(int currentLevel) {
		MissileExchange.currentLevel = currentLevel;
	}

	public static int getHumanLives() {
		return humanLives;
	}

	public static void setHumanLives(int humanLives) {
		MissileExchange.humanLives = humanLives;
	}

	public static UFOSpaceship getUfoSpaceshipObj() {
		return ufoSpaceshipObj;
	}

	public static void setUfoSpaceshipObj(UFOSpaceship ufoSpaceshipObj) {
		MissileExchange.ufoSpaceshipObj = ufoSpaceshipObj;
	}

	public JPanel getDrawPanel() {
		return drawPanel;
	}

	public void setDrawPanel(JPanel drawPanel) {
		this.drawPanel = drawPanel;
	}

	public static int getOuterspaceWidth() {
		return outerspaceWidth;
	}

	public static void setOuterspaceWidth(int outerspaceWidth) {
		MissileExchange.outerspaceWidth = outerspaceWidth;
	}

	public static int getOuterspaceHeight() {
		return outerspaceHeight;
	}

	public static void setOuterspaceHeight(int outerspaceHeight) {
		MissileExchange.outerspaceHeight = outerspaceHeight;
	}

	public int getUFOSpaceshipType() {
		return UFOSpaceshipType;
	}

	public void setUFOSpaceshipType(int uFOSpaceshipType) {
		UFOSpaceshipType = uFOSpaceshipType;
	}

	public static ArrayList<Explosion> getExplosions() {
		return explosions;
	}

	public static void setExplosions(ArrayList<Explosion> explosions) {
		MissileExchange.explosions = explosions;
	}

	public static ArrayList<Image> getExplosionAnimation() {
		return explosionAnimation;
	}

	public static void setExplosionAnimation(ArrayList<Image> explosionAnimation) {
		MissileExchange.explosionAnimation = explosionAnimation;
	}

	public static Image getOuterspacePicture() {
		return outerspacePicture;
	}

	public static void setOuterspacePicture(Image outerspacePicture) {
		MissileExchange.outerspacePicture = outerspacePicture;
	}

	public static Image getMissilePicture() {
		return missilePicture;
	}

	public static void setMissilePicture(Image missilePicture) {
		MissileExchange.missilePicture = missilePicture;
	}

	public static Image getUFOSpaceship1Picture() {
		return UFOSpaceship1Picture;
	}

	public static void setUFOSpaceship1Picture(Image uFOSpaceship1Picture) {
		UFOSpaceship1Picture = uFOSpaceship1Picture;
	}

	public static Image getUFOSpaceship2Picture() {
		return UFOSpaceship2Picture;
	}

	public static void setUFOSpaceship2Picture(Image uFOSpaceship2Picture) {
		UFOSpaceship2Picture = uFOSpaceship2Picture;
	}

	public static Image getUFOSpaceship3Picture() {
		return UFOSpaceship3Picture;
	}

	public static void setUFOSpaceship3Picture(Image uFOSpaceship3Picture) {
		UFOSpaceship3Picture = uFOSpaceship3Picture;
	}

	public static Image getFireball1Picture() {
		return fireball1Picture;
	}

	public static void setFireball1Picture(Image fireball1Picture) {
		MissileExchange.fireball1Picture = fireball1Picture;
	}

	public static Image getFireball2Picture() {
		return fireball2Picture;
	}

	public static void setFireball2Picture(Image fireball2Picture) {
		MissileExchange.fireball2Picture = fireball2Picture;
	}

	public static Image getFireball3Picture() {
		return fireball3Picture;
	}

	public static void setFireball3Picture(Image fireball3Picture) {
		MissileExchange.fireball3Picture = fireball3Picture;
	}

	public static Image getFireball4Picture() {
		return fireball4Picture;
	}

	public static void setFireball4Picture(Image fireball4Picture) {
		MissileExchange.fireball4Picture = fireball4Picture;
	}

	public static Image getFireball5Picture() {
		return fireball5Picture;
	}

	public static void setFireball5Picture(Image fireball5Picture) {
		MissileExchange.fireball5Picture = fireball5Picture;
	}

	public static Image getHumanshipPicture() {
		return humanshipPicture;
	}

	public static void setHumanshipPicture(Image humanshipPicture) {
		MissileExchange.humanshipPicture = humanshipPicture;
	}

	public static Image getNukePicture() {
		return nukePicture;
	}

	public static void setNukePicture(Image nukePicture) {
		MissileExchange.nukePicture = nukePicture;
	}

	public static Level getLevel() {
		return level;
	}

	public static void setLevel(Level level) {
		MissileExchange.level = level;
	}

	public static java.util.ArrayList<Destroyable> getUfos() {
		return ufos;
	}

	public static void setUfos(java.util.ArrayList<Destroyable> ufos) {
		MissileExchange.ufos = ufos;
	}

	public static int getPlayerScore() {
		return playerScore;
	}

	public static void setPlayerScore(int playerScore) {
		MissileExchange.playerScore = playerScore;
	}

	public static int getSelectedUfo1() {
		return selectedUfo1;
	}

	public static void setSelectedUfo1(int selectedUfo1) {
		MissileExchange.selectedUfo1 = selectedUfo1;
	}

	public static int getSelectedUfo2() {
		return selectedUfo2;
	}

	public static void setSelectedUfo2(int selectedUfo2) {
		MissileExchange.selectedUfo2 = selectedUfo2;
	}

	public static int getSelectedUfo3() {
		return selectedUfo3;
	}

	public static void setSelectedUfo3(int selectedUfo3) {
		MissileExchange.selectedUfo3 = selectedUfo3;
	}

	public static Destroyable getSelectedUFO() {
		return selectedUFO;
	}

	public static void setSelectedUFO(Destroyable selectedUFO) {
		MissileExchange.selectedUFO = selectedUFO;
	}

	public static Missile getMissileObj() {
		return missileObj;
	}

	public static void setMissileObj(Missile missileObj) {
		MissileExchange.missileObj = missileObj;
	}

	public static int getSpacebarFrames() {
		return spacebarFrames;
	}

	public static void setSpacebarFrames(int spacebarFrames) {
		MissileExchange.spacebarFrames = spacebarFrames;
	}

	public static int getSpacebarGate() {
		return spacebarGate;
	}

	public static void setSpacebarGate(int spacebarGate) {
		MissileExchange.spacebarGate = spacebarGate;
	}

	public boolean isSpacebarStop() {
		return spacebarStop;
	}

	public void setSpacebarStop(boolean spacebarStop) {
		this.spacebarStop = spacebarStop;
	}

	public static int getGameScore() {
		return gameScore;
	}

	public static void setGameScore(int gameScore) {
		MissileExchange.gameScore = gameScore;
	}

	public static Nuke getNukeObj1() {
		return nukeObj1;
	}

	public static void setNukeObj1(Nuke nukeObj1) {
		MissileExchange.nukeObj1 = nukeObj1;
	}

	public int getHUMAN_SPACESHIP_SPEED() {
		return HUMAN_SPACESHIP_SPEED;
	}

	public static int getNumberOfLevels() {
		return NUMBER_OF_LEVELS;
	}

	public static double getUfoMissileSpeed() {
		return UFO_MISSILE_SPEED;
	}

	public static double getHumanMissileSpeed() {
		return HUMAN_MISSILE_SPEED;
	}

	public static int getWindowWidth() {
		return WINDOW_WIDTH;
	}

	public static int getWindowHeight() {
		return WINDOW_HEIGHT;
	}
}   //end class MissileExchange
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

    public static void main(String[] args) {
    	JFrame frame = new MissileExchange();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    }	//end method main

    public MissileExchange() {
    	explosionAnimation = new ArrayList<Image>();
        explosions = new ArrayList<Explosion>();
        gameItems = new java.util.ArrayList<GameItem>();
        ufos = new java.util.ArrayList<Destroyable>();
        pressedNukes = new java.util.ArrayList<Nuke>();
        pressedHumanMissiles = new java.util.ArrayList<Missile>();
        typedHumanMissiles = new java.util.ArrayList<Missile>();
        endGameScreen = new JFrame();

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

        human = new Humanship(230, 465, 60, 20, false, HUMAN_SPACESHIP_SPEED, humanshipPicture);
        gameItems.add(human);

        LevelStarter.startLevel();

        gameTimer.schedule(new Animate(), 0, 50);
        gameTimer.schedule(new SpacebarTrafficLight(), 20, 333);
        gameTimer.schedule(new VictoryChecker(), 35, 1000);
        gameTimer.schedule(new UfoAttack(), 25, 1000);
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
    }   //end method loadImages

    public static Image getExplosionImage(int pictureIndex) {
    	return explosionAnimation.get(pictureIndex);
    }	//end method getExplosionImage

    public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();

    	if(keyCode == KeyEvent.VK_SPACE) {
    		spacebarFrames++;

    		if(spacebarFrames > 1) {
    			if(spacebarGate % 10 == 0){
    				m = new Missile((int)(human.getX() + human.width / 2 - 4), 430, 5, 10, false, true, HUMAN_MISSILE_SPEED, missilePicture);
    				pressedHumanMissiles.add(m);
    			}	//end if
    		}	//end if

    		spacebarGate++;
    	} else if(keyCode == KeyEvent.VK_0) {
    		n = new Nuke((int)(human.getX() + human.width / 2 - 4), 430, 5, 10, false, true, HUMAN_MISSILE_SPEED, nukePicture);
    		pressedNukes.add(n);
    	} else if(keyCode == KeyEvent.VK_LEFT) {
    		human.travelingToLeft = true;
    		human.update();
    	} else if(keyCode == KeyEvent.VK_RIGHT) {
    		human.travelingToLeft = false;
    		human.update();
    	}   //end if
    }	//end method keyPressed

    public void keyReleased(KeyEvent e) {
		if(spacebarFrames > 1) {
		    spacebarGate = 0;
		    System.out.println("frames > 1");
		} else if(spacebarFrames == 1) {
		    if(spacebarStop == true) {
			m = new Missile((int)(human.getX() + human.width / 2 - 4), 430, 5, 10, false, true, HUMAN_MISSILE_SPEED, missilePicture);
			pressedHumanMissiles.add(m);
			spacebarStop = false;
		    }	//end if
		}   //end if
	
		spacebarFrames = 0;
    }	//end method keyReleased

    public void keyTyped(KeyEvent e) {}

    private class Animate extends java.util.TimerTask {
		public void run() {
		    drawPanel.repaint();
		}   //end method run
    }	//end class Animate

    private class SpacebarTrafficLight extends java.util.TimerTask {
		public void run() {
		    spacebarStop = true;
		}   //end method run
    }	//end class SpacebarTrafficLight

    private class UfoAttack extends java.util.TimerTask {
		public void run() {
		    if(MissileExchange.ufos.size() > 0){
				for(int j = 0; j < 2; j++){
				    selectedUFO = MissileExchange.ufos.get(generator.nextInt(MissileExchange.ufos.size()));
				    s = (UFOSpaceship)(selectedUFO);
				    s.fireMissile();
				}   //end for
		    }	//end if    
		}   //end method run
    }	//end class UfoAttack

    private class VictoryChecker extends java.util.TimerTask {
		public void run() {
		    if(humanLives > 0){
		    	if(MissileExchange.regenerateHumanship == true) {
		    		MissileExchange.regenerateHumanship = false;
	
		    		System.out.println(MissileExchange.humanLives + " lives remaining");
		    		MissileExchange.human = new Humanship(230, 465, 60, 20, false, HUMAN_SPACESHIP_SPEED, humanshipPicture);
		    		gameItems.add(human);
		    	}	//end if
		    }	//end if
		    
		    if(humanLives <= 0) {
			
		    	gameTimer.cancel();
		    	explosionTimer.cancel();
		    	levelTimer.cancel();
		    } else if(victory == true) {
		    	gameTimer.cancel();
		    	explosionTimer.cancel();
		    	levelTimer.cancel();
		    }	//end if
	
		    if(switchingLevels == true) {
		    	LevelStarter.startLevel();
		    	switchingLevels = false;
		    }	//end if
		}	//end method run
    }	//end class Animate

    private class DrawPanel extends JPanel {
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
		
			while(GameItem.explosionsWaiting > 0){
			    MissileExchange.gameItems.add(Missile.explosionsToAdd[GameItem.explosionsWaiting - 1]);
			    System.out.println("explosion added");
			    GameItem.explosionsWaiting--;
			}   //end while
		
			for(int w = 0; w < MissileExchange.pressedHumanMissiles.size(); w++) {
			    Missile a = MissileExchange.pressedHumanMissiles.get(w);
		
			    if(a == null){
					System.out.println("null");
					continue;
				} //end if
		
			    MissileExchange.gameItems.add(a);
			    MissileExchange.pressedHumanMissiles.remove(a);
			}   //end for
		
			for(int w = 0; w < MissileExchange.pressedNukes.size(); w++) {
			    Nuke a = MissileExchange.pressedNukes.get(w);
		
			    if(a == null){
			    	System.out.println("null");
			    	continue;
			    } //end if
		
			    MissileExchange.gameItems.add(a);
			    MissileExchange.pressedNukes.remove(a);
			}   //end for
		
			g2.drawImage(MissileExchange.outerspacePicture, 0, 0, null);
		
			for (int i = 0; i < MissileExchange.gameItems.size(); i++) {
			    GameItem s = MissileExchange.gameItems.get(i);
		
			    if(s == null){
			    	System.out.println("null");
			    	continue;
				} //end if
		
			     if(s instanceof UFOSpaceship) {
			    	 UFOSpaceship u = (UFOSpaceship)(s);
			    	 u.update();
			     }	//end if
		
			     if(s instanceof Missile) {
			    	 GameItem q = (GameItem)(s);
			    	 q.translateVertically();
				 }	//end if
		
			    if (s.isOutOfWindow()) {
					if(s instanceof UFOSpaceship){
					    if(s.travelingToLeft == false)
						s.travelingToLeft = true;
					    else s.travelingToLeft = false;
					}	//end if
		
					if(s instanceof Missile){
					    MissileExchange.gameItems.remove(s);
					}	//end if
			    }   //end if
		
			    imageX = (int)s.x;
			    imageY = (int)s.y;
		
			    g2.drawImage(s.itemImage, imageX, imageY, null);
			    //g2.draw(s);
		
	            if(s instanceof Explosion){
	                MissileExchange.explosionTimer.schedule(new ExplosionTask(s.x, s.y, g2), 0, 50);
	                MissileExchange.gameItems.remove(i);
	            }   //end if
			}   //end for
	    }   //end method paintComponent
	    
	    int imageX = 0;
	    int imageY = 0;
    } //end class DrawPanel

    public static java.util.ArrayList<GameItem> gameItems;
    public static java.util.ArrayList<Missile> pressedHumanMissiles;
    public static java.util.ArrayList<Missile> typedHumanMissiles;
    public static java.util.ArrayList<Nuke> pressedNukes;
    private static java.util.Timer gameTimer = new java.util.Timer();
    public static java.util.Timer levelTimer = new java.util.Timer();
    private static java.util.Timer explosionTimer = new java.util.Timer();
    private static Random generator = new Random();
    public static boolean switchingLevels = false;
    public static int ufosDestroyedThisLevel = 0;
    public static int ufosGeneratedThisLevel = 0;
    private static boolean victory = false;
    private static int upgradeNumber = 0;
    public static boolean regenerateHumanship = false;
    public static Humanship human;
    public static int numberOfUFOSOnScreen = 0;
    public static int currentLevel = 1;
    public static int humanLives = 3;
    public final int HUMAN_SPACESHIP_SPEED = 15;
    public static final int NUMBER_OF_LEVELS = 5;
    public static final double UFO_MISSILE_SPEED = 8.0;
    public static final double HUMAN_MISSILE_SPEED = 20.0;
    private static UFOSpaceship s;
    private JPanel drawPanel;
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;
    private static int outerspaceWidth;
    private static int outerspaceHeight;
    private int UFOSpaceshipType = 1;
    public static ArrayList<Explosion> explosions;
    public static ArrayList<Image> explosionAnimation;
    public static Image outerspacePicture;
    public static Image missilePicture;
    public static Image UFOSpaceship1Picture;
    public static Image UFOSpaceship2Picture;
    public static Image UFOSpaceship3Picture;
    public static Image fireball1Picture;
    public static Image fireball2Picture;
    public static Image fireball3Picture;
    public static Image fireball4Picture;
    public static Image fireball5Picture;
    public static Image humanshipPicture;
    public static Image nukePicture;
    public static Level1 l1;
    public static Level2 l2;
    public static Level3 l3;
    public static Level4 l4;
    public static Level5 l5;
    public static java.util.ArrayList<Destroyable> ufos;
    public static int playerScore = 0;
    private static JFrame endGameScreen;
    private static int selectedUfo1 = 0;
    private static int selectedUfo2 = 0;
    private static int selectedUfo3 = 0;
    private static Destroyable selectedUFO;
    private static Missile m;
    private static int spacebarFrames = 0;
    private static int spacebarGate = 0;
    private boolean spacebarStop = true;
    public static int gameScore = 0;
    private static boolean nukePressed = false;
    private static Nuke n;
}   //end class MissileExchange
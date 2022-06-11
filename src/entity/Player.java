package entity;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.Timer;

import main.GamePanel;

public class Player extends Entity
{
	private int lives;
	private boolean invincible;
	private int invincibleCounter;
	private int deaths;
	//diffculty
	private int diff;
	private int key;
	private boolean saveState;
	private int pureNail;
	//if time is stop
	private boolean stopTime;
	//counts how long time should be stop
	private int timeCounter;
	private int stopTimeUses;
	//length of timestop if multiple are used
	private int stopTimeLength;
	
	public Player(GamePanel gP)
	{
		super(gP, 40, 40);
		createHitBox(getX(), getY());
		setup("player", "playerImage");
		setSpeed(4);
	}
	//set lives based on user chosen difficulty
	public void setDefaultLives()
	{
		if(diff == 1)
		{
			lives = 1;
		}
		else
		{
			if(lives <= 3)
			{
				lives = 3;
			}
		}
		
	}
	public void setDiff(int i)
	{
		diff = i;
	}
	
	//is called everytime a new level is started to set the player in the right position
	public void setDefaultPos()
	{
		switch(getgP().getMapLevel())
		{
			case 1: setDefaultPosMap(1, 7); break;
			case 2: setDefaultPosMap(1, 1); break;
			case 3: setDefaultPosMap(12, 1, -20, 24); break;
			case 4: 
				if(!saveState)
				{
					setDefaultPosMap(2, 14, 4, 0); 
				}
				else
				{
					setDefaultPosMap(11, 13, 16, 20); 
				}
				break;
			case 5: 
				if(!saveState)
				{
					setDefaultPosMap(1, 1, 24, 24);
				}
				else
				{
					setDefaultPosMap(18, 7, 24, 24);
				}
				break;
		}
	}
	
	public void setDefaultPosMap(int x, int y)
	{
		setX(getgP().getTileSize() * x);
		setY(getgP().getTileSize() * y);
	}
	public void setDefaultPosMap(int x, int y, int offsetX, int offsetY)
	{
		setX(getgP().getTileSize() * x + offsetX);
		setY(getgP().getTileSize() * y + offsetY);
	}

	public void reset()
	{
		lives = 3;
		key = 0;
		stopTimeUses = 0;
		stopTimeLength = 0;
		saveState = false;
		deaths = 0;
		pureNail = 0;
	}

	//called to update player movement
	//overrides method in superclass
	public void update()
	{
		//creates hitbox in the beginning has issues when at the end;
		createHitBox(getX(), getY());

		//checks wall collision
		//passes an obj of a superclass but takes a subclass of an the super class	
		getgP().getCollisionChecker().checkTile(this);
		//check for lvl 5 cell and door
		getgP().getCollisionChecker().checkDoor(this);
		
		int skullIndex = getgP().getCollisionChecker().checkSkull(this, getgP().getSkull());
		checkSkull(skullIndex);
		//moves the player
		move();
		//check save collision lvl 4
		getgP().getCollisionChecker().checkIfSaveState(this);
		
		//check obstacle collision
		int index = getgP().getCollisionChecker().checkObstacle(this, getgP().getObstacle());
		checkObstacle(index);
		
		//check obj collision
		int objIndex = getgP().getCollisionChecker().checkObj(this, getgP().getObject());
		pickUpObj(objIndex);
		
		//checks if in win area
		getgP().getCollisionChecker().checkIfWin(this);
		
		//allows for diagonal movement
		
		if(getgP().getKeyH().isUpPressed())
		{
			setyVel(-getSpeed());
		}
		if(getgP().getKeyH().isDownPressed())
		{
			setyVel(getSpeed());
		}
		if(getgP().getKeyH().isLeftPressed())
		{
			setxVel(-getSpeed());
		}
		if(getgP().getKeyH().isRightPressed())
		{
			setxVel(getSpeed());
		}
		
		
		

		
		//player gains 25 invinciblility frames/60
		if(invincible)
		{
			invincibleCounter++;
			if(invincibleCounter > 25)
			{
				invincible = false;
				invincibleCounter = 0;
			}
		}
		//frames of stop Time
		// 2 secs so 120/60 fps
		//can stack
		if(stopTime)
		{
			timeCounter++;
			if(timeCounter > stopTimeLength)
			{
				stopTime = false;
				timeCounter = 0;
				stopTimeLength = 0;
			}
		}
		checkLife();
	}
	
	public void checkSkull(int i)
	{
		if(i != 999)
		{
			if(getgP().getKeyH().isEnteredPressed())
			{
				getgP().setTalking(true);
				getgP().setGameState(getgP().getDialogueState());
				getgP().getSkull()[i].speak();
				
			}
		}
	}
	//allows time for death animation if lives <= 0 and resets lives to 1
	public void checkLife()
	{
		if(lives <= 0 && !isDying())
		{
			setDying(true);
			Timer pause = new Timer(400, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					setDefaultPos();
				}
			});
			pause.start();
			pause.setRepeats(false);
			lives = 1;
		}
	}
	//checks which powerups you picked
	public void pickUpObj(int i)
	{
		if(i != 999)
		{
			
			String objName = getgP().getObject()[i].getName();
			boolean showHelp = true;
			String help = "";
			switch(objName)
			{
				case "Heart": 
					getgP().playSE(2);
					lives++; 
					showHelp = false;
					getgP().getObject()[i] = null;
					break;
				case "Diamond":
					getgP().playSE(2);
					showHelp = false;
					getgP().getUserInterface().setFinishedGame(true);
					getgP().getObject()[i] = null;
					break;
				case "Key":
					key++;
					showHelp = false;
					getgP().getObject()[i] = null;
					getgP().playSE(2);
					break;
				case "Door":
					showHelp = false;
					if(key >= 1)
					{
						getgP().playSE(3);
						getgP().getObject()[i] = null;
						key--;
						
					}
					break;
				case "Nail":
					getgP().playSE(4);
					pureNail++;
					showHelp = true;
					help = "\nYou've gained the pure nail.\nAny skull you touch will disintegrate"
							+ "\nPress F to equip and again to unequip";
					getgP().getObject()[i] = null;
					break;
				case "Dio":
					getgP().playSE(2);
					showHelp = true;
					help = "\nYou've gained the power to control time.\nPress space to stop time for 2 sec!";
					setStopTimeUses(getStopTimeUses() + 1);
					getgP().getObject()[i] = null;
					break;
			}
			if(showHelp)
			{
				getgP().setGameState(getgP().getDialogueState());
				getgP().getUserInterface().setCurrentDialogue(help);
			}
			
			
		}
	}
	//checks if the player hit an obs
	public void checkObstacle(int i)
	{
		if(i != 999)
		{
			//pureNail checker
			if(pureNail >= 1 && getgP().getKeyH().isfPressed())
			{
				pureNail--;
				getgP().getObstacle()[i] = null;
				getgP().playSE(5);
			}
			else if(!invincible && !isDying())
			{
				
				lives--;
				deaths++;
				getgP().playSE(1);
				invincible = true;
			}
		}
	}


	//draws the player and makes it so it turns translucent
	//overrides super methods
	public void draw(Graphics2D g2)
	{
		
		//player
		if(invincible)
		{
			//calls inherited methods
			setOpacity(g2, 0.4f);
		}

		//calls superclass method with super keyword
		super.draw(g2);
		setOpacity(g2, 1f);
		
	}

	public void stopTime()
	{
		if(stopTimeUses >= 1)
		{
			stopTime = true;
			stopTimeUses--;
			stopTimeLength += 120;
				
		
		
			getgP().playSE(6);
			
		}
	
		//1 to 100
		
	}
	public void usePureNail()
	{
		if(pureNail >= 1)
		{
			if(getgP().getKeyH().getfPressedTime() == 1)
			{
				getgP().getKeyH().setfPressed(false);
				getgP().getKeyH().setfPressedTime(0);
				getgP().playSE(8);
			}
			else
			{
				getgP().getKeyH().setfPressed(true);
				getgP().getKeyH().setfPressedTime(1);
				getgP().playSE(7);
			}
		}
		
	}
	//getters and setters
	public int getStopTimeLength()
	{
		return stopTimeLength;
	}
	public void setSaveState(boolean bool)
	{
		saveState = bool;
	}
	public int getLives()
	{
		return lives;
	}
	public void setLives(int lives)
	{
		this.lives = lives;
	}
	public int getDeaths()
	{
		return deaths;
	}
	public boolean getInvicible()
	{
		return invincible;
	}
	
	public int getKey()
	{
		return key;
	}
	public int getPureNail()
	{
		return pureNail;
	}
	public void setPureNail(int pureNail)
	{
		this.pureNail = pureNail;
	}
	public boolean isStopTime()
	{
		return stopTime;
	}
	public void setStopTime(boolean stopTime)
	{
		this.stopTime = stopTime;
	}
	
	public String toString()
	{
		return "I'm a player with " + lives + " lives";
	}
	public boolean equals(Object other)
	{
		return (Player)other == this;	
	}
	public int getTimeCounter()
	{
		return timeCounter;
	}
	public int getStopTimeUses()
	{
		return stopTimeUses;
	}
	public void setStopTimeUses(int stopTimeUses)
	{
		this.stopTimeUses = stopTimeUses;
	}
	

	
}

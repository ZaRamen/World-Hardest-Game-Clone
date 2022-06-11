package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener
{

	private boolean upPressed, downPressed, leftPressed, rightPressed, enteredPressed, fPressed;
	private int fPressedTime;
	private GamePanel gP;
	private Player player;
	private boolean checkDrawTime;
	public KeyHandler(GamePanel gP, Player player)
	{
		this.gP = gP;
		this.player = player;
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		//playState
		if(gP.getGameState() == gP.getPlayState())
		{
			movement(code);
			if(code == KeyEvent.VK_P)
			{
				gP.setGameState(gP.getPauseState());
			}
			if(code == KeyEvent.VK_SPACE)
			{
				gP.getPlayer().stopTime();
			}
			if(code == KeyEvent.VK_ENTER)
			{
				enteredPressed = true;
			}
			if(code == KeyEvent.VK_F)
			{
				gP.getPlayer().usePureNail();
			}
		
		}//pause
		else if(gP.getGameState() == gP.getPauseState())
		{
			gP.setGameState(gP.getPlayState());
		}//level state
		else if(gP.getGameState() == gP.getStoryState())
		{
			if(code == KeyEvent.VK_ENTER)
			{	
				if(gP.getUserInterface().isLvlState())
				{
					gP.setUpGame();
					gP.setGameState(gP.getPlayState());
					gP.getUserInterface().setLvlState(false);
				}
			}
		}//dialogue
		else if(gP.getGameState() == gP.getDialogueState())
		{
			if(code == KeyEvent.VK_ENTER)
			{	
				gP.setGameState(gP.getPlayState());
			}
		}
		else if(gP.getGameState() == gP.getHelpState())
		{
			if(code == KeyEvent.VK_ENTER)
			{	
				gP.setGameState(gP.getStoryState());
				gP.getEasyButton().setVisible(true);
				gP.getHardButton().setVisible(true);
			}
		}
		else if(gP.getGameState() == gP.getTitleState())
		{
			movement(code);
		}
		if(code == KeyEvent.VK_T)
		{
			if(!checkDrawTime)
			{
				checkDrawTime = true;
			}
			else
			{
				checkDrawTime = false;
			}
		}
		if(code == KeyEvent.VK_ESCAPE)
		{
			gP.reset();
			fPressedTime = 0;
			fPressed = false;
			enteredPressed = false;
		}
			

		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			setUpPressed(false);
			player.setyVel(0);
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			setDownPressed(false);
			player.setyVel(0);
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			setLeftPressed(false);
			player.setxVel(0);
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			setRightPressed(false);
			player.setxVel(0);
		}
		if(code == KeyEvent.VK_ENTER)
		{
			//makes sure when enter is let go in dialogue it doesn't mean to go back to play mode
			if(gP.getGameState() != gP.getDialogueState())
			{
				setEnteredPressed(false);
			}
		}
	}
	
	public void movement(int code)
	{
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			setUpPressed(true);
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			setDownPressed(true);
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			setLeftPressed(true);
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			setRightPressed(true);
		}
	}
	public boolean isUpPressed()
	{
		return upPressed;
	}

	public void setUpPressed(boolean upPressed)
	{
		this.upPressed = upPressed;
	}

	public boolean isDownPressed()
	{
		return downPressed;
	}

	public void setDownPressed(boolean downPressed)
	{
		this.downPressed = downPressed;
	}

	public boolean isLeftPressed()
	{
		return leftPressed;
	}

	public void setLeftPressed(boolean leftPressed)
	{
		this.leftPressed = leftPressed;
	}

	public boolean isRightPressed()
	{
		return rightPressed;
	}

	public void setRightPressed(boolean rightPressed)
	{
		this.rightPressed = rightPressed;
	}
	public boolean isCheckDrawTime()
	{
		return checkDrawTime;
	}
	public boolean setCheckDrawTime(boolean checkDrawTime)
	{
		this.checkDrawTime = checkDrawTime;
		return checkDrawTime;
	}
	public boolean isEnteredPressed()
	{
		return enteredPressed;
	}
	public void setEnteredPressed(boolean enteredPressed)
	{
		this.enteredPressed = enteredPressed;
	}
	public boolean isfPressed()
	{
		return fPressed;
	}
	public void setfPressed(boolean fPressed)
	{
		this.fPressed = fPressed;
	}
	public int getfPressedTime()
	{
		return fPressedTime;
	}
	public void setfPressedTime(int fPressedTime)
	{
		this.fPressedTime = fPressedTime;
	}

}

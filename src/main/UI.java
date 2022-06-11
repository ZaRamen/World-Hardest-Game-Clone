package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.DecimalFormat;

import entity.Entity;
import entity.Obstacle;
import entity.Skull;
import obj.OBJ_Heart;
import obj.OBJ_PureNail;

public class UI 
{
	private GamePanel gP;
	private BufferedImage heartImage;
	private DecimalFormat dFormat = new DecimalFormat("#0.00");
	private boolean finishedGame = false;
	private Graphics2D g2;
	private Font pixelFont;
	private int storyScreens = 0;
	private boolean lvlState = false;
	private String currentDialogue = "";
	private BufferedImage startImage;
	private BufferedImage nailImage;
	public UI(GamePanel gP)
	{
		this.gP = gP;
		try
		{
			InputStream is = getClass().getResourceAsStream("/font/pixelFont.ttf");
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		OBJ_Heart heart = new OBJ_Heart(0, 0);
		Obstacle skull = new Obstacle();
		OBJ_PureNail nail = new OBJ_PureNail(0, 0);
		startImage = skull.getImage();
		heartImage = heart.getImage();
		nailImage = nail.getImage();
	}
	
	public void draw(Graphics2D g2)
	{
		this.g2 = g2;
		g2.setFont(pixelFont);
		
		if(finishedGame)
		{
			drawEndScreen();
			gP.stopMusic();
			gP.setGameThread(null);
		}
		else
		{
			int gameState = gP.getGameState();
			if(gameState ==  gP.getTitleState())
			{
				drawTitleScreen();
			}
			else if(gameState == gP.getHelpState())
			{
				drawHelpScreen();
			}
			else if(gameState == gP.getPlayState())
			{
				drawPlayScreen();
			}
			else if(gameState == gP.getPauseState())
			{
				drawPauseScreen();
			}
			else if(gameState == gP.getDialogueState())
			{
				drawDialogueScreen();
			}
			else if(gameState == gP.getStoryState())
			{
				if(gP.getMapLevel() == gP.getFinalLevel())
				{
					storyScreens = 2;
				}
				if(storyScreens == 0)
				{
					//default is 0 only changes when you press the difficulty
					drawStoryScreen();
				}
				else if(storyScreens == 1)
				{
					drawLevels();
					//sets to true which results in KeyHandler making the level appear
					//when pressing enter
					setLvlState(true);
				}		
				else if(storyScreens == 2)
				{
					
					drawFinalLevel();
					setLvlState(true);
				}
			}
			
		}
		
	}

	public void drawTitleScreen()
	{
		//titleScreen
		g2.setColor(new Color(0xaa9679));
		g2.fillRect(0, 0, gP.getScreenWidth(), gP.getScreenHeight());
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "The Heist";
		int x = getXCenter(text);
		int y = gP.getTileSize() * 5;

		g2.setColor(new Color(0x6e4c4b));
		g2.drawString(text, x, y);	
		
		x += 370;
		g2.drawImage(startImage, x - 225, y + 20, 80, 80, null);	

	}
	public void drawFinalLevel()
	{
		g2.setColor(new Color(0xaa9679));
		g2.fillRect(0, 0, gP.getScreenWidth(), gP.getScreenHeight());
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		
		String text = "Final Trial";
		
		
		int y = gP.getScreenHeight()/2;
		
		g2.setColor(new Color(0x6e4c4b));
		
		int x = getXCenter(text);
		g2.drawString(text, x, y);	
		
		
	}
	public void drawDialogueScreen()
	{
		int x = gP.getTileSize()* 2;
		int y = gP.getTileSize()/2;
		
		int width = gP.getScreenWidth() - gP.getTileSize() * 4;
		int height = gP.getTileSize() * 4;
		
		
		drawWindow(x, y, width, height);
		
		
		
		x += gP.getTileSize();
		y += gP.getTileSize();
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
		for(String line: currentDialogue.split("\n"))
		{
			g2.drawString(line, x, y);
			y += 30;
		}
		g2.dispose();
		
	}
	public void drawWindow(int x, int y, int width, int height)
	{
		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}
	public void drawStoryScreen()
	{
		g2.setColor(new Color(0xaa9679));
		g2.fillRect(0, 0, gP.getScreenWidth(), gP.getScreenHeight());
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		
		String text = "You have traveled to Egypt in search of the legendary diamond.\n"
					+ "However, in order to obtain it, you have to pass 5 trials. \r\n"
					+ "\n\n"
					+ "GOOD LUCK!";
		
		int y = gP.getScreenHeight()/6;
		
		g2.setColor(new Color(0x6e4c4b));
		for(String s: text.split("\n"))
		{
			int x = getXCenter(s);
			g2.drawString(s, x, y += 50);
		}
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
		int x = getXCenter("Select Difficulty:");
		g2.drawString("Select Difficulty:", x, y + 150);
		
		
	}
	public void drawLevels()
	{
		g2.setColor(new Color(0xaa9679));
		g2.fillRect(0, 0, gP.getScreenWidth(), gP.getScreenHeight());
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		
		String text =  "Trial " + gP.getMapLevel();
		
		int x = getXCenter(text);
		int y = gP.getScreenHeight()/2;
		
		g2.setColor(new Color(0x6e4c4b));

		g2.drawString(text, x, y);
		
	}
	public void drawHelpScreen()
	{
		g2.setColor(new Color(0xaa9679));
		g2.fillRect(0, 0, gP.getScreenWidth(), gP.getScreenHeight());
		
		g2.setColor(new Color(0x6e4c4b));
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));
		
		String text = "Tutorial";
		int x = getXCenter(text);
		int y = gP.getScreenHeight()/2 - (gP.getTileSize() * 4);
		g2.drawString(text, x, y);
		
		text = "W A S D or Arrow Keys to Move";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2 - (gP.getTileSize() * 2);
		g2.drawString(text, x, y);	
		
		text = "Press P to Pause";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2 - (gP.getTileSize());
		g2.drawString(text, x, y);
		
		text = "Pickup Powerups to help aid you.";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2  + (gP.getTileSize());
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		
		g2.drawImage(heartImage, length + x, y - 40, gP.getTileSize(), gP.getTileSize(), null);
		g2.drawString(text, x, y);
		
		text = "Press Esc to go back to Start Screen";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2 + (gP.getTileSize() * 2);
		g2.drawString(text, x, y);	
		
		text = "Press Enter to skip to next screen/dialogue and to";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2 + (gP.getTileSize() * 3);
		g2.drawString(text, x, y);
		
		text = "interact with the skeleton remains.";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2 + (gP.getTileSize() * 4);
		g2.drawString(text, x, y);
		
		
	}
	public void drawEndScreen()
	{
		//0x484A59 old color
		g2.setColor(new Color(0xaa9679));
		g2.fillRect(0, 0, gP.getScreenWidth(), gP.getScreenHeight());
		
		g2.setColor(new Color(0x333399));
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "Congratulations!!";
		int x = getXCenter(text);
		int y = gP.getScreenHeight()/2 - (gP.getTileSize() * 3);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		text = "You have obtained the DIAMOND!!";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2 + (gP.getTileSize());
		g2.drawString(text, x, y);
		
		text = "You played for " + String.format("%.2f", gP.getPlayTime())
		+ " seconds and died " + gP.getPlayer().getDeaths() + " times";
		x = getXCenter(text);
		y = gP.getScreenHeight()/2 + (gP.getTileSize() * 2);
		
		g2.drawString(text, x, y);
	}
	public void drawPlayScreen()
	{
		g2.setColor(Color.white);
		//hearts
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
		g2.drawImage(heartImage, 10, 5, gP.getTileSize(), gP.getTileSize(), null);
		g2.drawString("x" + gP.getPlayer().getLives(), 65, 45);
		
		//timer
		if(!gP.getPlayer().isStopTime())
		{
			gP.setPlayTime(gP.getPlayTime() + ((double)1/60));
		}
		else
		{
			g2.drawString("Stopped Time Left: " + 
			dFormat.format(((double)(gP.getPlayer().getStopTimeLength() - gP.getPlayer().getTimeCounter())/60)), 
			gP.getTileSize() * 12, 45);
		}
		if(gP.getPlayer().getPureNail() >= 1)
		{
			g2.drawImage(nailImage, 200, 5, gP.getTileSize(), gP.getTileSize(), null);
			g2.drawString("x" + gP.getPlayer().getPureNail(), 250, 45);
			
		}
		g2.drawString("Time: " + dFormat.format(gP.getPlayTime()), gP.getTileSize() * 20, 45);
	}
	
	public void drawPauseScreen()
	{
		g2.setColor(new Color(0x2596be));
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 100));
		String text = "PAUSED";
		int x = getXCenter(text);
		int y = gP.getScreenHeight()/2;
		
		g2.drawString(text, x, y);
	}
	public int getXCenter(String text)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gP.getScreenWidth()/2 - length/2;
		return x;
	}

	public boolean isFinishedGame()
	{
		return finishedGame;
	}

	public void setFinishedGame(boolean finishedGame)
	{
		this.finishedGame = finishedGame;
	}

	public int getStoryScreens()
	{
		return storyScreens;
	}

	public void setStoryScreens(int storyScreens)
	{
		this.storyScreens = storyScreens;
	}

	public boolean isLvlState()
	{
		return lvlState;
	}

	public void setLvlState(boolean lvlState)
	{
		this.lvlState = lvlState;
	}

	public String getCurrentDialogue()
	{
		return currentDialogue;
	}

	public void setCurrentDialogue(String currentDialogue)
	{
		this.currentDialogue = currentDialogue;
	}


	
	
}

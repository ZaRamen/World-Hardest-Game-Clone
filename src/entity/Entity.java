package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import obj.SuperObject;

public class Entity
{
	private GamePanel gP;
	private int x;
	private int y;
	private Rectangle hitBox;
	private boolean collisionOn = false;
	private String direction = " ";
	private BufferedImage image;
	private int spriteCounter;
	private int spriteNum = 1;
	private int xVel;
	private int yVel;
	private int speed;
	//regular width and height
	private int width;
	private int height;
	private int dyingCounter = 0;
	private boolean isDying;
	//hitbox width and height
	private int hWidth;
	private int hHeight;
	private String[] dialogue = new String[20];
	private String name = "";
	private int dialogueIndex = 0;
	
	public Entity(GamePanel gP, int width, int height)
	{
		this.gP = gP;
		this.width = width;
		this.height = height;
		this.hWidth = width;
		this.hHeight = height;
		
	}
	public Entity(GamePanel gP, int width, int height, int hWidth, int hHeight)
	{
		this.gP = gP;
		this.width = width;
		this.height = height;
		this.hWidth = hWidth;
		this.hHeight = hHeight;
	}
	public Entity()
	{
	}
	public void createHitBox(int x, int y)
	{
		hitBox = new Rectangle(x, y, hWidth, hHeight);
	}
	public void setHitBox(Rectangle rec)
	{
		hitBox = rec;
	}
	public void defaultPosMap(int x, int y)
	{
		setX(gP.getTileSize() * x);
		setY(gP.getTileSize() * y);
	}

	
	public Rectangle getOffSet()
	{
		return new Rectangle(x + xVel, y + yVel, width, height);
	}
	public Rectangle getOffSet(int i)
	{
		return new Rectangle(x + xVel + i, y + yVel + i, width, height);
	}
	
	public void setAction() {}
	public void speak() {}
	public void draw(Graphics2D g2)
	{
		if(isDying)
		{
			dyingAnimation(g2);
		}
		g2.drawImage(image, x, y, width, height);
	}
	
	
	
	public void setup(String packagePath, String imagePath)
	{
		BufferedImage image = null;
		UtilityTool uT = new UtilityTool();
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/" + packagePath + "/" + imagePath + ".png"));
			image = uT.scaleImage(image, width, height);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		setImage(image);
	}
	
	public void move()
	{
		if(getOffSet().getMinX() >= 0 && getOffSet().getMaxX() <= getgP().getScreenWidth())
		{
			x += xVel;
		}
		
		if(getOffSet().getMinY() >= 0 && getOffSet().getMaxY() <= getgP().getScreenHeight())
		{
			y += yVel;
		}
		
	}
	public void update()
	{
		setAction();
		
		switch(direction)
		{
			case "up": yVel = -speed; break;
			case "down": yVel = speed; break;
			case "right": xVel = speed; break;
			case "left" :  xVel = -speed; break;
		}
		move();
		
		
		createHitBox(getX(), getY());
		
	}
	public void dyingAnimation(Graphics2D g2)
	{
		dyingCounter++;
		int counter = 5;
		if(dyingCounter <= counter) {setOpacity(g2, 0.9f);}
		if(dyingCounter > counter && dyingCounter <=  counter * 2) {setOpacity(g2, 0.8f);}
		if(dyingCounter > counter * 2 && dyingCounter <= counter * 3) {setOpacity(g2, 0.7f);}
		if(dyingCounter > counter * 3 && dyingCounter <= counter * 4) {setOpacity(g2, 0.6f);}
		if(dyingCounter > counter * 4 && dyingCounter <= counter * 5) {setOpacity(g2, 0.5f);}
		if(dyingCounter > counter * 5 && dyingCounter <= counter * 6) {setOpacity(g2, 0.4f);}

		
		if(dyingCounter > 30)
		{
			isDying = false;
			dyingCounter = 0;
			setOpacity(g2, 1f);
		}
		
	}
	public String[] getDialogue()
	{
		return dialogue;
	}
	public void setOpacity(Graphics2D g2, float opacity)
	{
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	
	public void setSolidArea(Rectangle hitBox)
	{
		this.hitBox = hitBox;
	}
	public boolean isCollisionOn()
	{
		return collisionOn;
	}
	public void setCollisionOn(boolean collisionOn)
	{
		this.collisionOn = collisionOn;
	}
	public String getDirection()
	{
		return direction;
	}
	public void setDirection(String direction)
	{
		this.direction = direction;
	}
	public GamePanel getgP()
	{
		return gP;
	}
	public void setgP(GamePanel gP)
	{
		this.gP = gP;
	}
	public BufferedImage getImage()
	{
		return image;
	}
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	public int getxVel()
	{
		return xVel;
	}
	public void setxVel(int xVel)
	{
		this.xVel = xVel;
	}
	public int getyVel()
	{
		return yVel;
	}
	public void setyVel(int yVel)
	{
		this.yVel = yVel;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void resetHitBox()
	{
		hitBox = null;
	}
	public void setObstaclePos(int x, int y)
	{
		
	}
	public void setDimension()
	{
		width = 48;
		height = 48;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	public int getDyingCounter()
	{
		return dyingCounter;
	}
	public void setDyingCounter(int dyingCounter)
	{
		this.dyingCounter = dyingCounter;
	}
	public boolean isDying()
	{
		return isDying;
	}
	public void setDying(boolean isDying)
	{
		this.isDying = isDying;
	}
	public int getDialogueIndex()
	{
		return dialogueIndex;
	}
	public void setDialogueIndex(int dialogueIndex)
	{
		this.dialogueIndex = dialogueIndex;
	}
	public int getSpriteCounter()
	{
		return spriteCounter;
	}
	public void setSpriteCounter(int spriteCounter)
	{
		this.spriteCounter = spriteCounter;
	}
	public int getSpriteNum()
	{
		return spriteNum;
	}
	public void setSpriteNum(int spriteNum)
	{
		this.spriteNum = spriteNum;
	}
	public String toString()
	{
		return "I'm an Entity, a superclass";
	}
	public boolean equals(Object other)
	{
		return (Entity)other == this;	
	}
	
	
}

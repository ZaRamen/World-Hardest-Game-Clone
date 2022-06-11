package obj;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.UtilityTool;

public class SuperObject
{
	private BufferedImage image;
	private String name = "";
	private boolean collision = false;
	private int x, y;
	private Rectangle hitBox;
	private UtilityTool uT = new UtilityTool();
	
	public SuperObject(int x, int y)
	{
		this.x = x;
		this.y = y;
		createHitBox();
	}
	public SuperObject(){}
	public SuperObject(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		createHitBox(width, height);
	}
	public void setup(String imagePath)
	{
		BufferedImage image = null;
		UtilityTool uT = new UtilityTool();
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/obj/" + imagePath + ".png"));
			image = uT.scaleImage(image, 48, 48);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		setImage(image);
	}
	public void createHitBox()
	{
		hitBox = new Rectangle(x, y, 48, 48);
	}
	public void createHitBox(int w, int h)
	{
		hitBox = new Rectangle(x, y, w, h);
	}
	public void draw(Graphics2D g2)
	{
		g2.drawImage(image, getX(), getY(), 48, 48, null);
	}
	public BufferedImage getImage()
	{
		return image;
	}
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public boolean isCollision()
	{
		return collision;
	}
	public void setCollision(boolean collision)
	{
		this.collision = collision;
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
	public UtilityTool getuT()
	{
		return uT;
	}
	public void setuT(UtilityTool uT)
	{
		this.uT = uT;
	}
}

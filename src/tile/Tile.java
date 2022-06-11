package tile;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile 
{
	private BufferedImage image;
	private boolean collision = false;
	private Rectangle hitBox = null;
	
	public void createHitBox(int x, int y, int w, int h)
	{
		hitBox = new Rectangle(x, y, w, h);
	}
	public BufferedImage getImage()
	{
		return image;
	}
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	public boolean isCollision()
	{
		return collision;
	}
	public void setCollision(boolean collision)
	{
		this.collision = collision;
	}
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	public void setHitBox(Rectangle hitBox)
	{
		this.hitBox = hitBox;
	}
}

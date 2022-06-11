package entity;
import main.GamePanel;

public class Obstacle extends Entity
{
	private boolean isMoving;
	private int xLimitMin = 0;
	private int xLimitMax = 1152;
	private int yLimitMin = 0;
	private int yLimitMax = 768;
	private boolean isHorizontal;
	
	public Obstacle(GamePanel gP, boolean isMoving, boolean isHorizontal, int xLimitMin, int xLimitMax, int yLimitMin, int yLimitMax)
	{
		super(gP, 48, 48, 42, 42); //hitbox, in future you can change
		setup("obstacles", "Obstacle");
		this.isMoving = isMoving;
		this.setHorizontal(isHorizontal);
		this.xLimitMin = xLimitMin;
		this.xLimitMax = xLimitMax;
		this.yLimitMin = yLimitMin;
		this.yLimitMax = yLimitMax;
	}
	
	public Obstacle()
	{
		setDimension();
		setup("obstacles", "Obstacle");
	}

	public void setIntialDirection(String text)
	{
		setDirection(text);
	}
	
	public void setObstaclePos(int x, int y)
	{
		setX(x);
		setY(y);
		createHitBox(getX(), getY());
	}
	public void setAction()
	{
		if(isMoving) 
		{
			if(isHorizontal)
			{
				if(getX() >= getxLimitMax())
				{
					setDirection("left");
				}
				if(getX() <= getxLimitMin())
				{
					setDirection("right");
				}
			}
			else
			{
				if(getY() >= getyLimitMax())
				{
					setDirection("up");
				}
				if(getY() <= getyLimitMin())
				{
					setDirection("down");
				}
			}
		}	
	}
	

	public int getxLimitMin()
	{
		return xLimitMin;
	}

	public void setxLimitMin(int xLimitMin)
	{
		this.xLimitMin = xLimitMin;
	}

	public int getxLimitMax()
	{
		return xLimitMax;
	}

	public void setxLimitMax(int xLimitMax)
	{
		this.xLimitMax = xLimitMax;
	}

	public int getyLimitMin()
	{
		return yLimitMin;
	}

	public void setyLimitMin(int yLimitMin)
	{
		this.yLimitMin = yLimitMin;
	}

	public int getyLimitMax()
	{
		return yLimitMax;
	}

	public void setyLimitMax(int yLimitMax)
	{
		this.yLimitMax = yLimitMax;
	}
	public boolean isHorizontal()
	{
		return isHorizontal;
	}
	public void setHorizontal(boolean isHorizontal)
	{
		this.isHorizontal = isHorizontal;
	}
	
	public String toString()
	{
		return "I'm an obstacle";
	}
	public boolean equals(Object other)
	{
		return (Obstacle)other == this;	
	}
		
}


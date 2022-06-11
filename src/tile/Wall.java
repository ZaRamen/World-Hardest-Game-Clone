package tile;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Wall extends Rectangle
{
	public Wall(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = 48;
		this.height = 48;
	}
	
	
}

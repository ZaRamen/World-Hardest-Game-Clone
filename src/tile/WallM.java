package tile;

import main.GamePanel;

public class WallM 
{
	private Wall[] wall = new Wall[384];
	private GamePanel gP;
	
	public WallM(GamePanel gP)
	{
		this.gP = gP;
	}
	
	public void setWalls()
	{
		int[][] map = gP.getTileM().getMap();
		int col = 0;
		int row = 0;
		int loop = 0;
		int x = 0;
		int y = 0;
		
		while(col < gP.getScreenCol() && row < gP.getScreenRow())
		{
			while(col < gP.getScreenCol())
			{
				if(map[col][row] == 0)
				{
					wall[loop] = new Wall(x, y);
				}
				x +=  gP.getTileSize();
				col++;
				loop++;
			}
			if(col == gP.getScreenCol())
			{
				col = 0;
				x = 0;
				row++;
				y+= gP.getTileSize();
			}
		}
		
		
		
	}
	public void resetWalls()
	{
		for(int i = 0; i < wall.length; i++)
		{
			wall[i] = null;
		}
	}
	public Wall[] getWalls()
	{
		return wall;
	}
}

package tile;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileM
{
	private GamePanel gP;
	private Tile[] tile;
	//2d array of obj references
	//intializer list
	private Tile[][] tile2 = {{new Tile()}, {new Tile()}};
	private int mapTile[][];
	WallM wallM = new WallM(gP);
	
	public TileM(GamePanel gP)
	{
		this.gP = gP;
		tile = new Tile[10];
		mapTile = new int[gP.getScreenCol()][gP.getScreenRow()];
		getTileImage();
	}
	public TileM()
	{
		
	}
	
	public void getTileImage()
	{
			setup(0, "backgroundTile", true);
			setup(1, "startTile", false);
			setup(2, "chestnutTile", false);
			setup(3, "whitePinkTile", false);
			setup(4, "winTile", false);
	}
	public void setup(int index, String imagePath, boolean collision)
	{
		UtilityTool uT = new UtilityTool();
		try
		{
			tile[index] = new Tile();
			tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")));	
			tile[index].setImage(uT.scaleImage(tile[index].getImage(), gP.getTileSize(), gP.getTileSize()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap()
	{
		switch(gP.getMapLevel())
		{
			case 1: mapSetter("Map1"); break;
			case 2: mapSetter("Map2"); break;
			case 3: mapSetter("Map3"); break;
			case 4: mapSetter("Map4"); break;
			case 5: mapSetter("Map5"); break;
		}
		
		
	}
	
	public void mapSetter(String mapName)
	{
		try 
		{
			InputStream is = getClass().getResourceAsStream("/map/" + mapName + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			//nested iteration
			while(col < gP.getScreenCol() && row < gP.getScreenRow())
			{
				String line = br.readLine();
				
				while(col < gP.getScreenCol())
				{
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTile[col][row] = num;
					col++;
					
				}
				if(col == gP.getScreenCol())
				{
					col = 0;
					row++;
				}
			}
			
			br.close();
		}
		catch(Exception e)
		{
			System.out.println("Error Map");
		}
	}
	
	
	public void draw(Graphics2D g2)
	{
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		
		while(col < gP.getScreenCol() && row < gP.getScreenRow())
		{
			
			int tileNum = mapTile[col][row];
			
			g2.drawImage(tile[tileNum].getImage(), x, y, null);
			
			col++;
			x +=  gP.getTileSize();
			
			if(col == gP.getScreenCol())
			{
				col = 0;
				x = 0;
				row++;
				y+= gP.getTileSize();
			}
		}
		
	}
	
	public int getMapTile(int col, int row) {return mapTile[col][row];}
	public Tile getTile(int n) {return tile[n];}
	public int[][] getMap() {return mapTile;}
	
	
}

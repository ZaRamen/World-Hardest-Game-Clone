package main;


import java.awt.Rectangle;
import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import obj.SuperObject;

import tile.Wall;
import tile.WallM;

public class CollisionChecker
{
	private GamePanel gP;
	private WallM wallM = new WallM(gP);
	private ArrayList<Rectangle> winAreas = new ArrayList<>();
	private ArrayList<Rectangle> saveStates = new ArrayList<>();
	private Rectangle[] cells = new Rectangle[10];
	private Rectangle[] door = new Rectangle[10];
	public CollisionChecker(GamePanel gP, WallM wallM)
	{
		this.gP = gP;
		this.wallM = wallM;
		//add where the win areas are
		winAreas.add(new Rectangle(964 + 24, 288, 192 - 24, 144));
		winAreas.add(new Rectangle(21 * 48 + 24, 12 * 48, 2 * 48 - 24, 2 * 48));
		winAreas.add(new Rectangle(11 * 48 + 12, 12 * 48 + 24, 2 * 48 - 24, 2 * 48));
		winAreas.add(new Rectangle(19 * 48, 13 * 48 + 24, 3 * 48, 3 * 48));
		winAreas.add(new Rectangle(0, 0, 0, 0));
		
		saveStates.add(null);
		saveStates.add(null);
		saveStates.add(null);
		saveStates.add(new Rectangle(13 * 48, 14 * 48, 3 * 48 - 24, 2 * 48));
		saveStates.add(new Rectangle(18 * 48, 6 * 48, 2 * 48, 4 * 48));
		
		cells[0] = new Rectangle(48 * 21, 48 * 12, 48, 48);
		cells[1] = new Rectangle(48 * 21, 48 * 14, 48, 48);
		door[0] = new Rectangle(48 * 21, 48 * 13, 48, 48);
	}
	
	//checks if the player intersects a wall 
	//gets the offset which means the player doesn't actually intersects but merely touches
	//allows the player to still move because if it's instead getHitBox(), the player gets stuck
	//passes an obj of a superclass but takes a subclass of an the super class
	public void checkTile(Entity entity)
	{
		for(Wall w : wallM.getWalls())
		{
			if(w != null && entity.getOffSet().intersects(w))
			{
				entity.setxVel(0);
				entity.setyVel(0);
				break;
			}
		}
		if(gP.getMapLevel() == gP.getFinalLevel())
		{
			for(Rectangle c: cells)
			{
				if(c != null && entity.getOffSet().intersects(c))
				{
					entity.setxVel(0);
					entity.setyVel(0);
					break;
				}
			}
		}
		
	}
	public void checkDoor(Player player)
	{
		if(gP.getMapLevel() == gP.getFinalLevel())
		{
			for(int i = 0; i < door.length; i++)
			{
				if(door[i] != null && player.getOffSet().intersects(door[i]))
				{
					if(player.getKey() < 1)
					{	
						player.setxVel(0);
						player.setyVel(0);
						break;
					}	
					else
					{
						door[i] = null;
					}
				}
			}
			
		}
	}
	public void checkIfSaveState(Player player)
	{
		if(saveStates.get(gP.getMapLevel() - 1) != null 
		&& player.getHitBox().intersects(saveStates.get(gP.getMapLevel() - 1)))
		{
			player.setSaveState(true);
		}
	}
	public void checkIfWin(Player player)
	{
		if(player.getHitBox().intersects(winAreas.get(gP.getMapLevel() - 1)))
		{
			gP.setMapLevel(gP.getMapLevel() + 1);
			gP.setGameState(gP.getStoryState());
			player.setSaveState(false);
		}
	}
	//checks if player intersects an obstacle otherwise return 999
	public int checkObstacle(Player player, Entity[] target)
	{
		int index = 999;
		for(int i = 0; i < target.length; i++)
		{
			if(target[i] != null && target[i].getHitBox() != null)
			{
				if(player.getHitBox().intersects(target[i].getHitBox()))
				{
					index = i;
				}
			}
			
		}
		return index;
	}
	public int checkSkull(Entity entity, Entity[] target)
	{
		int index = 999;
		for(int i = 0; i < target.length; i++)
		{
			if(target[i] != null && target[i].getHitBox() != null)
			{
				if(entity.getOffSet().intersects(target[i].getHitBox()))
				{
					entity.setxVel(0);
					entity.setyVel(0);
					index = i;
					break;
				}
				if(entity.getOffSet(-2).intersects(target[i].getHitBox()) 
				|| entity.getOffSet(2).intersects(target[i].getHitBox()))
				{
					index = i;
					break;
				}
			}
		}
		return index;
	}
	//checks if player intersects an object otherwise return 999
	public int checkObj(Entity entity, SuperObject[] obj)
	{
		int index = 999;
		
		for(int i = 0; i < gP.getObject().length; i++)
		{
			if(gP.getObject()[i] != null && gP.getObject()[i].getHitBox() != null)
			{
				if(entity.getHitBox().intersects(gP.getObject()[i].getHitBox()))
				{
					index = i;
				}
			}
		}
		
		return index;
		
	}
	
	public void print(SuperObject[] obj)
	{
		for(SuperObject o: obj)
		{
			System.out.println(o);
		}
	}
	public ArrayList<Rectangle> getWinAreas()
	{
		return winAreas;
	}
	public ArrayList<Rectangle> getSaveStates()
	{
		return saveStates;
	}
}

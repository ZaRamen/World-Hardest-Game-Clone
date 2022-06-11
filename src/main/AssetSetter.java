package main;


import entity.Entity;
import entity.Obstacle;
import entity.Skull;
import obj.OBJ_Heart;
import obj.OBJ_Key;
import obj.OBJ_PureNail;
import obj.OBJ_Cell;
import obj.OBJ_Diamond;
import obj.OBJ_Dio;
import obj.OBJ_Door;
import obj.SuperObject;

public class AssetSetter 
{
	private GamePanel gP;
	private Entity[] obs;
	private SuperObject[] obj;
	private int dimension = 48;
	private Entity[] skull;
	public AssetSetter(GamePanel gP, Entity[] obs, SuperObject[] obj, Entity[] skull)
	{
		this.gP = gP;
		this.obs = obs;
		this.obj = obj;
		this.skull = skull;
	}
	
	public void setObstacle()
	{
		switch(gP.getMapLevel())
		{	
			case 1: obstacleMap1(); break;
			case 2: obstacleMap2(); break;
			case 3: obstacleMap3(); break;
			case 4: obstacleMap4(); break;
			case 5: obstacleMap5(); break;
		}
	}
	
	public void setObject()
	{
		switch(gP.getMapLevel()) 
		{
			case 1: objectMap1(); break;
			case 2: objectMap2(); break;
			case 3: objectMap3(); break;
			case 4: objectMap4(); break;
			case 5: objectMap5(); break;
		}
	}
	public void setSkull()
	{
		switch(gP.getMapLevel()) 
		{
			case 1: skullMap1(); break;
			case 2: skullMap2(); break;
			case 3: skullMap3(); break;
			case 4: skullMap4(); break;
			case 5: skullMap5(); break;
		}
	}
	
	private void skullMap1()
	{
		skull[0] = new Skull(gP, dimension * 5 , dimension * 8);
		skull[0].getDialogue()[0] = "\n\nDon't touch the red-eyed skulls...";
	}
	private void skullMap2()
	{
		skull[0] = new Skull(gP, dimension * 11 , dimension * 9);
		skull[0].getDialogue()[0] = "\n\nYour really gonna waste time for a heart?";
	}
	private void skullMap3()
	{
		skull[0] = new Skull(gP, dimension * 7 , dimension * 13);
		skull[0].getDialogue()[0] = "\n\nEven ZA Warudo couldn't help me.";
		
		
	}
	private void skullMap4()
	{
		skull[0] = new Skull(gP, dimension * 7 , dimension * 12);
		skull[0].getDialogue()[0] = "\n\nTop Ten anime betrayals";
		
		skull[1] = new Skull(gP, dimension * 15 , dimension * 14);
		skull[1].getDialogue()[0] = "\n\nI knew Sir Rial was a Liar.";
		
		skull[2] = new Skull(gP, dimension * 13 , dimension * 2);
		skull[2].getDialogue()[0] = "\nUhh.. my nail.. the only treasure I had."
				+ "\nSharper than anything in the world.";
	}
	private void skullMap5()
	{
		skull[0] = new Skull(gP, dimension * 3, dimension * 6);
		
		skull[0].getDialogue()[0] ="Next to the skeleton remains is a journal. You begin to read it:\n"
		+ "Mar 24, 2022\r\n"
		+ "It was a typical day for me with my friends Sir Timmons and Drue.\n"
		+ "We happened to pass by Bob who was a builder and he told us about a ";
		
		skull[0].getDialogue()[1] = "\ndiamond located in a pyramid in Egypt."
				+ "\nWe were all hyped up to go obtain the diamond so\n"
				+ "we decided in 2 days we would depart.\n";
		
		skull[0].getDialogue()[2] = "Mar 26, 2022\n"
		+ "We reached the pyramid and realized it was \nmore dangerous than we expected.\n"
		+ "It seems each trial is guarded by skulls.\n"
		+ "Even so, we preserved and were able to make up to the 4th Trial.";
		
		skull[0].getDialogue()[3] = "\nHowever, it was way more dangerous than the previous trials.\n"
		+ "Sir Timmons and Drue said they wanted to leave but I had a dream.\n"
		+ "A dream to conquer this pyramid and become rich.";
		
		skull[0].getDialogue()[4] = "\nThey were forcing me to leave but when they looked the other way,\n"
		+ "I pushed them into the skulls.\n"
		+ "They perished but I was able to progress with their sacrifice";
		
		skull[0].getDialogue()[5] = "Only the 5th Trial left..........\n"
		+ "\n\n\nNo Cost Too Great";
	}
	
	public void objectMap1()
	{
	}
	public void objectMap2()
	{
		obj[0] = new OBJ_Heart(dimension * 12, dimension * 9);
	}
	public void objectMap3()
	{
		//assigns an object of a subclass to a superclass reference
		//obj array is a arr of SuperObjects
		obj[0] = new OBJ_Dio(dimension * 6, dimension * 13);
	}
	public void objectMap4()
	{
		obj[0] = new OBJ_Heart(dimension * 14, dimension * 7);
		obj[1] = new OBJ_PureNail(dimension * 13, dimension);
	}
	public void objectMap5()
	{
		obj[0] = new OBJ_Diamond(dimension * 22, dimension * 13);
		obj[1] = new OBJ_Key(dimension * 10, dimension * 8);
		obj[2] = new OBJ_Door(dimension * 21, dimension * 13);
		obj[3] = new OBJ_Cell(dimension * 21, dimension * 12);
		obj[4] = new OBJ_Cell(dimension * 21, dimension * 14);
	}
	
	public void reset()
	{
		for(int i = 0; i < obj.length; i++)
		{
			if(obj[i] != null)
			{
				obj[i] = null;
			}
			
		}
		for(int i = 0; i < obs.length; i++)
		{
			if(obs[i] != null)
			{
				obs[i] = null;
			}
			
		}
		for(int i = 0; i < skull.length; i++)
		{
			if(skull[i] != null)
			{
				skull[i] = null;
			}
		}
	}
	public void obstacleMap1()
	{
		//gP, isMoving, isHorizontal, xLimitMin, xLimitMax, yLimitMin, yLimitMax
		obs[0] = new Obstacle(gP, true, false, 0, 0, 6 * dimension, 8 * dimension);
		obs[0].setObstaclePos(dimension * 8, dimension * 7);
		obs[0].setDirection("up");
		obs[0].setSpeed(2);
		
		obs[1] = new Obstacle(gP, true, false, 0, 0,  6 * dimension, 8 * dimension);
		obs[1].setObstaclePos(dimension * 11, dimension * 7);
		obs[1].setDirection("down");
		obs[1].setSpeed(2);
		
		obs[2] = new Obstacle(gP, true, false, 0, 0, 6 * dimension, 8 * dimension);
		obs[2].setObstaclePos(dimension * 14, dimension * 7);
		obs[2].setDirection("up");
		obs[2].setSpeed(2);
	}
	public void print(SuperObject[] a)
	{
		for(SuperObject e: a)
		{
			System.out.println(e);
		}
	}
	
	public void obstacleMap2()
	{
		
		obs[1] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[1].setObstaclePos(dimension * 4, dimension * 3);
		
		obs[2] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[2].setObstaclePos(dimension * 6, dimension);
	
		obs[3] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[3].setObstaclePos(dimension * 6, dimension * 2);
		
		obs[4] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[4].setObstaclePos(dimension * 8, dimension * 2);
		
		
		obs[5] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[5].setObstaclePos(dimension * 8, dimension * 3);
		
		
		obs[6] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[6].setObstaclePos(dimension * 10, dimension * 2);
		
		obs[7] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[7].setObstaclePos(dimension * 10, dimension * 3);
		
		obs[8] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[8].setObstaclePos(dimension * 11, dimension * 3);
		
		obs[9] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[9].setObstaclePos(dimension * 12, dimension * 3);
		
		obs[10] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[10].setObstaclePos(dimension * 13, dimension * 3);
		
		obs[11] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[11].setObstaclePos(dimension * 13, dimension * 4);
		
		obs[12] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[12].setObstaclePos(dimension * 13, dimension * 4);
		
		obs[13] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[13].setObstaclePos(dimension * 13, dimension * 5);
		
		obs[14] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[14].setObstaclePos(dimension * 13, dimension * 7);
		
		obs[15] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[15].setObstaclePos(dimension * 9, dimension * 6);
		
		obs[16] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[16].setObstaclePos(dimension * 10, dimension * 6);
		
		obs[17] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[17].setObstaclePos(dimension * 11, dimension * 6);
		
		obs[18] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[18].setObstaclePos(dimension * 13, dimension * 8);
		
		obs[19] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[19].setObstaclePos(dimension * 13, dimension * 9);
		
		obs[20] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[20].setObstaclePos(dimension * 13, dimension * 10);
		
		obs[21] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[21].setObstaclePos(dimension * 13, dimension * 11);
		
		obs[22] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[22].setObstaclePos(dimension * 10, dimension * 8);
		
		obs[23] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[23].setObstaclePos(dimension * 11, dimension * 8);
		
		obs[24] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[24].setObstaclePos(dimension * 12, dimension * 8);
		
		obs[25] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[25].setObstaclePos(dimension * 10, dimension * 9);
		
		obs[26] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[26].setObstaclePos(dimension * 13, dimension * 10);
		
		obs[27] = new Obstacle(gP, true, false, 0, 0, dimension,  dimension * 12);
		obs[27].setObstaclePos(dimension * 14, dimension * 6);
		obs[27].setDirection("up");
		obs[27].setSpeed(2);
		
		obs[28] = new Obstacle(gP, true, true, dimension * 9, dimension * 20, 0, 0);
		obs[28].setObstaclePos(dimension * 14, dimension * 12);
		obs[28].setDirection("left");
		obs[28].setSpeed(5);
		
		obs[29] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[29].setObstaclePos(dimension * 12, dimension * 11);
		
		obs[30] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[30].setObstaclePos(dimension * 10, dimension * 11);
		
		obs[31] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[31].setObstaclePos(dimension * 10, dimension * 10);
	}
	
	public void obstacleMap3()
	{
		obs[0] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[0].setObstaclePos(dimension, dimension * 4);
		obs[0].setSpeed(18);
	
		
		obs[1] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[1].setObstaclePos(dimension, dimension * 6);
		obs[1].setSpeed(18);
		
		
		obs[2] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[2].setObstaclePos(dimension, dimension * 8);
		obs[2].setSpeed(18);
	
		
		obs[3] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[3].setObstaclePos(dimension, dimension * 10);
		obs[3].setSpeed(18);
		
		
		obs[4] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[4].setObstaclePos(dimension * 22, dimension * 7);
		obs[4].setSpeed(12);
		
		obs[5] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[5].setObstaclePos(dimension * 22, dimension * 5);
		obs[5].setSpeed(12);
		
		obs[6] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[6].setObstaclePos(dimension * 22, dimension * 9);
		obs[6].setSpeed(12);
		
		obs[7] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[7].setObstaclePos(dimension * 22, dimension * 11);
		obs[7].setSpeed(12);
		
		obs[8] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[8].setObstaclePos(dimension * 22, dimension * 3);
		obs[8].setSpeed(12);
		
	}
	
	public void obstacleMap4()
	{
		obs[0] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[0].setObstaclePos(dimension * 2, dimension * 9);
		
		obs[1] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[1].setObstaclePos(dimension * 2, dimension * 10);
		
		obs[2] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[2].setObstaclePos(dimension * 2, dimension * 11);
		
		obs[3] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[3].setObstaclePos(dimension * 1, dimension * 6);
		
		obs[4] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[4].setObstaclePos(dimension * 1, dimension * 7);
		
		obs[5] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[5].setObstaclePos(dimension * 3, dimension * 6);
		
		obs[6] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[6].setObstaclePos(dimension * 3, dimension * 7);
		
		obs[7] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[7].setObstaclePos(dimension * 2, dimension * 2);
		
		obs[8] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[8].setObstaclePos(dimension * 2, dimension * 3);
		
		obs[9] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[9].setObstaclePos(dimension * 2, dimension * 4);
		
		
		obs[10] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[10].setObstaclePos(dimension * 4, dimension * 1);
		
		obs[11] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[11].setObstaclePos(dimension * 6, dimension * 2);
		
		obs[12] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[12].setObstaclePos(dimension * 7, dimension * 3);
		
		obs[13] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[13].setObstaclePos(dimension * 7, dimension * 5);
		
		obs[14] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[14].setObstaclePos(dimension * 7, dimension * 7);
		
		obs[15] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[15].setObstaclePos(dimension * 7, dimension * 9);
		
		obs[16] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[16].setObstaclePos(dimension * 7, dimension * 11);
		
		obs[17] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[17].setObstaclePos(dimension * 7, dimension * 3);
		
		obs[18] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[18].setObstaclePos(dimension * 9, dimension * 4);
		
		obs[19] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[19].setObstaclePos(dimension * 9, dimension * 6);
		
		obs[20] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[20].setObstaclePos(dimension * 9, dimension * 8);
		
		obs[21] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[21].setObstaclePos(dimension * 9, dimension * 10);
		
		obs[22] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[22].setObstaclePos(dimension * 9, dimension * 12);
		
		obs[23] = new Obstacle(gP, true, false, 0, 0, 48, 48 * 14);
		obs[23].setObstaclePos(dimension * 8, dimension * 14);
		obs[23].setSpeed(6);
		obs[23].setDirection("up");
		
		obs[25] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[25].setObstaclePos(dimension * 13, dimension * 4);
		
		obs[26] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[26].setObstaclePos(dimension * 13, dimension * 6);

		obs[27] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[27].setObstaclePos(dimension * 13, dimension * 8);
		
		obs[28] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[28].setObstaclePos(dimension * 13, dimension * 10);
		
		obs[29] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[29].setObstaclePos(dimension * 13, dimension * 12);
		
		obs[30] = new Obstacle(gP, true, false, 0, 0, 48, 48 * 14);
		obs[30].setObstaclePos(dimension * 14, dimension * 1);
		obs[30].setDirection("down");
		obs[30].setSpeed(6);
		
		obs[31] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[31].setObstaclePos(dimension * 15, dimension * 3);
		
		obs[32] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[32].setObstaclePos(dimension * 15, dimension * 5);
		
		obs[33] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[33].setObstaclePos(dimension * 15, dimension * 7);
		
		obs[34] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[34].setObstaclePos(dimension * 15, dimension * 9);
		
		obs[35] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[35].setObstaclePos(dimension * 15, dimension * 11);
		
		obs[36] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[36].setObstaclePos(dimension * 16, dimension * 2);
		
		obs[37] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[37].setObstaclePos(dimension * 18, dimension * 1);
		
		obs[38] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[38].setObstaclePos(dimension * 20, dimension * 2);
		
		obs[39] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[39].setObstaclePos(dimension * 20, dimension * 3);
		
		obs[40] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[40].setObstaclePos(dimension * 20, dimension * 4);
		
		obs[41] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[41].setObstaclePos(dimension * 19, dimension * 6);
			
		obs[42] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[42].setObstaclePos(dimension * 19, dimension * 7);
		
		obs[43] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[43].setObstaclePos(dimension * 21, dimension * 6);
		
		obs[44] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[44].setObstaclePos(dimension * 21, dimension * 7);
		
		obs[45] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[45].setObstaclePos(dimension * 20, dimension * 9);
		
		obs[46] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[46].setObstaclePos(dimension * 20, dimension * 10);
		
		obs[47] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[47].setObstaclePos(dimension * 20, dimension * 11);
		
	}
	public void obstacleMap5()
	{
		obs[0] = new Obstacle(gP, true, true, dimension, dimension * 22, 0, 0);
		obs[0].setObstaclePos(dimension * 1, dimension * 3);
		obs[0].setDirection("right");
		obs[0].setSpeed(4);
		
		obs[1] = new Obstacle(gP, true, true, dimension, dimension * 20, 0, 0);
		obs[1].setObstaclePos(dimension * 20, dimension * 12);
		obs[1].setDirection("left");
		obs[1].setSpeed(4);
		
		obs[2] = new Obstacle(gP, true, false, 0, 0, dimension * 6, dimension * 14);
		obs[2].setObstaclePos(dimension * 1, dimension * 6);
		obs[2].setDirection("down");
		obs[2].setSpeed(4);
		
		obs[3] = new Obstacle(gP, true, false, 0, 0, dimension, dimension * 9);
		obs[3].setObstaclePos(dimension * 22, dimension * 9);
		obs[3].setDirection("up");
		obs[3].setSpeed(4);
		
		obs[4] = new Obstacle(gP, true, false, 0, 0, dimension * 6, dimension * 9);
		obs[4].setObstaclePos(dimension * 16, dimension * 6);
		obs[4].setDirection("down");
		obs[4].setSpeed(4);
		
		obs[5] = new Obstacle(gP, true, true, dimension * 11, dimension * 15, 0, 0);
		obs[5].setObstaclePos(dimension * 15, dimension * 9);
		obs[5].setDirection("left");
		obs[5].setSpeed(4);	
		
		obs[6] = new Obstacle(gP, true, true, dimension * 9, dimension * 14, 0, 0);
		obs[6].setObstaclePos(dimension * 14, dimension * 7);
		obs[6].setDirection("left");
		obs[6].setSpeed(4);
		
		obs[7] = new Obstacle(gP, true, false, 0, 0, dimension * 5, dimension * 10);
		obs[7].setObstaclePos(dimension * 13, dimension * 10);
		obs[7].setDirection("up");
		obs[7].setSpeed(4);
		
		obs[8] = new Obstacle(gP, true, true, dimension * 10, dimension * 15, 0, 0);
		obs[8].setObstaclePos(dimension * 12, dimension * 8);
		obs[8].setDirection("right");
		obs[8].setSpeed(6);
		
		obs[9] = new Obstacle(gP, true, false, 0, 0, dimension * 6, dimension * 8);
		obs[9].setObstaclePos(dimension * 11, dimension * 6);
		obs[9].setDirection("down");
		obs[9].setSpeed(4);
		
		obs[10] = new Obstacle(gP, true, false, 0, 0, dimension * 6, dimension * 9);
		obs[10].setObstaclePos(dimension * 10, dimension * 9);
		obs[10].setDirection("up");
		obs[10].setSpeed(4);
		
		obs[11] = new Obstacle(gP, true, false, 0, 0, dimension * 5, dimension * 9);
		obs[11].setObstaclePos(dimension * 9, dimension * 5);
		obs[11].setDirection("down");
		obs[11].setSpeed(4);
		
		obs[12] = new Obstacle(gP, true, false, 0, 0, dimension * 7, dimension * 9);
		obs[12].setObstaclePos(dimension * 8, dimension * 7);
		obs[12].setDirection("down");
		obs[12].setSpeed(4);
		
		obs[13] = new Obstacle(gP, true, false, 0, 0, dimension * 5, dimension * 10);
		obs[13].setObstaclePos(dimension * 7, dimension * 10);
		obs[13].setDirection("up");
		obs[13].setSpeed(4);
		
		obs[14] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[14].setObstaclePos(dimension * 19, dimension * 13);
		
		obs[15] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[15].setObstaclePos(dimension * 18, dimension * 14);
		
		obs[16] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[16].setObstaclePos(dimension * 17, dimension * 12);
		
		obs[17] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[17].setObstaclePos(dimension * 16, dimension * 13);
	
		obs[18] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[18].setObstaclePos(dimension * 14, dimension * 13);
		
		obs[19] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[19].setObstaclePos(dimension * 13, dimension * 14);
		
		obs[20] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[20].setObstaclePos(dimension * 12, dimension * 12);
		
		obs[21] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[21].setObstaclePos(dimension * 11, dimension * 13);
	
		obs[22] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[22].setObstaclePos(dimension * 9, dimension * 13);
		
		obs[23] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[23].setObstaclePos(dimension * 8, dimension * 14);
		
		obs[24] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[24].setObstaclePos(dimension * 7, dimension * 12);
		
		obs[25] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[25].setObstaclePos(dimension * 6, dimension * 13);
		
		obs[26] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[26].setObstaclePos(dimension * 4, dimension * 13);
		
		obs[27] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[27].setObstaclePos(dimension * 3, dimension * 14);
		
		obs[28] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[28].setObstaclePos(dimension * 2, dimension * 12);
		
		obs[29] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[29].setObstaclePos(dimension * 3, dimension * 11);
		
		obs[30] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[30].setObstaclePos(dimension * 1, dimension * 10);
		
		obs[31] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[31].setObstaclePos(dimension * 6, dimension * 6);
		
		obs[32] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[32].setObstaclePos(dimension * 6, dimension * 9);
		
		obs[33] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[33].setObstaclePos(dimension * 4, dimension * 2);
		
		obs[34] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[34].setObstaclePos(dimension * 5, dimension * 1);
		
		obs[35] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[35].setObstaclePos(dimension * 6, dimension * 3);
		
		obs[36] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[36].setObstaclePos(dimension * 7, dimension * 2);
		
		obs[37] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[37].setObstaclePos(dimension * 9, dimension * 2);
		
		obs[38] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[38].setObstaclePos(dimension * 10, dimension * 1);
		
		obs[39] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[39].setObstaclePos(dimension * 11, dimension * 3);
		
		obs[40] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[40].setObstaclePos(dimension * 12, dimension * 2);
		
		obs[41] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[41].setObstaclePos(dimension * 15, dimension * 1);
		
		obs[42] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[42].setObstaclePos(dimension * 16, dimension * 3);
		
		obs[43] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[43].setObstaclePos(dimension * 17, dimension * 2);
		
		obs[44] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[44].setObstaclePos(dimension * 19, dimension * 2);
		
		obs[45] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[45].setObstaclePos(dimension * 20, dimension * 1);
		
		obs[46] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[46].setObstaclePos(dimension * 20, dimension * 4);
		
		obs[47] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[47].setObstaclePos(dimension * 21, dimension * 3);
		
		obs[48] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[48].setObstaclePos(dimension * 22, dimension * 5);
		
		obs[49] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[49].setObstaclePos(dimension * 17, dimension * 6);
		
		obs[50] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[50].setObstaclePos(dimension * 17, dimension * 9);
		
		obs[51] = new Obstacle(gP, false, false, 0, 0, 0, 0);
		obs[51].setObstaclePos(dimension * 14, dimension * 2);
	}
}

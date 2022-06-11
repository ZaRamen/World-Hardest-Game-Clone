package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import obj.SuperObject;
import tile.TileM;
import tile.WallM;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, ActionListener
{

	//tile size and screen
	private int originalTileSize = 16;
	private int scale = 3;
	
	private int tileSize = originalTileSize * scale;
	private int maxScreenCol = 24;
	private int maxScreenRow = 16;
	
	private int screenWidth = tileSize * maxScreenCol;
	private int screenHeight = tileSize * maxScreenRow;
	
	//System
	private Sound sound = new Sound();
	private Thread gameThread;
	private int mapLevel = 1;
	private int finalLevel = 5;
	private Player player = new Player(this);
	private int fps = 60;
	private KeyHandler keyH = new KeyHandler(this, player);
	
	//Game
	private TileM tileM = new TileM(this);
	private WallM wallM = new WallM(this);
	private Entity[] obstacle = new Entity[100];
	private SuperObject[] obj = new SuperObject[10];
	private Entity[] skull = new Entity[10];
	private CollisionChecker cChecker = new CollisionChecker(this, wallM);
	private UI ui = new UI(this);
	private AssetSetter aSetter = new AssetSetter(this, obstacle, obj, skull);
	private boolean isTalking;
	
	private int gameState;
	private final int titleState = 0;
	private final int playState = 1;
	private final int helpState = 2;
	private final int pauseState = 3;
	private final int dialogueState = 4;
	private final int storyState = 5;
	private double playTime;
	private CustomButton startButton = new CustomButton("Start", 420, 550, 300, 75);
	private CustomButton easyButton = new CustomButton("Easy", 200, 600, 300, 75);
	private CustomButton hardButton = new CustomButton("Hard", 650, 600, 300, 75);
	
	public GamePanel()
	{
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setLayout(null);
		this.setBackground(Color.black);
			
		this.addKeyListener(keyH);
		this.setFocusable(true);
		

		startButton.addActionListener(this);
		startButton.setActionCommand("Start");
		
		easyButton.addActionListener(this);
		easyButton.setActionCommand("Easy");
		
		hardButton.addActionListener(this);
		hardButton.setActionCommand("Hard");
		
		
		this.add(startButton);
		this.add(easyButton);
		this.add(hardButton);
		
		this.setVisible(true);	
		
		gameState = titleState;

		
//		playMusic(0);
	}
	
	public void setUpGame()
	{   
		tileM.loadMap();
		player.setDefaultPos();
		player.setDefaultLives();
		aSetter.reset();
		wallM.resetWalls();
		aSetter.setObject();
		aSetter.setObstacle();
		aSetter.setSkull();
		wallM.setWalls();
		
	}
	public void startGameThread() 
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run()
	{
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			
			
			if(delta >= 1)
			{
				update();
				repaint();
				requestFocus();
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000)
			{
//				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update()
	{
		if(gameState == playState)
		{
			player.update();
			//obstacles don't move during dialogue(enter) and when using time stop
			if(!player.isStopTime() && !isTalking)
			{
				for(int i = 0; i < obstacle.length; i++)
				{
					if(obstacle[i] != null)
					{
						obstacle[i].update();
					}
				}
			}
			
		}
		if(gameState == pauseState || gameState == dialogueState)
		{

		}
		if(gameState == titleState)
		{
			if(keyH.isUpPressed() && keyH.isLeftPressed() && keyH.isRightPressed())
			{
				player.setPureNail(100);
				player.setStopTimeUses(100);
				player.setLives(100);
			}
		}
		
	}
	
	public void paintComponent(Graphics	g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		long drawStart = 0;
		drawStart = System.nanoTime();
		if(gameState == titleState)
		{
			ui.draw(g2);
			startButton.draw(g2);
		}
		else if(gameState == helpState)
		{
			ui.draw(g2);
		}
		else if(gameState == storyState)
		{
			ui.draw(g2);
			//can't have buttons showing in storyScreens of level transitions which
			//are shown when storyscreen is 1 or 2
			if(ui.getStoryScreens() != 1 && ui.getStoryScreens() != 2)
			{
				easyButton.draw(g2);
				hardButton.draw(g2);
			}
			
		}
		else
		{
			//draw map
			tileM.draw(g2);
			
			//player draw
			player.draw(g2);
			//obj 
			for(int i = 0; i < obj.length; i++)
			{
				if(obj[i] != null)
				{
					obj[i].draw(g2);
				}
			}
			
			//obstacle draw
			for(int i = 0; i < obstacle.length; i++)
			{
				if(obstacle[i] != null)
				{
					obstacle[i].draw(g2);
				}
			}
			for(int i = 0; i < skull.length; i++)
			{
				if(skull[i] != null)
				{
					skull[i].draw(g2);
				}
			}

			
			

			//ui draw
			ui.draw(g2);
		}
		
		//debug comment out in final
//		if(keyH.isCheckDrawTime() && gameState == playState)
//		{
//			long drawEnd = System.nanoTime();
//			long passed = drawEnd - drawStart;
//			g2.setColor(Color.white);
//			g2.drawString("Draw Time " + passed, 400, 40);
//			
//			g2.setColor(Color.CYAN);
//			g2.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
//			
//			if(mapLevel <= finalLevel 
//			&& cChecker.getWinAreas().get(mapLevel - 1) != null 
//			&& cChecker.getSaveStates().get(mapLevel - 1) != null)
//			{
//				Rectangle rec = cChecker.getWinAreas().get(mapLevel - 1);
//				g2.fillRect((int)rec.getX(), (int)rec.getY(), (int)rec.getWidth(), (int)rec.getHeight());
//				
//				Rectangle rec2 = cChecker.getSaveStates().get(mapLevel - 1);
//				g2.fillRect((int)rec2.getX(), (int)rec2.getY(), (int)rec2.getWidth(), (int)rec2.getHeight());
//			}
//			
//		}
//		
		g2.dispose();
	}
	
	public int getTileSize(){return tileSize;}
	public int getScreenCol() {return maxScreenCol;}
	public int getScreenRow() {return maxScreenRow;}
	public CollisionChecker getCollisionChecker() {return cChecker;}
	public TileM getTileM() {return tileM;}
	public int getScreenWidth(){return screenWidth;}
	public int getScreenHeight(){return screenHeight;}
	public Entity[] getObstacle(){return obstacle;}
	public Player getPlayer() {return player;}
	public SuperObject[] getObject() {return obj;}
	public int getMapLevel(){return mapLevel;}
	public int getGameState(){return gameState;}
	public int getPlayState(){return playState;}
	public int getPauseState(){return pauseState;}
	public double getPlayTime(){return playTime;}
	public UI getUserInterface() {return ui;}
	public Thread getGameThread() {return gameThread;}
	public int getTitleState(){return titleState;}
	public void setSound(Sound sound){this.sound = sound;}
	public void setFinalLevel(int finalLevel){this.finalLevel = finalLevel;}
	
	public void setGameThread(Thread gameThread) {this.gameThread = gameThread;}
	public void setObstacle(Entity obstacle[]){this.obstacle = obstacle;}
	public void setObject(SuperObject[] obj){this.obj = obj;}
	public void setMapLevel(int mapLevel){this.mapLevel = mapLevel;}
	public void setGameState(int gameState){this.gameState = gameState;}
	public void setPlayTime(double playTime){this.playTime = playTime;}
	public KeyHandler getKeyH(){return keyH;}
	public int getHelpState(){return helpState;}
	public int getStoryState(){return storyState;}
	public Sound getSound(){return sound;}
	public int getFinalLevel(){return finalLevel;}
	
	public void reset()
	{
		if(gameThread != null)
		{
			gameState = titleState;
			ui.setStoryScreens(0);
			startButton.setVisible(true);
			mapLevel = 1;
			playTime = 0;
			player.reset();
			setUpGame();
		}
		
	}
	public void playMusic(int i)
	{
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic()
	{
		sound.stop();
	}
	public void playSE(int i)
	{
		if(gameThread != null)
		{
			sound.setFile(i);
			sound.play();
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String code = e.getActionCommand();
		
		switch(code)
		{
			case "Start": 
				gameState = helpState; 
				startButton.setVisible(false);
				easyButton.setVisible(false);
				hardButton.setVisible(false);
				break;
			case "Easy": 
				gameState = storyState; 
				ui.setStoryScreens(1);
				easyButton.setVisible(false);
				hardButton.setVisible(false);
				break;
			case "Hard": 
				player.setDiff(1); 
				gameState = storyState;
				ui.setStoryScreens(1); 
				easyButton.setVisible(false);
				hardButton.setVisible(false);
				break;
		}
		
	}

	public JButton getStartButton()
	{
		return startButton;
	}
	public JButton getEasyButton()
	{
		return easyButton;
	}
	public JButton getHardButton()
	{
		return hardButton;
	}

	public Entity[] getSkull()
	{
		return skull;
	}

	public void setSkull(Entity[] skull)
	{
		this.skull = skull;
	}

	public int getDialogueState()
	{
		return dialogueState;
	}

	public boolean isTalking()
	{
		return isTalking;
	}

	public void setTalking(boolean isTalking)
	{
		this.isTalking = isTalking;
	}

	

	
	
}

package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Main 
{
	
	private GamePanel gameScreen = new GamePanel();
	public Main()
	{
	    JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon image = new ImageIcon(getClass().getResource("/icon/Icon.png"));
		window.setIconImage(image.getImage());
		
		
		window.add(gameScreen);
		
		window.pack();
		
		gameScreen.setUpGame();
		gameScreen.startGameThread();
		
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setLayout(null);
		window.setVisible(true);
		
		
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
	

	
	
	
	
	
	
	

}

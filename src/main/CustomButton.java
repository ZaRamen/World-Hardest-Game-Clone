package main;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;

import java.io.InputStream;

import javax.swing.JButton;

public class CustomButton extends JButton 
{
	private int x, y, width, height;
	private String text;
	private Font pixelFont;
	private Graphics2D g2;
	public CustomButton(String text, int x, int y, int width, int height) 
	{
		 this.text = text;
	     super.setBounds(x, y, width , height);
	     super.setContentAreaFilled(false); //need this to make sure the color is set to white/not filled
	     this.x = x;
	     this.y = y;
	     this.width = width;
	     this.height = height;
     try
		{
			InputStream is = getClass().getResourceAsStream("/font/pixelFont.ttf");
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
     public void draw(Graphics2D g2) 
     {
    	 this.g2 = g2;
    	 g2.setFont(pixelFont);
         if (getModel().isRollover()) 
         {
             g2.setColor(Color.gray);
         } 
         else 
         {
             g2.setColor(Color.LIGHT_GRAY);
         }
         g2.fillRect(x, y, width, height); //fill the button back
         
         g2.setColor(new Color(0x6e4c4b));
         g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
         int posX = getXCenter(text); 
         int posY = getYCenter(text);
         g2.drawString(text, x + posX, y + posY);
         super.paintComponent(g2);
         
     }
    public int getXCenter(String text)
 	{
 		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
 		int posX = width/2 - length/2;
 		return posX;
 	}
    public int getYCenter(String text)
    {
    	int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
 		int posY = height/2 + length/2;
 		return posY;
    }
    public JButton getButton()
    {
    	 return this;
    }
     
	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	
}


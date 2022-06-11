package entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.GamePanel;

public class Skull extends Entity
{
	
	public Skull(GamePanel gP, int x, int y)
	{
		super(gP, 48, 48);
		setup("skull", "skull");
		setSkullPos(x, y);
		createHitBox(getX(), getY());
	}
	public void setSkullPos(int x, int y)
	{
		setX(x);
		setY(y);
	}
	public void speak()
	{
		getgP().getUserInterface().setCurrentDialogue(getDialogue()[getDialogueIndex()]);
		setDialogueIndex(getDialogueIndex() + 1);
		if(getDialogue()[getDialogueIndex()] == null)
		{
			getgP().getKeyH().setEnteredPressed(false);
			setDialogueIndex(0);
			getgP().setTalking(false);
			
		}
		
	}
	public String toString()
	{
		return "I'm a skull";
	}
	public boolean equals(Object other)
	{
		return (Skull)other == this;	
	}
	
	
}

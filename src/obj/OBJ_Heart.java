package obj;

import javax.imageio.ImageIO;

public class OBJ_Heart extends SuperObject
{
	public OBJ_Heart(int x, int y)
	{
		super(x, y);
		setName("Heart");
		setup("heart_full");
	}
	public String toString()
	{
		return "I'm a Heart";
	}
	public boolean equals(Object other)
	{
		return (OBJ_Heart)other == this;	
	}
}


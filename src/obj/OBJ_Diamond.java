package obj;

import javax.imageio.ImageIO;

public class OBJ_Diamond extends SuperObject
{

	public OBJ_Diamond(int x, int y)
	{
		super(x, y);
		setName("Diamond");
		setup("Diamond");
	}
	public String toString()
	{
		return "I'm a Diamond";
	}
	public boolean equals(Object other)
	{
		return (OBJ_Diamond)other == this;	
	}
}

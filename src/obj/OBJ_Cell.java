package obj;

import entity.Entity;

public class OBJ_Cell extends SuperObject
{

	public OBJ_Cell(int x, int y)
	{
		super(x, y);
		setName("Cell");
		setup("Cell");
	}
	public String toString()
	{
		return "I'm a Cell";
	}
	public boolean equals(Object other)
	{
		return (OBJ_Cell)other == this;	
	}
}

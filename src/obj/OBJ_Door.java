package obj;

public class OBJ_Door extends SuperObject
{

	public OBJ_Door(int x, int y)
	{
		super(x, y);
		setName("Door");
		setup("Door");
	}
	public String toString()
	{
		return "I'm a Door";
	}
	public boolean equals(Object other)
	{
		return (OBJ_Door)other == this;	
	}
}

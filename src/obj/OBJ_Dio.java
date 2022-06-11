package obj;

public class OBJ_Dio extends SuperObject
{
	public OBJ_Dio(int x, int y)
	{
		super(x, y);
		setName("Dio");
		setup("hourglass");
	}
	public OBJ_Dio()
	{
		
	}
	public String toString()
	{
		return "I'm DIO!";
	}
	public boolean equals(Object other)
	{
		return (OBJ_Dio)other == this;	
	}
}

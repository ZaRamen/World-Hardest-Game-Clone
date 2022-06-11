package obj;

public class OBJ_Key extends SuperObject
{

	public OBJ_Key(int x, int y)
	{
		super(x, y);
		setName("Key");
		setup("key");
	}
	public String toString()
	{
		return "I'm a Key";
	}
	public boolean equals(Object other)
	{
		return (OBJ_Key)other == this;	
	}
	
}

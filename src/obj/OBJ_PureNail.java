package obj;

//subclass extends Superclass
public class OBJ_PureNail extends SuperObject
{

	public OBJ_PureNail(int x, int y)
	{
		//calls super constructor and passes actual parameters
		super(x, y);
		setName("Nail");
		setup("nail");
	}
	public String toString()
	{
		return "I'm a nail";
	}
	public boolean equals(Object other)
	{
		return (OBJ_PureNail)other == this;	
	}

}

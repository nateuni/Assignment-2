package ass02;

public class Factory
{

	/** Make a sliding block puzzle of size n x n. */
	public static SlidingBlock make (int n)
	{
		return new Board(n);
	}	
	
}

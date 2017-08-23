import org.apache.commons.math3.complex.*;
public class SWAP extends Gate 
{
	static Complex[][] t = {{new Complex(1), new Complex(0), new Complex(0), new Complex(0)},
							{new Complex(0), new Complex(0), new Complex(1), new Complex(0)},
							{new Complex(0), new Complex(1), new Complex(0), new Complex(0)},
							{new Complex(0), new Complex(0), new Complex(0), new Complex(1)}};
	static int[] u={0,1};
	SWAP()
	{
		super(2,t,u,"SWAP");
	}
	SWAP(int[] i)
	{
		super(2,t,i,"SWAP");
	}
}

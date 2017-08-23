import org.apache.commons.math3.complex.*;
public class X extends Gate //NOT gate
{
	static Complex[][] t={{new Complex(0),new Complex(1)},
						  {new Complex(1),new Complex(0)}};
	static int[] u={0};
	X()
	{
		super(1,t,u,"X");
	}
	X(int[] i)
	{
		super(1,t,i,"X");
	}
}

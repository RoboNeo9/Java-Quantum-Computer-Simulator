import org.apache.commons.math3.complex.Complex;
public class H extends Gate //Hadamard gate
{
	static Complex[][] t={{new Complex(1/Math.sqrt(2)),new Complex(1/Math.sqrt(2))},
						  {new Complex(1/Math.sqrt(2)),new Complex(-1/Math.sqrt(2))}};
	static int[] u={0};
	H()
	{
		super(1,t,u,"H");
	}
	H(int[] i)
	{
		super(1,t,i,"H");
	}
}

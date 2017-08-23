import org.apache.commons.math3.complex.Complex;
public class Y extends Gate //Pauli-Y gate
{
	static Complex[][] t={{new Complex(0),new Complex(0,-1)},
						  {new Complex(0,1),new Complex(0)}};
	static int[] u={0};
	Y()
	{
		super(1,t,u,"Y");
	}
	Y(int[] i)
	{
		super(1,t,i,"Y");
	}
}

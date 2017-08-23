import org.apache.commons.math3.complex.Complex;
public class Z extends Gate //Pauli-Z gate
{
	static Complex[][] t={{new Complex(1),new Complex(0)},
						  {new Complex(0),new Complex(-1)}};
	static int[] u={0};
	Z()
	{
		super(1,t,u,"Z");
	}
	Z(int[] i)
	{
		super(1,t,i,"Z");
	}
}

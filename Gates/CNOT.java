import org.apache.commons.math3.complex.Complex;
public class CNOT extends Gate //switch NOT gate
{
	static Complex[][] t = {{new Complex(1), new Complex(0), new Complex(0), new Complex(0)},
							{new Complex(0), new Complex(1), new Complex(0), new Complex(0)},
							{new Complex(0), new Complex(0), new Complex(0), new Complex(1)},
							{new Complex(0), new Complex(0), new Complex(1), new Complex(0)}};
	static int[] u={0,1};
	CNOT()
	{
		super(2,t,u,"CNOT");
	}
	CNOT(int[] i)
	{
		super(2,t,i,"CNOT");
	}
}

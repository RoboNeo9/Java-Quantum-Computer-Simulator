import org.apache.commons.math3.complex.*;
//I'm actually not too sure how this works
public class Measure extends Gate
{
	int qubit;
	Measure(int q)
	{
		super(1, new Complex[2][2], new int[0], "Measure");
		qubit = q;
	}
	
	Measure()
	{
		super(1, new Complex[2][2], new int[0], "Measure");
		qubit = 0;
	}
}

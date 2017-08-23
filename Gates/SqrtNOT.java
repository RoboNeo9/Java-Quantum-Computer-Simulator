import org.apache.commons.math3.complex.*;
public class SqrtNOT extends Gate 
{
	static Complex[][] t = {{(Complex.ONE.add(Complex.I)).divide(2),(Complex.ONE.subtract(Complex.I)).divide(2)},
						  {(Complex.ONE.subtract(Complex.I)).divide(2),(Complex.ONE.add(Complex.I)).divide(2)}};
	static int[] u = {0};
	
	SqrtNOT()
	{
		super(1,t,u,"SqrtNOT");
	}
	SqrtNOT(int[] i)
	{
		super(1,t,i,"SqrtNOT");
	}
}

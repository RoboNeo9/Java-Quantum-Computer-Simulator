import org.apache.commons.math3.complex.*;
public class Phase extends Gate 
{
	static Complex[][] t = {{Complex.ONE,Complex.ZERO},{Complex.ZERO,null}};
	static int[] u = {0};
	
	Phase(double phi)
	{
		super(1,t,u,"Phase");
		super.matrix[1][1] = (Complex.I.multiply(phi)).exp();
	}
	Phase(double phi, int[] i)
	{
		super(1,t,u,"Phase");
		super.matrix[1][1] = (Complex.I.multiply(phi)).exp();
	}
}

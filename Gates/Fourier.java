import org.apache.commons.math3.complex.*;
public class Fourier extends Gate 
{
	Complex[][] t;
	Fourier(int size, int[] phase)
	{
		super(size, new Complex[size][size],phase,"Fourier");
		Complex root = (new Complex(0,2*Math.PI/size)).exp(); //nth root of unity
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				super.matrix[i][j] = root.pow((i*j)%size).multiply(Math.pow(size, -0.5));
			}
		}
	}
	Fourier(int size)
	{
		this(size,new int[size]);
	}
}

import org.apache.commons.math3.complex.*;
public abstract class Gate
{
	public Complex[][] matrix;
	int qubits;
	int[] rows;
	int size;
	String type;
	Gate(int size, Complex[][] rep, int[] r, String t)
	{
		qubits=size;
		matrix = new Complex[rep.length][rep[0].length];
		set(matrix,rep);
		rows=r;
		type = t;
		this.size=(int)(Math.pow(2, qubits));
	}
	Gate(){}
	
	private void set(Complex[][] a, Complex[][] b) //sets a to b (retangular matrix)
	{
		int i = 0;
		int j = 0;
		for(Complex[] r:b)
		{
			for(Complex c:r)
			{
				a[i][j] = c;
				j++;
			}
			j=0;
			i++;
		}
	}
}
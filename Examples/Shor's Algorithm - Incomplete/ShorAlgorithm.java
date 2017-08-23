import java.util.*;
public class ShorAlgorithm 
{
	public static int n = 15;
	private static int c;
	private static int r; //for determining period
	public static void main(String args[])
	{
		int bits1; //register 1
		int bits2; //register 2
		int factor = 1;
		int q = (int)(Math.ceil(log2(n*n)));
		while(factor==1||factor==n)
		{
			int x = (int)(Math.random()*(n-3)+3);
			int gcd = gcd(n,x);
			if(n%x==0||gcd!=1)
			{
				System.out.println("Premature Success");
				if(gcd==1)
					factor = x;
				else
					factor = gcd;
			}
			else
			{
				bits1 = qSystem.bitsToRepresent(q-1);
				bits2 = qSystem.bitsToRepresent(n-1);
				Circuit circuit = new Circuit(bits1+bits2);
				int m;
				int period;
				circuit.setStart();

				for(int i=0;i<bits1;i++) //sets register 1 to superposition of all numbers [0,q-1]
				{
					circuit.addGate(new H(new int[] {i}));
				}
				circuit.stepRest();
				System.out.println("[In superposition]");
				// x^register1 mod n  [NEEDS IMPLEMENTATION]

				for(int i=bits1;i<bits1+bits2;i++)
				{
					circuit.addGate(new Measure(i));
				}
				circuit.stepRest();
				System.out.println("[After measurement]");
				int[] range = new int[bits1];
				for(int i=0;i<bits1;i++)
				{
					range[i] = i;
				}
				circuit.addGate(new Fourier(bits1,range));
				circuit.measureAll();
				m = circuit.finalState;
				m = m>>bits2;

				period = continuedFractions(m,q);
				int mod = modExp(x,period/2,n);
				factor = gcd(mod-1,n);
			}
		}

		System.out.println(n+" -> "+factor+" * "+n/factor);
	}

	private static double logB(double d, double b)
	{
		return Math.log(d)/Math.log(b);
	}
	private static double log2(double d)
	{
		return logB(d,2);
	}
	private static int gcd(int a, int b) 
	{
		if (b==0) return a;
		return gcd(b,a%b);
	}
	private static ArrayList<Integer> extendedGCD(int a, int b)
	{
		ArrayList<Integer> fractions = new ArrayList<Integer>();
		while(b!=0)
		{
			fractions.add(a/b);
			int tA = a % b;
			a = b;
			b = tA;
		}
		return fractions;
	}
	private static int continuedFractions(int measured, int q)
	{
		ArrayList<Integer> fractions = extendedGCD(measured,q);
		int depth = 5; //depth for how far to search in list
		r = 0;
		for(int i=depth;i<fractions.size()+1;i++)
		{
			int tR = partial(fractions,i);
			if(tR==r||tR>n)
			{
				return r;
			}
			r = tR;
		}
		return r;
	}
	private static int partial(ArrayList<Integer> fractions, int depth)
	{
		c = 0;
		r = 1;
		for(int i=depth-1;i>=0;i--)
		{
			int tR = fractions.get(i)*r+c;
			c = r;
			r = tR;
		}
		return c;
	}
	private static int modExp(int a, int exp, int mod)
	{
		int x = 1;
		while(exp>0)
		{
			if((exp&1) == 1)
			{
				x = x*a%mod;
			}
			a = a*a%mod;
			exp = exp>>1;
		}
		return x;
	}
}
import org.apache.commons.math3.complex.Complex;
import java.util.*;
//The master gate represents a gate over an entire system
public class MasterGate extends Gate
{
	Complex[][] gate;
	MasterGate(int size) //Creates identity gate
	{
		gate=new Complex[size][size];
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(i==j)
				{
					gate[i][j]=new Complex(1.0);
				}
				else
				{
					gate[i][j]=Complex.ZERO;
				}
			}
		}
	}
	static String bin(Integer a, int len) //turns a number into binary representation of a certain length
	{
		String temp = Integer.toBinaryString(a);
		int temp2 = len-temp.length();
		while(temp2>0)
		{
			temp = "0"+temp;
			temp2 = len-temp.length();
		}
		return temp;
	}
	
	MasterGate(Gate g, int[] phase, int s)
	{
		gate = new Complex[(int)Math.pow(2, s)][(int)Math.pow(2, s)];
		for(int r=0;r<gate.length;r++)
		{
			for(int c=0;c<gate.length;c++)
			{
				boolean place = false;
				String temp="";
				String from=num(r,s);
				String from2 = from;
				String to=num(c,s);
				String to2 = to;
				for(int i:phase)
				{
					temp+=from.substring(i,i+1);
				}
				from=temp;
				temp="";
				for(int i:phase)
				{
					temp+=to.substring(i,i+1);
				}
				to=temp;
				to = new StringBuilder(to).reverse().toString();
				from = new StringBuilder(from).reverse().toString();
				int c1=Integer.parseInt(to,2);
				int r1=Integer.parseInt(from,2);
				
				sortGtoL(phase);
				
				for(int i:phase)
				{
					from2 = remove(from2,i);
					to2 = remove(to2,i);
				}
				
				if(from2.equals(to2))
				{
					place = true;
				}
				if(place)
				{
					gate[r][c]=g.matrix[r1][c1];//.divide(Math.sqrt(Math.pow(2,s-1)));
				}
				else
				{
					gate[r][c] = Complex.ZERO;
				}
				
				//print out for debug
				/*String tempy = "+" + gate[r][c].getImaginary()+"i ";
				if(gate[r][c].getImaginary()==0)
					tempy=" ";
				String tempy2 = gate[r][c].getReal()+"";
				if(gate[r][c].getReal()==(int)gate[r][c].getReal())
					tempy2 = (int)gate[r][c].getReal()+"";
				System.out.print(tempy2+tempy);*/
			}
		}
	}

	static boolean contains(int[] i, int j)
	{

		for(int k: i)
		{
			System.out.println(k+"="+j+"?");
			if(k==j)
			{

				return true;
			}
		}
		return false;
	}
	public String num(int num, int len)
	{
		String temp=Integer.toBinaryString(num);
		while(temp.length()<len)
		{
			temp="0"+temp;
		}
		return temp;
	}
	public static String remove(String s, int i)
	{
		if(s.length()==1)
			return "";
		if(i==0)
			return s.substring(1);
		if(i==s.length()-1)
			return s.substring(0, s.length()-1);
		return s.substring(0,i)+s.substring(i+1);
	}
	public static void sortGtoL(int[] nums)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i: nums)
		{
			temp.add(i);
		}
		Collections.sort(temp);
		Collections.reverse(temp);
		for(int i=0;i<temp.size();i++)
		{
			nums[i] = temp.get(i);
		}
	}
}
import org.apache.commons.math3.complex.*;
import java.util.*;
//this is where the steps take place and things like the master gate are created.
public class Circuit
{
	qSystem start;
	ArrayList<Gate> gates;
	qSystem current;
	MasterGate currentMaster;
	int qubits;
	int step;
	ArrayList<Complex[][]> steps;
	public int finalState; //this is the integer representation of the final state of the system

	private boolean display = true;
	private boolean displayFinal = true;

	Circuit(int size)
	{
		steps = new ArrayList<Complex[][]>();
		qubits=size;
		step = 0;
		start = new qSystem(qubits);
		current = new qSystem(qubits);
		int count = 0;
		for(Complex[] c: start.system)
		{
			current.system[count][0] = c[0];
			count++;
		}
		gates = new ArrayList<Gate>();
		currentMaster = new MasterGate(size);
	}

	Circuit(Complex[][] starter, int size)
	{
		steps = new ArrayList<Complex[][]>();
		start = new qSystem(size);
		int count = 0;
		for(Complex[] c: starter)
		{
			start.system[count][0] = c[0];
			count++;
		}
		count = 0;
		for(Complex[] c: start.system)
		{
			current.system[count][0] = c[0];
			count++;
		}
		qubits = size;
		gates = new ArrayList<Gate>();
		currentMaster = new MasterGate(qubits);
		step = 0;
	}

	Circuit(Complex[][] starter, ArrayList<Gate> gatesList, int size)
	{
		this(starter, size);
		setGates(gatesList);
	}

	Complex[][] multiply(Complex[][] one, Complex[][] two) //multiplies vectors and matrices
	{
		Complex[][] ret = new Complex[two.length][two[0].length];
		if(ret[0].length==1)
		{
			for(int i=0; i<ret.length; i++)
			{
				Complex sum = new Complex(0);
				for(int j=0; j<ret.length; j++)
				{
					sum = sum.add((one[i][j].multiply(two[j][0])));
				}
				ret[i][0] = sum;
			}
		}
		else
		{
			for(int i=0;i<ret.length;i++)
			{
				for(int j=0;j<ret.length;j++)
				{
					Complex sum = new Complex(0);
					for(int k=0;k<ret.length;k++)
					{
						sum.add((one[i][k].multiply(two[k][j])));
					}
					ret[i][j] = sum;
				}
			}
		}
		return ret;
	}

	void setStart() //to set all qubits to 0
	{
		for(int r=0;r<start.system.length;r++)
		{
			for(int c=0;c<1;c++)
			{
				if(r==0&&c==0)
				{
					start.system[r][c] = Complex.ONE;
					current.system[r][c] = Complex.ONE;
				}
				else
				{
					start.system[r][c] = Complex.ZERO;
					current.system[r][c] = Complex.ZERO;
				}
			}
		}

		if(display)
		{
			for(int i=0;i<start.system.length;i++)
			{
				System.out.println("|"+MasterGate.bin(i, start.bits)+">: "+qSystem.display((start.system[i][0])));
			}
		}
	}

	void measure(int i)
	{
		this.addGate(new Measure(i));
	}

	void measureAll()
	{
		for(int i=0;i<qubits;i++)
		{
			this.measure(i);
		}
		this.restStep();
		for(Complex[] i:current.system)
		{
			if(!i[0].equals(Complex.ZERO))
			{
				i[0] = Complex.ONE;
			}
		}

		if(displayFinal)
		{
			System.out.println(" |\n v");
		}
		for(int i=0;i<current.system.length;i++)
		{
			if(current.system[i][0].equals(Complex.ONE))
			{
				finalState = i;
				if(displayFinal)
				{
					System.out.println("Final State: |"+MasterGate.bin(i, start.bits)+">");
				}
			}
		}
	}

	void setStart(Complex[][] starter)
	{
		if(starter==null) //same as no parameter
		{
			for(int r=0;r<start.system.length;r++)
			{
				for(int c=0;c<1;c++)
				{
					if(r==0&&c==0)
					{
						start.system[r][c] = Complex.ONE;
						current.system[r][c] = Complex.ONE;
					}
					else
					{
						start.system[r][c] = Complex.ZERO;
						current.system[r][c] = Complex.ZERO;
					}
				}
			}
		}
		else
		{
			int count = 0;
			for(Complex[] c: starter)
			{
				start.system[count][0] = c[0];
				count++;
			}
			count=0;
			for(Complex[] c: start.system)
			{
				current.system[count][0] = c[0];
				count++;
			}
		}
		if(display)
		{
			for(int i=0;i<start.system.length;i++)
			{
				System.out.println("|"+MasterGate.bin(i, start.bits)+">: "+qSystem.display((start.system[i][0])));
			}
		}
	}

	public void turnOffDisplay()
	{
		display = false;
	}
	public void turnOnDisplay()
	{
		display = true;
	}
	public void turnOffDisplayFinal()
	{
		displayFinal = false;
	}
	public void turnOnDisplayFinal()
	{
		displayFinal = true;
	}
	void addGate(Gate g)
	{
		gates.add(g);
	}
	void setGates(ArrayList<Gate> g)
	{
		gates = g;
	}

	void step()
	{
		for(int i=0;i<current.system.length;i++)
		{
			if(start.system[i][0]==null)
			{
				start.system[i][0]=Complex.ZERO;
			}
			if(current.system[i][0]==null)
			{
				current.system[i][0]=Complex.ZERO;
			}
		}
		for(int i=0;i<gates.get(step).size;i++)
		{
			for(int j=0;j<gates.get(step).size;j++)
			{
				if(gates.get(step).matrix[i][j]==null)
				{
					gates.get(step).matrix[i][j]=Complex.ZERO;
				}
			}
		}
		if(gates.get(step).type.equals("Measure")) //measures individual qubits
		{
			currentMaster = new MasterGate(current.size);
			int which = ((Measure)gates.get(step)).qubit;
			Complex out;
			if(Math.random()<current.probQOne(which))
			{
				out = Complex.ONE;
			}
			else
			{
				out = Complex.ZERO;
			}
			char bit;
			//Character op;
			if(Complex.equals(out, Complex.ZERO))
			{
				bit = '0';
				//op = '1';
			}
			else if(Complex.equals(out, Complex.ONE))
			{
				bit = '1';
				//op = '0';
			}
			else
			{
				bit = 'u';
			}
			Complex[][] change = new Complex[current.size][1];
			double div = 0;
			for(int i=0;i<current.size;i++)
			{
				String temp = MasterGate.bin(i, current.bits);
				if(temp.charAt(current.bits-1-which)!=bit)
				{
					change[i][0] = Complex.ZERO;
				}
				else if(temp.charAt(current.bits-1-which)==bit)
				{
					div+=Math.pow(current.system[i][0].abs(),2);
				}

			}
			for(int i=0;i<current.size;i++)
			{
				String temp = MasterGate.bin(i, current.bits);
				if(temp.charAt(current.bits-1-which)!=bit)
				{
					change[i][0] = Complex.ZERO;
				}
				else if(temp.charAt(current.bits-1-which)==bit)
				{
					//String opposite;
					/*try
					{
						opposite = temp.substring(0, current.bits-1-which)+op.toString()+temp.substring(current.bits-which);}
					catch(Exception e)
					{
						opposite = temp.substring(0, current.bits-1-which)+op.toString()+temp.substring(current.bits-which);
					}
					int opInt = Integer.parseInt(opposite, 2);*/
					Complex a = current.system[i][0];
					change[i][0] = a.divide(Math.sqrt(div));
					//change[i][0] = a.divide(Math.hypot(a.abs(), b.abs()));

				}
			}

			for(int i = 0; i<change.length;i++)
			{
				current.system[i][0] = change[i][0];
			}
		}
		else
		{
			currentMaster = new MasterGate(gates.get(step),gates.get(step).rows,qubits);
			Complex[][] change = multiply(currentMaster.gate,current.system);
			for(int i = 0; i<change.length;i++)
			{
				current.system[i][0] = change[i][0];
			}
		}
		step++;
		if(display)
		{
			System.out.println(" |\n v");
			for(int i=0;i<current.system.length;i++)
			{
				System.out.println("|"+MasterGate.bin(i, start.bits)+">: "+qSystem.display((current.system[i][0])));
			}
		}
	}

	void restStep() //runs rest of system
	{
		for(int i=step;i<gates.size();i++)
		{
			step();
		}
	}
	void stepRest() //easier to remember but both work
	{
		restStep();
	}

	void resetToBeginning()
	{
		step = 0;
		setStart(start.system); //sets current to start system
	}
}
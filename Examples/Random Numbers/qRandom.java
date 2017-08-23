public class qRandom //generate a random number between 0 and 1
{
	Circuit circuit;
	int numQubits;
	private int measured;
	
	private final int NORMAL_NUMBER_QUBITS = 5;
	
	qRandom()
	{
		numQubits = NORMAL_NUMBER_QUBITS;
		setCircuit(numQubits);
	}
	
	qRandom(int qubits) //if 1, number will not be random at all
	{
		numQubits = qubits;
		setCircuit(numQubits);
	}
	
	public void setCircuit(int qubits)
	{
		circuit = new Circuit(qubits);
		circuit.turnOffDisplay();
		circuit.turnOffDisplayFinal();
		circuit.setStart();
		
		for(int i=0;i<qubits;i++) //put bits in superposition
		{
			circuit.addGate(new H(new int[] {i}));
		}
		circuit.stepRest();
		circuit.measureAll();
		measured = circuit.finalState;
	}
	
	public int getRawMeasurement()
	{
		return measured;
	}
	public double getRandom() //uses addition method to create truly random double from pseudo random number
	{
		double pseudoRandom = Math.random();
		double measuredPercent = measured/(Math.pow(2, numQubits)-1);
		
		double ret = pseudoRandom+measuredPercent;
		
		if(ret>=1)
		{
			ret-=1;
		}
		if(ret<0)
		{
			ret+=1;
		}
		setCircuit(numQubits);
		System.out.println("PseudoRandom: "+pseudoRandom); //to measure difference between random and pseudorandom
		return ret;
	}
	public double getRandom(boolean display)
	{
		double pseudoRandom = Math.random();
		double measuredPercent = measured/(Math.pow(2, numQubits)-1);
		
		double ret = pseudoRandom+measuredPercent;
		
		if(ret>=1)
		{
			ret-=1;
		}
		if(ret<0)
		{
			ret+=1;
		}
		setCircuit(numQubits);
		if(display)
		{
			System.out.println(pseudoRandom); //to measure difference between random and pseudorandom
		}
		return ret;
	}
}
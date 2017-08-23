public class SuperdenseCoding 
{
	static int input = 34; //number 0-3
	public static void main(String[] args) 
	{
		int bits; //so number can be represented in base 2. Is always a multiple of two because of fundamental workings of process
		int in = input;
		if(in<4)
			bits = 2;
		else
			bits = 2*(int)Math.ceil(Math.log(in+1)/Math.log(4));

		Circuit circuit = new Circuit(bits);
		circuit.turnOffDisplay();
		circuit.setStart();

		System.out.println("Start State: |"+MasterGate.bin(0, circuit.start.bits)+">\n |\n v");
		System.out.println("Progress -- "+"0/"+(bits/2));
		for(int i=0;i<bits;i+=2) //puts all bits into equal superposition
		{
			circuit.addGate(new H(new int[] {i}));
			circuit.addGate(new CNOT(new int[] {i,i+1}));
		}

		circuit.restStep();
		//break here to represent possible time break in actual implementation

		for(int i=bits-2;i>=0;i-=2)
		{
			switch(in%4)
			{
			case 0:
				break;
			case 1:
				circuit.addGate(new X(new int[] {i}));
				break;
			case 2:
				circuit.addGate(new Z(new int[] {i}));
				break;
			case 3:
				circuit.addGate(new Z(new int[] {i}));
				circuit.addGate(new X(new int[] {i}));
				break;
			}

			circuit.addGate(new CNOT(new int[] {i,i+1})); //takes bits out of superposition
			circuit.addGate(new H(new int[] {i}));

			in = in/4;
			circuit.restStep();
			System.out.println("Progress -- "+(bits/2-i/2)+"/"+(bits/2));
		}



		circuit.measureAll();
		System.out.println("Encoded Number: "+input);
	}
}

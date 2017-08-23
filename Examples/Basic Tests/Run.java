import org.apache.commons.math3.complex.Complex;
import java.util.*;
public class Run 
{
	public static void main(String args[])
	{
		Circuit circuit = new Circuit(2);
		Complex[][] start = {{Complex.ONE},{Complex.ZERO},{Complex.ZERO},{Complex.ZERO}};
		ArrayList<Gate> gates = new ArrayList<Gate>();
		gates.add(new X()); //defaults to bit 0
		gates.add(new X(new int[] {1})); //set to bit 1
		//gates.add(new Measure(0));
		circuit.setStart(start);
		circuit.setGates(gates);
		circuit.restStep();
		circuit.measureAll(); //sorta like an end state
	}
}

import org.apache.commons.math3.complex.Complex;
import java.util.*;
public class BellState 
{
	public static int round = 4;
	public static void main(String args[])
	{
		Circuit circuit = new Circuit(2);
		Complex[][] start = {{Complex.ONE/*.divide(Math.sqrt(2))*/},{Complex.ZERO/*.divide(Math.sqrt(2))*/},{Complex.ZERO/*.divide(Math.sqrt(2))*/},{Complex.ZERO.divide(Math.sqrt(2))}};
		ArrayList<Gate> gates = new ArrayList<Gate>();
		gates.add(new H());
		gates.add(new CNOT());
		//gates.add(new CNOT(new int[] {0,1}));
		gates.add(new Measure(0));
		//gates.add(new Measure(1));
		circuit.setStart(start);
		circuit.setGates(gates);
		circuit.restStep();
	}
}
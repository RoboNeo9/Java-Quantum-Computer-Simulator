public class randomGeneratorTest 
{
	public static void main(String[] args) 
	{
		qRandom random = new qRandom();
		for(int i=0;i<5;i++)
		{
			System.out.print("Pseudorandom: ");
			double randomNum = random.getRandom(true);
			System.out.println("True Random: "+randomNum+"\n");
		}
	}
}
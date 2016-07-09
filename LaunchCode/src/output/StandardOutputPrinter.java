package output;

public class StandardOutputPrinter implements StringOutput{
	
	@Override
	public void outputString(String output) {
		System.out.println(output);
	}

}

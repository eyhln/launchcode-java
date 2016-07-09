package output;

public class OutputContext {
	
	private StringOutput out;
	
	public OutputContext(StringOutput out) {
		this.out = out;
	}
	
	public void outputString(String output) {
		out.outputString(output);
	}

}

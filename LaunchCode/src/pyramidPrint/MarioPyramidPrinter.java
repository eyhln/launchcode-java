package pyramidPrint;

public class MarioPyramidPrinter {
	
	private int heightInSteps;
	private TextPyramidBuilder pyramid;
	private TerminalIntegerInputPrompt heightGetter;
	
	public MarioPyramidPrinter() {
		pyramid = new TextPyramidBuilder();
		heightGetter = new TerminalIntegerInputPrompt();
	}
	
	public static void main (String[] args) {
		MarioPyramidPrinter mpp = new MarioPyramidPrinter();
		mpp.printPyramid();
	}
	
	public void printPyramid() {
	  String prompt = "Enter a number of steps for a printed pyramid: ";
		heightInSteps = heightGetter.getBoundedIntegerInput(0,23,prompt);
		printPyramidToStandardOutput();
	}
	
	private void printPyramidToStandardOutput() {
		String pyramidOutput = pyramid.buildPyramidString(heightInSteps);
		System.out.println(pyramidOutput);
	}
	
}
		
	
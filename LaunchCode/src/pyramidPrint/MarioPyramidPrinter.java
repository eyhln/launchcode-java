package pyramidPrint;


public class MarioPyramidPrinter {
	
	private static int heightInSteps;
	
	public static void main (String[] args) {
		UserInputCollector inputCollector = new UserInputCollector();
		heightInSteps = inputCollector.getUserInput();
		PyramidToStandardOutput ptso = new PyramidToStandardOutput();
		ptso.printPyramidToStandardOutput(heightInSteps);
	}
	
}
		
	
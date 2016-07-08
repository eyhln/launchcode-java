package pyramidPrint;

public class MarioPyramidPrinter {
	
	private int heightInSteps;
	private PyramidOutput pyramidPrinter;
	private PyramidHeightInputGetter heightGetter;
	
	public static void main (String[] args) {
		MarioPyramidPrinter mpp = new MarioPyramidPrinter();
		mpp.printPyramid();
	}
	
	public MarioPyramidPrinter() {
		pyramidPrinter = new PyramidOutput();
		heightGetter = new PyramidHeightInputGetter();
	}
	
	public void printPyramid() {
		heightInSteps = heightGetter.getUserInput();
		pyramidPrinter.printPyramidToStandardOutput(heightInSteps);
	}
	
}
		
	
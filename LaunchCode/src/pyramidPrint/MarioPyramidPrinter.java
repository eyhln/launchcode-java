package pyramidPrint;

public class MarioPyramidPrinter {
	
	private int heightInSteps;
	private PyramidBuilder pyramid;
	private PyramidHeightInputGetter heightGetter;
	
	public MarioPyramidPrinter() {
		pyramid = new PyramidBuilder();
		heightGetter = new PyramidHeightInputGetter();
	}
	
	public static void main (String[] args) {
		MarioPyramidPrinter mpp = new MarioPyramidPrinter();
		mpp.printPyramid();
	}
	
	public void printPyramid() {
		heightInSteps = heightGetter.getUserInput();
		printPyramidToStandardOutput();
	}
	
	private void printPyramidToStandardOutput() {
		String pyramidOutput = pyramid.buildPyramidString(heightInSteps);
		System.out.println(pyramidOutput);
	}
	
}
		
	
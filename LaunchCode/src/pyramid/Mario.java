package pyramid;

import output.OutputContext;
import output.PyramidPrinter;

public class Mario {
	
	private IntegerInputPrompt heightGetter;
	private TextBasedMenu menu;
	private PyramidFactory pyramidFactory;
	private PyramidPrinter out;
	private OutputContext output;

	public Mario() {
		heightGetter = new IntegerInputPrompt();
		menu = new TextBasedMenu();
		pyramidFactory = new PyramidFactory();
		output = new OutputContext();
	}
	
	public static void main (String[] args) {
		Mario mpp = new Mario();
		mpp.printPyramidToUserSpecification();
	}
	
	public void printPyramidToUserSpecification() {
		String outputType = promptUserForOutputType();
		int heightInSteps = promptUserForPyramidHeight();
		Pyramid pyramid = pyramidFactory.getPyramid(heightInSteps);
		output.outputPyramid(outputType, pyramid);
	}
	
	private String promptUserForOutputType() {
		menu.addOption("Print pyramid to standard output", "standard output");
		menu.addOption("Print pyramid to file", "file");
		String outputType = menu.getSelectionFromMenu();
		return outputType;
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = heightGetter.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	public void outputPyramid(Pyramid pyramid) {
		out.outputPyramid(pyramid);
	}
	
}
		
	
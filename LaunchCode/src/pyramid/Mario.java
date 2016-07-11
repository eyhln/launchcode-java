package pyramid;

import output.FilePrinter;
import output.OutputContext;
import output.StandardOutputPrinter;
import output.PyramidPrinter;

public class Mario {
	
	private IntegerInputPrompt heightGetter;
	private TextBasedMenu menu;

	public Mario() {
		heightGetter = new IntegerInputPrompt();
		menu = new TextBasedMenu();
	}
	
	public static void main (String[] args) {
		Mario mpp = new Mario();
		mpp.printPyramidToUserSpecification();
	}
	
	public void printPyramidToUserSpecification() {
		Object outputType = promptUserForOutputType();
		int heightInSteps = promptUserForPyramidHeight();
		Pyramid pyramid = (new Pyramid(heightInSteps));
		OutputContext output = new OutputContext((PyramidPrinter) outputType);
		output.outputPyramid(pyramid);
	}
	
	private Object promptUserForOutputType() {
		menu.addOption("Print pyramid to standard output", new StandardOutputPrinter());
		menu.addOption("Print pyramid to file", new FilePrinter());
		Object outputType = menu.getSelectionFromMenu();
		return outputType;
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = heightGetter.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
}
		
	
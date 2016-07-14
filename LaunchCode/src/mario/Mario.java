package mario;

import output.PyramidPrinter;
import output.PyramidToFilePrinter;
import output.PyramidToStandardOutputPrinter;
import pyramid.Pyramid;
import pyramid.PyramidFactory;

public class Mario {
	
	private IntegerInputPrompt integerInputPrompt;
	private PyramidFactory pyramidFactory;
	private PyramidPrinter printer;

	public Mario() {
		integerInputPrompt = new IntegerInputPrompt();
		pyramidFactory = new PyramidFactory();
		printer = new PyramidToStandardOutputPrinter();
	}
	
	public static void main (String[] args) {
		Mario mpp = new Mario();
		mpp.printPyramidToUserSpecification();
	}
	
	public void printPyramidToUserSpecification() {
		promptUserToSetOutputType();
		int heightInSteps = promptUserForPyramidHeight();
		Pyramid pyramid = pyramidFactory.getPyramid(heightInSteps);
		printer.print(pyramid);
	}
	
	private void promptUserToSetOutputType() {
		printOptionsList();
		integerInputPrompt.setNotAnIntegerMessage("Error: Not a menu item number");
		int optionSelected = 
				integerInputPrompt.getBoundedIntegerInput(1, 2, "Select an option number: ");	
		if (optionSelected == 2)
			printer = new PyramidToFilePrinter();
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = integerInputPrompt.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	private void printOptionsList() {
		System.out.print("\n");
		System.out.println("  (1) Printer pyramid to standard output");
		System.out.println("  (2) Print pyramid to file");
		System.out.print("\n");
	}
	
}
		
	
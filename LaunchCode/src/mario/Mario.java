package mario;

import mario.pyramid.Pyramid;
import mario.pyramid.PyramidFactory;
import mario.pyramid.PyramidPrinter;
import mario.pyramid.PyramidToFilePrinter;
import mario.pyramid.PyramidToStandardOutputPrinter;

public class Mario {
	
	private IntegerInputPrompt integerInputPrompt;
	private PyramidFactory pyramidFactory;

	public Mario() {
		integerInputPrompt = new IntegerInputPrompt();
		pyramidFactory = new PyramidFactory();
	}
	
	public static void main (String[] args) {
		Mario mpp = new Mario();
		mpp.printPyramidToUserSpecification();
	}
	
	public void printPyramidToUserSpecification() {
		int heightInSteps = promptUserForPyramidHeight();
		PyramidPrinter printer = promptUserToSetOutputType();
		Pyramid pyramid = pyramidFactory.getPyramid(heightInSteps, printer);
		pyramid.print();
	}
	
	private PyramidPrinter promptUserToSetOutputType() {
		printOptionsList();
		integerInputPrompt.setNotAnIntegerMessage("Error: Not a menu item number");
		int optionSelected = 
				integerInputPrompt.getBoundedIntegerInput(1, 2, "Select an option number: ");	
		if (optionSelected == 1)
			return new PyramidToStandardOutputPrinter();
		else if (optionSelected == 2)
			return new PyramidToFilePrinter();
		return null;
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "\nEnter a height in steps for the pyramid: ";
		int heightInSteps = integerInputPrompt.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	private void printOptionsList() {
		System.out.print("\n");
		System.out.println("  (1) Print pyramid to standard output");
		System.out.println("  (2) Print pyramid to file");
		System.out.print("\n");
	}
	
}
		
	
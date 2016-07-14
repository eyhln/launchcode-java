package mario;

import mario.pyramid.Pyramid;
import mario.pyramid.PyramidFactory;
import mario.pyramid.PyramidToFilePrinter;

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
		Pyramid pyramid = pyramidFactory.getPyramid(heightInSteps);
		promptUserToSetOutputType(pyramid);
		pyramid.print();
	}
	
	private void promptUserToSetOutputType(Pyramid pyramid) {
		printOptionsList();
		integerInputPrompt.setNotAnIntegerMessage("Error: Not a menu item number");
		int optionSelected = 
				integerInputPrompt.getBoundedIntegerInput(1, 2, "Select an option number: ");	
		if (optionSelected == 2)
			pyramid.setPrinter(new PyramidToFilePrinter());
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
		
	
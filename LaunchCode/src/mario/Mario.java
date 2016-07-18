package mario;

public class Mario {
	
	private IntegerInputPrompt integerInputPrompt;
	private PyramidFactory pyramidFactory;
	private PyramidPrinterFactory printerFactory;

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
		return printerFactory.getPyramidPrinter(optionSelected);
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = integerInputPrompt.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	private void printOptionsList() {
		System.out.print("\n");
		System.out.println(" (1) Print pyramid to standard output");
		System.out.println(" (2) Print pyramid to file");
		System.out.print("\n");
	}
	
}
		
	
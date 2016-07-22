package mario;

import mario.printer.PyramidPrinter;
import mario.printer.PyramidPrinterFactory;
import mario.pyramid.Pyramid;
import mario.pyramid.PyramidFactory;
import mario.userinput.IntegerInputPrompt;
import mario.userinput.TextBasedMenu;

public class Mario {
	
	private IntegerInputPrompt integerInputPrompt;
	private PyramidFactory pyramidFactory;
	private PyramidPrinterFactory printerFactory;
	private TextBasedMenu menu;

	public Mario() {
		integerInputPrompt = new IntegerInputPrompt();
		pyramidFactory = new PyramidFactory();
		printerFactory = new PyramidPrinterFactory();
		menu = new TextBasedMenu();
	}
	
	public static void main (String[] args) {
		Mario mpp = new Mario();
		mpp.printPyramidToUserSpecification();
	}
	
	public void printPyramidToUserSpecification() {
		int heightInSteps = promptUserForPyramidHeight();
		PyramidPrinter printer = promptUserForOutputType();
		Pyramid pyramid = pyramidFactory.getPyramid(heightInSteps);
		printer.print(pyramid);
	}
	
	private PyramidPrinter promptUserForOutputType() {
		menu.addOption("Print pyramid to standard output", 
				PyramidPrinterFactory.getPyramidToStandardOutputPrinter());
		menu.addOption("Print pyramid to file", 
				PyramidPrinterFactory.getPyramidToFilePrinter());
		PyramidPrinter printer = (PyramidPrinter)menu.getSelectionFromMenu();
		return printer;
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = integerInputPrompt.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	
}
		
	
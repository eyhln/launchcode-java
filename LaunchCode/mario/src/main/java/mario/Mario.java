package mario;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import mario.printer.PyramidPrinter;
import mario.printer.PyramidPrinterFactory;
import mario.pyramid.Pyramid;
import mario.pyramid.PyramidFactory;
import mario.userinput.IntegerInputPrompt;

public class Mario {
	
	private IntegerInputPrompt integerInputPrompt;
	private PyramidPrinterFactory pyramidPrinterFactory;
	private PyramidFactory pyramidFactory;

	public Mario(IntegerInputPrompt integerInputPrompt, PyramidFactory pyramidFactory, 
			PyramidPrinterFactory pyramidPrinterFactory) {
		this.integerInputPrompt = integerInputPrompt;
		this.pyramidFactory = pyramidFactory;
		this.pyramidPrinterFactory = pyramidPrinterFactory;
	}
	
	public static void main (String[] args) {
		Mario mario;
		 try (ClassPathXmlApplicationContext context = 
				 new ClassPathXmlApplicationContext("application-context.xml")) {
			  mario = (Mario)context.getBean("mario");
			}
		mario.printPyramidToUserSpecification();
	}
	
	public void printPyramidToUserSpecification() {
		PyramidPrinter printer = promptUserForOutputType();
		int heightInSteps = promptUserForPyramidHeight();
		Pyramid pyramid = pyramidFactory.getPyramid(heightInSteps);
		printer.print(pyramid);
	}
	
	private PyramidPrinter promptUserForOutputType() {
		printOptions();
		PyramidPrinter printer = getUserPrintSelection();
		return printer;
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = integerInputPrompt.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	private void printOptions() {
		System.out.println();
		System.out.println("  (1) Print pyramid to standard output");
		System.out.println("  (2) Print pyramid to file");
		System.out.println();
	}
	
	private PyramidPrinter getUserPrintSelection() {
		int optionSelected = getOptionNumber();
		PyramidPrinter printer = setPrinterType(optionSelected);
		return printer;
	}
	
	private int getOptionNumber() {
		integerInputPrompt.setNotAnIntegerMessage("Error: Not a menu item number");
		return integerInputPrompt.getBoundedIntegerInput(1, 2, "Select an option number: ");
	}
	
	private PyramidPrinter setPrinterType(int optionSelected) {
		PyramidPrinter printer;
		if (optionSelected == 1) 
			printer = pyramidPrinterFactory.getPyramidToStandardOutputPrinter();
		else 
			printer = pyramidPrinterFactory.getPyramidToFilePrinter();
		return printer;
	}
	
}
		
	
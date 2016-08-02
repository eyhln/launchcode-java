package mario;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import mario.printer.PyramidPrinter;
import mario.printer.PyramidPrinterFactory;
import mario.pyramid.Pyramid;
import mario.pyramid.PyramidFactory;
import mario.userinput.IntegerInputPrompt;
import mario.userinput.TextBasedMenu;

public class Mario {
	
	private IntegerInputPrompt integerInputPrompt;
	private TextBasedMenu menu;
	private PyramidPrinterFactory pyramidPrinterFactory;
	private PyramidFactory pyramidFactory;

	public Mario(IntegerInputPrompt integerInputPrompt, TextBasedMenu menu,
			PyramidFactory pyramidFactory, PyramidPrinterFactory pyramidPrinterFactory) {
		this.integerInputPrompt = integerInputPrompt;
		this.menu = menu;
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
		menu.addOption("Print pyramid to standard output", 
				pyramidPrinterFactory.getPyramidToStandardOutputPrinter());
		menu.addOption("Print pyramid to file", 
				pyramidPrinterFactory.getPyramidToFilePrinter());
		PyramidPrinter printer = (PyramidPrinter)menu.getSelectionFromMenu();
		return printer;
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = integerInputPrompt.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	
}
		
	
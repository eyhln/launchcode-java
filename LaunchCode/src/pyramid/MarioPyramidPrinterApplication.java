package pyramid;

import output.FilePrinter;
import output.OutputContext;
import output.StandardOutputPrinter;
import output.StringOutput;

public class MarioPyramidPrinterApplication {
	
	private TextPyramidBuilder pyramidBuilder;
	private IntegerInputPrompt heightGetter;
	private TextBasedMenu menu;

	public MarioPyramidPrinterApplication() {
		pyramidBuilder = new TextPyramidBuilder();
		heightGetter = new IntegerInputPrompt();
		menu = new TextBasedMenu();
	}
	
	public static void main (String[] args) {
		MarioPyramidPrinterApplication mpp = new MarioPyramidPrinterApplication();
		mpp.printPyramidToUserSpecification();
	}
	
	public void printPyramidToUserSpecification() {
		Object outputType = promptUserForOutputType();
		int heightInSteps = promptUserForPyramidHeight();
		String pyramidOutput = pyramidBuilder.buildPyramidString(heightInSteps);
		OutputContext output = new OutputContext((StringOutput) outputType);
		output.outputString(pyramidOutput);
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
		
	
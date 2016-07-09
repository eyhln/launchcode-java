package pyramid;

import output.FilePrinter;
import output.OutputContext;
import output.StandardOutputPrinter;

public class MarioPyramidPrinter {
	
	private TextPyramidBuilder pyramidBuilder;
	private IntegerInputPrompt heightGetter;
	private TextBasedMenu menu;

	public MarioPyramidPrinter() {
		pyramidBuilder = new TextPyramidBuilder();
		heightGetter = new IntegerInputPrompt();
		menu = new TextBasedMenu();
	}
	
	public static void main (String[] args) {
		MarioPyramidPrinter mpp = new MarioPyramidPrinter();
		mpp.printPyramid();
	}
	
	public void printPyramid() {
		int selectedOption = promptUserForOutputType();
		int heightInSteps = promptUserForPyramidHeight();
		if (selectedOption == 0) {
			printPyramidToStandardOutput(heightInSteps);
		}
		else if (selectedOption == 1) {
			printPyramidToFile(heightInSteps);
		}
	}
	
	private int promptUserForOutputType() {
		String option0 = "Print pyramid to standard output";
		String option1 = "Print pyramid to file";
		int selectedOption = menu.getUserSelectionFromMenu(option0, option1);
		return selectedOption;
	}
	
	private int promptUserForPyramidHeight() {
		String prompt = "Enter a height in steps for the pyramid: ";
		int heightInSteps = heightGetter.getBoundedIntegerInput(0,23,prompt);
		return heightInSteps;
	}
	
	private void printPyramidToStandardOutput(int heightInSteps) {
		String pyramidOutput = pyramidBuilder.buildPyramidString(heightInSteps);
		OutputContext output = new OutputContext(new StandardOutputPrinter());
		output.outputString(pyramidOutput);
	}
	
	private void printPyramidToFile(int heightInSteps) {
		String pyramidOutput = pyramidBuilder.buildPyramidString(heightInSteps);
		OutputContext output = new OutputContext(new FilePrinter());
		output.outputString(pyramidOutput);
		
	}
	
}
		
	
package pyramidPrint;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MarioPyramidPrinter {
	
	private TextPyramidBuilder pyramidBuilder;
	private IntegerInputPrompt heightGetter;
	private TextBasedMenu menu;
	private FilePathPrompt pathPrompt;

	public MarioPyramidPrinter() {
		pyramidBuilder = new TextPyramidBuilder();
		heightGetter = new IntegerInputPrompt();
		menu = new TextBasedMenu();
		pathPrompt = new FilePathPrompt();
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
		System.out.println(pyramidOutput);
	}
	
	private void printPyramidToFile(int heightInSteps) {
		String filePath = pathPrompt.promptFilePath();
		String pyramidOutput = pyramidBuilder.buildPyramidString(heightInSteps);
		try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
			out.print(pyramidOutput);
			System.out.println("File written");
		} catch (IOException e) {
			System.out.println("Error: Unable to write file");
		} 
	}
	
}
		
	
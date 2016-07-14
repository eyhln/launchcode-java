package mario.pyramid;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import mario.FilePathPrompt;

public class PyramidToFilePrinter implements PyramidPrinter {
	
	private FilePathPrompt pathPrompt; 
	private String output;
	
	public PyramidToFilePrinter() {
		pathPrompt = new FilePathPrompt();
	}
	
	@Override
	public void print(Pyramid pyramid) {
		this.output = pyramid.toString();
		boolean sucessful = false;
		do {			
			sucessful = writeToFile();
		} while (sucessful == false);
	}
	
	private boolean writeToFile() {
		String filePath = pathPrompt.promptFilePathFromUser();
		return attemptToWriteFile(filePath);
	}
	
	private boolean attemptToWriteFile(String filePath) {
		try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
			out.print(output);
			printSuccessMessage();
			return true;
		} catch (IOException e) {
			printWriteErrorMessage();
		} 
		return false;
	}
	
	private void printSuccessMessage() {
			System.out.println("File written");
	}
	
	private void printWriteErrorMessage() {
		System.out.println("Error: Unable to write file");
	}

}
package output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilePrinter implements StringOutput {
	
	FilePathPrompt pathPrompt; 
	
	public FilePrinter() {
		pathPrompt = new FilePathPrompt();
	}
	
	@Override
	public void outputString(String output) {
		boolean processSuccessful = false;
		do {
		String filePath = pathPrompt.promptFilePath();
			try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
				out.print(output);
				System.out.println("File written");
				processSuccessful = true;
			} catch (IOException e) {
				System.out.println("Error: Unable to write file\n "
						+ "      Check file permissions and target directory");
			} 
		} while (processSuccessful == false);	
	}

}

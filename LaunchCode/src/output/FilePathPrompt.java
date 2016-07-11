package output;

import java.io.File;
import java.util.Scanner;

class FilePathPrompt {
	
	private Scanner scanner;

	FilePathPrompt() {
		scanner = new Scanner(System.in);
	}
	
	String promptFilePathFromUser() {
		String input;
		do {
			promptUserForFilepath();
			input = scanner.nextLine();
		} while (isValid(input) == false);
		return input;
	}
	
	private boolean isValid (String filePath) {
		File file = new File(filePath);
		try {
			if (directoryForFileExists(file)) 
				return true;
		} catch (NullPointerException e) {
			printInvalidPathMessage();
		}
		return false;
	}
	
	private boolean directoryForFileExists(File file) {
		if (!file.isDirectory())
			file = file.getParentFile();
		if (file.exists()) 
			 return true;
		else
			printDoesNotExistMessage();
		return false;
	}
	
	private void promptUserForFilepath() {
			System.out.println("Enter a path destination for the file: ");
	}
	
	private void printDoesNotExistMessage() {
		System.out.println("Error: Directory does not exist");
	}
	
	private void printInvalidPathMessage() {
		System.out.println("Error: Invalid path");
	}

}

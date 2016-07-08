package pyramidPrint;

import java.io.File;
import java.util.Scanner;

public class FilePathPrompt {
	
	private Scanner scanner;
	
	FilePathPrompt() {
		scanner = new Scanner(System.in);
	}
	
	String promptFilePath() {
		String input;
		do {
			System.out.println("Enter a path destination for the file: ");
			input = scanner.nextLine();
		} while (isValid(input) == false);
		return input;
	}
	
	private boolean isValid (String filePath) {
		File file = new File(filePath);
			if (!file.isDirectory())
			   file = file.getParentFile();
			try {
				if (file.exists()) 
				    return true;
			} catch (SecurityException e) {
				System.out.println("Error: No permissions for this path");
			}	catch (Exception e) {
				System.out.println("Error: Invalid path");
				System.out.println("Examples of valid paths:   Unix-like: /home/user/docs/Pyramid.txt\n" +
													 "                           Windows: C:\\user\\docs\\Pyramid.txt");
			}
			return false;
	}

}

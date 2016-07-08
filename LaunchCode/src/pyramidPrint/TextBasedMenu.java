package pyramidPrint;

import java.util.Scanner;

class TextBasedMenu {
	
	int getUserSelectionFromMenu(String...options) {
		System.out.println();
		for (int i = 0; i < options.length; i++)
			System.out.printf("   (%d) %s\n", i+1, options[i]);
		String prompt = "\nSelect an option number: ";
		Scanner scanner = new Scanner(System.in);
		int optionSelected = 0;
		//TODO input is only valid if it is an option on the menu
		// will still need to catch errors for input that is not int
		while (true) {
			try {
					System.out.println(prompt);
					String input = scanner.nextLine();
					optionSelected = Integer.parseInt(input);
					break;
			} catch (NumberFormatException e) {
					System.out.println("Error: Input must be an integer (whole number).");
			}
		}
		return optionSelected-1;
	}
	
	void printMenuHeader(int width, String title) {
		// TODO width must be a positive number! Cannot be 0 or 1?
		// length of title must not be less than the width
		// String title must not be null
		String ruleComponent = "-";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < width; i++)
			sb.append(ruleComponent);
		String rule = sb.toString();
		sb.setLength(0);
		for (int i = 0; i < (width/2) - (title.length()/2); i++) 
			sb.append(" ");
		sb.append(title);
		for (int i = 0; i < (width/2) - (title.length()/2); i++) 
			sb.append(" ");
		String titleLine = sb.toString();
		System.out.println(rule);
		System.out.println(titleLine);
		System.out.println(rule);
	}
}

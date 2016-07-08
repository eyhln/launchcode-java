package pyramidPrint;

import java.util.Scanner;

public class UserInputCollector {
	
	private int heightInSteps;
	private Scanner scanner;

	public int getUserInput() {
		scanner = new Scanner(System.in);
		do {
			getIntegerInput();
		} while (inputIsInRange() == false);
		scanner.close();
		return heightInSteps;
	}
		
	private void getIntegerInput() {
		while (true) {
			try {
					System.out.println("Enter a number of steps for a printed pyramid:");
					String input = scanner.nextLine();
					heightInSteps = Integer.parseInt(input);
					break;
			} catch (NumberFormatException e) {
					System.out.println("Please enter an integer (whole number) from 0 to 23.");
			}
		}
	}
	
	private boolean inputIsInRange() {
		if (0 <= heightInSteps && heightInSteps <= 23) 
			return true;
		else
			System.out.println("The number of steps must be in the range 0 to 23.");
		return false;
	}
		
}


import java.util.Scanner;

public class MarioPyramidPrinter {
	
	private int heightInSteps;
	private StringBuilder line;
	private Scanner scanner;
	
	public static void main (String[] args) {
		MarioPyramidPrinter mpp = new MarioPyramidPrinter();
		mpp.printPyramidToUserSpecifiedHeight();
	}
		
	public void printPyramidToUserSpecifiedHeight() {
		getUserInput();
		printPyramid();
	}

	private void getUserInput() {
		scanner = new Scanner(System.in);
		do {
			getIntegerInput();
		} while (inputIsInRange() == false);
		scanner.close();
	}
	
	private void printPyramid() {
		line = new StringBuilder();
		for (int rowNumber = 0; rowNumber < heightInSteps; rowNumber++) {
			clearElementsToStartNewLine();
			addSpacesToLine(rowNumber);
			addHashesToLine(rowNumber);
			System.out.println(line);
		}
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
	
	private void clearElementsToStartNewLine() {
		line.setLength(0);
	}
	 
	private void addSpacesToLine(int rowNumber) {
		for (int j = heightInSteps-2-rowNumber; j >= 0; j--)
			line.append(" ");
	}
	
	private void addHashesToLine(int rowNumber) {
		for (int k = 1+rowNumber; k >= 0; k--)
			line.append("#");
	}
		
}

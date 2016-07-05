import java.util.Scanner;

public class MarioPyramidPrinter {
	
	private int heightInSteps;
	private StringBuilder stringBuilder;
	
	public static void main (String[] args) {
		MarioPyramidPrinter mpp = new MarioPyramidPrinter();
		mpp.printPyramidToUserSpecifiedHeight();
	}
		
	public void printPyramidToUserSpecifiedHeight() {
		getUserInput();
		printPyramid();
	}

	private void getUserInput() {
		Scanner scanner = new Scanner(System.in);
		promptUserUntilGetValidInput(scanner);
		scanner.close();
	}
	
	private void promptUserUntilGetValidInput(Scanner scanner) {
		boolean isValidInput;	
		do {
			getIntegerInput(scanner);
			isValidInput = testRangeOfInput();
		} while (isValidInput == false);
	}

	
	private void getIntegerInput(Scanner scan) {
		while (true) {
			try {
					System.out.println("Enter a number of steps for a printed pyramid:");
					String input = scan.nextLine();
					heightInSteps = Integer.parseInt(input);
					break;
			} catch (NumberFormatException e) {
					System.out.println("Please enter an integer (whole number).");
			}
		}
	}
	
	private boolean testRangeOfInput() {
		if (0 <= heightInSteps && heightInSteps <= 23) 
			return true;
		else
			System.out.println("The number of steps must be from 0 to 23.");
			return false;
	}
	
	
	private void printPyramid() {
		stringBuilder = new StringBuilder();
		int lineWidth = heightInSteps;
		for (int i = 0; i < lineWidth; i++) {
			stringBuilder.setLength(0);
			addSpacesToLine(i);
			addBlocksToLine(i);
			System.out.println(stringBuilder);
			}
	}
	
	private void addSpacesToLine(int i) {
		for (int j = heightInSteps-2-i; j >= 0; j--)
			stringBuilder.append(" ");
	}
	
	private void addBlocksToLine(int i) {
		for (int k = 1+i; k >= 0; k--)
			stringBuilder.append("#");
	}
		
}

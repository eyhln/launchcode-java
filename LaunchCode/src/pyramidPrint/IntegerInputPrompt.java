package pyramidPrint;

import java.util.Scanner;

class IntegerInputPrompt {
	
	private Scanner scanner;
	private String prompt;
	private int number;
	
	IntegerInputPrompt() { 
			scanner = new Scanner(System.in);
	}
	
  //TODO improve the specificity of error handling, may need to define a custom error
  // ...naming could be improved (getInput, promptInput)
  
  int getBoundedIntegerInput(int minAcceptedValue, int maxAcceptedValue, String promptForUser) {
  	try {
  		testRangeParameters(minAcceptedValue, maxAcceptedValue);
  		testPromptParameter(promptForUser);
  		getInput(minAcceptedValue, maxAcceptedValue);
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
		return number;
  }

  private void testRangeParameters(int min, int max) throws Exception {
  	if (min > max)
  		throw new Exception();
  }
  
  private void testPromptParameter(String promptForUser) throws Exception {
  	if (promptForUser.length() > 0) 
  			prompt = promptForUser;
  	else
  		throw new Exception();
  }
  
  private void getInput(int min, int max) {
  	do {
			promptInput();
		} while (inputIsInRange(min, max) == false);
  }
		
	private void promptInput() {
		while (true) {
			try {
					System.out.println(prompt);
					String input = scanner.nextLine();
					number = Integer.parseInt(input);
					break;
			} catch (NumberFormatException e) {
					System.out.println("Error: Input must be an integer (whole number).");
			}
		}
	}
	
	private boolean inputIsInRange(int min, int max) {
		if (min <= number && number <= max) 
			return true;
		else
			System.out.printf("Error: Input must be in the range %d to %d.\n",min,max);
		return false;
	}
		
}


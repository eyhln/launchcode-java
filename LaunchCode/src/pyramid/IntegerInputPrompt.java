package pyramid;

import java.util.Scanner;

class IntegerInputPrompt {
	
	private Scanner scanner;
	private int min;
	private int max;
	private String prompt;
	private int userInputInteger;
	
	IntegerInputPrompt() { 
			scanner = new Scanner(System.in);
	}
  
  int getBoundedIntegerInput(int minAcceptedValue, int maxAcceptedValue, String promptForUser) {
  	try {
  		setRangeValues(minAcceptedValue, maxAcceptedValue);
  		setPromptValue(promptForUser);
  		getInput(minAcceptedValue, maxAcceptedValue);
  	} catch (IllegalArgumentException e) {
  		e.printStackTrace();
  	}
		return userInputInteger;
  }

  private void setRangeValues(int minAcceptedValue, int maxAcceptedValue) throws IllegalArgumentException {
  	if (min > max)
  		throw new IllegalArgumentException();
  	else
  		min = minAcceptedValue;
  		max = maxAcceptedValue;
  }
  
  private void setPromptValue(String promptForUser) throws IllegalArgumentException {
  	if (promptForUser.length() > 0) 
  			prompt = promptForUser;
  	else
  		throw new IllegalArgumentException();
  }
  
  private void getInput(int min, int max) {
  	String input;
  	do {
			System.out.println(prompt);
			input = scanner.nextLine();
		} while (isValid(input) == false);
  }
  
  private boolean isValid(String input) {
  	try {
					userInputInteger = Integer.parseInt(input);
					if (inputIsInRange() == true)
						return true;
			} catch (NumberFormatException e) {
					System.out.println("Error: Input must be an integer (whole number)");
			}
  	return false;
  }
		
	private boolean inputIsInRange() {
		if (min <= userInputInteger && userInputInteger <= max) 
			return true;
		else
			System.out.printf("Error: Input must be in the range %d to %d\n",min,max);
		return false;
	}
		
}


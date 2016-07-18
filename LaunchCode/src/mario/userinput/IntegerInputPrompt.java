package mario.userinput;

import java.util.Scanner;

public class IntegerInputPrompt {
	
	private Scanner scanner;
	private String notAnIntegerMessage;
	private int min;
	private int max;
	private String prompt;
	private int userInputInteger;
	
	public IntegerInputPrompt() { 
			scanner = new Scanner(System.in);
			notAnIntegerMessage = "Error: Input must be an integer (whole number)";
	}
  
  public int getBoundedIntegerInput(int minAcceptedValue, int maxAcceptedValue, String promptForUser) {
  	try {
  		setRangeValues(minAcceptedValue, maxAcceptedValue);
  		setPromptValue(promptForUser);
  		getInput(minAcceptedValue, maxAcceptedValue);
  	} catch (IllegalArgumentException e) {
  		e.printStackTrace();
  	}
		return userInputInteger;
  }

  private void setRangeValues(int min, int max) throws IllegalArgumentException {
  	if (min > max)
  		throw new IllegalArgumentException();
  	else
  		this.min = min;
  		this.max = max;
  }
  
  private void setPromptValue(String prompt) throws IllegalArgumentException {
  	if (prompt.length() > 0) 
  			this.prompt = prompt;
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
					System.out.println(notAnIntegerMessage);
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
	
	public void setNotAnIntegerMessage(String message) {
		notAnIntegerMessage = message;
	}
		
}


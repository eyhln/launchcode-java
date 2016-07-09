package pyramid;

class TextBasedMenu {
	
	private IntegerInputPrompt optionPrompter;
	private String prompt;
	
	TextBasedMenu() {
		optionPrompter = new IntegerInputPrompt();
		prompt = "Select an option number: ";
	}
	
	int getUserSelectionFromMenu(String...options) {
		printOptionsList(options);
		int optionSelected = optionPrompter.getBoundedIntegerInput(1, options.length, prompt);
		return optionSelected-1;
	}
	
	private void printOptionsList(String[] options) {
		System.out.println();
		for (int i = 0; i < options.length; i++)
			System.out.printf("   (%d) %s\n", i+1, options[i]);
		System.out.println();
	}
	
}

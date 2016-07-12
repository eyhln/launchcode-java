package pyramid;

import java.util.ArrayList;

class TextBasedMenu {
	
	private IntegerInputPrompt optionPrompter;
	private ArrayList<String> optionTexts;
	private ArrayList<String> actions;
	private String prompt;
	
	TextBasedMenu() {
		optionPrompter = new IntegerInputPrompt();
		optionTexts = new ArrayList<String>();
		actions = new ArrayList<String>();
		prompt = "Select an option number: ";
	}
	
	void addOption(String optionText, String action) {
			optionTexts.add(optionText);
			actions.add(action);
	}
	
	String getSelectionFromMenu() throws NullPointerException {
		if (optionTexts.size() == 0) 
			throw new NullPointerException();
		printOptionsList();
		optionPrompter.setNotAnIntegerMessage("Error: Not a menu item number");
		int optionSelected = 
				optionPrompter.getBoundedIntegerInput(1, optionTexts.size(), prompt);			 
		String actionSelected = actions.get(optionSelected-1);
		return actionSelected;
	}
			
	private void printOptionsList() {
		System.out.print("\n");
		for (int i = 0; i < optionTexts.size(); i++) {
			System.out.printf("   (%d) %s\n", i+1, optionTexts.get(i));
		}
		System.out.print("\n");
	}
	
}

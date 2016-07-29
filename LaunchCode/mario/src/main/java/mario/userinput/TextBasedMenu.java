package mario.userinput;

import java.util.ArrayList;

public class TextBasedMenu {
	
	private IntegerInputPrompt optionPrompter;
	private ArrayList<String> optionTexts;
	private ArrayList<Object> actions;
	private String prompt;
	
	public TextBasedMenu() {
		optionPrompter = new IntegerInputPrompt();
		optionTexts = new ArrayList<String>();
		actions = new ArrayList<Object>();
		prompt = "Select an option number: ";
	}
	
	public void addOption(String optionText, Object action) {
			optionTexts.add(optionText);
			actions.add(action);
	}
	
	public Object getSelectionFromMenu() throws NullPointerException {
		if (optionTexts.size() == 0) 
			throw new NullPointerException();
		printOptionsList();
		optionPrompter.setNotAnIntegerMessage("Error: Not a menu item number");
		int optionSelected = 
				optionPrompter.getBoundedIntegerInput(1, optionTexts.size(), prompt);			 
		Object actionSelected = actions.get(optionSelected-1);
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
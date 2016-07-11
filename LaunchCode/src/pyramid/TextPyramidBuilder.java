package pyramid;

/*
 * Creates text-based half pyramid strings using spaces and hashes. 
 * Example for height of 7:
 * 
 *        ##
		     ###
		    ####
		   #####
		  ######
		 #######
		########
 *
 */

class TextPyramidBuilder {
	
	private StringBuilder pyramid;
	private int heightInSteps;
	
	TextPyramidBuilder() {
			pyramid = new StringBuilder(); 
	}
	
	String buildPyramidString(int heightInSteps) {
		clearCharactersFromBuilder();
		this.heightInSteps = heightInSteps;
		for (int lineNumber = 0; lineNumber < heightInSteps; lineNumber++) {
			createLine(lineNumber);
		}
		return pyramid.toString();
	}
	
	private void clearCharactersFromBuilder() {
		pyramid.setLength(0);
	}
	
	private void createLine(int lineNumber) {
		startNewLine();
		addSpacesToLine(lineNumber);
		addHashesToLine(lineNumber);
	}
	
	private void startNewLine() {
		pyramid.append("\n");
	}
	 
	private void addSpacesToLine(int lineNumber) {
		for (int i = heightInSteps-2-lineNumber; i >= 0; i--)
			pyramid.append(" ");
	}
	
	private void addHashesToLine(int lineNumber) {
		for (int i = 1+lineNumber; i >= 0; i--)
			pyramid.append("#");
	}
}

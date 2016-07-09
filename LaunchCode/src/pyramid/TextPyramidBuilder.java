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
	private int height;
	
	TextPyramidBuilder() {
			pyramid = new StringBuilder(); 
	}
	
	String buildPyramidString(int heightInSteps) {
		clearCharactersFromBuilder();
		height = heightInSteps;
		for (int lineNumber = 0; lineNumber < height; lineNumber++) {
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
		for (int i = height-2-lineNumber; i >= 0; i--)
			pyramid.append(" ");
	}
	
	private void addHashesToLine(int lineNumber) {
		for (int i = 1+lineNumber; i >= 0; i--)
			pyramid.append("#");
	}
}

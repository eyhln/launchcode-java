package pyramidPrint;

class PyramidOutput {
	
	private StringBuilder line;
	private int height;
	
	PyramidOutput() {
			line = new StringBuilder();
	}
	
	void printPyramidToFile(int heightInSteps) {
		height = heightInSteps;
		for (int rowNumber = 0; rowNumber < height; rowNumber++) {
			createLine(rowNumber);
			// TODO add print to file functionality
		}
	}
	
	void printPyramidToStandardOutput(int heightInSteps) {
		height = heightInSteps;
		for (int rowNumber = 0; rowNumber < height; rowNumber++) {
			createLine(rowNumber);
			System.out.println(line);
		}
	}
	
	private void createLine(int rowNumber) {
		startNewLine();
		addSpacesToLine(rowNumber);
		addHashesToLine(rowNumber);
	}
	
	private void startNewLine() {
		line.setLength(0);
	}
	 
	private void addSpacesToLine(int rowNumber) {
		for (int i = height-2-rowNumber; i >= 0; i--)
			line.append(" ");
	}
	
	private void addHashesToLine(int rowNumber) {
		for (int i = 1+rowNumber; i >= 0; i--)
			line.append("#");
	}
}

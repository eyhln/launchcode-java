
public class PyramidToStandardOutput {
	
	private StringBuilder line;
	private int height;

	public void printPyramidToStandardOutput(int heightInSteps) {
		height = heightInSteps;
		line = new StringBuilder();
		for (int rowNumber = 0; rowNumber < height; rowNumber++) {
			startNewLine();
			addSpacesToLine(rowNumber);
			addHashesToLine(rowNumber);
			System.out.println(line);
		}
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

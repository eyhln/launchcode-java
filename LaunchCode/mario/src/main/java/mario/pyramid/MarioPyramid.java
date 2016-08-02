package mario.pyramid;

public class MarioPyramid implements Pyramid {
	
	private StringBuilder builder;
	private int heightInSteps;
	
	MarioPyramid(int heightInSteps) {
			builder = new StringBuilder();
			this.heightInSteps = heightInSteps;
	}
	
	@Override
	public String toString() {
		return buildPyramid(heightInSteps);
	}
	
	String buildPyramid(int heightInSteps) {
		clearCharactersFromBuilder();
		this.heightInSteps = heightInSteps;
		for (int lineNumber = 0; lineNumber < heightInSteps; lineNumber++) {
			createLine(lineNumber);
		}
		return builder.toString();
	}
	
	private void clearCharactersFromBuilder() {
		builder.setLength(0);
	}
	
	private void createLine(int lineNumber) {
		startNewLine();
		addSpacesToLine(lineNumber);
		addHashesToLine(lineNumber);
	}
	
	private void startNewLine() {
		builder.append("\n");
	}
	 
	private void addSpacesToLine(int lineNumber) {
		for (int i = heightInSteps-2-lineNumber; i >= 0; i--)
			builder.append(" ");
	}
	
	private void addHashesToLine(int lineNumber) {
		for (int i = 1+lineNumber; i >= 0; i--)
			builder.append("#");
	}
}

package mario.pyramid;

import mario.printer.PyramidPrinter;

public class PyramidFactory {
	
	public Pyramid getPyramid(int heightInSteps, PyramidPrinter printer){
		return new MarioPyramid(heightInSteps, printer);
	}
}	

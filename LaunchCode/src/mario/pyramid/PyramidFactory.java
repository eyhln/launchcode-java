package mario.pyramid;

import mario.printer.PyramidPrinter;

public class PyramidFactory {
	
	public Pyramid getPyramid(int heightInSteps){
		return new MarioPyramid(heightInSteps);
	}
}	

package output;

import pyramid.Pyramid;

public class PyramidToStandardOutputPrinter implements PyramidPrinter{
	
	@Override
	public void outputPyramid(Pyramid pyramidToOutput) {
		System.out.println(pyramidToOutput.toString());
	}

}

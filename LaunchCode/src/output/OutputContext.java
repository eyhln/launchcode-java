package output;

import pyramid.Pyramid;

public class OutputContext {
	
	private PyramidPrinter out;
	
	public OutputContext(PyramidPrinter out) {
		this.out = out;
	}
	
	public void outputPyramid(Pyramid pyramidToOutput) {
		out.outputPyramid(pyramidToOutput);
	}
	
}

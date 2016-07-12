package output;

import pyramid.Pyramid;

public class OutputContext {
	
	private PyramidPrinter out;
	
	public OutputContext(String outputType) {
		if (outputType.equals("standard output")) 
			out = new PyramidToStandardOutputPrinter();
		else if (outputType.equals("file")) 
			out = new PyramidToFilePrinter();
	}
	
	public void outputPyramid(Pyramid pyramidToOutput) {
		out.outputPyramid(pyramidToOutput);
	}
	
}

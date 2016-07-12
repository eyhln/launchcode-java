package output;

import pyramid.Pyramid;

public class OutputContext {
	
	private PyramidPrinter out;
	
	public void outputPyramid(String outputType, Pyramid pyramid) {
		if (outputType.equals("standard output")) 
			out = new PyramidToStandardOutputPrinter();
		else if (outputType.equals("file"))  
			out = new PyramidToFilePrinter();
		
		out.outputPyramid(pyramid);
	}
	
}

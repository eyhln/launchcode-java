package mario.printer;

public class PyramidPrinterFactory {
	
	public static PyramidPrinter getPyramidToStandardOutputPrinter() {
		return new PyramidToStandardOutputPrinter();
	}
	
	public static PyramidPrinter getPyramidToFilePrinter() {
		return new PyramidToFilePrinter();
	}

}

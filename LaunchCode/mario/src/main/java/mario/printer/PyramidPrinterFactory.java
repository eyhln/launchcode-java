package mario.printer;

public class PyramidPrinterFactory {
	
	public PyramidPrinter getPyramidToStandardOutputPrinter() {
		return new PyramidToStandardOutputPrinter();
	}
	
	public PyramidPrinter getPyramidToFilePrinter() {
		return new PyramidToFilePrinter();
	}

}

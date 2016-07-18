package mario;

public class PyramidPrinterFactory {

	public PyramidPrinter getPyramidPrinter(int option){
		if (!(option == 1 || option == 2)) 
			throw new IllegalArgumentException();
		if (option == 1) 
			return new PyramidToStandardOutputPrinter();
		else if (option == 2)
			return new PyramidToFilePrinter();
		return null;
	}
}

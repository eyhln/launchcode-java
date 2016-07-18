package mario;

public class PyramidToStandardOutputPrinter implements PyramidPrinter{
	
	@Override
	public void print(Pyramid pyramid) {
		System.out.println(pyramid.toString());
	}

}

package mario.pyramid;

public interface Pyramid {

	@Override
	public String toString();
	
	public void setPrinter(PyramidPrinter printer);
	
	public void print();
	
}

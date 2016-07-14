package pyramid;

public class PyramidFactory {
	
	public Pyramid getPyramid(int heightInSteps){
		return new MarioPyramid(heightInSteps);
	}
}	

package mario.pyramid;

public class PyramidFactory {
	
	public Pyramid getPyramid(int heightInSteps){
		return new Pyramid(heightInSteps);
	}
}	

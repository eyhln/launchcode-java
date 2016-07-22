package mario.pyramid;

public class PyramidFactory {
	
	public static Pyramid getPyramid(int heightInSteps){
		return new MarioPyramid(heightInSteps);
	}
}	

package greedy.coins;

public class EuroCoinSpecification extends CoinSpecification {
	
	int[] coinValues = {200, 100, 50, 20, 10, 5, 2, 1};
	String coinNameCodePrefix = "Euro";
	
	public EuroCoinSpecification() {
		coinNameCodes = super.createNameCodes();
	}

}

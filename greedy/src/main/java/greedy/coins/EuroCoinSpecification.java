package greedy.coins;

public class EuroCoinSpecification extends CoinSpecification {
	
	int[] localCoinValues = {200, 100, 50, 20, 10, 5, 2, 1};
	
	public EuroCoinSpecification() {
		coinValues = localCoinValues;
		coinNameCodePrefix = "Euro";
		coinNameCodes = super.createNameCodes();
	}

}

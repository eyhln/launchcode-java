package greedy.coins;

public class USDollarCoinSpecification extends CoinSpecification  {
	
	int[] constructCoinValuesInCents = {100, 25, 10, 5, 1};
	
	public USDollarCoinSpecification() {
		coinValuesInCents = constructCoinValuesInCents;
		coinNameCodePrefix = "USDollar";
		coinNameCodes = super.createNameCodes();
	}
	
}

